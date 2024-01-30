<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<section class="top_wallpaper">
</section>



<section class="container d-flex justify-content-evenly col-md-12 gap-5 flex-wrap sibling-fade">

    <div class="container d-flex col-md-12 gap-2" >
        <c:set var="label" scope="request" value="Date"/>
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="component/sortable.jsp" %>

        <c:set var="label" scope="request" value="Date"/>
        <c:set var="sortable" value="createdAt"/>
        <%@ include file="component/sortable.jsp" %>

        <c:set var="page" scope="request" value="${pageReviews}"/>
    </div>


    <c:forEach items="${pageReviews.content}" var="review">
       <a href="${UrlRoute.URL_REVIEW}/${review.id}">
           <div class="card my-2 text-center slide_diagonal" title="${review.game.name}">
            <div class="card-header">${jspUtils.excerpt(review.game.name, 16)}</div>
            <div class="card-body">
                <blockquote class="blockquote d-column-flex align-items-center justify-content-center">
                    <p>${jspUtils.excerpt(review.description, 55)}</p>
                    <footer class="blockquote-footer text-muted">${review.gamer.pseudo}</footer>
                </blockquote>
            </div>
            <div class="card-footer"><p>Le ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}</p></div>
                <%--            ${dateUtils.getDateFormat(review.createdAt, "dd/MM/yyyy")}--%>
        </div>
       </a>
    </c:forEach>

    <%@ include file="component/pagination.jsp" %>
</section>

<hr>

<h3 class="container top4header" id="top4">Les plus commentés de la semaine</h3>
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
