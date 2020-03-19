/**
 * Created by Huarf on 2020/3/6.
 */
$(function () {

    //初始化实例元数据，配置数据等，即获取那些在后端配置的数据，特别是yml中配置的，将数据存入sessionStorage中
    initMetadata();

    //监听按下回车键，提交
    document.onkeydown = enterKeyDown;

    $('#login').click(function () {
        login();
    });
});

function login() {
    var username = $(':input[name="username"]').val();
    if(!isValid(username)){
        alert('请填写用户名');
        return;
    }
    var password = $(':input[name="password"]').val();
    if(!isValid(password)){
        alert('请填写密码');
        return;
    }
    var imgCheckCode = $(':input[name="imgCheckCode"]').val();
    if(!isValid(imgCheckCode)){
        alert('请填写验证码');
        return;
    }

    showMask();
    //Accept: application/json, text/javascript, */*; q=0.01
    //Content-Type: application/json;charset=UTF-8
    $.ajax({
        type: "POST",
        url: getContextPath() + '/login/doLogin',
        data: {
            'username': username,
            'password': password/*,
            'imgCheckCode': imgCheckCode*/
        },
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                //登录成功，保存认证数据
                saveAuthData(data.payload);
                window.location.href = getContextPath() + '/home';
                // window.open(getContextPath() + '/home');
            } else {
                printErrorMsg(data.msg);
                changeImg();
            }
            hideMask();
        }
    });
}

function printErrorMsg(text) {
    $('#loginError').html(text);
}

function enterKeyDown(e) {
    // 兼容FF和IE和Opera
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        login();
        return false;
    }
    return true;
}

//刷新图片验证码
function changeImg() {
    var id = Math.random();//产生一个随机数，作为假的参数传送产生验证码的Servlet，这样就避免了浏览器使用缓存的验证码了。
    document.getElementById("check").src = getContextPath() + "/login/checkCode?id=" + id;
}

//显示遮罩层
function showMask() {
    $("#mask").css("height", $(document).height());
    $("#mask").show();
}
//隐藏遮罩层
function hideMask() {
    $("#mask").hide();
}