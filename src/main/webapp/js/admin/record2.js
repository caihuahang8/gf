$(function () {
    $("#jqGrid").jqGrid({
        url: '../record/list',
        datatype: "json",
        colModel: [
            {
                label: 'id',
                name: 'id',
                index: 'id',
                width: 50,
                key: true,
                hidden: true,
                formatter: function (v, a, r) {
                    return "<a onclick='vm.info(\"" + r.id + "\")'>" + v + " </a>"
                }
            },
            {
                label: '作业',
                name: 'zuoyeEntity.name',
                index: 'zuoyeEntity.name',
                width: 80
            },

            {
                label: '作业内容',
                name: 'content',
                index: 'content',
                width: 80
            },
            {
                label: '分数',
                name: 'score',
                index: 'score',
                width: 80
            },
            {
                label: '评价',
                name: 'comment',
                index: 'comment',
                width: 80
            },
            {
                label: '添加时间',
                name: 'gmttime',
                index: 'gmtTime',
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
                vm.record.url = r.url;
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
            zuoye: ''
        },
        showAdd: false,
        showInfo: false,
        showList: true,
        title: null,
        users: [],


        zuoyes: [],


        users: [],


        record: {},

        zuoye: {}
    },
    created: function () {

        $.getJSON("../zuoye/list2", function (r) {
            vm.zuoyes = r.list;
        })

        $.getJSON("../sys/user/list2", function (r) {
            vm.users = r.list;
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
            vm.record = {url: ''};
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
                .record.id ==
            null ? "../record/save" : "../record/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.record),
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
                    url: "../record/delete",
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
            $.get("../record/info/" + id, function (r) {
                vm.record = r.record;
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
        },
        download: function (id) {
            console.log(id)
            // $.get("../zuoye/download/" + id, function (r) {
            // });
            window.location.href="http://localhost:8080/gf_war/zuoye/download/"+ id
            // // var id = getSelectedRow();
            // console.log(1)
            // http://127.0.0.1:8080/gf_war/zuoye/download/21
            // var url =  "localhost:8080/gf_war/zuoye/download/21"
            // // var url =  "localhost:8080/gf_war/zuoye/download/21"
            // window.open(url);
        }
    }
});