<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="logoutHref" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
<@main.body>
<@nav.main>
    <@nav.items>
    <form class="form-inline mt-2 mt-md-0" action="${logoutHref}" method="post" enctype="application/x-www-form-urlencoded">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
    </form>
    </@nav.items>
</@nav.main>
</@main.body>
</@main.html>
