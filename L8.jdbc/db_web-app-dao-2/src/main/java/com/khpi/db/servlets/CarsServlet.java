package com.khpi.db.servlets;

import com.khpi.db.dao.CRUD;
import com.khpi.db.dao.CarDAO;
import com.khpi.db.dao.CarDAO_JdbcImpl;
import com.khpi.db.models.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars")
public class CarsServlet extends BaseServlet
{
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)
        throws ServletException, IOException
    {
        CarDAO dao = new CarDAO_JdbcImpl(getDataSource());

        final String firstName = rq.getParameter("acc");
        if (firstName != null)
        {
            rq.setAttribute("carsFromServer", dao.findAllByOwner(firstName));
        }
        else
        {
            rq.setAttribute("carsFromServer", dao.fetchAll());
        }

        rq.getServletContext()
            .getRequestDispatcher("/jsp/cars.jsp")
            .forward(rq, rs);
    }
}
