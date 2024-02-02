<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>


<header class="top_wallpaper" style="background-image:url(${game.backgroundImage})">
</header>

<section class="container flex-column">
    <div class="d-flex align-items-center justify-content-around flex-wrap px-5 game_spec">
        <card style="background-image:url(${game.image})">
        </card>
        <div class="flex-column game_spec_list">
            <ul class="list-group list-group-light" data-header="${game.name}">
                <li class="list-group-item px-3 mb-2">Par : <a href="/">${game.publisher.name}</a></li>
                <li class="list-group-item px-3 mb-2">Sortie le: ${game.releaseDate}</li>
                <li class="list-group-item px-3 mb-2">Classification : ${game.classification.name}</li>
                <li class="list-group-item px-3 mb-2">Genre : ${game.genre.name}</li>
                <li class="list-group-item px-3 mb-2">Type : ${game.businessModel.name}</li>
                <c:if test="${game.platforms.size() > 0}">

                    <li class="list-group-item px-3"> Disponible sur :
                    <c:forEach items="${game.platforms}" var="platform">
                        <a class="link-if" href="#">
                                ${platform.name}
                        </a>

                    </c:forEach>
                    </li>

                </c:if>
            </ul>
        </div>

        <div class="container mt-5 py-5 text-white">
            <c:out value="${game.description}" escapeXml="false"/>
        </div>
    </div>
</section>

<section class="adbanner1 container">
    <iframe width="66%" height="500" src="${game.trailer}?autoplay" class="container"></iframe>
</section>
<section class="adbanner"></section>


<section class="container">
    <h2> Ils ont donné leur avis
        <button class="ms-2 btn btn-link"
                title="Ajouter un commentaire"
                data-hide-show-button="formReview">
            <i class="fa fa-pen fa-2x"></i>
        </button>
    </h2>

    <%--HIDDEN fORM--%>
    <div class="my-3 d-none"
         data-hide-show-container="formReview">
        <f:form cssClass="col-md-8 col-sm-12 mx-auto"
                action="${currentUrl}"
                method="post"
                modelAttribute="reviewDto">
            <div class="mb-3 row">
                <f:label path="description" class="col-sm-2 col-form-label">Description</f:label>
                <div class="col-sm-10">
                    <f:textarea cssClass="form-control" path="description"/>
                    <f:errors path="description" cssClass="invalid-feedback"/>
                </div>
            </div>
            <div class="my-3 row">
                <f:label path="rating" class="col-sm-2 col-form-label">Note</f:label>
                <div class="col-sm-10">
                    <f:input type="number" cssClass="form-control" path="rating"/>
                    <f:errors path="rating" cssClass="invalid-feedback"/>
                </div>
            </div>
            <f:button type="submit" class="btn btn-success">
                <i class="fa fa-check"></i> Ajouter
            </f:button>
        </f:form>
    </div>

    <%--  AFFICHAGE DES REVIEW--%>
    <section class="d-flex justify-content-around align-items-center flex-wrap gap-4">
        <c:forEach items="${game.reviews}" end="5" var="review">
            <div class="d-column-flex gap-2">
                <div class="card-body game-card-body">
                    <blockquote class="blockquote p-4">
                        <p>${jspUtils.excerpt(review.description, 90)}</p>
                        <footer class="blockquote-footer text-muted">${review.gamer.pseudo}</footer>
                    </blockquote>
                </div>

            </div>
        </c:forEach>
        <core:set var="page" value="${pageReviews}"/>
        <%@ include file="../component/pagination.jsp" %>
        <%--        <jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/component/pagination.jsp"/>--%>
    </section>
</section>

<%@ include file="../footer.jsp" %>
