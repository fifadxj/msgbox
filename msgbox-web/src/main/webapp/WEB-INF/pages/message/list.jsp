<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>
  <title><spring:message code="message.list.title" /></title>
</head>
<body>
<div class="content-box">

  <div class="content-box-header">
     <spring:url value="/web/message/list.html" var="list" htmlEscape="true"/>
    <form:form action="${list}" method="get" id="_form" modelAttribute="condition">
      <form:select path="categoryId" style="width:300px">
        <form:option value="-1"><spring:message code="message.selectCategory" /></form:option>
        <form:options items="${categories}" itemValue="id" itemLabel="name"/>
      </form:select>
      <form:select path="disabled" style="width:100px">
        <form:option value="false"><spring:message code="message.enabled" /></form:option>
        <form:option value="true"><spring:message code="message.disabled" /></form:option>
      </form:select>
      <a class="button" onclick="$('#_form').submit();"><spring:message code="button.search" /></a>
    </form:form>
  </div>
  
  <div class="content-box-header">
    <h3><spring:message code="message.list.topedMessages" /></h3>
  </div>
  <div class="content-box-content">
      <table>
        <thead>
          <tr>
            <th style="width:20%"><spring:message code="message.title" /></th>
            <th style="width:30%"><spring:message code="message.content" /></th>
            <th style="width:15%"><spring:message code="message.category.name" /></th>
            <th style="width:10%"><spring:message code="message.rank" /></th>
            <th style="width:10%"><spring:message code="message.source" /></th>
            <th style="width:15%"><spring:message code="message.operation" /></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="message" items="${topedMessages}">
	      <spring:url value="/web/message/view.html?id=${message.id}" var="view" htmlEscape="true"/>
	      <spring:url value="/web/message/editForm.html?id=${message.id}" var="edit" htmlEscape="true"/>
	      <spring:url value="/web/message/delete.html?id=${message.id}" var="delete" htmlEscape="true"/>
	      <spring:url value="/web/message/disable.html?id=${message.id}" var="disable" htmlEscape="true"/>
	      <spring:url value="/web/message/enable.html?id=${message.id}" var="enable" htmlEscape="true"/>
	      <spring:url value="/web/message/top.html?id=${message.id}" var="top" htmlEscape="true"/>
	      <spring:url value="/web/message/untop.html?id=${message.id}" var="untop" htmlEscape="true"/>
	      <tr>
	        <td><a href="${view}"><str:truncateNicely upper="10">${message.title}</str:truncateNicely></a></td>
	        <td><a href="${view}"><str:truncateNicely upper="20">${message.content}</str:truncateNicely></a></td>
	        <td>${message.category.name}</td>
	        <td>${message.rank}</td>
	        <td>${message.source}</td>
	        <td> 
	          <a href="${view}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/details.png" title="<spring:message code="button.view" />" /></a>
              <c:if test="${!message.disabled}"><a href="${edit}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/edit.png" title="<spring:message code="button.edit" />" /></a></c:if>
              <a href="${delete}" class="popupConfirm"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/delete.png" title="<spring:message code="button.delete" />" /></a>
	          <c:if test="${!message.disabled}"><a href="${untop}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/untop.png" title="<spring:message code="button.untop" />" /></a></c:if>
	          <c:choose>
	            <c:when test="${message.disabled}">
	            <a href="${enable}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/enable.png" title="<spring:message code="button.enable" />" /></a>
	            </c:when>
	            <c:otherwise>
                <a href="${disable}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/disable.png" title="<spring:message code="button.disable" />" /></a>
	            </c:otherwise>
	          </c:choose>
	        </td>
	      </tr>
		  </c:forEach>
        </tbody>
      </table>
  </div>
  
  <div class="content-box-header">
    <h3><spring:message code="message.list.untopedMessages" /></h3>
  </div>
  <div class="content-box-content">
      <table>
        <thead>
          <tr>
            <th style="width:20%"><spring:message code="message.title" /></th>
            <th style="width:30%"><spring:message code="message.content" /></th>
            <th style="width:15%"><spring:message code="message.category.name" /></th>
            <th style="width:10%"><spring:message code="message.rank" /></th>
            <th style="width:15%"><spring:message code="message.source" /></th>
            <th style="width:10%"><spring:message code="message.operation" /></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="message" items="${untopedMessages}">
          <spring:url value="/web/message/view.html?id=${message.id}" var="view" htmlEscape="true"/>
          <spring:url value="/web/message/editForm.html?id=${message.id}" var="edit" htmlEscape="true"/>
          <spring:url value="/web/message/delete.html?id=${message.id}" var="delete" htmlEscape="true"/>
          <spring:url value="/web/message/disable.html?id=${message.id}" var="disable" htmlEscape="true"/>
          <spring:url value="/web/message/enable.html?id=${message.id}" var="enable" htmlEscape="true"/>
          <spring:url value="/web/message/top.html?id=${message.id}" var="top" htmlEscape="true"/>
          <spring:url value="/web/message/untop.html?id=${message.id}" var="untop" htmlEscape="true"/>
          <tr>
            <td><a href="${view}"><str:truncateNicely upper="10">${message.title}</str:truncateNicely></a></td>
            <td><a href="${view}"><str:truncateNicely upper="20">${message.content}</str:truncateNicely></a></td>
            <td>${message.category.name}</td>
            <td>${message.rank}</td>
            <td>${message.source}</td>
            <td> 
              <a href="${view}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/details.png" title="<spring:message code="button.view" />" /></a>
              <c:if test="${!message.disabled}"><a href="${edit}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/edit.png" title="<spring:message code="button.edit" />" /></a></c:if>
              <a href="${delete}" class="popupConfirm"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/delete.png" title="<spring:message code="button.delete" />" /></a>
              <c:if test="${!message.disabled}"><a href="${top}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/top.png" title="<spring:message code="button.top" />" /></a></c:if>
              <c:choose>
                <c:when test="${message.disabled}">
                <a href="${enable}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/enable.png" title="<spring:message code="button.enable" />" /></a>
                </c:when>
                <c:otherwise>
                <a href="${disable}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/disable.png" title="<spring:message code="button.disable" />" /></a>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
  </div>

</div>

<spring:url value="/web/message/createForm.html" var="createForm" htmlEscape="true" />
<a class="button" href="${createForm}" ><spring:message code="button.create" /></a>

</body>
</html>
