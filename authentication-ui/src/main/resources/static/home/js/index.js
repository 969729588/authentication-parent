$(function () {

});

function toOtherSys(){
    var url = 'http://192.168.223.1:9980/oa-ui/testReadindex';
    var paramsJsonObj = new Object();
    paramsJsonObj.metadata = JSON.stringify(getMetadata());
    paramsJsonObj.authData = JSON.stringify(getAuthData());
    //window.open(url + '?access_token=' + getAccessToken());
    formSubmitWithAccessToken(paramsJsonObj, url, 'post', '_blank');
}

