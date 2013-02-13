<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>

  <style>
  BODY {
    background-color: #bfbfbf;
  }
  </style>
  <title>500</title>
</head>
  <body>
  <spring:url value="/web/home.html" var="home" htmlEscape="true"/>
  <spring:url value="/resources/images/500.png" var="_500" htmlEscape="true"/>

    <a href="${home}">
      <p align="center"><img src="${_500}" border="0" style="position: relative; top: 100px;" /></p>
    </a>
  </body>
</html>