var layoutframe = function () {


    return {

        //main function
        init: function () {
            $('body').layout({
                applyDefaultStyles: false, //是否使用默认样式
                north__closable: false,//可以被关闭
                north__resizable: false,//可以改变大小
                north__size: 42,//pane的大小
                north__spacing_open: 0,//打开时的边距0像素
                north__spacing_close: 0,//关闭时的边距0像素
                center__spacing_open: 0,//打开时的边距0像素
                center__spacing_close: 0,//关闭时的边距0像素
                west__closable: false,//可以被关闭
                west__resizable: false, //可以改变大小
                west__spacing_open: 0,//打开时的边距0像素
                west__spacing_close: 0,//关闭时的边距0像素
                west__size: 35
            });
        }



    };

}();