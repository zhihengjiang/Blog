# 基于Spring,Spring MVC,Mybatis的个人博客系统

## 项目简介：

- 这是一个简单的个人博客网站的实现，采用java语言，整个项目使用[Spring](https://spring.io/)框架,简单地说，这就是一个关于Java开发练手项目。
- web端框架使用Spring Web MVC，这是从一开始就被包含在Spring框架的建立在Servlet API原始Web，俗称“Spring MVC”。
- 持久层框架使用[mybatis](http://blog.mybatis.org/)，mybatis由ibatis演化而来，虽然相比于提供了全自动的对象–关系映射机制且几乎不需要写任何的SQL语句(根据配置好的映射关系文件自动生成对应的 SQL 并调用 JDBC
  接口加以执行)的Hibernate框架，mybatis需要自己编写SQL语句，但这也使mybatis具有更大的灵活性。数据库使用mysql
- 此项目使用JSP作为视图模版，JSP技术虽然已经落后，但对于不熟悉前端开发的人来说只要懂一点html和CSS技术就能轻松上手。
- 使用[Bootstrap3](https://www.jeasyui.com/) UI框架实现博客的分页显示，博客分类，文章归类显示。
- 使用[EasyUI](https://www.jeasyui.com/)实现后台对博客、博客类别、用户评论、博主信息的管理。
- 使用[editormd](https://pandao.github.io/editor.md/)作为编辑器，实现博客内容的编辑。
- 使用[Font Awesome](https://fontawesome.com)字体图标
  
  ## 博客网站[Demo](htttp://zhihengjiang.com)
  
  ## ToDo列表
- [ ] 将视图模板从JSP改为Thymeleaf
- [ ] 将后台登录验证工具从shiro改为Spring Security

