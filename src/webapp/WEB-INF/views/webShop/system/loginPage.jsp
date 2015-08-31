<!DOCTYPE html>
<html>
<head>
    <title>Web Shop</title>
    <script type="text/javascript" src="${basePath }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
</head>
<body>
    <div class="" id="loginForm">
        <input class="" id="accountName" placeholder="">
        <input class="" id="accountPwd" placeholder="">
        <button class="" id="submitBtn" type="button">提交</button>
        <button class="" id="resetBtn" type="button">清除</button>
    </div>
</body>
<script>
    function loginForm() {
        $.post("${basePath }/webShop/system/login",
                {
                    'accountName': $('#accountName').val(),
                    'accountPwd': $('#accountPwd').val()
                },
                function(resultData) {

                });
    }

    function resetForm() {

    }

    // 页面初始化
    $(function() {
        $('#submitBtn').on('click', login);
        $('#resetBtn').on('click', login);
    });
</script>
</html>