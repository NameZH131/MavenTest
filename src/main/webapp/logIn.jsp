<%--
  Created by IntelliJ IDEA.
  User: jake
  Date: 2024/5/10
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>logging student data</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">

    <style>
        #rotatingPlane {
            width: 380px;
            height: 42px;
            border-radius: 20px;
           box-shadow: 0 0 26px rgb(255, 255, 255);
            background-color: rgb(255, 158, 158);
            /*相对父级布局*/
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%) rotateX(0deg);
            transition: transform 0.5s ease-in-out;
        }
    </style>

</head>
<body>
<h1>Student Data Logging</h1>
<p>This page is used to log student data.</p>
<p>上下问路径：${pageContext.request.contextPath}</p>
<!-- 要实现文本在垂直方向上居中显示，可以使用 Flexbox 布局 -->
<div style="background-image: none;">
    <form action="${pageContext.request.contextPath}/logIn" method="get" style="border-radius:6%">
        <table>
            <tr>
                <div id="rotatingPlane" style="display: flex;
    justify-content: center;
    align-items: center;
    ;text-shadow: 0 0 15px rgb(255,214,243);background-image: none">Thanks to the Coding World!</div>
            </tr>
            <tr>
                <td><label for="id">ID:</label></td>
                <td><input type="text" id="id" name="id"></td>
            </tr>
            <tr>
                <td><label for="password">Password:</label></td>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit">
                    <input type="reset" value="Reset">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p><a href="${pageContext.request.contextPath}/register.jsp">Register now</a></p>
                    <p><a href="${pageContext.request.contextPath}/index.jsp">Back to Home</a></p>
                    <p><a href="${pageContext.request.contextPath}/mainUi.jsp"> Back to Main UI</a></p>
                </td>
            </tr>
            <tr><td colspan="2">

            </td></tr>
        </table>
    </form>

    <%--    <%if(request.getAttribute("errorMessage")!=null){--%>
    <%--        out.print("<p style='color:red'>"+request.getAttribute("errorMessage")+"</p>");--%>
    <%--       --%>
    <%--    }%> --%>

    <p class="font-face" style="color:#ff5a77;">${requestScope.errorMessage}${requestScope.successMessage}</p>
</div>


<script>
    let plane = document.getElementById('rotatingPlane');
    let rotationSpeed = 0;
    let currentRotation = 0;
    let axis = 'x';

    function rotatePlane() {
        if (axis === 'x') {
            currentRotation += rotationSpeed;
            plane.style.transform = `translate(-50%, -50%) rotateX(${currentRotation}deg)`;
        } else {
            currentRotation += rotationSpeed;
            plane.style.transform = `translate(-50%, -50%) rotateZ(${currentRotation}deg)`;
        }
    }

    function changeColor() {
        const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16);
        plane.style.backgroundColor = randomColor;
    }

    setInterval(changeColor, 1000);

    setInterval(rotatePlane, 1000 / 60);

    plane.addEventListener('mouseover', () => {
        rotationSpeed = 10;
    });

    plane.addEventListener('mouseout', () => {
        rotationSpeed = 1;
    });

    plane.addEventListener('click', () => {
        axis = axis === 'x' ? 'z' : 'x';
    });
</script>
</body>
</html>
