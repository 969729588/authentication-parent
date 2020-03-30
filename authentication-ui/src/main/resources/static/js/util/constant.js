/**
 * Created by Huarf on 2020/3/6.
 * 全局的，常量，配置等
 */

var Constant = {
    returnSuccess : 0,//请求接口返回的code如果是0，表示成功，
    ajaxTimeout : 10000//ajax请求超时，单位ms
};

//应用类型
var AppType = {
    ui : "UI",
    service : "SERVICE",
    eureka : "EUREKA",
    auth : "AUTH",
    admin : "ADMIN",
    turbin : "TURBINE"
};

//认证数据存储key
var authDataKey = "authDataKey";
/**
 * 保存认证数据，
 * sessionStorage是安全的，同一个浏览器的不同窗口sessionStorage都是不能共享的。
 * @param inputAuthData
 */
function saveAuthData(inputAuthData){
    sessionStorage.setItem(authDataKey, JSON.stringify(inputAuthData));
}

/**
 * 获取认证数据
 * @returns {{jwt: {access_token: null, token_type: null, refresh_token: null, expires_in: null, born_time_millis: null, scope: null, jti: null}, user: {id: null, username: null, truename: null, mobile: null, email: null, password: null, activated: null}}}
 */
function getAuthData(){
    var authData = {
        jwt : {//登录接口返回的jwt
            "access_token": null,
            "token_type": null,
            "refresh_token": null,
            "expires_in": null,
            "born_time_millis": null,
            "scope": null,
            "jti": null
        },
        user : {//登录接口返回的user
            "id": null,
            "username": null,
            "truename": null,
            "mobile": null,
            "email": null,
            "password": null,
            "activated": null
        }
    };

    authData = sessionStorage.getItem(authDataKey);
    if(isValid(authData)){
        authData = JSON.parse(authData);
    }
    return authData;
}

/**
 * 获取access_token
 * @returns {{jwt: {access_token: null, token_type: null, refresh_token: null, expires_in: null, born_time_millis: null, scope: null, jti: null}, user: {id: null, username: null, truename: null, mobile: null, email: null, password: null, activated: null}}}
 */
function getAccessToken(){
    var authData = getAuthData();
    if(isValid(authData)){
        return authData.jwt.access_token;
    }else{
        return null;
    }
}

//实例元数据存储key
var metadataKey = 'metadataKey';

/**
 * 初始化实例元数据，配置数据等，即获取那些在后端配置的数据，特别是yml中配置的，
 * 将数据存入sessionStorage中
 */
function initMetadata(){
    $.ajax({
        async: false,
        type: "POST",
        url: window.location.href,
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                sessionStorage.setItem(metadataKey, JSON.stringify(data.payload));
            } else {
                alert(data.msg);
            }
        }
    });
}

/**
 * 保存实例元数据
 * @param inputAuthData
 */
function saveMetadata(inputMetadata){
    sessionStorage.setItem(metadataKey, JSON.stringify(inputMetadata));
}

/**
 * 获取实例元数据
 */
function getMetadata() {
    var metadata = {
        "contextPath": null,
        "loginSbaServerUser": null,
        "loginSbaServerPassword": null
    };
    var metadata = sessionStorage.getItem(metadataKey);
    if(isValid(metadata)){
        metadata = JSON.parse(metadata);
    }
    return metadata;
}

/**
 * 获取contextPath
 * @returns {null}
 */
function getContextPath() {
    var metadata = getMetadata();
    if(isValid(metadata)){
        return metadata.contextPath;
    }else{
        return null;
    }
}



