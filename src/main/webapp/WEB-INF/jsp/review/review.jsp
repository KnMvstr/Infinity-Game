<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<header class="top_wallpaper" style="background-image:url(${review.game.backgroundImage})">
</header>

<section class="container flex-column">
    <div class="d-flex align-items-center justify-content-around flex-wrap px-5 game_spec">
        <div class="flex-column bg-black game_spec_gamer rounded-3 p-3">
            <a href="/"><h2>${review.gamer.pseudo}</h2></a>
            <p>${review.rating}</p>
        </div>
        <div class="flex-column game_spec_list">
            <div>
                <a href="${UrlRoute.URL_GAME}/${review.game.slug}"><h2>${review.game.name}</h2></a>
                <p>${review.description}</p>
                <p class="text-muted">Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</p>
                <p class="rating">${review.rating}</p>
            </div>
        </div>
    </div>
</section>

<%@ include file="../footer.jsp" %>
