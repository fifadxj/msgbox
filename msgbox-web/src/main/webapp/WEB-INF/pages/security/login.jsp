<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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

    <title><spring:message code="security.login.title" /></title>
    
    <script>
      $(document).ready(function(){
        $("#_form").validationEngine({promptPosition : "topRight", scroll: true});
      });
    </script>
  </head>
  <body id="login">
	<div id="login-wrapper">
	  <div id="login-top">
	    <h2 style="color:white;font-size:25px;"><spring:message code="security.login.title" /></h2>
	    <!-- <img id="logo" src="resources/images/logo.jpg" width="50px" height="50px" /> -->
	    
	   </div>
	  <!-- End #logn-top -->
	  <div id="login-content">
	    <form:form action="${contextPath}/web/security/loginSubmit.html" method="post" id="_form" modelAttribute="user">
	      <input name="goto" value="${param.goto}" type="hidden" />
	      <div class="notification">
          <c:if test="${errorcode != null}">
            <img src="resources/images/icons/cross_circle.png" /><span class="error"><spring:message code="${errorcode}" /></span>
          </c:if>
          </div>
	      <p>
	        <label for="passcode"><spring:message code="security.login.passcode" /></label>
	        <form:input class="validate[required] text-input" type="text" path="passcode" id="passcode" />
	      </p>
	      
	      <p>
	        <span style="padding-right:10px;"><spring:message code="language" /></span>
            <form:radiobutton path="language" id="zh" value="zh" />
            <label for="zh"><spring:message code="language.chinese" /></label>
            <form:radiobutton path="language" id="en" value="en" />
            <label for="en"><spring:message code="language.chinese" /></label>
          </p>

	      <p>
	        <input class="button" type="submit" value="<spring:message code="login" />" />
	        <input class="button" type="reset" value="<spring:message code="reset" />" />
	        
	        <!-- <spring:url value="/web/common/language.html" var="language" htmlEscape="true"/>
			 <a class="button" style="font-size:5px;" href="${language}&language=en&referer=/security/loginForm.html">
			  <spring:message code="language.english" />
			</a>
			<a class="button" style="font-size:8px;" href="${language}&language=zh&referer=/security/loginForm.html">
			  <spring:message code="language.chinese" />
			</a> -->
	      </p>
	    </form:form>
	  </div>
	  <!-- End #login-content -->
	</div>
	<!-- End #login-wrapper -->
  </body>
</html>
