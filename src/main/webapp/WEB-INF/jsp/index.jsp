<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<section class="top_wallpaper">
</section>

<section class="container d-flex justify-content-evenly col-md-12 gap-4 flex-wrap mb-5 sibling-fade">

    <h2 id="all_comment">Tous les commentaires</h2>
    <%--  FILTRES & DOWNLOAD --%>
    <div class="container d-flex col-md-12 gap-2 filter">
        <c:set var="label" scope="request" value="Date"/>
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Note"/>
        <c:set var="sortable" value="rating"/>
        <%@ include file="component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Jeu"/>
        <c:set var="sortable" value="game"/>
        <%@ include file="component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Joueur"/>
        <c:set var="sortable" value="gamer"/>
        <%@ include file="component/sortable.jsp" %>

        <%@ include file="component/filter-reset.jsp" %>

        <security:authorize access="hasRole('MODERATOR')">
            <a href="${UrlRoute.URL_EXPORT}" class="excel">
                <i class="fa-regular fa-file-excel"></i>
                Export Excel
            </a>
        </security:authorize>

        <%-- MODERATED FILTER --%>
        <security:authorize access="hasRole('MODERATOR')">
            <div class="sort-filter mt-4 me-3">
                <select class="form-select sortable-select">
                    <option value="all" data-filter-url="${currentUrl}">
                        Tous les commentaires
                    </option>
                    <option value="sort=moderator,desc"
                            data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,desc")}"
                    >
                        Modérés
                    </option>
                    <option value="sort=moderator,asc"
                            data-filter-url="${jspUtils.generateUrlFrom(currentUrl, currentQuery, "sort=moderator,asc")}"
                    >
                        À modérer
                    </option>
                </select>
            </div>
        </security:authorize>
    </div>
</section>

<section class="container d-flex align-content-around flex-wrap justify-content-around gap-5 mt-5 mb-5 card-section">
    <c:forEach items="${pageReviews.content}" var="review">
        <div class="container card text-center slide_diagonal" title="${review.game.name}">
            <a href="${UrlRoute.URL_REVIEW}/${review.id}">
                <div class="card-body">
                    <h5 class="card-title">${jspUtils.excerpt(review.game.name, 16)}</h5>
                    <c:if test="${not empty review.moderator}">
                        <cite title="A modérer" class="modstatus">Modéré par ${review.moderator.pseudo}
                            le ${dateUtils.getDateFormat(review.moderatedAt, "dd/MM/yyyy")}</cite>
                    </c:if>

                    <c:if test="${empty review.moderator}">
                        <cite title="A modérer" class="modstatus">En attente de moderation</cite>⌛
                    </c:if>
                </div>
                <blockquote class="blockquote d-column-flex align-items-center justify-content-center">
                    <p>${jspUtils.excerpt(review.description, 55)}</p>
                </blockquote>
            </a>
            <p class="text-muted">Par ${review.gamer.pseudo}
                le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</p>
            <div class="container d-inline">
                <c:if test="${empty review.moderator}">
                    <c:if test="${userLogged.moderator}">
                        <a class="btn btn-link fa fa-check " href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/1"
                           title="Accepter">
                        </a>
                        /
                        <a class="btn btn-link fa fa-xmark " href="${UrlRoute.URL_REVIEW_MODERATE}/${review.id}/0"
                           title="Refuser">
                        </a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </c:forEach>
</section>


<hr>

<h3 class="container top4header" id="top4">Les plus commentés de la semaine</h3>
<%--TOP 4 SECTION--%>
<section class="container d-flex justify-content-around mt-4 gap-5 flex-wrap top4section">
    <c:forEach items="${Top4TrendGames}" var="game">
        <a href="${UrlRoute.URL_GAME}/${game.slug}" class="card-link">
            <div class="card my-4 top4bg" style="background-image:url(${game.image})">
            </div>
            <div class="card-body d-inline-flex justify-content-center">
                <p class="card-text">${game.name}</p>
            </div>
        </a>
    </c:forEach>
</section>

<%@ include file="footer.jsp" %>
