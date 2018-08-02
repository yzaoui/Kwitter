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
    <ul class="navbar-nav mr-auto"></ul>
    <#nested />
</div>
</#macro>

<#macro login loginURL>
<#-- @ftlvariable name="loginURL" type="String" -->
<form class="form-inline mt-2 mt-md-0" action="${loginURL}" method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name="username" class="form-control mr-sm-2" placeholder="Username" aria-label="Username" required>
    <input type="password" name="password" class="form-control mr-sm-2" placeholder="Password" aria-label="Password" required>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
</form>
</#macro>

<#macro nonguest loggedInUser loggedInUserURL logoutURL>
<#-- @ftlvariable name="loggedInUser" type="kwitter.data.model.User" -->
<#-- @ftlvariable name="loggedInUserURL" type="String" -->
<#-- @ftlvariable name="logoutURL" type="String" -->
<a href="${loggedInUserURL}" style="margin-right: 16px">
    <img src="${loggedInUser.profilePictureURL}" alt="Profile picture" style="width: 40px; height: 40px">
</a>
<form class="form-inline mt-2 mt-md-0" action="${logoutURL}" method="post" enctype="application/x-www-form-urlencoded">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
</form>
</#macro>
