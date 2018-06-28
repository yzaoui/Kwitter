<#-- @ftlvariable name="loginHref" type="String" -->
<#-- @ftlvariable name="errorMessage" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Login - Kwitter</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="static/login.css" rel="stylesheet">
</head>

<body class="text-center">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="/">Kwitter</a>
</nav>

<form id="login-form" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
    <h1 class="h3 mb-3 font-weight-normal">Please log in</h1>
    <#if errorMessage??>
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </#if>
    <input type="text" class="form-control" placeholder="Username" required autofocus>
    <input type="password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>
</body>
</html>
