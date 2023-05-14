import static java.lang.Math.sqrt;
import java.util.Scanner;

public class Race {
    private int numberOfSegments;           // Variable that will receive the number of segment from user
    private double segmentsLength[];        // Variable that will receive the length of segment from user
    private int numberOfParticipants; // Variable that will receive the number of participants from user
    Drivers[] drivers; //create an array to hold the drivers
    Car[] cars;            //create an array to hold the cars
    Scanner sc = new Scanner (System.in);
    private static int totalInstantiatedRaces = 0;

    public Race(){
        this.numberOfSegments = 0;
        this.segmentsLength = new double[numberOfSegments];
        this.numberOfParticipants = 0;
        this.drivers = new Drivers[numberOfParticipants];
        this.cars = new Car[numberOfParticipants];
        totalInstantiatedRaces++;
    }

    public void getRouteFromSysIn(){
        // A procedure to get a route from the user.
        do{
        System.out.println("How many segments does the route contain?");
        numberOfSegments = sc.nextInt();        // Get from user number of segment
        if(numberOfSegments <= 0){               // Check if the input is legal
            System.out.println("Input must be a positive integer, got " + numberOfSegments);
        }
        } while(numberOfSegments <= 0);              // repeat while the number is not legal

        segmentsLength = new double[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++){
            System.out.println("What is the length of segment "+ i + "?");
            segmentsLength[i] = sc.nextDouble();         // Get from user length of each segment
            while (segmentsLength[i]<=0){                 // repeat while the number is not legal
                System.out.println("Input must be a positive double, got "+ segmentsLength[i]);
                System.out.println("What is the length of segment "+ i + "?");
                segmentsLength[i] = sc.nextDouble();     // Get from user length of each segment
            }
        }
    }

    public void getDriversAndCarsFromSysIn(){
        // A procedure to get the lists of drivers and cars from the user.
        do{
            System.out.println("How many participants are there in the race?");
            numberOfParticipants = sc.nextInt();        // Get from user number of participants
            if(numberOfParticipants <= 0){               // Check if the input is legal
                System.out.println("Input must be a positive integer, got " + numberOfParticipants);
            }
        } while(numberOfParticipants < 0);             // repeat while the number is not legal


        drivers = new Drivers[numberOfParticipants];
        for (int i =0; i<numberOfParticipants; i++){           // loop to get information for each driver
            System.out.println("Please enter details for driver " + i);
            System.out.println("What is the driver's name:");
            String name = sc.next();
            System.out.println("What is the driver's breaking rate:");
            double breakingRate = sc.nextDouble();
            System.out.println("What is the driver's delay time:");
            double timeDelay = sc.nextDouble();
            drivers[i] = new Drivers(name,breakingRate,timeDelay); // create a new driver object and add it to the array
        }

        cars = new Car[numberOfParticipants];
        for(int i =0; i<numberOfParticipants; i++){             // loop to get information for each car
            System.out.println("Please enter details for car " + i);
            System.out.println("What is the car's manufacturer:");
            String manufacturer = sc.next();
            System.out.println("What is the car's acceleration:");
            double acceleration = sc.nextDouble();
            System.out.println("What is the driver's maximum speed:");
            double maximumSpeed = sc.nextDouble();
            cars[i] = new Car(manufacturer,acceleration,maximumSpeed); // create a new car object and add it to the array
        }
    }

    public void runRace() {
        // A procedure to compute and print the time which took each user to
        // complete the route, and to print the winner.
        double times[] = new double[numberOfParticipants];
        for (int i =0; i < numberOfParticipants; i++) {
            Drivers driver = drivers[i];
            Car car = cars[i];
            double time = 0.0;

            // Acceleration phase
            double acceleration = car.getAcceleration();    // get Acceleration of the car
            double v = car.getMaximumSpeed();           // get maximum speed of the car
            double accelerationTime = v / acceleration;             // calculate the time to get to max speed
            double xStep1 = (0.5) * v * accelerationTime;           // calculate the distance of acceleration phase

            // Breaking phase
            double breakingRate = driver.getBreakingRate();             //get breakingRate of the driver
            double breakingTime = v / breakingRate;                 // calculate the breaking time
            double xStep3 = (0.5) * v * breakingTime;               // calculate the distance of breaking phase


            // check if the segment is long enough for all 3 phases
                for (int j = 0; j < numberOfSegments; j++) {
                    boolean canDoMaxSpeedPhase = (segmentsLength[j] - xStep1 - xStep3) >= 0;
                    double segmentLength = segmentsLength[j];
                    if (canDoMaxSpeedPhase) {
                        // Max speed phase
                        double xStep2 = segmentLength - xStep1 - xStep3; // calculate distance of the max speed phase
                        double maxSpeedTime = xStep2 / v; // calculate the max speed time phase

                        // Calculate each segment time
                        time += accelerationTime + maxSpeedTime + breakingTime;
                    }
                    else { // do acceleration and breaking phases only
                        // Calculate each segment time using acceleration and breaking only
                        // Acceleration phase
                        xStep1 = (breakingRate * segmentLength) / (acceleration + breakingRate);
                        xStep3 = segmentLength - xStep1;

                        accelerationTime = sqrt((2*xStep1)/acceleration);             // calculate the time of acceleration phase

                        // Breaking phase
                        breakingTime = sqrt((2*xStep3)/breakingRate);               // calculate the breaking time

                        time += accelerationTime + breakingTime;
                    }
            }
            time += driver.getTimeDelay() * (numberOfSegments - 1);
            times[i] = time; //store the time in the array
            System.out.println(driver.getName() + "," + car.getName() + "," + times[i]);    // Display each drive + car + time
        }

        // Compare all the times and get the smallest
        int smallTime = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[smallTime]) {
                smallTime = i;
            }
        }
        System.out.println("The winner is " + drivers[smallTime].getName() + "!");          //Display the winner
    }

    public void runAllPairsRace() {
        // A similar procedure to runRace, which consider all driver-car pairs.

        double times[] = new double[numberOfParticipants];
        double fastestTime = Double.MAX_VALUE;
        String winnerName = "";
        String winnerCarName = "";
        for (int i =0; i < numberOfParticipants; i++) {
            Drivers driver = drivers[i];
            for (int j = 0; j < cars.length; j++) {
                Car car = cars[j];

                double time = 0.0;
                // Acceleration phase
                double acceleration = car.getAcceleration();    // get Acceleration of the car
                double v = car.getMaximumSpeed();           // get maximum speed of the car
                double accelerationTime = v / acceleration;             // calculate the time to get to max speed
                double xStep1 = (0.5) * v * accelerationTime;           // calculate the distance of acceleration phase

                // Breaking phase
                double breakingRate = driver.getBreakingRate();             //get breakingRate of the driver
                double breakingTime = v / breakingRate;                 // calculate the breaking time
                double xStep3 = (0.5) * v * breakingTime;               // calculate the distance of breaking phase

                for (int k = 0; k < numberOfSegments; k++) {
                    double segmentLength = segmentsLength[k];
                    // Max speed phase
                    double xStep2 = segmentLength - xStep1 - xStep3;            // calculate distance of the max speed phase
                    double maxSpeedTime = xStep2 / v;                       // calculate the max speed time phase

                    // Calculate each segment time
                    time += accelerationTime + maxSpeedTime + breakingTime;
                }
                time += driver.getTimeDelay() * (numberOfSegments -1);
                times[i] = time;        //store the time in the array
                System.out.println(driver.getName() + "," + car.getName() + "," + times[i]);    // Display each drive + car + time
                if (time < fastestTime) {
                    fastestTime = time;
                    winnerName = driver.getName();
                    winnerCarName = car.getName();
                }
            }
        }
        System.out.println("The winning pair is " + winnerName + " and " + winnerCarName
                + " ,who completed the race in " + fastestTime +" seconds !");          //Display the winning pair

    }

    public void printTotalInstantiatedRaces() {
        // Prints the total number of instantiated Race objects throughout the
        // run of the program.
        System.out.println("The number of constructed Race instances is " + totalInstantiatedRaces + ".");
    }

    public void runRaceWithSortedOutput() {
        // A bonus question: same as runRace, with the exception that the list
        // of drivers should be printed with increasing times.
        double times[] = new double[numberOfParticipants];
        for (int i = 0; i < numberOfParticipants; i++) {
            Drivers driver = drivers[i];
            Car car = cars[i];
            double time = 0.0;
            // Acceleration phase
            double acceleration = car.getAcceleration();    // get Acceleration of the car
            double v = car.getMaximumSpeed();           // get maximum speed of the car
            double accelerationTime = v / acceleration;             // calculate the time to get to max speed
            double xStep1 = (0.5) * v * accelerationTime;           // calculate the distance of acceleration phase

            // Breaking phase
            double breakingRate = driver.getBreakingRate();             //get breakingRate of the driver
            double breakingTime = v / breakingRate;                 // calculate the breaking time
            double xStep3 = (0.5) * v * breakingTime;               // calculate the distance of breaking phase

            for (int j = 0; j < numberOfSegments; j++) {
                double segmentLength = segmentsLength[j];
                // Max speed phase
                double xStep2 = segmentLength - xStep1 - xStep3;            // calculate distance of the max speed phase
                double maxSpeedTime = xStep2 / v;                       // calculate the max speed time phase

                // Calculate each segment time
                time += accelerationTime + maxSpeedTime + breakingTime;
            }
            time += driver.getTimeDelay() * (numberOfSegments - 1);
            times[i] = time;        //store the time in the array
        }

        // Sort the times array along with the drivers and cars arrays using insertion sort
        for (int i = 1; i < numberOfParticipants; i++) {
            double key = times[i];
            Drivers driver = drivers[i];
            Car car = cars[i];
            int j = i - 1;
            while (j >= 0 && times[j] > key) {
                times[j + 1] = times[j];
                drivers[j + 1] = drivers[j];
                cars[j + 1] = cars[j];
                j--;
            }
            times[j + 1] = key;
            drivers[j + 1] = driver;
            cars[j + 1] = car;
        }

        // Print the sorted results
        for (int i = 0; i < numberOfParticipants; i++) {
            System.out.println(drivers[i].getName() + " , " + cars[i].getName() + " , " + times[i]);
        }
    }
}
