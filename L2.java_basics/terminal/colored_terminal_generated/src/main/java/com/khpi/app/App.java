package com.khpi.app;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

		ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
		    .foreground(FColor.WHITE)
		    .background(BColor.RED)
		    .build();

        cp.setAttribute(Attribute.LIGHT);
		cp.println("Hello World!");
    }
}
