<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="taglibs.jsp" %>
<html>
<!-- Head -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>顶部</title>
    <link rel="stylesheet" href="../css/layui.css">
</head>

<!-- Body -->
<body>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item layui-nav-itemed">
                <a class="#" href="javascript:;">审核管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${cyw}/tup/pendingList?status=0">待审核列表</a></dd>
                </dl>
                <dl class="layui-nav-child">
                    <dd><a href="${cyw}/tup/auditedList?status=1">审核通过列表</a></dd>
                </dl>
                <dl class="layui-nav-child">
                    <dd><a href="${cyw}/tup/dowmList?status=2">驳回列表</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a class="#" href="javascript:;">用户管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="${cyw}/user/list">用户列表</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

</body>
<!-- //Body -->

</html>