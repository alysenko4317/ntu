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
    <title>Пример JSP страницы</title></head>

<body>
<h1>Добро пожаловать</h1>
<%-- Запросы объектов (request object) --%>
<b>Текущая дата</b>: <% out.print(getFormattedDate()); %><br><br>
<b>Заголовок User-Agent</b>: <%= request.getHeader("User-Agent") %><br><br>

<%-- Добавить cookie --%>
<% response.addCookie(new Cookie("Test","Value")); %>

<%-- ServletConfig --%>
<b>Значение параметра ServletName </b>: <%= config.getServletName() %><br><br>

<%-- Объект application --%>
<b>Контейнер сервлетов поддерживает версию</b> :
<%-- out.print(application.getMajorVersion() + "." +  application.getMinorVersion()) --%>
<br><br>

<%-- Объект session --%>
<b>Идентификатор сессии SessionID</b>: <%= session.getId() %><br><br>

<%-- Объект pageContext --%>
<p>Установлен атрибут pageContext:  ключ "Account", значение "Serg".</p>
<% pageContext.setAttribute("Account", "Serg"); %>
<b>Атрибут PageContext</b>: {
Key = "Account", Value = "<%= pageContext.getAttribute("Account") %>"}<br><br>

<%-- Объект page --%>
<b>Имя сгенерированного класса сервлета</b>: <%= page.getClass().getName() %>

</body>
</html>
