/**
 * Created by Huarf on 2020/3/6.
 * ajax请求相关工具方法
 */

$.ajaxSetup({
    dataType : "json",
    //contentType : "application/json;charset=utf-8",
    beforeSend : function (jqXHR) {
        var accessToken = getAccessToken();
        if(isValid(getAccessToken())){
            //console.log('ajaxSetup设置Authorization');
            jqXHR.setRequestHeader("Authorization", "Bearer " + accessToken);
        }else{
            console.log('access_token不存在，ajaxSetup无法设置Authorization');
        }
    },
    cache : false,
    timeout : Constant.ajaxTimeout,
    error : function(jqXHR, statusText, errorThrown) {
        //jqXHR：XMLHttpRequest 对象，默认在IE下是ActiveXObject 而其他情况下是XMLHttpRequest 。
        var errorMsg = '';
        if(isValid(jqXHR.status)){
            errorMsg = errorMsg + 'status=' + jqXHR.status + "；";
        }
        if(isValid(jqXHR.statusText)){
            errorMsg = errorMsg + 'statusText=' + jqXHR.statusText + "；";
        }
        if(isValid(jqXHR.responseJSON)){
            errorMsg = errorMsg + 'error=' + jqXHR.responseJSON.error + "；" + 'error_description=' + jqXHR.responseJSON.error_description + "；";
        }
        //console.log(errorMsg);
        error(errorMsg);//之后要换成bootstrap弹窗
        $("#mask").hide();
        // switch (jqXHR.status) {
        //     case (500):
        //         alert('['+ jqXHR.status +']服务器系统内部错误');
        //         break;
        //     case (401):
        //         alert('['+ jqXHR.status +']未登录');
        //         break;
        //     case (403):
        //         alert('['+ jqXHR.status +']无权限执行此操作');
        //         break;
        //     case (408):
        //         alert('['+ jqXHR.status +']请求超时');
        //         break;
        //     default:
        //         alert('['+ jqXHR.status +']未知错误，请联系管理员');
        // }
    }
});

