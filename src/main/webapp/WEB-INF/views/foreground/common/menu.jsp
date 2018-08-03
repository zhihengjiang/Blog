<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
    function checkData() {

        var q = document.getElementById("q").value.trim();
        if(q == null || q == "") {
            alert("请输入您要查询的关键字！");
            return false;
        } else {
            return true;
        }
    }
</script>

<div class="col-md-12 col-sm-12">
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container-fluid">
          <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">切换导航</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
              <a href="#" class="navbar-brand nav-title">导航</a>
          </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
              <li><a class="navbar-brand" href="${pageContext.request.contextPath}/index.html"
                     onclick="changeClass(this)">博客首页</a></li>
              <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/aboutme.html"
                   onclick="changeClass(this)">关于博主</a></li>
              <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/myalbum.html"
                   onclick="changeClass(this)">CV</a></li>
              <li><a class="navbar-brand" href="${pageContext.request.contextPath}/blogger/resource.html"
                   onclick="changeClass(this)">工具</a></li>
              <li><a class="navbar-brand" href="${pageContext.request.contextPath}/admin/blogger/main.do" target="blank"><i
                      class="fa fa-user-alt"></i>
              </a></li>
          </ul>
          <form action="${pageContext.request.contextPath}/blog/search.do" class="navbar-form navbar-right"
                role="search" method="post" onsubmit="return checkData()">
              <div class="form-group">
                  <input type="text" id="q" name="q" value="${q}" class="form-control" placeholder="请输入要查询的关键字">
              </div>
            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i> </button>
          </form>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
</div>

