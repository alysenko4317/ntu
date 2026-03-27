import java.util.Scanner;

public class Program {

    static class Table implements Box
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

    static private final int COST_PER_KG = 10;
    static private final int COST_PER_KM = 10;

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);

        System.out.print("enter weight: ");
        double weight = s.nextDouble();
        System.out.print("enter distance: ");
        int distance = s.nextInt();

        Table smallTable = new Table(3, weight);
        Table bigTable = new Table(4, weight * 3);

        CostCalculator cc = new CostCalculator(COST_PER_KG, COST_PER_KM);

        double cost1 = cc.calcCost(smallTable, distance);
        double cost2 = cc.calcCost(bigTable, distance);

        System.out.println("calculated transfer cost for smallTable is: " + cost1);
        System.out.println("calculated transfer cost for bigTable is: " + cost2);
    }
}
