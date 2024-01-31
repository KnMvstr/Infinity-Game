<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<%
    Object title = request.getAttribute("title");
    if (title == null) {
        title = "InfinityGaming";
    }
    request.setAttribute("title", title);
%>

<html lang="fr">
<head>

    <title>${title}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%-- CSS--%>
    <link href="${contextPath}/css/main.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" rel="stylesheet">
    <link href="${contextPath}/css/component/pagination.css" rel="stylesheet">
<%-- JAVASCRIPT--%>
    <script type="text/javascript" src="${contextPath}/js/init-sortable.js"></script>
    <script type="text/javascript" src="${contextPath}/js/hide-form.js"></script>
<%-- LOGO --%>
    <link rel="icon" type="image/png" href="${contextPath}/media/Logo-removebg-preview.png"/>
</head>
<body>


<%-- Navbarici : SI NECESSAIRE --%>
<nav class="navbar navbar-expand-lg d-flex align-items-center justify-content-around col-12 fixed-top">
    <a href="/" class="logo">
        <img src="${contextPath}/media/Logo-removebg-preview.png" width="130vw" height="120vh">
    </a>

    <security:authorize access="isAuthenticated()">
    <div class="collapse navbar-collapse d-flex align-items-center justify-content-between " id="navbarSupportedContent">
        <ul class="navbar-nav d-flex align-items-center justify-content-around text-white gap-5">
            <li class="nav-item">
                <a class="nav-link menu-link" href="${UrlRoute.URL_GENRE}">Genre</a>
            </li>
            <li class="nav-item">
                <a class="nav-link menu-link" href="/#top4">Top</a>
            </li>
            <security:authorize access="hasRole('ROLE_MODERATOR')">
            <li class="nav-item">
                <a class=" menu-link admin_access" href="#">Ajouter un Jeu</a>
            </li>
            </security:authorize>
        </ul>

        <div class="welcome_message d-inline-flex justify-content-center align-items-center">
            <span class="pe-2">Bonjour <span class="subspan"><security:authentication property="name"/></span></span>
            <security:authorize access="hasRole('GAMER')">
            <a href="/"><i class="fa-regular fa-user security-icon"></i></a>
            </security:authorize>
            <security:authorize access="hasRole('MODERATOR')">
            <a href="/"><i class="fa-solid fa-user-shield security-icon"></i></a></security:authorize>

            <span class="pe-2"> | </span>
            <form method="POST" action="${UrlRoute.URL_LOGOUT}" autocomplete="off">
                <button type="submit" tabindex="3" class="btn btn-link">
                    <i class="fa-solid fa-arrow-right-from-bracket security-icon"></i>
                </button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </div>
    </div>
    </security:authorize>
</nav>
