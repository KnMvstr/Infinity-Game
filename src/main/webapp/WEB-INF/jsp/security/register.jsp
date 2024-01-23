<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<header class="form-signin-heading container py-5">
<h1>Create your account</h1>
</header>

<div class="col-md-12 d-inline-flex justify-content-evenly align-items-end mt-5 flex-md-wrap">


    <section class="form-group ${status.error ? 'has-error' : ''}">
        <f:form method="POST" modelAttribute="userForm" class="form-signin">
        <div class="input-group mb-4">
            <div class="input-group-prepend">
                <div class="input-group-text">@</div>
            </div>
            <f:input type="text" path="username" class="form-control" placeholder="Email"
                     autofocus="true"/>
            <f:errors path="username" cssClass="invalid-feedback"/>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''} mb-4">
            <f:input type="text" path="pseudo" class="form-control" placeholder="Votre pseudo de connexion"
                     autofocus="true"/>
            <f:errors path="pseudo" cssClass="invalid-feedback"/>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''} mb-4">
            <f:input type="password" path="pwd" class="form-control" placeholder="Password"/>
            <small id="passwordHelpBlock" class="form-text text-muted">
                Votre mot de passe doit contenir de 8 à 15 caractères.
            </small>
            <f:errors path="pwd" cssClass="invalid-feedback"/>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </f:form>
    </section>


        <section class="col-md-5 ps-5">
            <div class="mb-3 p-3 bg-dark text-white">
                <h5 class="card-title ">Donnez votre avis</h5>
                <p class="card-text">Chaque avis compte, donnez le vôtre</p>
            </div>
            <div class="mb-3 p-3 bg-dark text-white">
                <h5 class="card-title">Tendance</h5>
                <p class="card-text">Suivez les tendances du public</p>
            </div>
            <div class="mb-3 p-3 bg-dark text-white">
                <h5 class="card-title">Communauté</h5>
                <p class="card-text">Rejoignez vos commmunautés de joueurs</p>
            </div>
        </section>

</div>


<%@ include file="../footer.jsp" %>