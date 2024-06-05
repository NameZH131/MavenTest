
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%--引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>mainUi</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">

    <style>
        .img-content{
            width: 100%;
        }
    </style>
</head>
<body>
<h1>学生管理系统用户主页</h1>
<%--include用户页面导航栏--%>
<%@include file="navigationBar.jsp"%>
<%--制作功能选择--%>
<div style="width: 80%;background-image: none ;padding: 0;
    background-color: rgba(255, 255, 255, 0);
    border-radius: 0;
    box-shadow: none;" class="flex-container">
    <c:forEach begin="0" end="5" var="i">
        <div style="background-image: none" class="div-NormalStyle">
            <div style="background-image: none;box-shadow:0 0 14px black">
                <a href="${pageContext.request.contextPath}/error.jsp">
                    <img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" class="img-content" alt="图片">
                </a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>