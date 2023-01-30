package com.khpi.request;

import java.io.IOException;
import java.net.Authenticator;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestTools
{
    private void login(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String authTypeParam = request.getParameter("authType");

        // заменить
        //    Authenticator authenticator = new AuthenticatorImpl();
        // на:
        Authenticator authenticator = new SimpleAuthenticatorImpl();

        // заменить
        //    boolean isAuthentificated = false;
        // на:
        Person person;
        String password = request.getParameter("psw");
        String authValue = request.getParameter("loginValue");
        if (authTypeParam.equals("email")) {
            person = authenticator.authenticateByUserEmail(authValue, password);
        }
        else {
            person = authenticator.authenticateByUserName(authValue, password);
        }

        // заменить
        //    if (isAuthentificated) {
        // на:
        if (person != null)
        {
            HttpSession session = request.getSession();

            // добавить:
            person.setLoginDate(ProfileTools.generateLoginDate());
            getPersonDAO().save(person);

            // заменить
            //    session.setAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME, authValue);
            // на:
            session.setAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME, person.getName());

            // добавить
            if (ProfileTools.isAdmin(person))
            {
                // добавить
                session.setAttribute(ProfileTools.PERSON_IS_ADMIN, true);
                session.setAttribute(ProfileTools.ALL_PERSONS_ATTRIBUTE_NAME, getPersonDAO().getAll());
            }
            request.getSession().setAttribute("failedLoginAttemptsCount", 0); request.getSession().removeAttribute("blockedDate"); request.getSession().removeAttribute("blockedTime"); response.sendRedirect("home.jsp");
        }
        else
        {
            RequestTools.addLoginAttemptToSession(request, response);
            if (RequestTools.isBlocked(request, response)) {
                RequestTools.redirectToBlockedPage(request, response);
            }
            else {
                response.sendRedirect("error-login.jsp");
            }
        }
    }
}
