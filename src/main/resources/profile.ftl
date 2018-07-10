<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loginHref" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="logoutHref" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
<@main.body>
<@nav.main>
    <@nav.items>
    <#if loggedInUser??>
    <a href="${loggedInUserURL}" style="margin-right: 16px">
        <img src="${loggedInUser.profilePictureURL}" alt="Profile picture" style="width: 40px; height: 40px">
    </a>
    <form class="form-inline mt-2 mt-md-0" action="${logoutHref}" method="post" enctype="application/x-www-form-urlencoded">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
    </form>
    <#else>
    <form class="form-inline mt-2 mt-md-0" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
        <input type="text" name="username" class="form-control mr-sm-2" placeholder="Username" aria-label="Username" required>
        <input type="password" name="password" class="form-control mr-sm-2" placeholder="Password" aria-label="Password" required>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
    </form>
    </#if>
    </@nav.items>
</@nav.main>
</@main.body>
</@main.html>
