
$(function () {
    //fill当前租户下的服务实例
    fillServiceInstance();
});

/**
 * fill当前租户下的服务实例
 * <!-- -->
 */
function fillServiceInstance() {
    var colorArray = ['panel-success','panel-info','panel-warning','panel-primary','panel-danger'];
    $.ajax({
        type: "GET",
        url: getContextPath() + '/index/serviceInstance',
        success: function (data) {
            /**
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
                for(var i=0; i<data.payload.length; i++){
                    var item = data.payload[i];
                    var color = colorArray[i%5];
                    var $div = $('  <div class="col-md-6">' +
                                 '      <div class="panel '+ color +'">' +
                                 '          <div class="panel-heading">' +
                                 '              <h3 class="panel-title">'+ item.name +'</h3>' +
                                 '          </div>' +
                                 '          <div class="panel-body">' +
                                 '              <p>服务名称：<a href="javascript:void(0);" class="serverName">'+ item.name +'（'+ item.appName +'）</a></p>' +
                                 '              <p>服务类型：'+  item.appType +'</p>' +
                                 '              <p>版本：'+ item.version +'</p>' +
                                 '              <p>描述：'+ item.description +'</p>' +
                                 '              <p>实例ID：'+ item.instanceId +'</p>' +
                                 '              <p>租户：'+ item.tenant +'</p>' +
                                 '              <p>权重：'+ item.weight +'</p>' +
                                 '              <p>或标签：'+ item.labelOr +'</p>' +
                                 '              <p>与标签：'+ item.labelAnd +'</p>' +
                                 '          </div>' +
                                 '      </div>' +
                                 '  </div>');
                    $div.find('.serverName').data({
                        url:item.url,
                        contextPath:item.contextPath,
                        appType:item.appType
                    });
                    $div.find('.serverName').click(function () {
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
                        }
                    });

                    $('.serviceInstanceList').append($div)
                }
            } else {
                error(data.msg);
            }
        }
    });
}
