/**
 * 解析REST请求返回结果
 * Created by DarKnight on 2015/9/7.
 */
ResultData = {
    getSuccessDataInfo: function(resultData) {
        if(!!resultData) {
            resultData = eval('(' + resultData + ')');
            if(!!resultData['status'] && resultData['status'] == 'success') {

                return resultData['dataInfo'];
            }
        }

        return;
    },
    getSuccessStatus: function(resultData) {
        if(!!resultData) {
            resultData = eval('(' + resultData + ')');
            if(!!resultData['status'] && resultData['status'] == 'success') {

                return true;
            }
        }

        return false;
    }
}