<%--
  Created by IntelliJ IDEA.
  User: jake
  Date: 2024/5/29
  Time: 下午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error Page</h1>
<p>Sorry, an error occurred.</p>
<%
    // 获取异常信息并显示
    Exception ex = (Exception)request.getAttribute("javax.servlet.error.exception");
    if(ex != null) {
%>
<p><%= ex.getMessage() %></p>
<%
    }
%>
<%
    // 获取状态码信息并显示
    Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
    if(statusCode != null) {
%>
<p>Status Code: <%= statusCode %></p>
<%
    }
%>
<%
    // 获取请求 URI 信息并显示
    String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
    if(requestUri != null) {
%>
<p>Request URI: <%= requestUri %></p>
<%
    }
%>
</body>
</html>
