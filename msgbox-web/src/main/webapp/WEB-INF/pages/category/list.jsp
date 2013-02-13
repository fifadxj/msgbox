<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>
  <title><spring:message code="category.list.title" /></title>
</head>
<body>
<div class="content-box">

  <div class="content-box-header">
    <h3><spring:message code="category.list.title" /></h3>
  </div>

  <div class="content-box-content">
      <table>
        <thead>
          <tr>
            <th><spring:message code="category.name" /></th>
            <th><spring:message code="category.parent" /></th>
            <th><spring:message code="category.description" /></th>
            <th><spring:message code="category.operation" /></th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="category" items="${categories}">
	      <spring:url value="/web/category/view.html?id=${category.id}" var="view" htmlEscape="true"/>
	      <spring:url value="/web/category/editForm.html?id=${category.id}" var="edit" htmlEscape="true"/>
	      <spring:url value="/web/category/delete.html?id=${category.id}" var="delete" htmlEscape="true"/>
	      <tr>
	        <td><a href="${view}">${category.name}</a></td>
	        <td>${category.parent.name}</td>
	        <td><str:truncateNicely upper="30">${category.description}</str:truncateNicely></td>
	        <td> 
	          <a href="${view}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/details.png" title="<spring:message code="button.view" />" /></a>
	          <a href="${edit}"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/edit.png" title="<spring:message code="button.edit" />" /></a>
	          <c:if test="${category.deletable}"><a href="${delete}" class="popupConfirm"><img src="${pageContext.request.contextPath}/resources/images/icons/ops/delete.png" title="<spring:message code="button.delete" />" /></a></c:if>
	        </td>
	      </tr>
		  </c:forEach>
        </tbody>
      </table>
  </div>
  
</div>

<spring:url value="/web/category/createForm.html" var="createForm" htmlEscape="true" />
<a class="button" href="${createForm}" ><spring:message code="button.create" /></a>

</body>
</html>
