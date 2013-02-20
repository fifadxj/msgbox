<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>
  <title><spring:message code="message.view.title" /></title>
</head>
<body>
<div class="content-box">
  <div class="content-box-header">
    <h3><spring:message code="message.view.title" /></h3>
  </div>
  <div class="content-box-content">
      <table>
          <tr>
            <td><spring:message code="message.category.name" /></td>
          </tr>
          <tr>
            <td>${message.category.name}</td>
          </tr>
          <tr>
            <td><spring:message code="message.rank" /></td>
          </tr>
          <tr>
            <td>${message.rank}</td>
          </tr>
          <tr>
            <td><spring:message code="message.source" /></td>
          </tr>
          <tr>
             <td>${message.source}</td>
          </tr>
          <tr>
            <td><spring:message code="message.content" /></td>
          </tr>
          <tr>
            <td><pre><str:wordWrap width ="100" delimiter="<br/>">${message.content}</str:wordWrap></pre></td>
          </tr>
      </table>
  </div>
</div>

<spring:url value="/web/message/delete.html?id=${message.id}" var="delete" htmlEscape="true"/>
<spring:url value="/web/message/editForm.html?id=${message.id}" var="edit" htmlEscape="true"/>
<spring:url value="/web/message/list.html" var="list" htmlEscape="true"/>
<spring:url value="/web/message/disable.html?id=${message.id}" var="disable" htmlEscape="true"/>
<spring:url value="/web/message/enable.html?id=${message.id}" var="enable" htmlEscape="true"/>
<spring:url value="/web/message/top.html?id=${message.id}" var="top" htmlEscape="true"/>
<spring:url value="/web/message/untop.html?id=${message.id}" var="untop" htmlEscape="true"/>
<c:if test="${!message.disabled}"><a class="button" href="${edit}"><spring:message code="button.edit" /></a></c:if>

<c:if test="${!message.disabled}">
<c:choose>
  <c:when test="${message.top}">
  <a class="button" href="${untop}"><spring:message code="button.untop" /></a>
  </c:when>
  <c:otherwise>
  <a class="button" href="${top}"><spring:message code="button.top" /></a>
  </c:otherwise>
</c:choose>
</c:if>

<c:choose>
  <c:when test="${!message.disabled}">
  <a class="button" href="${disable}"><spring:message code="button.disable" /></a>
  </c:when>
  <c:otherwise>
  <a class="button" href="${enable}"><spring:message code="button.enable" /></a>
  </c:otherwise>
</c:choose>

<a class="button popupConfirm" href="${delete}"><spring:message code="button.delete" /></a>
<a class="button" href="${list}"><spring:message code="button.back" /></a>
</body>
</html>