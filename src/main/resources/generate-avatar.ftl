<#-- @ftlvariable name="generateAvatarURL" type="String" -->
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
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
            <form action="${generateAvatarURL}" method="post">
                <button type="submit">Generate Avatar</button>
            </form>
        </div>
    </main>
</@main.body>
</@main.html>
