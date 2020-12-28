<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%@ include file="../common/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>服务器出错</title>
    <link href="../css/error.css" rel="stylesheet" type="text/css"/>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript">
        $(function () {
            var h = $(window).height();
            $('body').height(h);
            $('.mianBox').height(h);
            centerWindow(".tipInfo");
        });

        //2.将盒子方法放入这个方，方便法统一调用
        function centerWindow(a) {
            center(a);
            //自适应窗口
            $(window).bind('scroll resize',
                function () {
                    center(a);
                });
        }

        //1.居中方法，传入需要剧中的标签
        function center(a) {
            var wWidth = $(window).width();
            var wHeight = $(window).height();
            var boxWidth = $(a).width();
            var boxHeight = $(a).height();
            var scrollTop = $(window).scrollTop();
            var scrollLeft = $(window).scrollLeft();
            var top = scrollTop + (wHeight - boxHeight) / 2;
            var left = scrollLeft + (wWidth - boxWidth) / 2;
            $(a).css({
                "top": top,
                "left": left
            });
        }
    </script>
</head>
<body>
<!-- 头部区域（顶层水平导航栏） -->


<div class="mianBox">
    <img src="../image/error/yun0.png" alt="" class="yun yun0"/>
    <img src="../image/error/yun1.png" alt="" class="yun yun1"/>
    <img src="../image/error/yun2.png" alt="" class="yun yun2"/>
    <img src="../image/error/bird.png" alt="" class="bird"/>
    <img src="../image/error/san.png" alt="" class="san"/>
    <div class="tipInfo">
        <div class="in">
            <div class="textThis">
                <h2>验证身份出错啦！</h2>
                <p>
                    <c:if test="${not empty param.error}">
                        <span style="color: red">用户身份校验失败或非管理员登录</span>
                        <span>等待<b id="wait">3</b>秒自动到<a id="href" href="${cyw}/" style="color: red">登录</a>页面</span>
                    </c:if>
                </p>
                <script type="text/javascript">
                    (function () {
                        var wait = document.getElementById('wait'), href = document.getElementById('href').href;
                        var interval = setInterval(function () {
                            var time = --wait.innerHTML;
                            if (time <= 0) {

                                clearInterval(interval);
                                location.href = "${cyw}/";
                                //alert("开始跳转提示");
                            }
                            ;
                        }, 3000);
                    })();
                </script>
            </div>
        </div>
    </div>
</div>

</body>
</html>
