package view.tm;

import javafx.scene.control.TableColumn;

public class POutputListTM {
   private String VehicleNumber;
   private String VehicleType;
   private Integer ParkingSlot;
   private String ParkedTime;

    public POutputListTM() {
    }

    public POutputListTM(String vehicleNumber, String vehicleType, Integer parkingSlot, String parkedTime) {
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        ParkingSlot = parkingSlot;
        ParkedTime = parkedTime;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public Integer getParkingSlot() {
        return ParkingSlot;
    }

    public void setParkingSlot(Integer parkingSlot) {
        ParkingSlot = parkingSlot;
    }

    public String getParkedTime() {
        return ParkedTime;
    }

    public void setParkedTime(String parkedTime) {
        ParkedTime = parkedTime;
    }

    @Override
    public String toString() {
        return "POutputListTM{" +
                "VehicleNumber='" + VehicleNumber + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", ParkingSlot=" + ParkingSlot +
                ", ParkedTime='" + ParkedTime + '\'' +
                '}';
    }
}
