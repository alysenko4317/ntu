<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href=<c:url value="/css/styles.css" /> rel="stylesheet" type="text/css"></head>

<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Already registered users!</div>
    <table>
        <tr><th>Full Name (e-mail)</th></tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr><td>${user.firstName} ${user.lastName} (${user.email})</td></tr>
        </c:forEach>
    </table>
</div>
</body>

</html>
