package model;

import java.io.IOException;

public abstract class Vehicle {

    private String vehicleNumber;
    private String vehicleType;
    private double maxWeight;
    private int NoPassengers;
    private String Status;


    public Vehicle() {
    }

    public Vehicle(String vehicleNumber, String vehicleType, double maxWeight, int noPassengers) {
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", maxWeight=" + maxWeight +
                ", NoPassengers=" + NoPassengers +
                '}';
    }

    abstract public int park(String vehicleNumber, String VehicleType) throws IOException;
}