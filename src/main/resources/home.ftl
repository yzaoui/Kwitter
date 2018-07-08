<#-- @ftlvariable name="displayName" type="String" -->
<#-- @ftlvariable name="logoutHref" type="String" -->
<#-- @ftlvariable name="kweetHref" type="String" -->
<#-- @ftlvariable name="maxKweetLength" type="Integer" -->
<#-- @ftlvariable name="kweets" type="kotlin.collections.List<kwitter.data.model.Kweet>" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="Home" />
<@main.body>
<@nav.nav>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
        </ul>
        <form class="form-inline mt-2 mt-md-0" action="${logoutHref}" method="post" enctype="application/x-www-form-urlencoded">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
        </form>
    </div>
</@nav.nav>
<main role="main" class="container">
    <div class="jumbotron">
        <h1>Welcome, ${displayName}!</h1>
    </div>
    <div>
        <form action="${kweetHref}" method="post" enctype="application/x-www-form-urlencoded">
            <textarea name="new-kweet-text" title="New kweet input" maxlength="${maxKweetLength}" placeholder="What's up?" style="width: 100%" required></textarea>
            <button type="submit" style="float: right">Kweet</button>
        </form>
    </div>
    <div>
        <@kweetList.kweet_list kweets=kweets />
    </div>
</main>
</@main.body>
</@main.html>
