<%--
  User: Sam
  Date: 2019/12/3
  Time: 20:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/static/css/common/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrapStyle/bootstrapStyle.css" rel="stylesheet">
    <script type="text/javascript" src="/static/js/common/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/static/js/common/bootstrap.min.js"></script>
    <script type="text/javascript" src="/static/js/common/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="/static/js/common/ztree/jquery.ztree.excheck.js"></script>
</head>
<body>
    <ul id="treeDemo" class="ztree"></ul>
    <button id="nodeIdSubmit" >提交</button>
    <script type="text/javascript">
        $("#nodeIdSubmit").click(function () {
            console.log("zTreeOnclick:"+zTreeOnClick())
        });
        var setting = {
            async: {
                enable: true,//开启异步加载
                url:"/admin/nodeInfo/ztree",
                autoParam:["id"],//加载所需要的传递的值
                type: "post"
            },
            callback: {
                beforeAsync: beforeAsync,
                onAsyncSuccess: onAsyncSuccess,
                onClick:zTreeOnClick
            },check:{
                enable:true
            }
        };

        //获取所有的选中节点的id值
        function zTreeOnClick(event, treeId, treeNode){
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            console.log("1:"+treeObj)
            var nodes = treeObj.getCheckedNodes(true);
            var id = [];
            for (var i = 0; i < nodes.length; i++) {
                id[i]= nodes[i].id; //第三部
            }
            return id;
        }

        var  curAsyncCount = 0, goAsync = false;
        //每次异步加载都加一
        function beforeAsync() {
            curAsyncCount++;
        }

        //加载成功之后调用
        function onAsyncSuccess(event, treeId, treeNode, msg) {
            curAsyncCount--;
            expandNodes(treeNode.children);
        }
        function expandNodes(nodes) {
            if (!nodes) return;
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            for (var i=0, l=nodes.length; i<l; i++) {
                zTree.expandNode(nodes[i], true, false, false);//展开节点就会调用后台查询子节点
                if (nodes[i].isParent && nodes[i].zAsync) {
                    expandNodes(nodes[i].children);//递归
                } else {
                    goAsync = true;
                }
            }
        }
        //--------------延迟加载代码---------------
        function expandAll() {
            if (!check()) {
                return;
            }
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            expandNodes(zTree.getNodes());
            if (!goAsync) {
                curStatus = "";
            }
        }
        function check() {
            if (curAsyncCount > 0) {
                return false;
            }
            return true;
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting);
            setTimeout(function(){
                expandAll("treeDemo");
            },500);//延迟加载
        });
    </script>
</body>
</html>
