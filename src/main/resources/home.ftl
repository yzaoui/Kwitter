<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#-- @ftlvariable name="newKweetHref" type="String" -->
<#-- @ftlvariable name="maxKweetLength" type="Integer" -->
<#-- @ftlvariable name="htmlKweets" type="kotlin.collections.List<kwitter.freemarker.HTMLKweet>" -->
<#-- @ftlvariable name="generateAvatarURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="Home" />
<@main.body>
<@nav.main>
<@nav.items>
<@nav.nonguest loggedInUser=loggedInUser loggedInUserURL=loggedInUserURL logoutURL=logoutURL generateAvatarURL=generateAvatarURL />
</@nav.items>
</@nav.main>
<main class="timeline-main">
    <div class="jumbotron">
        <h1>Welcome, ${loggedInUser.displayName}!</h1>
    </div>
    <div class="new-kweet-div">
        <form class="new-kweet-form" action="${newKweetHref}" method="post" enctype="application/x-www-form-urlencoded">
            <textarea name="new-kweet-text" title="New kweet input" maxlength="${maxKweetLength}" placeholder="What's up?" required></textarea>
            <button type="submit">Kweet</button>
        </form>
    </div>
    <div>
        <@kweetList.kweet_list htmlKweets=htmlKweets />
    </div>
</main>
</@main.body>
</@main.html>
