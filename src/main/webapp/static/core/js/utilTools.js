/**
 * Created by songqingxin on 2015/7/23.
 */
/**
 * 处理转义字符
 * @param str 待处理的转义字符
 * @returns {string|XML|*}
 */
function transferrString(str) {
    str = str.replace(/\\/g, '\\\\');
    //str = str.replace(/'/g, '&qpos;');
    //str = str.replace(/"/g, '&quot;');
    str = str.replace(/'/g, '\\\'');
    str = str.replace(/"/g, '\\\"');
    //str = str.replace(/\\/g, '\\\\');
    //str = str.replace(/\b/g, '\\b');
    str = str.replace(/\r/g, '\\r');
    str = str.replace(/\n/g, '\\n');
    //str = str.replace(/\f/g, '\\f');
    //str = str.replace(/\t/g, '\\t');

    return str;
}

/**
 * 创建动态form表单并提交
 * @param action form表单的action
 * @param method 提交方法，默认为 post
 * @param target 页面刷新方法，默认为 _self
 * @param formObj form表单JSON对象，
 *   key为表单的name，value为表单的值
 */
function formSubmit(action, method, target, formObj) {
    // 动态创建Form表单
    var form = $('<form></form>');
    // 设置提交路径
    action = action ? action : '/';
    form.attr('action', action);
    // 设置请求方式
    method = method ? method : 'post';
    form.attr('method', method);
    // 设置页面刷新方式
    target = target ? target : '_self';
    form.attr('target', target);

    var formCol;
    for(var objName in formObj) {
        // 创建提交参数
        formCol = $('<input type="hidden" name="' + objName + '" />');
        formCol.attr('value', formObj[objName]);

        // 添加至表单
        form.append(formCol);
    }

    // 提交表单
    form.appendTo(document.body).submit();
}

/**
 * 提交同步GET请求，并返回请求结果
 * @param url 请求URL
 * @param data 请求参数，JSON格式
 */
function getSync() {
    var url = null;
    var data = null;
    if(arguments.length == 1) {
        url = arguments[0];
    }else if(arguments.length == 2) {
        url = arguments[0];
        data = arguments[1];
    }
    $.ajax({
        type : "GET",
        async: false,
        url: url,
        data: data,
        success: function(resultData) {
            return resultData;
        }
    });
}

/**
 * 提交同步POST请求，并返回请求结果
 * @param url 请求URL
 * @param data 请求参数，JSON格式
 */
function postSync() {
    var url = null;
    var data = null;
    if(arguments.length == 1) {
        url = arguments[0];
    }else if(arguments.length == 2) {
        url = arguments[0];
        data = arguments[1];
    }
    $.ajax({
        type : "POST",
        async: false,
        url: url,
        data: data,
        success: function(resultData) {
            return resultData;
        }
    });
}

/**
 * 验证传入的IP字段是否符合规范
 * @param ipStr IP字段
 * @returns {boolean}
 */
function isIp(ipStr) {
    var ipArray = ipStr.split('.');

    if(!!ipArray && ipArray.length == 4) {
        for(var i in ipArray) {
            if(ipArray[i] < 0 || ipArray[i] > 255) {
                return false;
            }else if(ipArray[i] > 0 && ipArray[i] <= 255) {
                if(ipArray[i].indexOf("0") == 0) {
                    return false;
                }
            }
        }
    }else {
        return false;
    }

    return true;
}

/**
 * 验证传入的PORT端口字段是否符合规范
 * @param portStr PORT端口字段
 * @returns {boolean}
 */
function isPort(portStr) {
    if(!!portStr) {
        if(portStr >= 0 && portStr <= 65535) {
            if(portStr != 0) {
                if(portStr.indexOf("0") == 0) {
                    return false;
                }
            }
        }else {
            return false;
        }
    }else {
        return false;
    }

    return true;
}

function clickPageForwardBtn(btnSelector, url) {
    $(btnSelector).on('click', function() {
        window.location.href = url;
    });
}