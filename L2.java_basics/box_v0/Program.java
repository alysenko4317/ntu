
import java.util.Scanner;

public class Program
{
    public static void main(String[] args)
	{
        Scanner s = new Scanner(System.in);

        System.out.print("enter weight: ");
        double weight = s.nextDouble();

        System.out.print("enter distance: ");
        int distance = s.nextInt();

        double cost = 10 * distance + 10 * weight;
        System.out.println("calculated transfer cost is: " + cost);
    }
}
