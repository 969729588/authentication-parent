$(function () {

});

function toExampleSys(){
    var url = 'http://192.168.223.1:9980/example-ui/index';
    var paramsJsonObj = new Object();
    paramsJsonObj.metadata = JSON.stringify(getMetadata());
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

