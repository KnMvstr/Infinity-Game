<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<%-- Imports nécessaires pour récupérer le les Utils--%>
<%@ page import="HB_CAPE_MAK.hb_cape_makindu.utils.DateUtils " %>
<%@ page import="HB_CAPE_MAK.hb_cape_makindu.utils.JspUtils" %>

<%--&lt;%&ndash; Renomme de manière plus simple le "pageContext.request.contextPath" &ndash;%&gt;--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String path = request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
    Object query = request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
    String queryString = "";
    if (query != null) {
        queryString = query.toString();
    }
    request.setAttribute("currentQuery", queryString);
    request.setAttribute("currentPath", path);
    request.setAttribute("currentUrl", url + path);

    WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(request);
    if (ctx != null) {
        request.setAttribute("dateUtils", ctx.getBean(DateUtils.class));
        request.setAttribute("jspUtils", ctx.getBean(JspUtils.class));
        UserServiceImpl userService = ctx.getBean(UserServiceImpl.class);
        if (request.getUserPrincipal() != null) {
            User user = userService.findByPseudo(request.getUserPrincipal().getName());
            request.setAttribute("userLogged", user);
        }
    }
%>


