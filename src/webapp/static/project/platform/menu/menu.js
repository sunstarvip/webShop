/**
 * 定义构造函数
 * @param ctx WEB运行环境
 * @param tableId 展示列表数据的表格ID
 * @param dialogId 增改实体的窗口ID
 * @param formId 增改实体的表单ID
 * @param treeId 关联展示的左侧菜单树ID
 * @constructor
 */
function Menu(ctx, tableId, dialogId, formId, treeId) {
    this.treeId = treeId;
    Base.call(this, ctx, tableId, dialogId, formId);
}

/**
 * 定义重载方法与拓展方法
 * @type {{add: Function, save: Function, edit: Function, delete: Function, doSearch: Function}}
 */
var menuExtend = {
    initDialog: function(option) {

        var defaultOption = {
            width: 320
        }

        defaultOption = $.extend({}, defaultOption, option);

        var dialogObj = Base.prototype.initDialog.call(this, 'dialogBlock', defaultOption);
        return dialogObj;
    },

    /**
     * 重载新增方法
     * @param dialogTitle 增改菜单的弹窗标题
     */
    add: function(dialogTitle) {
        var dialogPath = '/platform/system/menu/dialogPage';
        var urlPath = '/platform/system/menu/save';
        Base.prototype.add.call(this, dialogTitle, dialogPath, urlPath);
    },
    /**
     * 重载保存方法
     * @param treeId 菜单保存后需要重新加载的菜单树ID
     */
    save: function(obj) {
        var dialogId = obj.dialogId;
        var tableId = obj.tableId;
        var formId = obj.formId;
        var treeId = obj.treeId;
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
                    // 重载权限信息列表
                    $('#'+tableId).datagrid('reload');
                    // 重载左侧系统菜单树
                    parent.$('#'+treeId).tree('reload');
                }else {
                    $.messager.show({
                        title: '错误信息',
                        msg: result
                    });
                }
            }
        });
    },
    /**
     * 重载编辑方法
     * @param dialogTitle 增改菜单的弹窗标题
     */
    edit: function(dialogTitle) {
        var row = $('#'+this.tableId).datagrid('getSelected');
        if(row) {
            var dialogPath = '/platform/system/menu/dialogPage?menuId='+row['id'];
            var urlPath = '/platform/system/menu/update';
            Base.prototype.edit.call(this, dialogTitle, dialogPath, urlPath);
        }

    },
    /**
     * 重载删除方法
     */
    delete: function() {
        var tableId = this.tableId;
        var ctx = this.ctx;
        var treeId = this.treeId;
        var row = $('#'+tableId).datagrid('getSelected');
        if (row){
            parent.$.messager.confirm('删除系统菜单', '是否确认删除选中系统菜单？',
                function(e) {
                    if(e) {
                        $.post(ctx + '/platform/system/menu/delete',
                            {id: row.id},
                            function(result) {
                                var result = eval('('+result+')');
                                if (result['status']=='success') {
                                    // 重载角色信息列表
                                    $('#'+tableId).datagrid('reload');
                                    // 重载左侧系统菜单树
                                    parent.$('#'+treeId).tree('reload');
                                }else {
                                    // 展示错误信息
                                    $.messager.show({
                                        title: '错误信息',
                                        msg: result
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
     * 自定义查询方法
     * @param searchKeyMap 保存查询条件的Map
     */
    doSearch: function(searchKeyMap) {
        $('#'+this.tableId).datagrid('load',{
            name: $('#'+searchKeyMap['name']).val()
        });
    }
}

/**
 * 合并父类方法与重载或拓展的方法
 */
Menu.prototype = $.extend({}, new Base(), menuExtend);
