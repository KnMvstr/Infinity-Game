<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="tag.jsp" %>
<c:set var="title" scope="request" value="CapEntre"/>
<jsp:include flush="true" page="${contextPath}/WEB-INF/jsp/base.jsp"/>


<section class="top_wallpaper genrewlp">
</section>

<section class="container flex-column justify-content-around gap-2 col-12 flex-wrap genresection">
    <h2 class="pb-3" id="add_game">Nos jeux par genre  <c:if test="${userLogged.moderator}">
        <a title="Ajouter un commentaire" data-hide-show-button="formGame">
        <i class="fa-solid fa-plus"></i>
        </a>
    </c:if>
    </h2>


<c:if test="${userLogged.moderator}" >
    <f:form method="POST" modelAttribute="gameDto" class="col-lg-8 col-md-12 col-sm-12 mx-auto" data-hide-show-container="formGame">
        <div class="row mb-3 d-none">
            <div class="col-md-8 col-sm-12">
                <f:label class="col-form-label" path="name">Nom</f:label>
                <f:input type="text" path="name"
                         class="form-control"
                         placeholder="Nom"
                         autofocus="true"/>
                <f:errors path="name" cssClass="invalid-feedback"/>
            </div>
            <div class="col-md-4 col-sm-12">
                <f:label class="col-form-label" path="releaseDate">Date de sortie</f:label>
                <f:input type="date" path="releaseDate"
                         class="form-control"
                         autofocus="true"/>
                <f:errors path="releaseDate" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-4 col-sm-12">
                <f:label class="col-form-label" path="genre">
                    Genre
                </f:label>
                <f:select path="genre"
                          items="${genres}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="genre" cssClass="invalid-feedback"/>
            </div>
            <div class="col-md-4 col-sm-12">
                <f:label class="col-form-label" path="classification">
                    Classification
                </f:label>
                <f:select path="classification"
                          items="${classifications}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="classification" cssClass="invalid-feedback"/>
            </div>
            <div class="col-md-4 col-sm-12">
                <f:label class="col-form-label" path="businessModel">
                    Modèle économique
                </f:label>
                <f:select path="businessModel"
                          items="${businessModels}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="businessModel" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md-6 col-sm-12">
                <f:label class="col-form-label" path="publisher">
                    Editeur
                </f:label>
                <f:select path="publisher"
                          items="${publishers}"
                          cssClass="form-select"
                          itemLabel="name"
                >
                </f:select>
                <f:errors path="publisher" cssClass="invalid-feedback"/>
            </div>
            <div class="col-md-6 col-sm-12">
                <f:label class="col-form-label" path="platforms">
                    Plateformes
                </f:label>
                <input class="form-control" data-multiple-select-input="platforms"/>
                <f:select path="platforms"
                          items="${platforms}"
                          cssClass="form-select"
                          itemLabel="name"
                          data-multiple-select="platforms"
                >
                </f:select>
                <f:errors path="platforms" cssClass="invalid-feedback"/>
            </div>
        </div>
        <div class="col-12 mb-3">
            <f:label class="col-form-label" path="description">Description</f:label>
            <f:textarea type="text" path="description"
                        class="form-control"
                        placeholder="Description"
                        autofocus="true"/>
            <f:errors path="description" cssClass="invalid-feedback"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            Valider
        </button>
    </f:form>
</c:if>






    <div class="container flex-column">
        <c:forEach items="${AllGenre}" var="genre">

            <c:if test="${genre.games.size()!=0}">
                <h3 class="pt-3 pb-2">${genre.name.toUpperCase()}</h3>
            </c:if>


            <c:forEach items="${genre.games}" var="game">
                <a href="${UrlRoute.URL_GAME}/${game.slug}" class="card-link">
                    <div class="card container d-inline-flex genresection-card mb-5"
                         style="background-image:url(${game.image})">
                    </div>
                </a>
            </c:forEach>

        </c:forEach>
    </div>
    <hr>


</section>
