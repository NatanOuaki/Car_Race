import java.util.Scanner;
public class Car {
    private String manufacturerName;
    private double acceleration;
    private double maximumSpeed;

    Scanner sc = new Scanner (System.in);

    // _______________ Constructor Car _______________
    public Car (String manufacturerName, double acceleration, double maximumSpeed){
        this.manufacturerName = manufacturerName;
        this.acceleration= acceleration;
        this.maximumSpeed = maximumSpeed;
    }

    // _______________ Set and Get Car's Manufacturer Name _______________
    public void setName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
    public String getName() {
        return manufacturerName;
    }

    // _______________ Set and Get Car's Acceleration _______________
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }
    public double getAcceleration() {
        return acceleration;
    }

    // _______________ Set and Get Driver's Maximum Speed _______________
    public void setMaximumSpeed(double maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }
    public double getMaximumSpeed() {
        return maximumSpeed;
    }
}
