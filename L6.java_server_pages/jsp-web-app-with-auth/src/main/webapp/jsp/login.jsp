<%@ page import="java.util.ArrayList" %>
<%@ page import="com.khpi.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"></head>

<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!</div>
    <form method="post" action=<c:url value="/login" />>
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name"></label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password"></label>
        <input type="submit" value="Sign In">
        <a href=<c:url value="/signup" />>Sign Up</a>
    </form>
</div>

</body>
</html>
