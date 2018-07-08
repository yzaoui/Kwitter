<#-- @ftlvariable name="signUpHref" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<#-- @ftlvariable name="MIN_LENGTH_USERNAME" type="java.lang.Number" -->
<#-- @ftlvariable name="MAX_LENGTH_USERNAME" type="java.lang.Number" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="Sign Up" />
<@main.body>
<@nav.nav />
<form class="centered-form" action="${signUpHref}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal text-center">Create your account</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" name="username" class="form-control" minlength="${MIN_LENGTH_USERNAME}" maxlength="${MAX_LENGTH_USERNAME}" placeholder="Username" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <input type="text" name="displayName" class="form-control" placeholder="Display Name" required>
    <input type="email" name="email" class="form-control" placeholder="Email" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
</form>
</@main.body>
</@main.html>
