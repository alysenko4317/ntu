package com.khpi.alysenko;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CostCalcServlet extends HttpServlet
{
    static private final int COST_PER_KG = 10;
    static private final int COST_PER_KM = 10;

    public class Table implements Box
    {
        private double _weight;
        private int _countOfLegs;

        public Table(int countOfLegs, double weight) {
            _weight = weight;
            _countOfLegs = countOfLegs;
        }

        public double getWeight()  {
            return _weight;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();

        final String d = request.getParameter("d");
        final String w = request.getParameter("w");
        if (d == null || w == null)
        {
            writer.println("ERROR: Bad parameters!");
            return;
        }

        final float weight = Float.parseFloat(w);
        final int distance = Integer.parseInt(d);
        if (weight < 0.1 || distance <= 0)
        {
            writer.println("ERROR: Bad parameters!");
            return;
        }

        Table smallTable = new Table(3, weight);
        Table bigTable = new Table(4, weight * 3);

        CostCalculator cc = new CostCalculator(COST_PER_KG, COST_PER_KM);

        double cost1 = cc.calcCost(smallTable, distance);
        double cost2 = cc.calcCost(bigTable, distance);

        writer.println("<h1>COST:</h1><br>");
        writer.println("calculated transfer cost for smallTable is: " + cost1 + "<br>");
        writer.println("calculated transfer cost for bigTable is: " + cost2);
    }
}
