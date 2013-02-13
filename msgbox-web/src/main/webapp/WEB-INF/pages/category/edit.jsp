<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<html>
<head>
  <title><spring:message code="category.edit.title" /></title>
  
  <script>
    $(document).ready(function(){
      $("#_form").validationEngine({promptPosition : "topRight"});
    });
  </script>
</head>
<body>

<spring:url value="/web/category/editSubmit.html" var="editSubmit" htmlEscape="true"/>
<form:form action="${editSubmit}" method="post" id="_form" modelAttribute="command">
<form:input path="id" type="hidden" />
<div class="content-box">
  <div class="content-box-header">
    <h3><spring:message code="category.edit.title" /></h3>
  </div>
  <div class="content-box-content">
      <table>
          <tr>
            <td><spring:message code="category.name" /></td>
          </tr>
          <tr>
            <td><form:input path="name" class="validate[required] text-input" size="80" type="text" /></td>
          </tr>
          <tr>
            <td><spring:message code="category.parent" /></td>
          </tr>
          <tr>
            <td>
              <form:select path="parentId" style="width:580px">
				<form:option value=""><spring:message code="category.selectParentCategory" /></form:option>
				<form:options items="${categories}" itemValue="id" itemLabel="name"/>
			  </form:select>
            </td>
          </tr>
          <tr>
            <td><spring:message code="category.children" /></td>
          </tr>
          <tr>
            <td>
            <table style="width:580px;">
            <c:forEach var="child" items="${command.children}">
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
            <td><form:textarea path="description" class="text-input textarea wysiwyg" name="textfield" cols="1" rows="8"></form:textarea></td>
          </tr>
      </table>
  </div>
</div>

<spring:url value="/web/category/list.html" var="list" htmlEscape="true"/>
<spring:url value="/web/category/delete.html?id=${command.id}" var="delete" htmlEscape="true"/>
<a class="button" onclick="$('#_form').submit();"><spring:message code="button.submit" /></a>
<c:if test="${command.deletable}"><a class="button popupConfirm" href="${delete}"><spring:message code="button.delete" /></a></c:if>
<a class="button" href="${list}"><spring:message code="button.back" /></a>

</form:form>
</body>
</html>