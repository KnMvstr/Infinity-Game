<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<section class="top_wallpaper genrewlp">
</section>

<section class="container flex-column justify-content-around gap-2 col-12 flex-wrap genresection">
    <h2 class="pb-3">Nos jeux par genre</h2>
    <div class="container flex-column">
        <c:forEach items="${AllGenre}" var="genre">
        <c:if test="${genre.games.size()!=0}">
            <h3 class="pt-3 pb-2">${genre.name.toUpperCase()}</h3>
        </c:if>
            <a href="${UrlRoute.URL_GAME}/${game.slug}" class="card-link">
        <c:forEach items="${genre.games}" var="game">
        <div class="card container d-inline-flex genresection-card mb-5" style="background-image:url(${game.image})">
        </div>



    </c:forEach>
            </a>
    </div> <hr>

    </c:forEach>
</section>
