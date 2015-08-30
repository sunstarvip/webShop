/**
 * 定义构造函数
 * @param ctx WEB运行环境
 * @param tableId 展示列表数据的表格ID
 * @param dialogId 增改实体的窗口ID
 * @param formId 增改实体的表单ID
 * @constructor
 */
function Base(ctx, tableId, dialogId, formId) {
    this.ctx = ctx;
    this.tableId = tableId;
    this.dialogId = dialogId;
    this.formId = formId;
    this.urlPath = '';
};

/**
 * 定义通用CURD方法
 * @type {{add: Function, save: Function, edit: Function, delete: Function, cancel: Function}}
 */
Base.prototype = {
    initDialog: function(targetId, option) {

        var style = {
            padding: '10px 20px'
        }

        var defaultOption = {
            width: 320,
            style: style,
            closed: true,
            cache: false
        }

        defaultOption = $.extend({}, defaultOption, option);

        var dialogStr = '<div id="' + this.dialogId + '" ></div>';
        parent.$('#' + targetId).append(dialogStr);
        var dialogObj = parent.$('#' + this.dialogId).dialog(defaultOption);
        return dialogObj;
    },
    openDialog: function(option) {
        var dialogObj = null;
        if(!!parent.$('#' + this.dialogId)) {
            var defaultOption = parent.$('#' + this.dialogId).dialog('options');
            defaultOption = $.extend({}, defaultOption, option);
            dialogObj = parent.$('#' + this.dialogId).dialog(defaultOption).dialog('open');
        }else {
            dialogObj = this.initDialog(option);
        }
        return dialogObj;
    },
    /**
     * 通用新增方法
     * @param dialogTitle 增改实体的弹窗标题
     * @param urlPath 保存实体时的请求路径
     */
    add: function(dialogTitle, dialogPath, urlPath) {
        //parent.$('#' + this.dialogId).dialog('setTitle', dialogTitle).dialog('open');
        this.openDialog({href: this.ctx + dialogPath});
        parent.$('#' + this.dialogId).dialog('setTitle', dialogTitle);
        this.urlPath = this.ctx + urlPath;
        //parent.$('#'+this.formId).form('clear');
    },
    /**
     * 通用保存方法
     */
    save: function(obj) {
        var dialogId = obj.dialogId;
        var tableId = obj.tableId;
        var formId = obj.formId;
        var urlPath = obj.urlPath;
        parent.$('#' + formId).form('submit',{
            url: urlPath,
            onSubmit: function(){
                return parent.$(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result['status']=='success'){
                    // 清除表单数据
                    parent.$('#'+formId).form('clear');
                    // 关闭对话框
                    parent.$('#'+dialogId).dialog('close');
                    // 重载数据列表
                    $('#'+tableId).datagrid('reload');
                }else {
                    $.messager.show({
                        title: '错误信息',
                        msg: result['msgInfo']
                    });
                }
            }
        });
    },
    /**
     * 通用编辑方法
     * @param dialogTitle 增改实体的弹窗标题
     * @param urlPath 更新实体时的请求路径
     */
    edit: function(dialogTitle, dialogPath, urlPath) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            //parent.$('#' + this.dialogId).dialog('setTitle', dialogTitle).dialog('refresh', this.ctx + dialogPath);
            this.openDialog({href: this.ctx + dialogPath});
            parent.$('#' + this.dialogId).dialog('setTitle', dialogTitle);
            this.urlPath = this.ctx + urlPath;
        }
    },
    /**
     * 通用删除方法
     * @param title 删除确认时的弹窗标题
     * @param content 删除确认时的弹窗提示语
     * @param urlPath 删除实体时的请求路径
     */
    delete: function(title, content, urlPath) {
        var tableId = this.tableId;
        var ctx = this.ctx;
        var row = $('#'+tableId).datagrid('getSelected');
        if (row){
            parent.$.messager.confirm(title, content,
                function(e) {
                    if(e) {
                        $.post(ctx + urlPath,
                            {id: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if (result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+tableId).datagrid('reload');
                                }else {
                                    // 展示错误信息
                                    $.messager.show({
                                        title: '错误信息',
                                        msg: result['msgInfo']
                                    });
                                }
                            },
                            'text');
                    }
                }
            );
        }
    },
    /**
     * 通用取消方法
     * 用于放弃增改实体操作时，关闭弹窗
     */
    cancel: function(obj) {
        parent.$('#' + obj.dialogId).dialog('close');
    }
}