<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tecky Job Portal</title>
</head>
<body>
    ${username} : ${sid}
    <a href="http://localhost:9091/auth/realms/master/protocol/openid-connect/auth?client_id=resource-server-client&response_mode=fragment&response_type=code&login=true&redirect_uri=http://localhost:8082/user">Login with Keycloak</a>
</body>
</html>