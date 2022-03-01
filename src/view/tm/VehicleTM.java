package view.tm;

import javafx.scene.control.Button;

public class  VehicleTM {
    private String vehicleNumber;
    private String vehicleType;
    private double maxWeight;
    private int NoPassengers;

    public VehicleTM() {
    }

    public VehicleTM(String vehicleNumber, String vehicleType, double maxWeight, int noPassengers) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maxWeight = maxWeight;
        NoPassengers = noPassengers;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getNoPassengers() {
        return NoPassengers;
    }

    public void setNoPassengers(int noPassengers) {
        NoPassengers = noPassengers;
    }

    @Override
    public String toString() {
        return "VehicleTM{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", maxWeight=" + maxWeight +
                ", NoPassengers=" + NoPassengers +
                '}';
    }
}
