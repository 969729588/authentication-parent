
$(function () {
    //fill当前租户下的服务实例
    fillServiceInstance();
});

/**
 * fill当前租户下的服务实例
 */
function fillServiceInstance() {
    $.ajax({
        type: "GET",
        url: getContextPath() + '/index/serviceInstance',
        success: function (data) {
            /**
             *
             name: "业务系统例子服务UI"
             description: "用来做相关例子的"
             appName: "EXAMPLE-UI"
             instanceId: "192.168.223.1:tenant1:example-ui:9980"
             milepost-type: "UI"
             tenant: "tenant1"
             url: "http://192.168.223.1:9980/example-ui/index"
             version: "1.0.0.100"
             */
            if (data.code == Constant.returnSuccess) {
                for(var i in data.payload){
                    var item = data.payload[i];
                    var itemEle = $('<a href="javascript:void(0);">' +
                        'name=' + item.name + '<br>' +
                        'description=' + item.description + '<br>' +
                        'appName=' + item.appName + '<br>' +
                        'instanceId=' + item.instanceId + '<br>' +
                        'tenant=' + item.tenant + '<br>' +
                        'version=' + item.version + '<br>' +
                        '</a>')
                        .data({
                            url:item.url,
                            contextPath:item.contextPath,
                            appType:item.appType,
                        })
                        .click(function(){
                            var url = $(this).data('url');
                            var appType = $(this).data('appType');

                            var actuatorPath = 'milepost-actuator/info';
                            switch(appType) {
                                case AppType.ui:
                                    //参数
                                    var paramsJsonObj = new Object();

                                    //实例元数据
                                    var metadata = {contextPath: $(this).data('contextPath')};
                                    paramsJsonObj.metadata = JSON.stringify(metadata);

                                    //认证数据
                                    var authData = getAuthData();
                                    //删除没用的数据
                                    delete authData.jwt.jti;
                                    delete authData.jwt.refresh_token;
                                    delete authData.jwt.scope;
                                    delete authData.jwt.token_type;
                                    delete authData.user.password;
                                    delete authData.user.activated;
                                    paramsJsonObj.authData = JSON.stringify(authData);
                                    formSubmitWithAccessToken(paramsJsonObj, url, 'post', '_blank');
                                    break;
                                case AppType.service:
                                    window.open(url);
                                    break;
                                case AppType.auth:
                                    window.open(url);
                                    break;
                                case AppType.admin:
                                    //参数
                                    var paramsJsonObj = new Object();
                                    //从认证UI的实例元数据中获取
                                    var authUiMetadata = getMetadata();
                                    paramsJsonObj.username=authUiMetadata.loginSbaServerUser;
                                    paramsJsonObj.password=authUiMetadata.loginSbaServerPassword;
                                    var adminLoginUrl = url.replace(actuatorPath, 'login');
                                    formSubmitWithAccessToken(paramsJsonObj, adminLoginUrl, 'POST', '_blank');
                                    break;
                                case AppType.turbin:
                                    //参数
                                    var turbinHystrixUrl = url.replace(actuatorPath, 'hystrix');
                                    window.open(turbinHystrixUrl);
                                    break;
                                default:
                                    //
                            }
                        });
                    $('#serviceInstanceList').append(itemEle).append('<hr>');
                }
            } else {
                alert(data.msg);
            }
        }
    });
}

function toExampleSys(){
    var url = 'http://192.168.223.1:9980/example-ui/index';
    var paramsJsonObj = new Object();
    //这里应该拿到当前点击的服务的元数据
    paramsJsonObj.metadata = JSON.stringify(getMetadata());
    //认证数据是统一共用的
    var authData = getAuthData();
    //删除没用的数据
    delete authData.jwt.jti;
    delete authData.jwt.refresh_token;
    delete authData.jwt.scope;
    delete authData.jwt.token_type;
    delete authData.user.password;
    delete authData.user.activated;
    paramsJsonObj.authData = JSON.stringify(authData);
    //window.open(url + '?access_token=' + getAccessToken());
    formSubmitWithAccessToken(paramsJsonObj, url, 'post', '_blank');
}

