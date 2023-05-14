import java.util.Scanner;
public class Drivers {
    private String name;
    private double breakingRate;
    private double timeDelay;

    Scanner sc = new Scanner (System.in);

    // _______________ Constructor Drivers _______________
    public Drivers (String name, double breakingRate, double timeDelay){
        this.name = name;
        this.breakingRate= breakingRate;
        this.timeDelay = timeDelay;
    }

    // _______________ Set and Get Driver's Name _______________
    public void setName(String name) {
        this.name = name;
    }           // Set Driver's Name
    public String getName() {
        return name;
    }                         //Get Driver's Name

    // _______________ Set and Get Driver's Breaking Rate _______________
    public void setBreakingRate(double breakingRate) {
        this.breakingRate = breakingRate;
    } //Set Driver's Breaking Rate
    public double getBreakingRate() {
        return breakingRate;
    }       //Get Driver's Breaking Rate

    // _______________ Set and Get Driver's Time Delay _______________
    public void setTimeDelay(double timeDelay ) {
        this.timeDelay = timeDelay;
    }   //Set Driver's Time Delay
    public double getTimeDelay() {
        return timeDelay;
    }           //Get Driver's Time Delay
}


