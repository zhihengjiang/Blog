<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>写博客页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/material/easyui.css">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/demo/demo.css">
<script type="text/javascript"
    src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/jquery.min.js"></script>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/locale/easyui-lang-zh_CN.js"></script>
<link href="${pageContext.request.contextPath}/static/editormd/css/editormd.css" rel="stylesheet" type="text/css" />
<%--<script type="text/javascript" charset="utf-8"--%>
        <%--src="https://cdn.bootcss.com/jquery/3.2.0/jquery.min.js"></script>--%>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/static/editormd/editormd.js"></script>
<script type="text/javascript" charset="utf-8"
        src="${pageContext.request.contextPath}/static/editormd/editormd.amd.js"></script>


</head>

<body style="margin: 10px; font-family: microsoft yahei">

    <div id="p" class="easyui-panel" title="编写博客" style="padding: 10px;">

        <table cellspacing="20px">
            <tr>
                <td width="80px"><label for="title">博客标题：</label></td>
                <td><input type="text" id="title" name="title" style="width:400px" /></td>
            </tr>
            <tr>
                <td><label for="blogTypeId">所属类别：</label></td>
                <td><select id="blogTypeId" class="easyui-combobox"
                    name="blogType.id" style="width:154px">
                        <option value="">请选择博客类别...</option>
                        <c:forEach items="${blogTypeList }" var="blogType">
                            <option value="${blogType.id }">${blogType.typeName }</option>
                        </c:forEach>
                </select></td>
            </tr>
        </table>
        <div class="editormd form-control" style="margin: 0px;" id="test-editormd">
            <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc" id="editormd"></textarea>
            <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
            <!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
            <textarea class="editormd-html-textarea" name="editorhtml" id="editorhtml"></textarea>
        </div>

        <table>
            <tr>
                <td>关键字：</td>
                <td><input type="text" id="keyWord" name="keyWord"
                    style="width:400px" />&nbsp;&nbsp;&nbsp;多个关键字的话请用空格隔开</td>
            </tr>
            <tr>
                <td></td>
                <td><a href="javascript:submitData()" class="easyui-linkbutton"
                    data-options="iconCls:'icon-submit'">发布博客</a></td>
            </tr>
        </table>
    </div>




    <!-- 实例化编辑器 -->
    <script type="text/javascript">

        var testEditor;
        testEditor=$(function() {
            editormd("test-editormd", {
                width   : "100%",
                height  : 640,
                codeFold : true,
                syncScrolling : "single",
                //你的lib目录的路径
                path    : "<%=request.getContextPath()%>/static/editormd/lib/",
                imageUpload: false,//关闭图片上传功能
                emoji: false,
                taskList: true,
                tocm: true,         // Using [TOCM]
                tex: true,                   // 开启科学公式TeX语言支持，默认关闭
                flowChart: true,             // 开启流程图支持，默认关闭
                sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
                saveHTMLToTextarea : true
            });
        });

    </script>
    <script type="text/javascript">
        function submitData() {
            const title = $("#title").val();
            const blogTypeId = $("#blogTypeId").combobox("getValue");
            const content = $("#editorhtml").val();
            const keyWord = $("#keyWord").val();
            const contentNoTag = $("#editormd").val();
            const summary = contentNoTag.substr(0, 155);

            if (title == null || title == '') {
                $.messager.alert("系统提示", "请输入标题！");
            } else if (blogTypeId == null || blogTypeId == '') {
                $.messager.alert("系统提示", "请选择博客类型！");
            } else if (content == null || content == '') {
                $.messager.alert("系统提示", "请编辑博客内容！");
            } else {
                $.post("${pageContext.request.contextPath}/admin/blog/save.do",
                        {
                            'title' : title,
                            'blogType.id' : blogTypeId,
                            'content' : content,
                            'summary' : summary,
                            'keyWord' : keyWord,
                            'contentNoTag' : contentNoTag
                        }, function(result) {
                            if (result.success) {
                                $.messager.alert("系统提示", "博客发布成功！");
                                clearValues();
                            } else {
                                $.messager.alert("系统提示", "博客发布失败！");
                            }
                        }, "json");
            }
        }
        function clearValues() {
            $("#title").val("");
            $("#blogTypeId").combobox("setValue", "");
            $("#editormd").val("");
            $("#editorhtml").val("");
            $("#keyWord").val("");
        }
    </script>
</body>
</html>
