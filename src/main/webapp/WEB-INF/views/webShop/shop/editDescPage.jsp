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
            <input class="" id="description" value="${shopDesc}">
            <button class="" id="editBtn" type="button">修改</button>
            <button class="shop-form-button" id="cancelBtn" type="button">取消</button>
        </div>
    </div>

    <script>
        function editShopDesc() {
            var shopObj = {};
            shopObj['description'] = $('#description').val();

            formSubmit('${ctx }/page/shop/editDesc', 'post', '_self', shopObj);
        }

        // 页面初始化
        $(function() {
            // 提交编辑
            $('#editBtn').on('click', function() {
                editShopDesc();
            });

            // 取消编辑
            clickPageForwardBtn('#cancelBtn', '${ctx }/page/shop/managerPage');
        });
    </script>
</body>
</html>
