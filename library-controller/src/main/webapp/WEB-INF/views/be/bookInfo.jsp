<%--
  User: Sam
  Date: 2019/12/5
  Time: 9:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="/static/js/common/vue.min.js"></script>
    <script type="text/javascript" src="/static/js/common/jquery-3.3.1.min.js"></script>
    <style>
        .numColor{
            background-color: yellow;
        }
    </style>
</head>
<body>
    <div id="app">
        <input type="button" @click="bookInfoDeleteBatch" value="删除"/><br>
        <a href="/admin/bookInfo/download">下载</a>
        <form id="uploadForm" enctype="multipart/form-data">
            <input type="file" id="uploadExcel"/>
            <input type="button" @click="upload" value="点击上传"/>
        </form>
        <table>
            <tr>
                <td><input type="checkbox" ref="checkAll" @click="checkAll($event)"/></td>
                <td>isbn</td>
                <td>名称</td>
                <td>作者</td>
                <td>类型</td>
                <td>出版社</td>
                <td>价格</td>
                <td>库存册数</td>
                <td>现存册数</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            <tr v-for="bookInfo in pageInfos.list">
                <td><input type="checkbox" ref="checkItem" @click="checkItemClick($event)"/></td>
                <td>{{bookInfo.isbn}}</td>
                <td>{{bookInfo.bookName}}</td>
                <td>{{bookInfo.bookAuthor}}</td>
                <td>{{bookInfo.bookType}}</td>
                <td>{{bookInfo.bookPublish}}</td>
                <td>{{bookInfo.bookPrice}}</td>
                <td>{{bookInfo.tmamount}}</td>
                <td>{{bookInfo.bookStock}}</td>
                <td v-if="bookInfo.bookState == 0">入库</td>
                <td v-else="bookInfo.bookState == 1">出库</td>
                <td><input type="button" value="删除"  @click="bookInfoDeleteById(bookInfo.id)"/></td>
            </tr>
        </table>

        <input type="button" @click="pageNum=1" value="首页">
        <input type="button" @click="pageNum=pageInfos.isFirstPage?pageInfos.pages:pageInfos.prePage" value="上一页">
        <input type="button" @click="pageNum=num" :value="num" v-for="num in pageInfos.navigatepageNums" :class="{'numColor':pageNum==num}">
        <input type="button" @click="pageNum=pageInfos.isLastPage?1:pageInfos.nextPage" value="下一页">
        <input type="button" @click="pageNum=pageInfos.pages" value="尾页">


        <br>

        <form id="bookInfoForm" >
            isbn：<input type="text" name="isbn" value="11111" />
            编号：<input type="text" name="bookName" value="123"/>
            名称：<input type="text" name="bookAuthor" value="测试书籍"/>
            作者：<input type="text" name="bookType" value="admin"/>
            类型：<input type="text" name="bookPublish" value="学习"/>
            价格：<input type="text" name="bookPrice" value="12"/>
            库存册数：<input type="text" name="tmamount"  />
            状态：
            <select name="bookState">
                <option value="0" selected="selected">入库</option>
                <option value="1">出库</option>
            </select>
            <input type="button" @click="bookInfoInsert" value="提交"/>
        </form>
    </div>

    <script type="text/javascript">
        var vm = new Vue({
            el:"#app",
            data:{
                pageInfos:'',
                pageNum:1,
                pageSize:4
            },
			mounted:function() {
                this.getAll(this.pageNum,this.pageSize);
            },methods:{
                getAll:function(pageNum,pageSize) {
                    var _this = this;
                    $.ajax({
                        url: "/admin/bookInfo/getAll",
                        type: "post",
                        dataType: "json",
                        data: {"pageNum": pageNum, "pageSize": pageSize},
                        success: function (result) {
                            vm.pageInfos = result;
                        }
                    });//查询图书信息
                }//getAll
                ,bookInfoInsert:function () {
                    $.ajax({
                        url: "/admin/bookInfo/bookInfoInsert",
                        type: "post",
                        dataType: "json",
                        data: $("#bookInfoForm").serialize(),
                        success: function (result) {
                            if(result.code=="200"){
                                alert(result.msg)
                                vm.getAll(vm.pageNum,vm.pageSize)
                            }else{
                                alert(result.msg)
                            }
                        }
                    });//查询图书信息

                }//bookInfoInsert,
                ,bookInfoDeleteById:function (bookId) {
                    $.ajax({
                        url: "/admin/bookInfo/bookInfoDeleteById",
                        type: "get",
                        dataType: "json",
                        data:{"id":bookId},
                        success: function (result) {
                            if(result.code=="200"){
                                alert(result.msg)
                                vm.getAll(vm.pageNum,vm.pageSize)
                            }else{
                                alert(result.msg)
                            }
                        }
                    });//删除图书信息
                }//bookInfoDeleteById
                ,bookInfoUpdate:function () {
                    
                }//bookInfoUpdate
                ,upload:function () {
                    var formData = new FormData($("#uploadForm")[0]);
                    formData.append('excel',$('#uploadExcel').get(0).files[0]);
                    console.log(formData);
                    $.ajax({
                        url: "/admin/bookInfo/upload",
                        data:formData,
                        type:"post",
                        cache: false,
                        contentType: false,
                        processData: false,
                        success: function(result) {
                            if(result.code=="200"){
                                alert(result.msg)
                                vm.getAll(vm.pageNum,vm.pageSize)
                            }else{
                                alert(result.msg)
                            }
                        },
                        error: function() {
                            alert("上传失败")
                        }
                    })//上传图片
                }//upload
                ,checkAll:function (e) {
                   var checkObj = vm.$refs.checkItem;
                   for (var i = 0;i<checkObj.length; i++) {
                        checkObj[i].checked = e.target.checked;
                   }
                }
                ,checkItemClick:function (e) {
                    var checkObj = vm.$refs.checkItem;
                    vm.$refs.checkAll.checked = true;
                    for (var i = 0;i<checkObj.length; i++) {
                        var flag = checkObj[i].checked;
                        if(!flag){
                            vm.$refs.checkAll.checked=false
                        }
                    }
                }//checkItemClick
                ,bookInfoDeleteBatch:function () {

                    var _this = this;
                    console.log(_this.getChecked())

                    $.ajax({
                        url: "/admin/bookInfo/bookInfoDeleteBatch",
                        type: "post",
                        dataType: "json",
                        traditional:true,
                        data:{"isbns":_this.getChecked()},
                        success: function (result) {
                            if(result.code=="200"){
                                alert(result.msg)
                                vm.getAll(vm.pageNum,vm.pageSize)
                            }else{
                                alert(result.msg)
                            }
                        }
                    });
                }
            },watch:{
                pageNum:function (val) {
                    this.getAll(val,vm.pageSize)
                }
            }//watch
        });
        Vue.prototype.getChecked = function (){
            var checkObj = vm.$refs.checkItem ;
            var vals = [];
            for (var i = 0;i<checkObj.length; i++) {
                if(checkObj[i].checked) {
                    var val = checkObj[i].parentElement.nextElementSibling.textContent;
                    vals[i]=val;
                }
            }//
            return vals;
        }
    </script>
</body>
</html>
