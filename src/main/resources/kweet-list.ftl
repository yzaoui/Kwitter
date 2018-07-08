<#macro kweet_li kweet>
<#-- @ftlvariable name="kweet" type="kwitter.data.model.Kweet" -->
<li>${kweet.text} <strong>by @${kweet.username}</strong></li>
</#macro>

<#macro kweet_list kweets>
<ul>
    <#list kweets as kweet>
    <@kweet_li kweet=kweet />
    <#else>
    There are no kweets yet. Try following someone or posting your own!
    </#list>
</ul>
</#macro>
