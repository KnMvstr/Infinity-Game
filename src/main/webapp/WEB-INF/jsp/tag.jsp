<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%-- Importe l'objet UrlRoute pour pouvoir l'utiliser partout dans les JSP --%>
<%@ page import="HB_CAPE_MAK.hb_cape_makindu.mapping.UrlRoute" %>


<%-- Imports nécessaires pour récupérer le UserService dans les JSP --%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="HB_CAPE_MAK.hb_cape_makindu.service.UserServiceImpl" %>
<%@ page import="HB_CAPE_MAK.hb_cape_makindu.entity.User" %>
<%@ page import="java.util.Optional" %>


<%--&lt;%&ndash; Renomme de manière plus simple le "pageContext.request.contextPath" &ndash;%&gt;--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
    if (ctx != null) {
        UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
        if (request.getUserPrincipal() != null) {
            Optional<User> user = userService.findByEmail(request.getUserPrincipal().getName());
            request.setAttribute("userLogged", user);
        }
    }
%>
