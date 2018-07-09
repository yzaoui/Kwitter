<#-- @ftlvariable name="SIGN_UP_HREF" type="String" -->
<#-- @ftlvariable name="USERNAME_REGEX" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="Sign Up" />
<@main.body>
<@nav.main />
<form class="centered-form" action="${SIGN_UP_HREF}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal text-center">Create your account</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" name="username" class="form-control" pattern="${USERNAME_REGEX}" title="Username must consist of 1-15 letters, numbers, and/or underscores." placeholder="Username" required autofocus >
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <input type="text" name="displayName" class="form-control" placeholder="Display Name" required>
    <input type="email" name="email" class="form-control" placeholder="Email" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
</form>
</@main.body>
</@main.html>
