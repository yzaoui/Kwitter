<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="htmlKweets" type="kotlin.collections.List<kwitter.freemarker.HTMLKweet>" -->
<#-- @ftlvariable name="loginURL" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<#-- @ftlvariable name="generateAvatarURL" type="String" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<#import "macros/kweet-list.ftl" as kweetList>
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
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
    <main role="main" class="timeline-main">
        <div class="profile-info-container">
            <div class="profile-image">
                <img class="profile-image" src="${user.profilePictureURL}">
            </div>
            <div class="profile-details">
                <div class="profile-display-name"><h2>${user.displayName}</h2></div>
                <div class="profile-username">@${user.username}</div>
            </div>
        </div>
        <div>
            <@kweetList.kweet_list htmlKweets=htmlKweets />
        </div>
    </main>
</@main.body>
</@main.html>
