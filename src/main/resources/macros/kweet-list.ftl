<#macro kweet_li kweet>
<#-- @ftlvariable name="kweet" type="kwitter.data.model.Kweet" -->
<li class="kweet">
    <div>${kweet.text} <strong>by @${kweet.username}</strong></div>
</li>
</#macro>

<#macro kweet_list kweets>
<ol class="kweet-list">
    <#list kweets as kweet>
    <@kweet_li kweet=kweet />
    <#else>
    There are no kweets yet. Try following someone or posting your own!
    </#list>
</ol>
</#macro>
