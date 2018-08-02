<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>插入字符 / 设置和获取光标位置 / 设置、获取和替换选中的文本 - Editor.md examples</title>
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/material/easyui.css">
        <link href="${pageContext.request.contextPath}/static/editormd/css/editormd.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/icon.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/demo/demo.css">
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/static/editormd/examples/js/jquery.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/locale/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" charset="utf-8"
                src="${pageContext.request.contextPath}/static/editormd/editormd.js"></script>
    </head>
    <body>
        <%--<div id="layout">--%>
            <%--<header>--%>
                <%--<h1>插入字符 / 设置和获取光标位置 / 设置、获取和替换选中的文本</h1> --%>
                <%--<p>Insert value & Set / Get cursor & Set / Get / Replace selection</p> --%>
                <%--<br/>--%>
                <%--<div class="btns" style="margin:0">--%>
                    <%--<button id="btn1">设置光标位置 (line:1, ch:2)</button>--%>
                    <%--<button id="btn2">获取光标位置</button>--%>
                    <%--<button id="btn3">设置选中文本 ({line:1, ch:0}, {line:5, ch:100})</button>--%>
                    <%--<button id="btn4">获取选中文本</button>--%>
                    <%--<button id="btn5">替换选中文本为 "$$$$$$$$$"</button>--%>
                    <%--<button id="btn6">在光标处插入 "????"</button>                    --%>
                <%--</div>--%>
            <%--</header>--%>
            <%--<div id="test-editormd">  --%>
                <%--<textarea style="display:none;"></textarea>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div id="p" class="easyui-panel" title="修改博客" style="padding: 10px;">

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
                <textarea style="display: none;">${blog.contentNoTag}</textarea>
                <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
                <!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
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
                           data-options="iconCls:'icon-submit'">确认修改</a></td>
                </tr>
            </table>
        </div>
        
        <script type="text/javascript">
			var testEditormd;
			var html;

            $(function() {
                testEditormd = editormd("test-editormd", {
                    width: "90%",
                    height: 640,
                    path : '${pageContext.request.contextPath}/static/editormd/lib/'
                });
                html = testEditormd.getHTML();


                $("#btn1").click(function(){
                    testEditormd.setCursor({line:1, ch:2});
                    testEditormd.focus();
                });
                
                $("#btn2").click(function(){
                    console.log("getCursor =>", testEditormd.getCursor());
                    testEditormd.focus();
                });
                
                $("#btn3").click(function(){
                    testEditormd.setSelection({line:1, ch:0}, {line:5, ch:100});
                    testEditormd.focus();
                });
                
                $("#btn4").click(function(){
                    console.log("getSelection =>", testEditormd.getSelection());
                    testEditormd.focus();
                });
                
                $("#btn5").click(function(){
                    testEditormd.replaceSelection("$$$$$$$$$");
                    testEditormd.focus();
                });
                
                $("#btn6").click(function(){
                    testEditormd.insertValue("&lt;p&gt;请在输入框内贴入你需要转换的HTML代码&lt;/p&gt;\n" +
            "&lt;p&gt;HTML转换工具，可以将HTML代码转换为HTML转义字符串&lt;/p&gt;\n" +
            "&lt;p&gt;直接将你所要用程序输出的大串HTML代码贴到输入框中，即可一键生成&lt;/p&gt;\n" +
            "&lt;p&gt;如果您觉得好用，别忘了推荐给朋友！&lt;/p&gt;");

                    testEditormd.focus();

                });

            });
        </script>
    </body>
</html>
