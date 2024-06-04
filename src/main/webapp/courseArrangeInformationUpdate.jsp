<%--
  Created by IntelliJ IDEA.
  User: jake
  Date: 2024/5/22
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%--引入JSTL标签库--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>学生成绩信息录入修改</title>
    <script>
        function handleCheck(index) {
            var checkbox = document.getElementById("checkbox" + index);
            var input = document.getElementsByClassName(index);
            if (checkbox.checked) {
                for (var i = 0; i < input.length; i++)
                    input[i].readOnly = false;
            } else {
                for (var i = 0; i < input.length; i++)
                    input[i].readOnly = true;
            }
        }
    </script>



<%--    <script>--%>
<%--        function updateSelectOptions(selectedSelect) {--%>
<%--            // 获取被选中的选项的值--%>
<%--            const selectedValue = selectedSelect.value;--%>

<%--            // 遍历所有下拉框--%>
<%--            const allSelects = document.getElementsByName('selectTeacher');--%>
<%--            for (let i = 0; i < allSelects.length; i++) {--%>
<%--                const select = allSelects[i];--%>

<%--                // 如果是当前被选中的下拉框，则跳过--%>
<%--                if (select === selectedSelect) {--%>
<%--                    continue;--%>
<%--                }--%>

<%--                // 遍历下拉框中的所有选项--%>
<%--                for (let j = 0; j < select.options.length; j++) {--%>
<%--                    const option = select.options[j];--%>

<%--                    // 如果选项的值与被选中的值相同，则禁用或删除该选项--%>
<%--                    if (option.value === selectedValue) {--%>
<%--                        // option.disabled = true;--%>
<%--                        // 或者使用以下代码删除该选项--%>
<%--                        // option.parentNode.removeChild(option);--%>
<%--                    } else {--%>
<%--                        // 如果之前被禁用了，现在需要重新启用--%>
<%--                        // option.disabled = false;--%>
<%--                    }--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--    </script>--%>

<%--    引入脚本--%>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>
</head>
<body>
<h1>Information updating</h1>
<%--导航栏--%>
<%@include file="navigationBar.jsp" %>
<div style="width:100%;height:100%;box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);" class="div-NormalStyle">
    <div style="width:30%;" class="div-NormalStyle">
        <form action="${pageContext.request.contextPath}/courseArrangeInformationUpdate_search" method="get">
            <table>
                <tr>
                    <td><label for="sId">Student ID:</label></td>
                    <td><input type="text" id="sId" name="sId" placeholder="请输入学生ID"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="查询"></td>
                </tr>
                <p class="font-face" style="color:pink;">${requestScope.errorMessage}</p>
            </table>
        </form>
    </div>
    <%--    div左对齐--%>
    <div style="width:65%; float: left;" class="div-NormalStyle">
        <p class="font-face" style="color:pink;">Student Course Information: ${requestScope.student.sName}课程数量${requestScope.serveList.size()}</p>
        <div style="width:100%;box-shadow: none" class="flex-container div-NormalStyle" >
            <c:forEach items="${requestScope.serveList}" var="serve" varStatus="loop">
                <div class="div-NormalStyle">
                    <table>
                        <c:set var="course" value="${requestScope.courseList[loop.index]}"/>
                        <c:set var="teacher" value="${requestScope.teacherList[loop.index]}"/>
                        <thead>
                        <tr>
                            <td><label for="checkbox${loop.index}">🆗${requestScope.student.sName}</label></td>
                            <td><label for="checkbox${loop.index}">✅已选课程：${loop.index+1}</label></td>
                        </tr>
                        </thead>
                        <tr>
                            <td><label for="cId${loop.index}"> Course ID:</label></td>
                            <td><input type="text" id="cId${loop.index}" name="cId${loop.index}" value="${serve.cId}"
                                       class="${loop.index}" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="cName${loop.index}"> Course Name:</label></td>
                            <td><input type="text" id="cName${loop.index}" name="cName${loop.index}"
                                       value="${course.cName}"
                                       class="${loop.index}" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="tId${loop.index}"> Teacher ID:</label></td>
                            <td><input type="text" id="tId${loop.index}" name="tId${loop.index}" value="${serve.tId}"
                                       class="${loop.index}" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="tName${loop.index}"> Teacher Name:</label></td>
                            <td><input type="text" id="tName${loop.index}" name="tName${loop.index}"
                                       value="${teacher.tName}" class="${loop.index}" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="score${loop.index}">Course Score:</label></td>
                            <td><input type="text" id="score${loop.index}" name="score${loop.index}"
                                       value="${serve.score}"
                                       class="${loop.index}" readonly></td>
                        </tr>
                        <tr>
                            <td><label for="checkbox${loop.index}">Alert and Remarks:</label></td>
                            <td>
                                <input type="checkbox" id="checkbox${loop.index}" name="checkbox${loop.index}"
                                       onclick="handleCheck(${loop.index})" style=" width: 24px; height: 24px;">
                            </td>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </div>
    </div>
    <div style="width: 35%;float:right;" class="div-NormalStyle">
        <form action="${pageContext.request.contextPath}/courseArrangeInformationUpdate" method="get" onsubmit="return confirmQuery()">
            <table>
                <tr>
                    <td colspan="4">
                        <p class="font-face" style="color:pink;">${student.sId}${student.sName}更新课程</p>
                    </td>
                </tr>
                <c:forEach items="${requestScope.allCourseList}" var="allcourse" varStatus="loop">
                    <tr>
                        <td><label for="checkboxAllCourse${loop.index}" class="font-face"
                                   style="color:pink;">${allcourse.cId}:${allcourse.cName}</label>
                        </td>
                        <td>
                            <input type="hidden" name="studentCRUD" value="${requestScope.student.sId}">
                            <input type="checkbox" id="checkboxAllCourse${loop.index}" name="checkboxCourse"
                                   value="${allcourse.cId}" style=" width: 24px; height: 24px;">
                        </td>
                        <td>
                            <input type="text" id="score${loop.index}" name="score" placeholder="输入该课堂成绩描述">
                        </td>
                        <td>
                            <select name="selectTeacher" id="selectTeacher${loop.index}" onchange="updateSelectOptions(this)">
                                <c:forEach items="${requestScope.allTeacherList}" var="teacherList" varStatus="loop1">
                                    <option value="${teacherList.tId}" id="">${teacherList.tId}:${teacherList.tName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4">
                        <input type="submit" value="更新选课配置">
                    </td>
                </tr>
                <p class="font-face" style="color:#ff3edf;">${requestScope.errorMessageForUpdate}</p>
                <p class="font-face" style="color:#ff3edf;">${requestScope.successMessageForUpdate}</p>
            </table>
        </form>
    </div>
</div>
</body>
</html>
