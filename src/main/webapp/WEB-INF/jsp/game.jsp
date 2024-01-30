<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


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
                <li class="list-group-item px-3">Type : ${game.businessModel.name}</li>
            </ul>
        </div>

        <div class="container mt-5 py-5 text-white">
            <p class="description">${game.description}</p>
        </div>
    </div>
</section>

<section class="adbanner"></section>


<section class="container">
    <h2> Ils ont donné leur avis
        <a href="#" title="Voir tous les commentaires">
            <i class="fa fa-eye"></i>
        </a>
    </h2>

    <section class="d-flex justify-content-around align-items-center flex-wrap gap-4">
        <c:forEach items="${game.reviews}" end="5" var="review">
            <div class="d-column-flex gap-2" >
                <div class="card-body game-card-body">
                    <blockquote class="blockquote p-4">
                        <p>${jspUtils.excerpt(review.description, 90)}</p>
                        <footer class="blockquote-footer text-muted">${review.gamer.pseudo}</footer>
                    </blockquote>
                </div>

            </div>
        </c:forEach>
    </section>
</section>

<%@ include file="footer.jsp" %>
