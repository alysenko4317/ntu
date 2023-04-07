package com.khpi.app;

public class Application
{
    private String _str = "Hello";
    private String _arr[] = { "one", "two", "three" };

    String[] getArr() {
        return _arr;
    }

    String getStr() {
        return _str;
    }

    {
        System.out.println("1. initializer call");
        printAll();
    }

    Application() {
        System.out.println("2. constructor call");
    }

    void printAll() {
        System.out.println("_str = " + _str);
        System.out.println("_arr = {");
        for (String item : _arr) {
            System.out.println("  " + item);
        }
        System.out.println("}");
    }

    public static void typesTest()
    {
        double a = Long.MAX_VALUE;
        long b = Long.MAX_VALUE;
        int c = 1;
        System.out.println(a+b+c);  // 1.8446744073709552E19 (double)
        System.out.println(c+b+a);  // 0.0
        // ==========
        byte bb = 1;
        //bb = bb + 1;
        bb += 1;

    }

    public static void main(String[] args)
    {
        // just a comment but \u000a System.out.println("BU-GA-GA!!");

        typesTest();

        Application app = new Application();

        String[] refArr = app.getArr();
        refArr[0] = "oops";

        String refStr = app.getStr();
        refStr = "OOPS";

        app.printAll();
    }
}