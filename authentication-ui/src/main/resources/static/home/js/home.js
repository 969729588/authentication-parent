/**
 * Created by Huarf on 2020/3/6.
 */
//初始化服务元数据，配置数据等，即获取那些在后端配置的数据，特别是yml中配置的，将数据存入sessionStorage中
initMetadata();

//如果token不存在或者无效，则跳转到登录页
if(!testAccessToken()){
    window.location.href = getContextPath() + "/login";
}

/**
 * 打印认证数据、服务元数据
 */
function printData() {
    console.log(getAuthData());
    console.log(getAccessToken());
    console.log(getMetadata());
    console.log(getContextPath());
}

/**
 * 加载ContentFrame
 */
function loadContentFrame() {
    $('.content-frame').load(getContextPath() + '/home/index.html');
}

$(function () {

    //显示右侧内容
    //$('body').load(getContextPath() + '/home/home.html');

});

