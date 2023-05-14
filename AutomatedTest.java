import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AutomatedTest {

    public static void main(String[] args) {
        // Defining the routes:
        String[] route1 = new String[]{"42", "35", "17.8"}; // route segment lengths;
        String[] route2 = new String[]{"4", "5.1", "2"}; // route segment lengths;

        // Defining the drivers:
        String[][] drivers = new String[][]{
                {"Kate", "4", "2.2"},
                {"Suzan", "2.7", "1.1"},
                {"John", "3", "2.5"},
                {"Paul", "1.5", "6.4"}
        };

        // Defining the cars:
        String[][] cars = new String[][]{
                {"Porsche", "6", "2.7"},
                {"Lamborghini", "3", "4"},
                {"Bugatti", "8", "4.3"},
                {"Sussita", "2.5", "3.5"}
        };

        // Formatting the input which will mimic user behaviour. For the first
        // route, we add two illegal inputs (-3 and 0) as route lengths
        String route1Str = "-3\n0\n" + formatParams(route1, true);
        String route2Str = formatParams(route2, true);

        String[] driverStrArr = new String[drivers.length];
        for (int i = 0; i < drivers.length; i++) {
            driverStrArr[i] = formatParams(drivers[i], false);
        }
        String driverStr = formatParams(driverStrArr, true);

        String[] carStrArr = new String[cars.length];
        for (int i = 0; i < cars.length; i++) {
            carStrArr[i] = formatParams(cars[i], false);
        }
        String carStr = formatParams(carStrArr, false);

        String q1Input = formatParams(new String[]{route1Str, driverStr, carStr, route2Str}, false);

        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(q1Input.getBytes()));

            // Testing question 1 A:
            System.out.println("Question 1 A:");
            System.out.println("-------------");
            Race race1 = new Race();
            race1.getRouteFromSysIn();
            race1.getDriversAndCarsFromSysIn();
            race1.runRace();

            // Testing question 1 B:
            System.out.println("\nQuestion 1 B:");
            System.out.println("-------------");
            race1.getRouteFromSysIn();
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
        } finally {
            System.setIn(stdin);
        }


    }


    private static String formatParams(String[] params, boolean addLength){
        // An auxiliary function in order to replace user input via System.in
        // by predefined values:
        String formatted = "";

        if (addLength) {
            formatted += params.length + "\n";
        }

        for (String param : params) {
            formatted += param + "\n";
        }

        return formatted;

    }

}
