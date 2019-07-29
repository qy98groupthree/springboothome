package com.gt.home.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author 姜亚林
 * @date：2019/7/29 9:35
 */
@Component
public class FtpUtil {
    @Autowired
    private FtpConfig ftpConfig;
    //创建FTPClient 作为类变量，上传方法和下载方法都可以使用
    private static FTPClient ftpClient=new FTPClient();
    /**
     * 将文件上传到ftp远程服务器
     */
    public String upLoad(MultipartFile multipartFile){
        InputStream inputStream=null;
        try {
            //连接ftp服务器
            ftpClient.connect(ftpConfig.getRemoteIp(),ftpConfig.getUploadPort());
            //登录
            ftpClient.login(ftpConfig.getFtpUserName(),ftpConfig.getFtpPassWord());
            //设置上传路径
            String path = ftpConfig.getRemotePath(); ///ftp-dir-xn
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftpClient.changeWorkingDirectory(path);
            if(!flag){
                //创建上传的路径 该方法只能创建一级目录,在这里如果/home/ftpadmin存在则可以创建image
                ftpClient.makeDirectory(path);
            }
            //指定上传路径
            ftpClient.changeWorkingDirectory(path);
            //指定上传文件的类型 二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //获取源文件名称
            String originalFilename = multipartFile.getOriginalFilename();
            //组装新名称   UUID.randomUUID生成随机数
            // originalFilename.substring(originalFilename.lastIndexOf("."))截取源文件后缀
            Random random = new Random();
            // String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+random.nextInt(1000000)+originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName= UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            //判断本地路径是否存在，不存在创建
            File localF = new File(ftpConfig.getLocalPath());
            if(!localF.exists()){
                localF.mkdirs();
            }
            //读取本地文件
            File file =new File(ftpConfig.getLocalPath()+File.separator+newFileName);
            //调用transferTo方法，读写文件 把本地文件上传到本地目录
            multipartFile.transferTo(file);
            inputStream = new FileInputStream(file);
            //第一个参数是文件名, 把本地目录下的文件上传到ftp上
            ftpClient.storeFile(file.getName(),inputStream);
            return newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                //关闭文件流
                if(inputStream!=null)
                    inputStream.close();
                //退出
                if(ftpClient!=null) {
                    ftpClient.logout();
                    //断开连接
                    ftpClient.disconnect();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * ftp文件下载
     */
    public void downLoad(String fileName, HttpServletResponse response){
        InputStream local =null;
        OutputStream outputStream=null;
        try {
            //连接ftp服务器
            ftpClient.connect(ftpConfig.getRemoteIp(),ftpConfig.getUploadPort());
            //登录
            ftpClient.login(ftpConfig.getFtpUserName(),ftpConfig.getFtpPassWord());

            //切换FTP目录
            ftpClient.changeWorkingDirectory(ftpConfig.getRemotePath());
            FTPFile[] ftpFiles = ftpClient.listFiles();
            //判断本地下载目录是否存在，如果不存在，创建
            File downLoadPath = new File(ftpConfig.getDownLoadPath());
            if(!downLoadPath.exists()){
                downLoadPath.mkdirs();
            }
            //循环遍历 ftp 服务器上该目录下的所有文件
            for (FTPFile file : ftpFiles) {
                //找到文件名称等于要下载的文件名称    equalsIgnoreCase  忽略大小写比较
                if (fileName.equalsIgnoreCase(file.getName())){
                    //实例化本地文件，实例化就是一个空文件
                    File localFile=new File(ftpConfig.getDownLoadPath()+"/"+fileName);
                    outputStream=new FileOutputStream(localFile);
                    //从远程读写文件到本地
                    ftpClient.retrieveFile(file.getName(),outputStream);
                    //刷新管道
                    outputStream.flush();
                    //下载功能,把本地文件读取到页面上,实质给response.getOutputStream()
                    downloadFile(localFile,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(ftpClient.isConnected()) {
                    ftpClient.disconnect();
                }
                if(null != outputStream) {
                    outputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * 通用下载
     */
    public static String downloadFile(File file, HttpServletResponse response) {
        if (file != null) {
            //设置文件路径
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开 MIME
                response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
                byte[] by = new byte[1024];
                FileInputStream fileInputStream = null;
                BufferedInputStream bufferedInputStream = null;
                OutputStream outputStream = null;
                BufferedOutputStream bufferedOutputStream = null;
                try {
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    outputStream = response.getOutputStream();
                    bufferedOutputStream = new BufferedOutputStream(outputStream);
                    int len = 0;
                    while ((len = bufferedInputStream.read(by)) != -1) {
                        bufferedOutputStream.write(by, 0, len);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        //1，后打开的先关闭  2,a依赖b 关闭a    bufferedInputStream依赖fileInputStream,没有fileInputStream就没有bufferedInputStream
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
