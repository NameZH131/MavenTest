<%--
  Created by IntelliJ IDEA.
  User: jake
  Date: 2024/5/28
  Time: 下午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>navigationBar</title>
    <link rel="stylesheet" type="text/css" href="style/style.css">
    <style>
        a, span {
            margin-right: 20px; /* 增大右侧间距到20像素 */
        }
    </style>
</head>
<body>
<div style="width: 100% ;border-radius:0 ;">
    <span style="float: left"><img src="${pageContext.request.contextPath}/resource/image/navigationBar/index_logo.gif"
                                   alt="图标" width="506"></span>

    <%--    输出成功信息--%>
    <span class="font-face" style="color:pink;">${requestScope.successMessage} ${param.id}</span>

    <%--    增大间距--%>
    <a href="${pageContext.request.contextPath}/mainUi.jsp">首页</a>
    <a href="${pageContext.request.contextPath}/informationDisplay.jsp">信息查询输出</a>
    <a href="${pageContext.request.contextPath}/informationUpdate.jsp">信息录入修改</a>
    <a href="${pageContext.request.contextPath}/index.jsp">退出登录</a>
    <%--    用户头像和名字--%>
    <span style="float: right;">
        <img src="${pageContext.request.contextPath}/resource/image/navigationBar/OIP-C.jpg" alt="图标" width="82px">
        <span style="font-size: 20px;">${sessionScope.user.sName}</span>
  </span>

</div>
</body>
</html>
