import java.io.ByteArrayInputStream;

public class Test {

    public static void main(String[] args) {
        System.out.println("Question 1:");
        System.out.println("-------------");
        Race race1 = new Race();
        race1.getRouteFromSysIn();
        race1.getDriversAndCarsFromSysIn();
        race1.runRace();

        // Testing question 2:
        System.out.println("\nQuestion 2:");
        System.out.println("-----------");
        race1.runAllPairsRace();

        // Testing question 3:
        System.out.println("\nQuestion 3:");
        System.out.println("-----------");
        Race race2 = new Race();
        Race race3 = new Race();
        race1.printTotalInstantiatedRaces();
    }
}
