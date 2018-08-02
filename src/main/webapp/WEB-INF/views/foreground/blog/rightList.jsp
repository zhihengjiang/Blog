<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--<div class="data_list">--%>
    <%--<div class="data_list_title">--%>
        <%--<img--%>
            <%--src="${pageContext.request.contextPath}/static/images/user_icon.png" />--%>
        <%--博主信息--%>
    <%--</div>--%>
    <%--<div class="user_image">--%>
        <%--<img--%>
            <%--src="${pageContext.request.contextPath}/static/userImages/${blogger.imagename}" />--%>
    <%--</div>--%>
    <%--<div class="nickName"><strong><font style="color: #EE6A50">昵称：${blogger.nickname }</font></strong></div>--%>
    <%--&lt;%&ndash; <div class="visitNum">访问量：6666</div>  &ndash;%&gt;--%>
    <%--<div class="userSign">『<strong><font style="color: #EE6A50">${blogger.sign }</font></strong>』</div>--%>
<%--</div>--%>

<div class="data_list">
    <div class="data_list_title">
        <i class="fa fa-layer-group"></i>
        文章分类
    </div>
    <div class="datas">
        <ul>
            <c:forEach items="${blogTypeList}" var="blogType">
                <li><span> <a
                        href="${pageContext.request.contextPath}/index.html?typeId=${blogType.id }">${blogType.typeName }（${blogType.blogCount }）
                    </a></span></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <i class="fa fa-calendar-alt"></i>
        文章存档
    </div>
    <div class="datas">
        <ul>
            <c:forEach items="${blogTimeList }" var="blog">
                <li><span> <a
                        href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blog.releaseDateStr }">${blog.releaseDateStr }（${blog.blogCount }）
                    </a></span></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <i class="fa fa-link"></i>
        友情链接
    </div>
    <div class="datas">
        <ul>
            <c:forEach items="${linkList }" var="link">
                <li><span><a href="${link.linkurl }" target="_blank">${link.linkname }</a></span></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="data_list">
    <div class="data_list_title">
        <i class="fa fa-share-square"></i>
        分享到
    </div>
    <div class="datas">
        <ul>
            <div style="text-align:left;padding-top:20px;">
                <div class="bdsharebuttonbox">
                    <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                    <a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
                    <a href="#" class="bds_more" data-cmd="more"></a>
                </div>
            </div>
        </ul>
    </div>
</div>
