/**
 * Created by Huarf on 2020/3/15.
 */

function list() {
    $.ajax({
        type: "POST",
        data: {
            paramList: ["aa", "bb", "cc"]
        },
        url: getContextPath() + '/testParam/list',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function array() {
    $.ajax({
        type: "POST",
        data: {
            paramArray: ["aa", "bb", "cc"]
        },
        url: getContextPath() + '/testParam/array',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function listMap() {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify([person1, person2]),
        url: getContextPath() + '/testParam/listMap',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function listPojo() {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify([person1, person2]),
        url: getContextPath() + '/testParam/listPojo',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function complexPojo2() {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify({
            id: "1",
            firstName : "张三",
            birth : "2020-03-16 01:46:00",
            score : 12.11,
            user: {
                id: "2",
                username: "李四",
                mobile: "12345678901"
            }
        }),
        url: getContextPath() + '/testParam/complexPojo2',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function complexPojo1() {
    $.ajax({
        type: "POST",
        data: {
            "id": "1",
            "firstName" : "张三",
            "birth" : "2020-03-16 01:46:00",
            "score" : 12.11,
            "user.id": "2",
            "user.username": "李四",
            "user.mobile": "12345678901"
        },
        url: getContextPath() + '/testParam/complexPojo1',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function map2() {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify(person),
        url: getContextPath() + '/testParam/map2',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function map1() {
    $.ajax({
        type: "POST",
        data: person,
        url: getContextPath() + '/testParam/map1',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function pojo2() {
    $.ajax({
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify(person),
        url: getContextPath() + '/testParam/pojo2',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function pojo1() {
    $.ajax({
        type: "POST",
        data: person,
        url: getContextPath() + '/testParam/pojo1',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function pathVariable() {
    $.ajax({
        type: "GET",
        url: getContextPath() + '/testParam/pathVariable/11',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function basicTypes() {
    $.ajax({
        type: "POST",
        data: {
            name : "张三",
            count : 12,
            age : 1323
        },
        url: getContextPath() + '/testParam/basicTypes',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function forward() {
    $.ajax({
        type: "GET",
        url: getContextPath() + '/testParam/forward',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function redirect() {
    $.ajax({
        type: "POST",
        url: getContextPath() + '/testParam/redirect',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function cookieValue() {
    $.ajax({
        type: "POST",
        url: getContextPath() + '/testParam/cookieValue',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function requestHeader() {
    $.ajax({
        type: "POST",
        url: getContextPath() + '/testParam/requestHeader',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

function servlet() {
    $.ajax({
        type: "POST",
        url: getContextPath() + '/testParam/servlet',
        success: function (data) {
            if (data.code == Constant.returnSuccess) {
                console.log(data.payload);
            } else {
                alert(data.msg);
            }
        }
    });
}

var person2 = {
    id : "2",
    firstName : "张三2",
    birth : "2020-03-16 22:22:22",
    score : 22.22,
    user: {
        id: "2",
        username: "李四2",
        mobile: "12345678901"
    }
};

var person1 = {
    id : "1",
    firstName : "张三1",
    birth : "2020-03-16 11:11:11",
    score : 11.11,
    user: {
        id: "1",
        username: "李四1",
        mobile: "12345678901"
    }
};

var person = {
    id : "1",
    firstName : "张三",
    birth : "2020-03-16 01:46:00",
    score : 12.11
};