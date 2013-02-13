<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>

<spring:url value="/web/common/language.html" var="language" htmlEscape="true"/>
<spring:url value="/web/security/logout.html" var="logout" htmlEscape="true"/>
<div>
<a class="button" style="font-size:8px;" href="${logout}"><spring:message code="logout" /></a>
<!-- <a class="button" style="font-size:5px;" href="${language}&language=en"><spring:message code="language.english" /></a>
<a class="button" style="font-size:8px;" href="${language}&language=zh"><spring:message code="language.chinese" /></a> -->
</div>

<c:if test="${error != null}">
<img src="resources/images/icons/cross_circle.png" /><span class="error">${error}</span>
</c:if>