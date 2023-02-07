<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Title</title>
    <link href=<c:url value="/css/styles.css" /> rel="stylesheet" type="text/css"></head>

<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Cars in database!</div>
    <table>
        <tr><th>Registration Number  (Model)</th></tr>
        <c:forEach items="${carsFromServer}" var="car">
            <tr><td><b>${car.registrationNumber} </b> (${car.model.name})</td></tr>
        </c:forEach>
    </table>
</div>
</body>

</html>
