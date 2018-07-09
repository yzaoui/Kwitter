<#-- @ftlvariable name="loginHref" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="Login" />
<@main.body>
<@nav.main />
<form class="centered-form" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal text-center">Please log in</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
</form>
</@main.body>
</@main.html>
