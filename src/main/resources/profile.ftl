<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#import "macros/main.ftl" as main />
<#import "macros/nav.ftl" as nav />
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
<@main.body>
<@nav.nav />
</@main.body>
</@main.html>
