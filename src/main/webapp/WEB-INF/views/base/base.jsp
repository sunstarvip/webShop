<%--顶部属性每个页面需要单独引用, 无法继承--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.DarKnight.com.cn/jsp-extends" prefix="inheritance" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="locale" value="${pageContext.request.locale}"/>
<html>
<head>
    <inheritance:block name="title">
        <title>DarKnight支撑平台</title>
    </inheritance:block>

    <%-- Meta --%>
    <inheritance:block name="meta">
    </inheritance:block>

    <%-- CSS --%>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/themes/default/easyui.css" >
    <link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/themes/icon.css" >
    <%-- Validform --%>
    <%--<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/Validform/css/style.css" >--%>
    <inheritance:block name="cssLink">
    </inheritance:block>

    <%-- JS --%>
    <script type="text/javascript" src="${ctx}/static/plugins/jQuery/jQuery2.x/jquery-2.1.3.min.js" ></script>
    <script type="text/javascript" src="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/jquery.easyui.min.js" ></script>
    <script type="text/javascript" src="${ctx}/static/plugins/EasyUI/EasyUI1.4.1/locale/easyui-lang-zh_CN.js" ></script>
    <%-- Validform --%>
    <%--<script type="text/javascript" src="${ctx}/static/plugins/Validform/js/Validform_v5.3.2_min.js" ></script>--%>
    <%--<script type="text/javascript" src="${ctx}/static/plugins/Validform/js/Validform_Datatype.js" ></script>--%>
    <inheritance:block name="scriptSrc">
    </inheritance:block>

</head>

<inheritance:block name="body">
    <body>
        <%-- 内部body部分由子页面自行实现 --%>
    </body>
</inheritance:block>
</html>

