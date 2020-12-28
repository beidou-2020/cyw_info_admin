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
                <form class="layui-form layui-col-md12 x-so" action="${cyw }/tup/pendingList" method="get">
                    <input class="layui-input" placeholder="开始日期" name="submitBegintime" id="start" autocomplete="off"
                           value="<fmt:formatDate value="${query.submitBegintime}" type="date" pattern="yyyy-MM-dd"/>">
                    <input class="layui-input" placeholder="结束日期" name="submitEndtime" id="end" autocomplete="off"
                           value="<fmt:formatDate value="${query.submitEndtime}" type="date" pattern="yyyy-MM-dd"/>">
                    <input type="text" name="username" placeholder="账号" class="layui-input" value="${query.username }">
                    <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
                    </button>
                </form>
            </div>
            <xblock>
                <button class="layui-btn" onclick="reviewAll()">批量审核</button>
                <button class="layui-btn layui-btn-danger" onclick="downAll()">批量驳回</button>
                <%--<button class="layui-btn" onclick="x_admin_show('添加','')"><i class="layui-icon"></i>添加</button>--%>
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
                        <td class="td-status"><a>待审核</a></td>
                        <td class="td-manage">
                                <%--<a title="详情"  onclick="x_admin_show('详情','${beidou}/read/toDetailsView/${item.id }')" href="javascript:;">
                                    <i class="layui-icon">&#xe705;</i>
                                </a>--%>
                            <a title="审核" onclick="member_review(this,'${item.id}')" href="javascript:;">
                                <i class="layui-icon">&#xe6c6;</i>
                            </a>
                            <a title="驳回" onclick="member_down(this,'${item.id}')" href="javascript:;">
                                <i class="layui-icon">&#xe6c5;</i>
                            </a>
                                <%--<a title="编辑" onclick="x_admin_show('编辑','${beidou}/read/toEditView/${item.id }')"
                                   href="javascript:;">
                                    <i class="layui-icon">&#xe642;</i>
                                </a>--%>
                                <%--<a title="删除" onclick="member_del(this,'${item.id}')" href="javascript:;">
                                  <i class="layui-icon">&#xe640;</i>
                                </a>--%>
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
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=${currPageNumber-1}">上一页</a>
                        </c:when>
                        <c:when test="${currPageNumber<totalPage && currPageNumber>1}">
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=1">首页</a>
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=${currPageNumber-1}">上一页</a>
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=${currPageNumber+1}">下一页</a>
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=${totalPage}">尾页</a>
                        </c:when>
                        <c:otherwise>
                            <a class="prev" href="${cyw }/tup/pendingList?currentPageNumber=${currPageNumber+1}">下一页</a>
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

    /*审核通过*/
    function member_review(obj, id) {
        layer.confirm('确认要审核通过吗？', function () {
            //alert(id);
            $.ajax({
                type: "post",
                url: "${cyw}/tup/updateStatusById",
                data: {"id": id, "status": 1},
                success: function (data) {
                    if (data.code == 0 && data.data == 1) {
                        alert("审核已通过");
                        window.location.reload();//刷新页面
                    }
                }
            });
        });
    }

    /*驳回*/
    function member_down(obj, id) {
        layer.confirm('确认要驳回吗？', function () {
            //alert(id);
            $.ajax({
                type: "post",
                url: "${cyw}/tup/updateStatusById",
                data: {"id": id, "status": 2},
                success: function (data) {
                    if (data.code == 0 && data.data == 1) {
                        alert("数据已驳回");
                        window.location.reload();//刷新页面
                    }
                }
            });
        });
    }

    /*批量审核*/
    function reviewAll() {
        var data = tableCheck.getData();
        layer.confirm('确认要批量通过吗？', function () {
            $.ajax({
                type: "post",
                url: "${cyw}/tup/batchUpdateStatusById?ids=" + data + "&status=" + 1,
                success: function (data) {
                    if (data.code == 0 && data.data >= 1) {
                        alert("批量审核已通过");
                        window.location.reload();//刷新页面
                    }
                }
            });
        });
    }

    /*批量驳回*/
    function downAll() {
        var data = tableCheck.getData();
        layer.confirm('确认要批量驳回吗？', function () {
            $.ajax({
                type: "post",
                url: "${cyw}/tup/batchUpdateStatusById?ids=" + data + "&status=" + 2,
                success: function (data) {
                    if (data.code == 0 && data.data >= 1) {
                        alert("选中数据已批量驳回");
                        window.location.reload();//刷新页面
                    }
                }
            });
        });
    }
</script>
</body>
</html>