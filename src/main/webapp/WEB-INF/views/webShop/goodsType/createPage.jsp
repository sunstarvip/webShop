<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商品类型管理</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/project/base/restful.js"></script>
    <style>
        .shop-form-item {
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="shop-form" id="goodsTypeForm">
        <div class="shop-form-item">
            <label for="typeName">商品类型名称</label>
            <input class="shop-form-input" id="typeName" name="typeName">
        </div>
        <div class="shop-form-footer">
            <button class="shop-form-subbmit" id="createBtn" type="submit">提交</button>
        </div>
    </div>

    <script>
        function saveGoodsType() {
            $.post('${ctx }/webShop/goodsType/saveGoodsType',
                    {
                        typeName: $('#typeName').val()
                    },
                    function(resultData) {
                        if(ResultData.getSuccessStatus(resultData)) {
                            alert("商品类型添加成功");
                            window.location.href = '${ctx }/webShop/goodsType/listPage';
                        }
                    });
        }

        // 页面初始化
        $(function() {
            // 提交新增
            $('#createBtn').on('click', function() {
                saveGoodsType();
            });
        });
    </script>
</body>
</html>
