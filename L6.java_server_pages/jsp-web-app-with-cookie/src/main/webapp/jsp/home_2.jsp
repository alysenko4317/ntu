<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title></head>

<body>
<%--Колір тексту беремо зі значення cookie color --%>
<span style="color: ${cookie.color.value}">Hello</span>

<%--NOT we are using 'home', not '/home' as we want relative url --%>
<form method="post" action="home">
    <label for="color">
        <select name="color" id="color">
            <option value="red">Червоний</option>
            <option value="black">Чорний</option>
            <option value="white">Білий</option>
        </select>
    </label>
    <input type="submit" value="Color send">
</form>

</body>
</html>
