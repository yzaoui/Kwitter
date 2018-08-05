<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<#-- @ftlvariable name="followURL" type="String" -->
<#-- @ftlvariable name="unfollowURL" type="String" -->
<#-- @ftlvariable name="loginURL" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#-- @ftlvariable name="generateAvatarURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="${htmlKweet.authorDisplayName}: \"${htmlKweet.text}\"" />
<@main.body>
    <@nav.main>
        <@nav.items>
            <#if loggedInUser??>
            <@nav.nonguest loggedInUser=loggedInUser loggedInUserURL=loggedInUserURL logoutURL=logoutURL generateAvatarURL=generateAvatarURL />
            <#else>
            <@nav.login loginURL=loginURL />
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
