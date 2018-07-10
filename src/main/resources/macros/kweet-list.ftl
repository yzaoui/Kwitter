<#macro kweet_li htmlKweet>
<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<li class="kweet">
    <div class="kweet-inside">
        <div class="kweet-head">
            <a class="kweet-author-group" href="#">
                <img class="kweet-author-img" src="${htmlKweet.authorProfilePictureURL}">
                <span class="kweet-author-display-name"><strong>${htmlKweet.authorDisplayName}</strong></span>
                <span class="kweet-author-username">@${htmlKweet.authorUsername}</span>
            </a>
            <span class="kweet-time"><a href="#">${htmlKweet.dateText}</a></span>
        </div>
        <div class="kweet-body"><p class="kweet-content">${htmlKweet.html}</p></div>
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
