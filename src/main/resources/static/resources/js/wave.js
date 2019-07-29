var canvas, ctx;
var vertexes = [];
var diffPt = [];
var autoDiff = 1000;
var verNum = 250;
var canvasW = window.innerWidth;
var divclick = document.getElementById("wave");

/*加法器*/
var addListener = function (e, str, func) {
    if (e.addEventListener) {
        e.addEventListener(str, func, false);
    } else if (e.attachEvent) {
        e.attachEvent("on" + str, func);
    } else {

    }
};

/*加法器*/
addListener(window, "load", init);

//调整大小
function resize() {
    canvasW = document.getElementById('container').offsetWidth;
    initCanvas(canvasW, 300);
    var cW = canvas.width;
    var cH = canvas.height;
    for (var i = 0; i < verNum; i++)
        vertexes[i] = new Vertex(cW / (verNum - 1) * i, cH / 1.5, cH / 2);
    initDiffPt();
    var win_3 = window.innerWidth / 3;
}

/*初始化*/
function init() {
    resize();
    var FPS = 30;
    var interval = 1000 / FPS >> 0;
    var timer = setInterval(update, interval);
    if (window.addEventListener) addListener(window, "DOMMouseScroll", wheelHandler);
    addListener(window, "mousewheel", wheelHandler);
    addListener(window, "resize", resize);


    canvas.onmousedown = function (e) {
        var mouseX, mouseY;
        if (e) {
            mouseX = e.pageX;
            mouseY = e.pageY;
        } else {
            mouseX = event.x + document.body.scrollLeft;
            mouseY = event.y + document.body.scrollTop;
        }
        //alert("body:"+document.body.scrollTop);
        //alert(mouseX+"---"+mouseY);
        //alert(window.innerHeight/2);
        //alert(window.innerHeight/2 - mouseY);
        if (window.innerHeight / 2 - mouseY < 1400 && window.innerHeight / 2 - mouseY > -1400)
        //diffPt[150] = autoDiff;
        {
            autoDiff = 1000;
            if (mouseX < canvas.width) {
                xx = 1 + Math.floor((verNum - 2) * mouseX / canvas.width);

                diffPt[xx] = autoDiff;
            }

        }
    }
}

var wheelHandler = function (e) {
    var s = (e.detail) ? -e.detail : e.wheelDelta;
    s > 0 ? (dd > 15 ? dd-- : dd = dd) : (dd < 50 ? dd++ : dd = dd);
};

function initDiffPt() {
    for (var i = 0; i < verNum; i++)
        diffPt[i] = 0;
}

var xx = 150;
var dd = 15;

//更新
function update() {
    //ctx.rect(50,20,280,620);
    //ctx.stroke();
    //ctx.clip();
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    autoDiff -= autoDiff * 0.9;
    diffPt[xx] = autoDiff;
    //左侧
    //差分函数
    //差分，使得每个点都是上一个点的下一次的解，由于差分函数出来的解是一个曲线，且每次迭代后，曲线相加的结果形成了不断地波浪
    for (var i = xx - 1; i > 0; i--) {
        var d = xx - i;
        if (d > dd) d = dd;
        diffPt[i] -= (diffPt[i] - diffPt[i + 1]) * (1 - 0.01 * d);
    }
    //右侧
    for (var i = xx + 1; i < verNum; i++) {
        var d = i - xx;
        if (d > dd) d = dd;
        diffPt[i] -= (diffPt[i] - diffPt[i - 1]) * (1 - 0.01 * d);
    }
    //更新点Y坐标
    for (var i = 0; i < vertexes.length; i++) {
        vertexes[i].updateY(diffPt[i]);
    }
    draw();
}

var color1 = "#ffe680";
var color2 = "#ecbe3d";

function draw() {
    ctx.beginPath();
    ctx.moveTo(0, window.innerHeight);
    ctx.fillStyle = color1;
    ctx.lineTo(vertexes[0].x, vertexes[0].y);
    for (var i = 1; i < vertexes.length; i++) {
        ctx.lineTo(vertexes[i].x, vertexes[i].y);
    }
    ctx.lineTo(canvas.width, window.innerHeight);
    ctx.lineTo(0, window.innerHeight);
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(0, window.innerHeight);
    ctx.fillStyle = color2;
    ctx.lineTo(vertexes[0].x + 15, vertexes[0].y + 5);
    for (var i = 1; i < vertexes.length; i++) {
        ctx.lineTo(vertexes[i].x + 15, vertexes[i].y + 5);
    }
    ctx.lineTo(canvas.width, window.innerHeight);
    ctx.lineTo(0, window.innerHeight);
    ctx.fill();

}

function initCanvas(width, height) {
    canvas = document.getElementById("canvas");
    canvas.width = width;
    canvas.height = height;
    ctx = canvas.getContext("2d");
}

function Vertex(x, y, baseY) {
    this.baseY = baseY;
    this.x = x;
    this.y = y;
    this.vy = 0;
    this.targetY = 0;
    this.friction = 0.15;
    this.deceleration = 0.95;
}

Vertex.prototype.updateY = function (diffVal) {
    this.targetY = diffVal + this.baseY;
    this.vy += this.targetY - this.y
    this.y += this.vy * this.friction;
    this.vy *= this.deceleration;
}

/*轮播图*/
$(document).ready(function () {
    var blw = $("#myscrollbox li").width();
    //获取单个子元素所需宽度
    var liArr = $("#myscrollbox ul").children("li");
    //获取子元素数量
    var mysw = $("#myscroll").width();
    //获取子元素所在区域宽度
    var mus = parseInt(mysw / blw);
    //计算出需要显示的子元素的数量
    var length = liArr.length - mus;
    //计算子元素可移动次数（被隐藏的子元素数量）
    var i = 0
    $("#right").click(function () {
        i++
        //点击i加1
        if (i < length) {
            $("#myscrollbox").css("left", -(blw * i));
            //子元素集合向左移动，距离为子元素的宽度乘以i。
        } else {
            i = length;
            $("#myscrollbox").css("left", -(blw * length));
            //超出可移动范围后点击不再移动。最后几个隐藏的元素显示时i数值固定位已经移走的子元素数量。
        }
    });
    $("#left").click(function () {
        i--
        //点击i减1
        if (i >= 0) {
            $("#myscrollbox").css("left", -(blw * i));
            //子元素集合向右移动，距离为子元素的宽度乘以i。
        } else {
            i = 0;
            $("#myscrollbox").css("left", 0);
            //超出可移动范围后点击不再移动。最前几个子元素被显示时i为0。
        }
    });
});