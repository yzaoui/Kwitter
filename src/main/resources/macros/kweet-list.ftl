<#macro kweet_li htmlKweet>
<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<li class="kweet">
    <div class="content">
        <div class="kweet-head"><a href="">
            <img class="kweet-author-img" src="${htmlKweet.authorProfilePictureURL}">
            <span><strong>${htmlKweet.authorDisplayName}</strong></span>
            <span class="kweet-author-username">@${htmlKweet.authorUsername}</span>
        </a></div>
        <div class="kweet-body">${htmlKweet.html}</div>
    </div>
</li>
</#macro>

<#macro kweet_list htmlKweets>
<ol class="kweet-list">
    <#list htmlKweets as htmlKweet>
    <@kweet_li htmlKweet=htmlKweet />
    <#else>
    There are no kweets yet. Try following someone or posting your own!
    </#list>
</ol>
</#macro>
