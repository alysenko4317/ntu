package com.khpi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        writer.println("<h1>Hello!</h1><br>");
        writer.println("<h2><font color=red>Java is the best!</font></h2>");
        writer.println("<a href='signup'>registered users</a>");
    }
}
