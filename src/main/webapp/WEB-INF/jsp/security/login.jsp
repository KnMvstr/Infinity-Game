<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<jsp:include flush="true" page="../base.jsp"/>

<header class="form-signin-heading container py-5">
    <h1>Se connecter</h1>
</header>

<div class="container col-4 justify-content-around py-3">

    <%--Input connection--%>
    <form method="POST" action="${UrlRoute.URL_LOGIN}" class="form-signin">
        <div class="form-group ${error != null ? 'has-error' : ''} d-flex flex-column gap-4">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Pseudo"/>
            <input name="password" type="password" class="form-control" placeholder="Mot de passe"/>
            <p class="invalid-feedback">${error}</p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <!-- 2 column grid layout for inline styling -->
            <div class="row mb-1">
                <div class="col d-flex justify-content-center">
                    <!-- Checkbox -->
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" checked/>
                        <label class="form-check-label"> Remember me </label>
                    </div>
                </div>
                <!-- Forgot Password -->
                <div class="col">
                    <a href="#!">Forgot password?</a>
                </div>
            </div>
            <%--Login BTN--%>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>

            <%--Connection section--%>
            <div class="text-center">
                <p>Pas encore membre ? <a href="${contextPath}/register" class="btn-link"> S'inscrire</a></p>
                <p>or sign up with:</p>
                <button data-mdb-ripple-init type="button" class="btn btn-secondary btn-floating mx-1">
                    <i class="fab fa-facebook-f"></i>
                </button>

                <button data-mdb-ripple-init type="button" class="btn btn-secondary btn-floating mx-1">
                    <i class="fab fa-google"></i>
                </button>

                <button data-mdb-ripple-init type="button" class="btn btn-secondary btn-floating mx-1">
                    <i class="fab fa-twitter"></i>
                </button>

                <button data-mdb-ripple-init type="button" class="btn btn-secondary btn-floating mx-1">
                    <i class="fab fa-steam"></i>
                </button>
            </div>
        </div>
    </form>
</div>

<%@ include file="../footer.jsp" %>