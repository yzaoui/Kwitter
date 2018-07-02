<#-- @ftlvariable name="signUpHref" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<#-- @ftlvariable name="MIN_LENGTH_USERNAME" type="java.lang.Number" -->
<#-- @ftlvariable name="MAX_LENGTH_USERNAME" type="java.lang.Number" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Sign Up - Kwitter</title>

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/form.css">
</head>

<body class="text-center">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="/">Kwitter</a>
</nav>

<form class="centered-form" action="${signUpHref}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal">Create your account</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" name="username" class="form-control" minlength="${MIN_LENGTH_USERNAME}" maxlength="${MAX_LENGTH_USERNAME}" placeholder="Username" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <input type="text" name="displayName" class="form-control" placeholder="Display Name" required>
    <input type="email" name="email" class="form-control" placeholder="Email" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
</form>
</body>
</html>
