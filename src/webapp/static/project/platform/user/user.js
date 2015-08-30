// 定义构造函数
function User(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var userExtend = {
    initDialog: function(option) {
        var defaultOption = {

            width: 350
        }

        defaultOption = $.extend({}, defaultOption, option);

        var dialogObj = Base.prototype.initDialog.call(this, 'dialogBlock', defaultOption);
        return dialogObj;
    },
    add: function(dialogTitle) {
        var dialogPath = '/platform/account/user/dialogPage';
        var urlPath = '/platform/account/user/save';
        Base.prototype.add.call(this, dialogTitle, dialogPath, urlPath);
    },
    edit: function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            var dialogPath = '/platform/account/user/dialogPage?userId='+row['id'];
            var urlPath = '/platform/account/user/update';
            Base.prototype.edit.call(this, dialogTitle, dialogPath, urlPath);
        }
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除用户','是否确认删除选中用户？', '/platform/account/user/delete');
    },
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            accountName: $('#'+searchKeyMap['accountName']).val(),
            phoneNum: $('#'+searchKeyMap['phoneNum']).val()
        });
    }
}

// 合并父类方法与拓展
User.prototype = $.extend({}, new Base(), userExtend);
