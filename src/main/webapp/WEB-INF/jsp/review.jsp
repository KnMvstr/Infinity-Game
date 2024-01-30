<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<header class="top_wallpaper" style="background-image:url(${review.game.backgroundImage})">
</header>

<section class="container flex-column">
    <div class="d-flex align-items-center justify-content-around flex-wrap px-5 game_spec">
        <div class="flex-column">
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


<%--<section class="container d-flex justify-content-evenly col-md-12 mt-5 gap-5 flex-wrap sibling-fade">--%>
<%--<c:forEach items="${review.gamer.reviews}" var="review">--%>
<%--    <a href="${UrlRoute.URL_REVIEW}/${review.id}">--%>
<%--        <div class="card my-2 text-center slide_diagonal" title="${review.game.name}">--%>
<%--            <div class="card-header">${jspUtils.excerpt(review.game.name, 16)}</div>--%>
<%--            <div class="card-body">--%>
<%--                <blockquote class="blockquote d-column-flex align-items-center justify-content-center">--%>
<%--                    <p>${jspUtils.excerpt(review.description, 55)}</p>--%>
<%--                </blockquote>--%>
<%--            </div>--%>
<%--            <div class="card-footer"><p>Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</p></div>--%>
<%--                &lt;%&ndash;            ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </a>--%>
<%--</c:forEach>--%>
<%--</section>--%>




<%@ include file="footer.jsp" %>
