<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录页</title>
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
</head>
<body>
    <div class="" id="loginForm">
        <input class="" id="accountName" placeholder="">
        <input class="" id="accountPwd" placeholder="">
        <button class="" id="submitBtn" type="button">提交</button>
        <button class="" id="resetBtn" type="button">清除</button>
    </div>
    <script>
        function loginForm() {
            $.post("${ctx }/login",
                    {
                        'accountName': $('#accountName').val(),
                        'accountPwd': $('#accountPwd').val()
                    },
                    function(resultData) {
                        if(!!resultData) {
                            resultData = eval('(' + resultData + ')');
                            if(!!resultData['status'] && resultData['status'] == 'success') {
                                window.location.href = "${ctx }${targetUri }";
                            }
                        }
                    });
        }

        function resetForm() {
            $('#accountName').val('');
            $('#accountPwd').val('');
        }

        // 页面初始化
        $(function() {
            $('#submitBtn').on('click', loginForm);
            $('#resetBtn').on('click', resetForm);
        });
    </script>
</body>
</html>