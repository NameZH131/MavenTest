
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
    <title>Information displayiong</title>
    <link rel="stylesheet" type="text/css" href="style/style.css">


</head>
<body>
<%--这个jsp的标签怎么用的我看不太懂，几乎没用过，javabean老技术了就是jsp的属性对应上javabean中的参数--%>
<%--这个报错知道啥意思吗，不知道informationDisplay_jsp--%>


<%--设置jaadbean的参数--%>
<%--<jsp:useBean id="student" class="bean.Student" scope="request"/>--%>

<%--<jsp:setProperty name="student" property="sId" value="${param.id}"/>--%>
<%--<jsp:setProperty name="student" property="sName" value="${param.name}"/>--%>
<%--<jsp:setProperty name="student" property="sGender" value="${param.gender}"/>--%>

<%--&lt;%&ndash;各种碰壁&ndash;%&gt;--%>
<%--<jsp:setProperty name="student" property="sAge" value="${param.age}"/>--%>
<%--<c:set target="${student}" property="sAge" value="${param.age}" />--%>

<%--如果用这个标签，会报错，因为jsp:setProperty标签的属性名和javabean的属性名不一致，jsp:setProperty标签的属性名是sAge，javabean的属性名是age，所以会报错。--%>
<%--<c:set target="${student}" property="sAge" param="sAge" />--%>

<%--session和requeset的获取属性甚至是获取参数的方法都会给0,可能是传来null和空字符串导致的。--%>
<%--<jsp:setProperty name="student" property="sAge" value="${session.getAttribute('sAge')}"/>--%>
<%--<jsp:setProperty name="student" property="sAge" value="${request.getAttribute('sAge')}"/>--%>
<%--<jsp:setProperty name="student" property="sAge" value="${request.getParameter('sAge')}"/>--%>
<%--<c:set target="${student}" property="sAge" value="${sessionScope.sAge}" />--%>


<%--<jsp:setProperty name="student" property="sGrade" param="grade"/>--%>

<%--这个标签可以设置所有的javabean的属性，但是我表单的name属性没有对应上javabean的属性，所以不能用这个标签，只能用上面的多个标签。--%>
<%--<jsp:setProperty name="student" property="*"/>
<!-- set all properties of student bean 因为我的表单的name属性没有对应上javabean的属性，所以不能用这个标签，只能用上的多个标签，且发现--%>



<%--显示功能标签--%>
<h1>Information displayiong</h1>

<%--include用户页面导航栏--%>
<%@include file="navigationBar.jsp"%>


<div style="width:30%;" onchange="" class="div-NormalStyle">
    <form action="/informationDisplay" method="get"><table>
        <tr>
            <td><label for="sId">Student ID:</label></td>
            <td> <input type="text" id="sId" name="sId" ></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="查询"></td>
        </tr>
        <p class="font-face" style="color:pink;">${requestScope.errorMessage}</p>
    </table>
</form>
</div>
<div class="div-NormalStyle">
    <p class="font-face" style="color:pink">Student Information:${requestScope.student.sName}</p>
    <table>
        <tr>
            <td><label>学生id:</label></td>
            <td><input type="text" name="sId" value="${requestScope.student.sId}" readonly></td>
        </tr>
        <tr>
            <td><label>学生姓名:</label></td>
            <td><input type="text" name="sName" value="${requestScope.student.sName}" readonly></td>
        </tr>
        <tr>
            <td><label>学生性别:</label></td>
            <td><input type="text" name="sGender" value="${requestScope.student.sGender}" readonly></td>
        </tr>
        <tr>
            <td><label>学生年龄:</label></td>
            <td><input type="text" name="sAge" value="${ requestScope.student.sAge}" readonly></td>
        </tr>
        <tr>
            <td><label>学生年级:</label></td>
            <td><input type="text" name="sGrade" value="${requestScope.student.sGrade}" readonly></td>
        </tr>
<%--        <tr>--%>
<%--            <td><label>学生课程:</label></td>--%>
<%--            <td><input type="text" name="sCourse" value="${requestScope.course.cName}" readonly></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td><label>学生老师:</label></td>--%>
<%--            <td><input type="text" name="sTeacher" value="${requestScope.teacher.tName}" readonly></td>--%>
<%--        </tr>--%>
    </table>

</div>

</body>
</html>
