<#-- @ftlvariable name="loginHref" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Login - Kwitter</title>

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/form.css">
</head>

<body class="text-center">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="/">Kwitter</a>
</nav>

<form class="centered-form" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal">Please log in</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
</body>
</html>
