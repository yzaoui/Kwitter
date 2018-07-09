<#-- @ftlvariable name="signUpHref" type="String" -->
<#-- @ftlvariable name="loginHref" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="Welcome" />
<@main.body>
<@nav.main>
<@nav.items>
<form class="form-inline mt-2 mt-md-0" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name="username" class="form-control mr-sm-2" placeholder="Username" aria-label="Username" required>
    <input type="password" name="password" class="form-control mr-sm-2" placeholder="Password" aria-label="Password" required>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
</form>
</@nav.items>
</@nav.main>
<main role="main" class="container">
    <div class="jumbotron">
        <h1>Welcome to Kwitter!</h1>
        <p class="lead">This is a clone of some social media site no one's really heard of.</p>
        <a class="btn btn-lg btn-primary" role="button" href="${signUpHref}">Sign up</a>
        <a class="btn btn-lg btn-primary" role="button" href="${loginHref}">Log in</a>
    </div>
</main>
</@main.body>
</@main.html>
