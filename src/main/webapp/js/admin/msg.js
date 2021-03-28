$(function () {
    $("#jqGrid").jqGrid({
        url: '../msg/list',
        datatype: "json",
        colModel: [
            {
                label: 'id',
                name: 'id',
                index: 'id',
                width: 50,
                key: true,
                hidden: true
            },
            {
                label: '标题',
                name: 'title',
                index: 'title',
                width: 80,
                formatter: function (v, a, r) {
                    return "<a onclick='vm.info(\"" + r.id + "\")'>" + v + " </a>"
                }
            },
            {
                label: '接收人',
                name: 'sysUserEntity.username',
                index: 'sysUserEntity.username',
                width: 80
            },
            {
                label: '发送时间',
                name: 'gmttime',
                index: 'gmtTime',
                width: 80
            },
            {
                label: '阅读状态',
                name: 'state',
                index: 'state',
                width: 80
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    new AjaxUpload('#upload', {
        action: '../file/upload',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            // if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
            //     alert('只支持jpg、png、gif格式的图片！');
            //     return false;
            // }
        },
        onComplete: function (file, r) {
            if (r.code == 0) {
                vm.msg.url = r.url;
            } else {
                alert(r.msg);
            }
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            content: ''
        },
        showAdd: false,
        showInfo: false,
        showList: true,
        title: null,
        msg: {},
        list: []
    },
    created: function () {
        $.getJSON("../sys/user/list2?type=3", function (r) {
            vm.list = r.list;
        })
    },
    methods: {
        query: function () {
            vm.reload();
        }
        ,
        add: function () {
            vm.showAdd = true;
            vm.showList = false;
            vm.title = "新增";
            vm.msg = {url: ''};
        }
        ,
        update: function (event) {
            var id =
                getSelectedRow();
            if (id == null
            ) {
                return;
            }

            vm.showAdd = true;
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        }
        ,
        saveOrUpdate: function (event) {
            var url = vm
                .msg.id ==
            null ? "../msg/save" : "../msg/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.msg),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        }
        ,
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../msg/delete",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        }
        ,
        getInfo: function (id) {
            $.get("../msg/info/" + id, function (r) {
                vm.msg = r.msg;
            });
        }
        ,
        info: function (id) {
            if (isNaN(id)) {
                id
                    = getSelectedRow();
                if (id == null
                ) {
                    return;
                }
            }
            vm.showAdd = false;
            vm.showList = false;
            vm.showInfo = true;
            vm.title = "详情";

            vm.getInfo(id)
        }
        ,
        reload: function (event) {
            vm.showList = true;
            vm.showInfo = false;
            vm.showAdd = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        }
    }
});