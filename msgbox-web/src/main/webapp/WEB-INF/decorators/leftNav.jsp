<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url value="/web/category/list.html" var="listCategory" htmlEscape="true" />
<spring:url value="/web/category/createForm.html" var="createCategory" htmlEscape="true" />
<spring:url value="/web/message/list.html" var="listMessage" htmlEscape="true" />
<spring:url value="/web/message/createForm.html" var="createMessage" htmlEscape="true" />
<spring:url value="/web/home.html" var="home" htmlEscape="true" />

<div id="sidebar-wrapper">
  <a href="http://www.865171.cn"><img id="logo" src="resources/images/logo.jpg" width="180px" height="180px" /></a>
  <ul id="main-nav">
    <li> 
      <a href="${home}" class="nav-top-item no-submenu ${fn:contains(pageContext.request.requestURI, '/web/home.html') ? 'current' : ''}"><spring:message code="navigation.menu.home" /></a>
    </li>
    <li> 
      <a href="#" class="nav-top-item ${fn:contains(pageContext.request.requestURI, '/web/category') ? 'current' : ''}"><spring:message code="navigation.menu.category.manage" /></a>
      <ul>
        <li><a href="${listCategory}" ${(pageContext.request.requestURI eq listCategory) ? "class='current'" : ''}><spring:message code="navigation.menu.category.list" /></a></li>
        <li><a href="${createCategory}" ${(pageContext.request.requestURI eq createCategory) ? "class='current'" : ''}><spring:message code="navigation.menu.category.create" /></a></li>
      </ul>
    </li>
    <li> 
      <a href="#" class="nav-top-item ${fn:contains(pageContext.request.requestURI, '/web/message') ? 'current' : ''}"><spring:message code="navigation.menu.message.manage" /></a>
      <ul>
        <li><a href="${listMessage}" ${(pageContext.request.requestURI eq listMessage) ? "class='current'" : ''}><spring:message code="navigation.menu.message.list" /></a></li>
        <li><a href="${createMessage}" ${(pageContext.request.requestURI eq createMessage) ? "class='current'" : ''}><spring:message code="navigation.menu.message.create" /></a></li>
      </ul>
    </li>
  </ul>
</div>