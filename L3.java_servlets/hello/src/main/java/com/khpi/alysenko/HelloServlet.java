package com.khpi.alysenko;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException
    {
        //super.doGet(request, response);
        PrintWriter writer = response.getWriter();
        // спочатку встановіть тип змісту та інші поля заголовків відповіді
        response.setContentType("text/html");
        writer.println("<h1>Hello!</h1><br>");
        writer.println("<h2><font color=red>Java is the best!</font></h2>");
        writer.close();
    }
}
