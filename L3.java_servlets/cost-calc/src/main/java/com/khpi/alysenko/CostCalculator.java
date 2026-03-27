package com.khpi.alysenko;

public class CostCalculator
{
    private double _kgCost;
    private double _kmCost;

    public CostCalculator(double kgCost, double kmCost)
    {
	    this._kgCost = kgCost;
	    this._kmCost = kmCost;
    }

    public double calcCost(Box box, int distance)
    {
	    return _kmCost * distance + _kgCost * box.getWeight();
    }
}
