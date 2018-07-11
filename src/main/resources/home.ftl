<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="logoutHref" type="String" -->
<#-- @ftlvariable name="newKweetHref" type="String" -->
<#-- @ftlvariable name="maxKweetLength" type="Integer" -->
<#-- @ftlvariable name="htmlKweets" type="kotlin.collections.List<kwitter.freemarker.HTMLKweet>" -->
<#-- @ftlvariable name="profileURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="Home" />
<@main.body>
<@nav.main>
<@nav.items>
<a href="${profileURL}" style="margin-right: 16px">
    <img src="${loggedInUser.profilePictureURL}" alt="Profile picture" style="width: 40px; height: 40px">
</a>
<form class="form-inline mt-2 mt-md-0" action="${logoutHref}" method="post" enctype="application/x-www-form-urlencoded">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
</form>
</@nav.items>
</@nav.main>
<main class="dashboard-main">
    <div class="jumbotron">
        <h1>Welcome, ${loggedInUser.displayName}!</h1>
    </div>
    <div>
        <form class="new-kweet-form" action="${newKweetHref}" method="post" enctype="application/x-www-form-urlencoded">
            <textarea name="new-kweet-text" title="New kweet input" maxlength="${maxKweetLength}" placeholder="What's up?" style="width: 100%" required></textarea>
            <button type="submit" style="float: right">Kweet</button>
        </form>
    </div>
    <div>
        <@kweetList.kweet_list htmlKweets=htmlKweets />
    </div>
</main>
</@main.body>
</@main.html>
