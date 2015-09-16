<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺管理</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/core/js/utilTools.js"></script>
</head>
<body>
    <div>
        <div>
            <input class="" id="name" value="${shopName}">
            <button class="" id="editBtn" type="button">修改</button>
        </div>
    </div>

    <script>
        function editShopName() {
            var shopObj = {};
            shopObj['name'] = $('#name').val();

            formSubmit('${ctx }/page/shop/editName', 'post', '_self', shopObj);
        }

        // 页面初始化
        $(function() {
            // 提交编辑
            $('#editBtn').on('click', function() {
                editShopName();
            });
        });
    </script>
</body>
</html>
