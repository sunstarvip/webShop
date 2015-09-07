<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>店铺管理</title>
    <link type="text/css" rel="stylesheet" href="${ctx }/static/plugins/uploadify/uploadify.css" />
    <script type="text/javascript" src="${ctx }/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/plugins/uploadify/jquery.uploadify.min.js"></script>
    <script type="text/javascript" src="${ctx }/static/project/base/restful.js"></script>
    <script type="text/javascript" src="${ctx }/static/core/js/utilTools.js"></script>
    <style>
        .shop-pic {
            width: 10em;
            height: 10em;
            margin: 5px;
        }
    </style>
</head>
<body>
    <div>
        <div>
            <input id="id" name="id" type="hidden" value="${shopId}">
            <input id="picUrl" name="picUrl" type="hidden" value="${shopPicUrl}">
            <img class="shop-pic" id="shopPicImg" src="${ctx }/${shopPicUrl}">
            <input class="" id="picUrlBtn" name="file" type="file">
            <button class="" id="editBtn" type="button">修改</button>
        </div>
    </div>

    <script>
        function uploadSuccess(resultData) {
            var shopPicFile = ResultData.getSuccessDataInfo(resultData);
            if(!!shopPicFile) {
                shopPicFile = eval('(' + shopPicFile + ')');
                if(!!shopPicFile['filePath']) {
                    $('#shopPicImg').attr('src', '${ctx }/' + shopPicFile['filePath']);
                    $('#picUrl').val(shopPicFile['filePath']);
                }
            }
        }

        function initUploadify(uploadifyId) {
            $('#' + uploadifyId).uploadify({
                'auto': true,  // 是否自动上传
                'buttonCursor': 'hand',
                'buttonClass' : '',
                'buttonText': '选择图标',
                'fileObjName' : 'Filedata',  // 后台参数名
                'fileSizeLimit' : '10MB',
                'fileTypeDesc' : '支持文件类型为BMP，JPG，JPEG，GIF，PNG，SVG',  // 文件类型描述
                'fileTypeExts' : '*.bmp; *.jpg; *.jpeg; *.gif; *.png; *.svg',  // 支持文件类型
                'formData': {'shopName': '${shopName}'},
                'height': 30,  // 选择按钮高度
                'width': 120,  // 选择按钮宽度
                'itemTemplate': false,  // 为文件上传添加展示模板html
                'method': 'get',
                'multi': false,  // 是否允许文件多选
                'preventCaching': false,  // 是否关闭文件上传缓存功能
                'uploadLimit': 1,  // 上传文件个数的限制
                'swf': '${ctx }/static/plugins/uploadify/uploadify.swf',
                'checkExisting' : false,  // 检查文件重名时填写后台检测路径
                'uploader': '${ctx }/upload/springUploadFile;jsessionid=${pageContext.session.id }?secondPath=${shopId}',
                'onUploadSuccess': function(file, data, response) {
                    uploadSuccess(data);
                },
                'onUploadError': function(file, errorCode, errorMsg, errorString) {
                    alert('上传图片失败，请稍后再试');
                }
            });
        }

        function editShopPic() {
            var shopObj = {};
            shopObj['shopId'] = '${shopId}';
            shopObj['picUrl'] = $('#picUrl').val();

            formSubmit('${ctx }/webShop/shop/editPic', 'post', '_self', shopObj);
        }

        // 页面初始化
        $(function() {
            // 初始化上传插件
            initUploadify('picUrlBtn');

            // 提交编辑
            $('#editBtn').on('click', function() {
                editShopPic();
            });
        });
    </script>
</body>
</html>
