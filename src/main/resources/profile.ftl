<#-- @ftlvariable name="user" type="kwitter.data.model.User" -->
<#import "main.ftl" as main />
<#import "nav.ftl" as nav />
<@main.html>
<@main.head title="${user.displayName} (@${user.username})" />
<@main.body>
<@nav.nav />
</@main.body>
</@main.html>
