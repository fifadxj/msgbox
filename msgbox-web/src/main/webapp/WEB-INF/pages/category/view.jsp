<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>
  <title><spring:message code="category.view.title" /></title>
</head>
<body>
<div class="content-box">
  <div class="content-box-header">
    <h3><spring:message code="category.view.title" /></h3>
  </div>
  <div class="content-box-content">
      <table>
          <tr>
            <td><spring:message code="category.name" /></td>
          </tr>
          <tr>
            <td>${category.name}</td>
          </tr>
          <tr>
            <td><spring:message code="category.parent" /></td>
          </tr>
          <tr>
            <td>${category.parent.name}</td>
          </tr>
          <tr>
            <td><spring:message code="category.children" /></td>
          </tr>
          <tr>
            <td>
            <table style="width:580px;">
            <c:forEach var="child" items="${category.children}">
		      <spring:url value="/web/category/view.html?id=${child.id}" var="view" htmlEscape="true"/>
	          <tr>
	            <td><a href="${view}">${child.name}</a></td>
	          </tr>
            </c:forEach>
            </table>  
            </td>
          </tr>
          <tr>
            <td><spring:message code="category.description" /></td>
          </tr>
          <tr>
            <td>${category.description}</td>
          </tr>
      </table>
  </div>
</div>

<spring:url value="/web/category/delete.html?id=${category.id}" var="delete" htmlEscape="true"/>
<spring:url value="/web/category/editForm.html?id=${category.id}" var="edit" htmlEscape="true"/>
<spring:url value="/web/category/list.html" var="list" htmlEscape="true"/>
<a class="button" href="${edit}"><spring:message code="button.edit" /></a>
<c:if test="${category.deletable}"><a class="button popupConfirm" href="${delete}"><spring:message code="button.delete" /></a></c:if>
<a class="button" href="${list}"><spring:message code="button.back" /></a>
</body>
</html>