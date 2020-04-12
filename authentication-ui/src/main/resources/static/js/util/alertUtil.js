/**
 * Created by Huarf on 2020/4/12.
 * 封装Bootstrap的警告框
 */

/**
 * success
 * @param text
 * @param timeInSeconds 停留时间，默认停留3s，0表示永远停留，不会自动关闭
 */
function success(text, timeInSeconds) {
    successAndInfo('success', text, timeInSeconds)
}

/**
 * info
 * @param text
 * @param timeInSeconds 停留时间，默认停留3s，0表示永远停留，不会自动关闭
 */
function info(text, timeInSeconds) {
    successAndInfo('info', text, timeInSeconds)
}

/**
 * success 和 info
 * @param type success、info
 * @param text
 * @param timeInSeconds 停留时间，默认停留3s，0表示永远停留，不会自动关闭
 */
function successAndInfo(type, text, timeInSeconds) {
    var $div = $('  <div class="alert alert-'+ type +' alert-dismissible alert-center" role="alert">' +
                 '      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                        text +
                 '  </div>');

    //添加元素
    $('body').append($div);

    //0表示永远停留，不会自动关闭
    if(timeInSeconds == 0){
        return;
    }

    //没传入timeInSeconds，默认停留3s
    if(typeof(timeInSeconds)=="undefined"){
        timeInSeconds = 3;
    }

    setTimeout(function () {
        $div.find('button').click();
    }, (timeInSeconds*1000));
}


/**
 * warn
 * @param text
 * @param timeInSeconds 停留时间，默认永远停留，不会自动关闭
 */
function warn(text, timeInSeconds) {
    warningAndDanger('warning', text, timeInSeconds)
}

/**
 * error
 * @param text
 * @param timeInSeconds 停留时间，默认永远停留，不会自动关闭
 */
function error(text, timeInSeconds) {
    warningAndDanger('danger', text, timeInSeconds)
}

/**
 * warning 和 danger
 * @param type warning、danger
 * @param text
 * @param timeInSeconds 停留时间，默认永远停留，不会自动关闭
 */
function warningAndDanger(type, text, timeInSeconds) {
    var $div = $('  <div class="alert alert-'+ type +' alert-dismissible alert-center" role="alert">' +
        '      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        text +
        '  </div>');

    //添加元素
    $('body').append($div);

    //没传入timeInSeconds，默认永远停留，不会自动关闭
    if(typeof(timeInSeconds)=="undefined"){
        return;
    }

    setTimeout(function () {
        $div.find('button').click();
    }, (timeInSeconds*1000));
}

