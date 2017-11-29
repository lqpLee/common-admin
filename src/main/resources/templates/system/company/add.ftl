<style>
    ul.ztree {
        margin-top: 10px;
        border: 1px solid #617775;
        background: #f0f6e4;
        width: 220px;
        height: 360px;
        overflow-y: scroll;
        overflow-x: auto;
    }
</style>
<div class="row">
    <div class="col-md-12">
        <form id="companyAddForm">
            <div class="modal-body">
                <div class="form-group">
                    <label class="" id="nameLabel">公司名称</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="输入公司名称...">
                </div>
                <div class="form-group">
                    <label class="" id="urlLabel">公司简称</label>
                    <input type="text" class="form-control" name="simpleName" id="simpleName" placeholder="输入公司简称...">
                </div>
                <div class="form-group">
                    <label id="codeLabel">公司编码</label>
                    <input type="text" class="form-control" name="code" id="code" placeholder="输入公司编码...">
                </div>
                <div class="form-group">
                    <label id="sortNameLabel">排序</label>
                    <input type="number" class="form-control" name="sort" id="sort" placeholder="输入排序...">
                </div>
            </div>
        </form>
    </div>
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
    </div>

</div>
<div class="modal-footer">
    <div class="pull-right">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
        <button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存
        </button>
    </div>
</div>
<script type="text/javascript" src="other/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="other/zTree/js/jquery.ztree.excheck.js"></script>
<link rel="stylesheet" type="text/css" href="other/zTree/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript">
    function securitySave() {
        $("span").remove(".errorClass");
        $("br").remove(".errorClass");
        var status = 1;
        if ($("#name").val() == "") {
            $("#nameLabel").prepend('<span class="errorClass" style="color:red">*公司名称不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#simpleName").val() == "") {
            $("#urlLabel").prepend('<span class="errorClass" style="color:red">*公司简称不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#code").val() == "") {
            $("#codeLabel").prepend('<span class="errorClass" style="color:red">*公司编号不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if ($("#sort").val() == "") {
            $("#sortNameLabel").prepend('<span class="errorClass" style="color:red">*顺序编号不能为空</span><br class="errorClass"/>');
            status = 0;
        }
        if (status == 0) {
            return false;
        } else {
            $.ajax({
                url: '/company/save',
                type: 'post',
                dataType: 'text',
                data: $("#companyAddForm").serialize(),
                success: function (data) {
                    var json = JSON.parse(data);
                    if (json.status) {
                        $("#lgModal").modal('hide');
                        alertMsg("添加成功", "success");
                        reloadCompanyList();
                    } else {
                        alertMsg("添加失败:" + json.msg, "success");
                    }
                }
            });
        }
    }
</script>