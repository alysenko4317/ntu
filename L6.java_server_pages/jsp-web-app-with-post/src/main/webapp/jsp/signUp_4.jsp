<%@ page import="java.util.ArrayList" %>
<%@ page import="com.khpi.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href="css/styles_1.css" rel="stylesheet" type="text/css"></head>

<body>
<div class="form-style-4">
    <div class="form-style-4-heading">
        Please Sign Up!</div>
    <form method="post" action="signup">
        <label for="name">User name
            <input class="input-field" type="text" id="name" name="name"></label>
        <label for="birthDate">Birth date
            <input class="input-field" type="text" id="birthDate" name="birthDate"></label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password"></label>
        <input type="submit" value="Sign Up">
    </form>
</div>

<div class="form-style-4">
    <div class="form-style-4-heading">
        Already registered users!</div>
    <table>
        <tr><th>User name</th>
            <th>Birth Date</th></tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td><c:out value="${user.name}" /></td>
                <td><c:out value="${user.birthDate}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
