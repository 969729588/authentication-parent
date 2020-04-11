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


$(function () {
    //初始化左侧菜单
    initLeftMenu();

    //默认显示左侧菜单中的第一个
    $('.leftMenu ul').children(":first").click();

    //登出
    logout();
});

/**
 * 初始化左侧菜单
 */
function initLeftMenu() {
    //菜单数据
    var data = [
        {"id":"1", "name":"服务实例", "url":"/instance/instance.html"}/*,
        {"id":"2", "name":"用户", "url":"/user/user.html"}*/
    ];

    for(var i in data){
        var item = data[i];
        var $li = $('<li><a href="javascript:void(0);">'+ item.name +'</a></li>');
        $li.data('id', item.id);
        $li.data('url', item.url);
        $li.click(function () {
            //点击选中菜单
            $('.leftMenu').find('li').removeClass('active');
            $(this).addClass('active');
            
            //加载页面
            var url = $(this).data('url');
            loadMain(url);
        });

        //添加元素
        $('.leftMenu').find('ul').append($li);
    }
}

/**
 * 加载右侧内容
 * @param url
 * @param paramObj json参数
 */
function loadMain(url, paramObj) {
    if(isValid(paramObj)){
        $('.main').load(getContextPath() + url, paramObj);
    }else{
        $('.main').load(getContextPath() + url);
    }
}

/**
 * 登出
 */
function logout() {
    $('.logout').click(function () {
        //获取contextPath
        var contextPath = getContextPath();
        //清空数据
        sessionStorage.removeItem(metadataKey);
        sessionStorage.removeItem(authDataKey);
        //跳转到登录页
        window.location.href = contextPath + '/login';
    });
}

