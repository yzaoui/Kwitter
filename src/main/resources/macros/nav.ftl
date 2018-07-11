<#macro main>
<nav class="navbar navbar-expand navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="/">Kwitter</a>
    <#nested />
</nav>
</#macro>

<#macro items>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>
<div class="collapse navbar-collapse" id="navbarCollapse">
    <ul class="navbar-nav mr-auto">
    </ul>
    <#nested />
</div>
</#macro>
