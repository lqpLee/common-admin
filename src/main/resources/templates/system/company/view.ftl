<div class="row">
    <div class="col-md-12">
        <div class="box-body  no-padding">
            <table class="table table-striped">
                <tr>
                    <td>公司名称：</td>
                    <td style="width: 90%">${bean.name}</td>
                </tr>
                <tr>
                    <td>公司简称：</td>
                    <td>${bean.simpleName}</td>
                </tr>
                <tr>
                    <td>公司编码：</td>
                    <td>${bean.code}</td>
                </tr>
                <tr>
                    <td>创建时间：</td>
                    <td>${bean.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                </tr>
                <tr>
                    <td>更新时间：</td>
                <#if bean.updateTime??>
                    <td>${bean.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                </#if>
                </tr>
            </table>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>