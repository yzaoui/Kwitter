<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="htmlKweets" type="kotlin.collections.List<kwitter.freemarker.HTMLKweet>" -->
<#-- @ftlvariable name="loginURL" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
<@main.body>
    <@nav.main>
        <@nav.items>
            <#if loggedInUser??>
            <@nav.nonguest loggedInUser=loggedInUser loggedInUserURL=loggedInUserURL logoutURL=logoutURL />
            <#else>
            <@nav.login loginURL=loginURL />
            </#if>
        </@nav.items>
    </@nav.main>
    <main role="main" class="timeline-main">
        <section>
            <h1>${user.displayName} @${user.username}</h1>
        </section>
        <div>
            <@kweetList.kweet_list htmlKweets=htmlKweets />
        </div>
    </main>
</@main.body>
</@main.html>
