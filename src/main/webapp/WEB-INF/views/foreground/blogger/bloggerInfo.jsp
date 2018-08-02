<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/fontawesome5.2.0/css/all.min.css">
<div class="data_list">
    <div class="data_list_title">
        <i class="fa fa-info"></i>&nbsp;关于博主
    </div>
    <div class="datas">
            <ul>
                <li>
                    ${blogger.profile }
                </li>
                <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:10px;" />
            </ul>
    </div>
</div>