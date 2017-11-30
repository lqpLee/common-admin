<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">公司管理</h3>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <a class="btn btn-sm btn-primary" target="modal" modal="lg"
                           href="/company/add">添加</a>
                    </div>
                </div>
                <table id="company_tab" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>公司名称</th>
                        <th>公司简称</th>
                        <th>公司编码</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" type="text/css" href="/other/jquery-easyui-1.5.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/other/jquery-easyui-1.5.3/themes/icon.css">
<script type="text/javascript" src="/other/jquery-easyui-1.5.3/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
    var company_tab;
    $(function () {
        var No = 0;
        company_tab = $('#company_tab').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {"url": "/company/page", "type": "post"},
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "simpleName"},
                {"data": "code"},
                {"data": null},
                {"data": "createTime"},
                {"data": null}
            ],
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    render: function (data) {
                        No = No + 1;
                        return No;
                    }
                },
                {
                    targets: 4,
                    data: null,
                    render: function (data) {
                        if (data.isDelete == 0) {
                            return "可用";
                        }
                        if (data.isDelete == 1) {
                            return "不可用";
                        }
                        return "未知状态";
                    }
                },
                {
                    "targets": -1,
                    "data": null,
                    "render": function (data) {
                        var btn = "";
                        btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/company/view/' + data.id + '">查看</a> &nbsp;';
                        if (isNull(data.role) || 'super' != data.role.value) {
                            btn += '<@shiro.hasPermission name="company/edit">'
                                    + '<a class="btn btn-xs btn-info" onclick="securityToListAjax();" data-title="修改" target="modal" modal="lg" href="/company/edit/'+ data.id+ '">修改</a> &nbsp;'
                                    +'</@shiro.hasPermission>'
                                    + '<@shiro.hasPermission name="company/delete">'
                                    + '<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/company/delete/'+ data.id + '">删除</a>'
                                    +'</@shiro.hasPermission>';
                        }
                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });
    });
    function reloadCompanyList() {
        reloadTable(company_tab);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>