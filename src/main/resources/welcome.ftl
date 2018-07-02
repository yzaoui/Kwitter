<#-- @ftlvariable name="signUpHref" type="String" -->
<#-- @ftlvariable name="loginHref" type="String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Welcome - Kwitter</title>

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="/">Kwitter</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
        </ul>
        <form class="form-inline mt-2 mt-md-0" action="${loginHref}" method="post" enctype="application/x-www-form-urlencoded">
            <input type="text" name="username" class="form-control mr-sm-2" placeholder="Username" aria-label="Username" required>
            <input type="password" name="password" class="form-control mr-sm-2" placeholder="Password" aria-label="Password" required>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log in</button>
        </form>
    </div>
</nav>

<main role="main" class="container">
    <div class="jumbotron">
        <h1>Welcome to Kwitter!</h1>
        <p class="lead">This is a clone of some social media site no one's really heard of.</p>
        <a class="btn btn-lg btn-primary" role="button" href="${signUpHref}">Sign up</a>
        <a class="btn btn-lg btn-primary" role="button" href="${loginHref}">Log in</a>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
</html>
