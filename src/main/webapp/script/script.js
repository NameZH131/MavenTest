// 在页面加载完毕后执行初始化工作
document.addEventListener('DOMContentLoaded', function() {
    // 通过ID找到表单元素
    var form = document.querySelector('form');

    // 添加提交事件监听器，在提交前验证表单数据
    form.addEventListener('submit', function(event) {
        if (!validateForm()) {
            event.preventDefault(); // 阻止表单提交
            alert('请检查并正确填写所有字段');
        }
    });
});

// 验证表单数据的函数
function validateForm() {
    var username = document.getElementById('username').value;
    // var name = document.getElementById('name').value;
    // var password = document.getElementById('password').value;

    // 简单的非空验证示例
    if (username.trim() === '' ) {
        alert('请填写用户Id');
        return false; // 如果有任何字段为空，则验证失败
    }

    // 更多复杂的验证逻辑可以在这里添加
    // 例如，密码长度要求、用户名格式、等等

    return true; // 所有数据验证通过
}


//提交确认脚本
function confirmQuery(){
    var comfirm = confirm("确认提交？");
    if(comfirm){
        alert("正在提交，清等待系统回应");
    }else{
        alert("你取消了提交，提交失败");
        // 中断表单提交，重定向到首页
        window.location.href = "/informationUpdate.jsp";
        return false;
    }
}