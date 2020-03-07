/**
 * Created by Huarf on 2020/3/6.
 * 常用工具方法
 *
 */

/**
 * 是否为空
 * @param val
 * @returns {boolean}
 */
function isValid(val) {
    if(val!='' && val!=null && typeof(val)!="undefined" && val!=0)
        return true;
    else
        return false;
}

/**
 * 创建一个form并携带access_token提交，用于页面跳转，
 * @param params 传入的参数，是一个json对象
 * @param action 提交的url
 * @param method get、post
 * @param target _self、_blank、_top、_parent
 */
function formSubmitWithAccessToken(paramsJsonObj, action, method, target){
    var form = $("<form></form>");
    form.attr('action', action);
    form.attr('method', method);
    form.attr('target', target);
    //添加token
    var accessToken = $("<input type='hidden' name='access_token' />");
    accessToken.attr('value', getAccessToken());
    form.append(accessToken);

    //添加自定义参数
    if(isValid(paramsJsonObj)){
        for (var paramName in paramsJsonObj) {
            var paramValue = paramsJsonObj[paramName];
            var paramInput = $("<input type='hidden' name='"+ paramName +"' />");
            paramInput.attr('value', paramValue);
            form.append(paramInput);
        }
    }

    form.appendTo("body");
    form.css('display','none');
    form.submit();
}

/**
 * 测试accessToken是否有效
 * @returns {boolean}
 */
function testAccessToken() {
    var result = false;
    $.ajax({
        async: false,
        dataType : "html",
        type: "OPTIONS",
        url: getContextPath() + '/login/logined',
        success: function (data) {
            result = true;
        }
    });
    return result;
}