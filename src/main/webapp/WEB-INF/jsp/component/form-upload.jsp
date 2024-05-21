
<form method="POST"
      action="${urlAction}"
      enctype="multipart/form-data">
    <label>${label}</label>
    <input type="file" name="file" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Soumettre" class="btn btn-primary"/>
</form>
<div>
    ${message}
</div>

