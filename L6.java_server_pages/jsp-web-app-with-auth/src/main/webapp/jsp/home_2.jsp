<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%
    // this is not working in Tomcat 8.5.x + JDK17 with error
    //     System.out cannot be resolved to a type in JSPs
    // but works fine in Tomcat 9.0.x with the same JDK
    System.out.println("jsp.session.user = " + session.getAttribute("user"));
%>

<html>
<head>
    <title>Title</title></head>

<body>
<%--Цвет текста берем из значения куки color --%>
<span style="color: ${cookie.color.value}">Hello!</span><br><br>
<p>Идентификатор сессии SessionID:<b> <%= session.getId() %></b></p><br>
<p>You are logged in as:<b>${user}</b></p><br>
<%--Явно указываем scope --%>
<p>You are logged in as:<b>${sessionScope.user}</b></p><br><br>
<%--NOT we are using 'home', not '/home' as we want relative url --%>
<form method="post" action="home">
    <label for="color">
        <select name="color" id="color">
            <option value="red">Красный</option>
            <option value="black">Черный</option>
            <option value="white">Белый</option>
        </select>
    </label>
    <input type="submit" value="Color send">
</form>

</body>
</html>
