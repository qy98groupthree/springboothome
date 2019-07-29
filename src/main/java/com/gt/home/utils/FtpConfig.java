package com.gt.home.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 姜亚林
 * @date：2019/7/29 9:36
 */
@Data
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "ftp")
public class FtpConfig {
    private String remoteIp;//远程IP
    private int uploadPort;//上传端口
    private String ftpUserName;//账号
    private String ftpPassWord;//密码
    private String remotePath;//远程路径
    private String localPath;//本地路径
    private String downLoadPath;//下载路径
}
