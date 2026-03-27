<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.util.*, java.text.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%! String getFormattedDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        return sdf.format (new Date ());
    }%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Приклад JSP сторінки</title></head>

<h1>Ласкаво просимо</h1>
<%-- Запити об'єктів (request object) --%>
<b>Поточна дата</b>: <% out.print(getFormattedDate()); %><br><br>
<%-- Додати cookie --%>
<b>User-Agent header</b>: <%= request.getHeader("User-Agent") %><br><br>

<%-- Add cookie --%>
<b>Значення параметра ServletName </b>: <%= config.getServletName() %><br><br>

<%-- ServletConfig --%>
<%-- Об'єкт application --%>
<b>Контейнер сервлетів підтримує версію</b> :

<%-- Application object --%>
<b>Servlet container supports version</b> :
<%-- Об'єкт session --%>
<b>Ідентифікатор сесії SessionID</b>: <%= session.getId() %><br><br>
<br><br>

<%-- Об'єкт pageContext --%>
<p>Встановлено атрибут pageContext:  ключ "Account", значення "Serg".</p>

<%-- PageContext object --%>
<b>Атрибут PageContext</b>: {
<% pageContext.setAttribute("Account", "Serg"); %>
<b>PageContext attribute</b>: {
<%-- Об'єкт page --%>
<b>Ім'я згенерованого класу сервлета</b>: <%= page.getClass().getName() %>

<%-- Page object --%>
<b>Generated servlet class name</b>: <%= page.getClass().getName() %>

</body>
</html>
