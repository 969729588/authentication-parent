/**
 * Created by Huarf on 2020/3/6.
 */
//初始化实例元数据，配置数据等，即获取那些在后端配置的数据，特别是yml中配置的，将数据存入sessionStorage中
initMetadata();

//如果token不存在或者无效，则跳转到登录页
if(!testAccessToken()){
    window.location.href = getContextPath() + "/login";
}

/**
 * 打印认证数据、实例元数据
 */
function printData() {
    console.log(getAuthData());
    console.log(getAccessToken());
    console.log(getMetadata());
    console.log(getContextPath());
}

function reloadIndex() {
    //Accept: text/html, */*; q=0.01
    //Content-Type: text/html
    $('.content-frame').load(getContextPath() + '/index/index.html',function () {
        console.log('加载页面完成事件');
    });
}

$(function () {
    //显示首页内容
    $('.content-frame').load(getContextPath() + '/index/index.html');
});

