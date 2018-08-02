<#-- @ftlvariable name="signUpHref" type="String" -->
<#-- @ftlvariable name="loginHref" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="Welcome" />
<@main.body>
    <@nav.main>
        <@nav.items>
            <@nav.login loginURL=loginHref />
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
