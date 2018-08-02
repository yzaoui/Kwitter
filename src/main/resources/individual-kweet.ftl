<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<#-- @ftlvariable name="followURL" type="String" -->
<#-- @ftlvariable name="unfollowURL" type="String" -->
<#-- @ftlvariable name="loginURL" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="${htmlKweet.authorDisplayName}: \"${htmlKweet.text}\"" />
<@main.body>
<@nav.main>
    <@nav.items>
        <#if loggedInUser??>
        <a href="${loggedInUserURL}" style="margin-right: 16px">
            <img src="${loggedInUser.profilePictureURL}" alt="Profile picture" style="width: 40px; height: 40px">
        </a>
        <form class="form-inline mt-2 mt-md-0" action="${logoutURL}" method="post" enctype="application/x-www-form-urlencoded">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
        </form>
        <#else>
        <form class="form-inline mt-2 mt-md-0" action="${loginURL}" method="post" enctype="application/x-www-form-urlencoded">
            <input type="text" name="username" class="form-control mr-sm-2" placeholder="Username" aria-label="Username" required>
            <input type="password" name="password" class="form-control mr-sm-2" placeholder="Password" aria-label="Password" required>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
        </form>
        </#if>
    </@nav.items>
</@nav.main>
<main class="ikweet-main">
    <#if followURL??>
    <@kweetList.kweet_individual_follow htmlKweet=htmlKweet followURL=followURL/>
    <#elseif unfollowURL??>
    <@kweetList.kweet_individual_unfollow htmlKweet=htmlKweet unfollowURL=unfollowURL/>
    <#else>
    <@kweetList.kweet_individual htmlKweet=htmlKweet/>
    </#if>
</main>
</@main.body>
</@main.html>
