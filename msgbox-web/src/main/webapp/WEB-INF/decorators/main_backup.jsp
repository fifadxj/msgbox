<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <link href="<%=request.getContextPath()%>/styles/main.css" rel="stylesheet" type="text/css">
    <title>
      <decorator:title default="Default Title" />
    </title>
    <decorator:head />
  </head>
  <body>
    <div id="main">
      <div id="header">
        <jsp:include page="header.jsp" />
      </div>
      <div id="leftNav">
        <page:applyDecorator name="block" page="/WEB-INF/decorators/leftNav.jsp" />
        <!--<jsp:include page="leftNav.jsp" />-->
        <div style="clear:both"></div>
      </div>
      
      <div id="content">
        <decorator:body />
      </div>
      <div id="rightNav">
        <page:applyDecorator name="block" page="/WEB-INF/decorators/leftNav.jsp" />
        <!--<jsp:include page="leftNav.jsp" />-->
        <div style="clear:both"></div>
      </div>
      <div style="clear:both"></div>
      <div id="footer">
        <jsp:include page="footer.jsp" />
      </div>
    </div>
  </body>
</html>
