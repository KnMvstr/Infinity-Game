<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="Téléverser une image"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>

<div class="container mt-5">
    <h1>Téléverser une image pour un jeu</h1>

    <c:set var="label" scope="request" value=""/>
    <c:set var="urlAction" value="${currentUrl}"/>
    <%@ include file="component/form-upload.jsp" %>

</div>

<%@ include file="footer.jsp" %>
