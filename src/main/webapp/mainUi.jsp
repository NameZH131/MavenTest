<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>mainUi</title>
    <link rel="stylesheet" type="text/css" href="style/style.css">
    content
    <style>
        .img-content{
            width: 100%;
        }
    </style>
</head>
<body>
<!--    建立一个标准的学生系统主页-->

<h1>学生管理系统用户主页</h1>
<p>欢迎使用学生管理系统，请登录或注册。</p>
<%--导航栏--%>
<%--include用户页面导航栏--%>
<%@include file="navigationBar.jsp"%>

<%--制作功能选择--%>
<div style="width: 80%;" class="flex-container">
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
    <div> <span><img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片"></span></div>
</div>
</body>
</html>