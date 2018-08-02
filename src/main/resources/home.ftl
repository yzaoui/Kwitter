<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#-- @ftlvariable name="newKweetHref" type="String" -->
<#-- @ftlvariable name="maxKweetLength" type="Integer" -->
<#-- @ftlvariable name="htmlKweets" type="kotlin.collections.List<kwitter.freemarker.HTMLKweet>" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="Home" />
<@main.body>
<@nav.main>
<@nav.items>
<@nav.nonguest loggedInUser=loggedInUser loggedInUserURL=loggedInUserURL logoutURL=logoutURL />
</@nav.items>
</@nav.main>
<main class="timeline-main">
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
