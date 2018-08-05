<#macro html>
<!DOCTYPE html>
<html lang="en">
<#nested />
</html>
</#macro>

<#macro head title>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>${title} - Kwitter</title>

    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/main.css">
</head>
</#macro>

<#macro body>
<body>
<#nested />
<script src="/assets/js/jquery-3.3.1.slim.min.js"></script>
<script src="/assets/js/popper.min.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
</body>
</#macro>
