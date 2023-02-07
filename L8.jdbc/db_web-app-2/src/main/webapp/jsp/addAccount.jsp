<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href=<c:url value="/css/styles.css" /> rel="stylesheet" type="text/css"></head>

<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please add user</div>
    <form method="post" action="/users">
        <label for="first-name">First Name
            <input class="input-field" type="text" id="first-name" name="first-name"></label>
        <label for="last-name">Last Name
            <input class="input-field" type="text" id="last-name" name="last-name"></label>
        <label for="e-mail">E-mail
            <input class="input-field" type="text" id="e-mail" name="last-name"></label>
        <label for="password">Password
            <input class="input-field" type="text" id="password" name="last-name"></label>
        <input type="submit" value="Add user">
    </form></div>

</body>
</html>
