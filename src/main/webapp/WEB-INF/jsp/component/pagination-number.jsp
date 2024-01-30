
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mt-auto mb-2">
    <span>
        <core:set var="pageNumber" value="${page.number + 1}"/>
        page ${pageNumber} sur ${page.totalPages}
    </span>
</div>