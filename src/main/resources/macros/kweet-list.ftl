<#macro kweet_li htmlKweet>
<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<li class="kweet">
    <div class="kweet-inside">
        <div class="kweet-head">
            <a class="kweet-author-group" href="${htmlKweet.authorProfileURL}">
                <img class="kweet-author-img" src="${htmlKweet.authorProfilePictureURL}">
                <span class="kweet-author-display-name"><strong>${htmlKweet.authorDisplayName}</strong></span>
                <span class="kweet-author-username">@${htmlKweet.authorUsername}</span>
            </a>
            <span class="kweet-time"><a href="${htmlKweet.kweetURL}">${htmlKweet.dateText}</a></span>
        </div>
        <div class="kweet-body">
            <p class="kweet-content">${htmlKweet.html}</p>
        </div>
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

<#macro kweet_individual htmlKweet>
<#-- @ftlvariable name="htmlKweet" type="kwitter.freemarker.HTMLKweet" -->
<div style="background-color: white; padding: 30px 40px">
    <div class="ikweet-head">
        <a class="ikweet-author-group" href="${htmlKweet.authorProfileURL}">
            <img class="ikweet-author-img" src="${htmlKweet.authorProfilePictureURL}">
            <strong class="ikweet-author-display-name">${htmlKweet.authorDisplayName}</strong>
            <span class="ikweet-author-username">@${htmlKweet.authorUsername}</span>
        </a>
    </div>
    <div class="ikweet-body">
        <p class="ikweet-content">${htmlKweet.html}</p>
    </div>
    <div class="ikweet-foot">
        <span class="ikweet-time">${htmlKweet.dateText}</span>
    </div>
</div>
</#macro>
