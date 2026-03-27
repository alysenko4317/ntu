package com.khpi.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter
 * Клас-фільтр, що обробляє запит до того, як його оброблять сервети.
 */
@WebFilter("/home")
public class AuthFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    // на вхід фільтр отримує запит, відповідь, а також ланцюжок фільтрів,
    // яким слід передати запит далі.
    // to which the request should be passed next.
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain chain)
        // виконуємо перетворення Servlet-запитів/відповідей у HTTP-запити/відповіді
    {
        // cast Servlet request/response to HTTP request/response
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // дивимось, чи є сесія для цього запиту (перевіряється наявність Cookie
        // з назвою JSESSIONID
        // named JSESSIONID)
        // якщо сесію не створено або в сесії відсутній атрибут user,
        // перенаправляємо користувача на сторінку логіну
        // redirect the user to the login page
        if (session == null || session.getAttribute("user") == null)
        {
            servletRequest.getServletContext()
                          .getRequestDispatcher("/login")
        // передаємо запит далі в ланцюжок фільтрів
        }

        // pass the request further along the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
