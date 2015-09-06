// 定义构造函数
function Permission(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var permissionExtend = {
    initDialog: function(option) {
        var defaultOption = {

            width: 350
        }

        defaultOption = $.extend({}, defaultOption, option);

        var dialogObj = Base.prototype.initDialog.call(this, 'dialogBlock', defaultOption);
        return dialogObj;
    },
    add: function(dialogTitle) {
        var dialogPath = '/platform/account/permission/dialogPage';
        var urlPath = '/platform/account/permission/save';
        Base.prototype.add.call(this, dialogTitle, dialogPath, urlPath);
    },
    edit: function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            var dialogPath = '/platform/account/permission/dialogPage?permissionId='+row['id'];
            var urlPath = '/platform/account/permission/update';
            Base.prototype.edit.call(this, dialogTitle, dialogPath, urlPath);
        }
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除权限', '是否确认删除选中权限？', '/platform/account/permission/delete');
    },
    doSearch: function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    }
}

// 合并父类方法与拓展
Permission.prototype = $.extend({}, new Base(), permissionExtend);