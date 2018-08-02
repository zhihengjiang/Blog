<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>写博客页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/material/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/themes/icon.css">
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/demo/demo.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.4/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript">
    function submitData() {

        $("#fm").form("submit",{
            url: "${pageContext.request.contextPath}/admin/blogger/save.do",
            onSubmit: function() {
                // var profile = UE.getEditor("profile").getContent();
                // $("#pf").val(profile); //将UEditor编辑器中的内容放到隐藏域中提交到后台
                return $(this).form("validate");
            }, //进行验证，通过才让提交
            success: function(result) {
                result = JSON.parse(result); //将json格式的result转换成js对象
                if(result.success) {
                    $.messager.alert("系统提示", "博主信息更新成功");
                } else {
                    $.messager.alert("系统提示", "博主信息更新失败");
                    return;
                }
            }
        });
    }
</script>
</head>

<body style="margin: 10px; font-family: microsoft yahei">

    <div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px;">
        <form id="fm" method="post" enctype="multipart/form-data">
            <table cellspacing="20px">
                <tr>
                    <td><label for="username">用户名：</label></td>
                    <td>

                        <input type="hidden" id="id" name="id" value="${blogger.id }"/>
                        <input type="text" id="username" name="username" style="width:200px" readonly="readonly" value="${blogger.username }"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="nickname">昵称：</label></td>
                    <td>
                        <input type="text" id="nickname" name="nickname" style="width:200px"
                            class="easyui-validatebox" value="${blogger.nickname}" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="sign">个性签名：</label></td>
                    <td>

                        <input type="text" id="sign" name="sign" style="width:400px"
                            class="easyui-validatebox" value="${blogger.sign}" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="imageFile">个人头像：</label></td>
                    <td>

                        <input type="file" id="imageFile" name="imageFile"/>
                    </td>
                </tr>
                <tr>
                    <td align="center"><label for="profile">个人简介：</label></td>
                    <td>

                    <textarea name="profile" id="profile" cols="30" rows="10" class="easyui-validatebox" ></textarea>
                    </td>

                </tr>
                <tr>
                <td></td>
                <td><a href="javascript:submitData()" class="easyui-linkbutton"
                    data-options="iconCls:'icon-submit'">提交</a></td>
            </tr>
            </table>
        </form>
    </div>

<%-- 实例化编辑器 --%>
<%--<script type="text/javascript">--%>
    <%--var ue = UE.getEditor('profile');--%>
    <%--ue.addListener("ready", function(){--%>
        <%--//通过UE自己封装的ajax请求数据--%>
        <%--UE.ajax.request("${pageContext.request.contextPath}/admin/blogger/findBlogger.do",--%>
                <%--{--%>
                    <%--method: "post",--%>
                    <%--async: false,--%>
                    <%--data: {},--%>
                    <%--onsuccess: function(result) { //--%>
                        <%--result = eval("(" + result.responseText + ")");--%>
                        <%--$("#nickname").val(result.nickname);--%>
                        <%--$("#sign").val(result.sign);--%>
                        <%--UE.getEditor('profile').setContent(result.profile);--%>
                    <%--}--%>
                <%--});--%>
    <%--});--%>
<%--</script>--%>


</body>
</html>
