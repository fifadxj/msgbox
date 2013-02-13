<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    
    <link rel="stylesheet" href="${contextPath}/resources/styles/reset.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${contextPath}/resources/styles/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${contextPath}/resources/styles/validationEngine.jquery.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${contextPath}/resources/styles/jquery-ui.css" type="text/css" media="screen" />

    <script type="text/javascript" src="${contextPath}/resources/scripts/i18n_${localeSuffix}.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/jquery.wysiwyg.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/validationEngine/jquery.validationEngine.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/validationEngine/jquery.validationEngine-zh_CN.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/jquery-ui.js"></script>
    
    <script type="text/javascript" src="${contextPath}/resources/scripts/jquery.easy-confirm-dialog.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/scripts/msgbox.js"></script>

	
    <title>
      <decorator:title default="Default Title" />
    </title>
    <decorator:head />
  </head>
  <body>
    <div id="body-wrapper">
      
      <div id="sidebar">
        <%-- <page:applyDecorator name="block" page="/WEB-INF/decorators/leftNav.jsp" /> --%>
        <jsp:include page="leftNav.jsp" />
      </div>
      
      <div id="main-content">
        <div id="header">
          <jsp:include page="header.jsp" />
        </div>
        
        <decorator:body />
        
        <div id="footer">
	      <jsp:include page="footer.jsp" />
	    </div>
      </div>
      
    </div>
  </body>
</html>
