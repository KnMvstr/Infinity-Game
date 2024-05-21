<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../tag.jsp" %>
<c:set var="title" scope="request" value="${user.name}"/>
<jsp:include flush="true" page="../base.jsp"/>


<section class="p-3 m-0 border-0 bd-example m-0 border-0" style="background-color:#101010">

  <div class="d-flex flex-column mb-1 py-2 grid style=height: 100vh;">
  <section class="d-flex flex-column mb-1 py-3 grid row-gap-2">
  <img src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp" class="rounded-circle shadow-4 align-self-center" style="width: 12vw;"
  alt="Avatar"/>
  <div class="card-body align-self-center">
    <p class="card-text text-warning fw-semibold">${user.name}</p>
  </div>
  </section>
  </div>


  <section class="d-flex flex-column mb-1 row-gap-3" >
  <c:if test="${user.name == userLogged.name}">
  <div class="d-flex flex-row mb-3 justify-content-center column-gap-4">
  <div class="card text-white" style="width: 35%; background-color:#1d1d1d">
        <div class="card-body" >
          <h4 class="card-title text-warning">Profile</h4>
          <a href="#" class="btn btn-link text-white fs-6" style="text-decoration: none">Edit profile</a>
          <a href="#" class="btn btn-link text-white" style="text-decoration: none">Profile image</a>
        </div>
  </div>
  <div class="card text-white" style="width: 35%; background-color:#1d1d1d">
  <div class="card-body">
          <h4 class="card-title text-warning">Wallet</h4>
          <a href="#" class="btn btn-link text-white" style="text-decoration: none">Credit my wallet</a>
  </div>
</div>
</div>

<div class="d-flex flex-row mb-3 justify-content-center column-gap-4">
<div class="card text-white" style="width: 35%; background-color:#1d1d1d">
<div class="card-body">
             <h4 class="card-title text-warning">My Games</h4>
             <a href="#" class="btn btn-link text-white" style="text-decoration: none">See all</a>
</div>
</div>
<div class="card text-white" style="width: 35%; background-color:#1d1d1d">
<div class="card-body">
                   <h4 class="card-title text-warning">My Wishlist</h4>
                   <p class="card-text"></p>
                   <a href="#" class="btn btn-link text-white" style="text-decoration: none">See all</a>
</div>
</div>
</div>
</c:if>


<div class="d-flex flex-row mb-3 justify-content-evenly">
  <div class="card text-white" style="width: 73%; background-color:#1d1d1d">
         <div class="card-body">
                            <h4 class="card-title text-warning">My Review</h4>
                            <p class="card-text"></p>
                            <a href="#" class="btn btn-link text-white" style="text-decoration: none">See all</a>
         </div>
  </div>
</div>
  </section>
</div>

  </section>
