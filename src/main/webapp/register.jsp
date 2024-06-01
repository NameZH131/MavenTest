<%--
  Created by IntelliJ IDEA.
  User: jake
  Date: 2024/5/11
  Time: 下午6:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
    <script src="${pageContext.request.contextPath}/script/script.js" type="text/javascript"></script>
    <title>Register</title>
</head>
<body>
<h1>注册页面</h1>
<div>
    <form action="${pageContext.request.contextPath}/register" method="post" class="flex-container" style="border-radius:6%">
        <table>
            <tr>
                <td><label for="username">ID:</label></td>
                <td><input type="text" id="username" name="username"></td>
            </tr>
            <tr>
                <td><label for="password">密码:</label></td>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td><label for="name">Name:</label></td>
                <td><input type="text" id="name" name="name"></td>
            </tr>
            <tr>
                <td><label for="age">Age:</label></td>
                <td><input type="text" id="age" name="age"></td>
            </tr>
            <tr>
                <td><label for="gender">Gender:</label></td>
                <td>
                    <select id="gender" name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="grade">Grade:</label></td>
                <td>
                    <select id="grade" name="grade">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="注册" onclick="validateForm()">🥰
                    <input type="reset" value="重置">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="${pageContext.request.contextPath}/logIn.jsp">已有账号，去登录</a>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="${pageContext.request.contextPath}/index.jsp">返回首页</a>
                </td>
            </tr>
        </table>

    </form>
</div>
</body>
</html>
