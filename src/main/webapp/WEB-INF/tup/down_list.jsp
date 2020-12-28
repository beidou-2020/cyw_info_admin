<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>审核管理台</title>
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/xadmin.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 头部区域（顶层水平导航栏） -->
    <%@ include file="../../common/top.jsp" %>

    <!-- 左侧导航区域（垂直导航栏） -->
    <%@ include file="../../common/left.jsp" %>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="x-body">
            <div class="layui-row">
                <form class="layui-form layui-col-md12 x-so" action="${cyw }/tup/dowmList" method="get">
                    <input class="layui-input" placeholder="开始日期" name="submitBegintime" id="start" autocomplete="off"
                           value="<fmt:formatDate value="${query.submitBegintime}" type="date" pattern="yyyy-MM-dd"/>">
                    <input class="layui-input" placeholder="结束日期" name="submitEndtime" id="end" autocomplete="off"
                           value="<fmt:formatDate value="${query.submitEndtime}" type="date" pattern="yyyy-MM-dd"/>">
                    <input type="text" name="username" placeholder="用户" class="layui-input" value="${query.username }">
                    <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
                    </button>
                </form>
            </div>
            <xblock>
                <button class="layui-btn layui-btn-danger" onclick="downAll()">批量删除</button>
                <span class="x-right" style="line-height:40px">共有数据：${total } 条</span>
            </xblock>
            <table class="layui-table">
                <thead>
                <tr>
                    <th>
                        <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i>
                        </div>
                    </th>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>物品名</th>
                    <th>数量</th>
                    <th>总金额</th>
                    <th>提交时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list }" varStatus="num">
                    <tr>
                        <td>
                            <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.id}'><i
                                    class="layui-icon">&#xe605;</i></div>
                        </td>
                        <td>${num.count }</td>
                        <td>${item.username }</td>
                        <td>${item.pname }</td>
                        <td>${item.tpcount }</td>
                        <td>&yen;${item.ptotal }</td>
                        <td><fmt:formatDate value="${item.createtime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td class="td-status"><a>驳回</a></td>
                        <td class="td-manage">
                            <a title="删除" onclick="member_del(this,'${item.id}')" href="javascript:;">
                                <i class="layui-icon">&#xe640;</i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page">
                <div>
                    <c:choose>
                        <c:when test="${total == 0}">
                            <span class="current">暂无数据</span>
                        </c:when>
                        <c:when test="${totalPage == 1}">
                            <span class="current">${totalPage}</span>
                        </c:when>
                        <c:when test="${totalPage == currPageNumber}">
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=${currPageNumber-1}">上一页</a>
                        </c:when>
                        <c:when test="${currPageNumber<totalPage && currPageNumber>1}">
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=1">首页</a>
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=${currPageNumber-1}">上一页</a>
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=${currPageNumber+1}">下一页</a>
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=${totalPage}">尾页</a>
                        </c:when>
                        <c:otherwise>
                            <a class="prev" href="${cyw }/tup/dowmList?currentPageNumber=${currPageNumber+1}">下一页</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="../../layui/layui.js"></script>
<script type="text/javascript" src="../../js/xadmin.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>

<script>
    /* 时间组件 */
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });


    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function () {
            //发异步删除数据
            //alert(id);
            $.ajax({
                type: "post",
                url: "${beidou }/read/deleteById",
                data: {"id": id},
                success: function (data) {

                }
            });
            layer.msg('已删除!', {icon: 1, time: 1000});
            window.location.reload();//刷新父页面
        });

    }

    /*批量删除阅读信息*/
    function delAll(argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要批量删除吗？', function () {
            $.ajax({
                type: "post",
                url: "${beidou}/read/batchDelete?idListStr=" + data,
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('操作成功', {icon: 1});
                        window.parent.location.reload();//刷新父页面
                    }
                }
            });
        });
    }
</script>
</body>
</html>