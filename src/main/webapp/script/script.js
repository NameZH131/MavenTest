// 在页面加载完毕后执行初始化工作
document.addEventListener('DOMContentLoaded', function () {
    // 通过ID找到表单元素
    var form = document.querySelector('form');

    // 添加提交事件监听器，在提交前验证表单数据
    form.addEventListener('submit', function (event) {
        if (!validateForm()) {
            event.preventDefault(); // 阻止表单提交
            alert('请检查并正确填写所有字段');
        }
    });
});

// 验证表单数据的函数
function validateForm() {
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    if (username.value.trim() === "") {
        alert("ID不能为空");
        username.focus();
        return false;
    }
    if (password.value.trim() === "") {
        alert("密码不能为空");
        password.focus();
        return false;
    }
    if (isNaN(username.value)) {
        username.value = ""; // 清空用户名输入框的值
        username.focus(); // 重新获得用户名输入框的焦点
        alert("ID必须为数字");
        return false;
    } else {
        return true;
    }
}


//提交确认脚本
function confirmQuery() {
    var confirm = window.confirm("您确认提交？");
    if (confirm) {
        alert("正在提交，清等待系统回应");
        return true;  // 确认提交后返回 true
    }else {
        alert("你取消了提交，此次提交失败");
        return false;
        // 中断表单提交，重定向到首页
        // window.location.href = "/informationUpdate.jsp";

    }
}