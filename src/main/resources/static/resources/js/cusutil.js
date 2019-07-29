$(function () {
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    // var point = new BMap.Point(116.331398,39.897445);
    // map.centerAndZoom(point,12);
    function myFun(result) {
        var cityName = result.name;
        map.setCenter(cityName);
        //alert("当前定位城市:"+cityName);
        $("#allmap").html(cityName);
    }

    var myCity = new BMap.LocalCity();
    myCity.get(myFun);
})


/*检测用户是否登录*/
function judgeCusLogin() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/login/judgeCusLogin",
        success: function (data) {
            if (data.code == 200) {
                //alert(data.code);
                $("#customer").hide();
                $("#cus").show();
                $("#cusername").html(data.object);
            } else {
                $("#cus").hide();
                $("#customer").show();
            }
        }
    });
}

/*退出登录 销毁session*/
function killCusSession() {
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/login/killCusSession",
        success: function () {
            window.location.href = "index.html";
        }
    });
}

/*手机验证码按钮*/
var countdown = 60;

function settime() {
    if (countdown == 0) {
        $("#btn").attr("disabled", false);
        $("#btn").val("获取验证码")
        countdown = 60;
        return;
    } else {
        $("#btn").css("background-color", "rgba(226, 179, 42, 0.6)");
        $("#btn").attr("disabled", true);
        $("#btn").val("重新发送" + countdown)
        countdown--;
    }
    setTimeout(function () {
            settime();
        }
        , 1000)
}

/*验证手机号获取验证码测试*/
function selephonecodetext() {
    $.ajax({
        type: "post",
        url: "/login/getUsableGetPhone",
        data: $("#reForm").serialize(),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.code == 200) {
                this.$message.success(data.msg);
                settime();
            } else {
                this.$message.error(data.msg);
            }
        }
    })
}

/*用户登录  测试用*/
function logintext() {
    $.ajax({
        type: "post",
        url: "/login/cusLogin",
        data: $("#loform").serialize(),
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                location.href = "../index.html"
            } else {
                //alert(data.msg);
            }
        }
    })
}

Function.prototype.getMultiLine = function () {
    var lines = new String(this);
    lines = lines.substring(lines.indexOf("/*") + 3, lines.lastIndexOf("*/"));
    return lines;
}
var string = function () {
/*

 __      __   _       _
 \ \    / /  | |     | |
  \ \  / /__ | |_   _| |_ ___
   \ \/ / _ \| | | | | __/ _ \
    \  / (_) | | |_| | ||  __/
     \/ \___/|_|\__,_|\__\___|

蜗壳后台入口:http://localhost/page/backstage/blogin.html
*/
}



