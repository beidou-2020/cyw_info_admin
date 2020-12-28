<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../common/taglibs.jsp" %>
<html>

<head>
    <meta charset="utf-8"/>
    <title>审核管理台</title>
    <link rel="stylesheet" href="../css/login.css"/>
</head>

<body>

<div class="top">预购系统&nbsp;·&nbsp;用数据改变未来</div>

<div class="content">
    <div class="login">
        <div class="title">预购系统&nbsp;·&nbsp;登录</div>
        <form id="login" action="${cyw}/" method="post" enctype="application/json">
            <div class="line">
                <img class="smallImg" src="../image/icon-4.png"/>
                <input placeholder="请输入账号" type="text" id="name" name="username" value="${queryInfo.name}"/>
            </div>
            <div class="line">
                <img class="smallImg" src="../image/icon-5.png"/>
                <input placeholder="请输入密码" type="password" id="password" name="password" value="${queryInfo.password}"/>
            </div>
            <button type="button" class="logBut" onclick="login()">登&nbsp;&nbsp;录</button>
        </form>
    </div>
</div>

<script>
    /*登录提交用户信息*/
    function login(){
        var name = document.getElementById("name").value;
        var password = document.getElementById("password").value;

        if(name == "" ){
            alert("账号不能为空");
            return false;
        }
        if(password == "" ){
            alert("密码不能为空");
            return false;
        }
        // 提交表单数据
        document.getElementById("login").submit();
    }
</script>

</body>
</html>