// 定义构造函数
function Role(ctx, tableId, dialogId, formId) {
    Base.call(this, ctx, tableId, dialogId, formId);
}

// 定义重载方法与拓展方法
var roleExtend = {
    initDialog: function(option) {

        var defaultOption = {

            width: 350
        }

        defaultOption = $.extend({}, defaultOption, option);

        var dialogObj = Base.prototype.initDialog.call(this, 'dialogBlock', defaultOption);
        return dialogObj;
    },
    add: function(dialogTitle) {
        var dialogPath = '/platform/account/role/dialogPage';
        var urlPath = '/platform/account/role/save';
        Base.prototype.add.call(this, dialogTitle, dialogPath, urlPath);
    },
    edit: function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            var dialogPath = '/platform/account/role/dialogPage?roleId='+row['id'];
            var urlPath = '/platform/account/role/update';
            Base.prototype.edit.call(this, dialogTitle, dialogPath, urlPath);
        }
    },
    delete: function() {
        Base.prototype.delete.call(this, '删除角色','是否确认删除选中角色？', '/platform/account/role/delete');
    },
    doSearch: function(searchKey) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKey).val()
        });
    }
}

// 合并父类方法与拓展
Role.prototype = $.extend({}, new Base(), roleExtend);

