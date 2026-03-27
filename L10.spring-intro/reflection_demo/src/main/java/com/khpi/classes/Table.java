package com.khpi.classes;

public class Table
{
    public int _size;
    final public String color;
    private float _price = .5f;
    final private double _rate = .5;
    String _visibility = "qwerty";
    protected Integer _someValue = 32;

    public Table(int size, String color) {
        _size = size;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Table{" + "size=" + _size + ", color='" + color + '\'' + '}';
    }
}
