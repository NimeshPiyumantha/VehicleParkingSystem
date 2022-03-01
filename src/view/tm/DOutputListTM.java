package view.tm;

import javafx.scene.control.TableColumn;

public class DOutputListTM {
   private String VehicleNumber;
   private String VehicleType;
   private String DriverName;
   private  String  LeftTime;

    public DOutputListTM() {
    }

    public DOutputListTM(String vehicleNumber, String vehicleType, String driverName, String leftTime) {
        VehicleNumber = vehicleNumber;
        VehicleType = vehicleType;
        DriverName = driverName;
        LeftTime = leftTime;
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

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getLeftTime() {
        return LeftTime;
    }

    public void setLeftTime(String leftTime) {
        LeftTime = leftTime;
    }

    @Override
    public String toString() {
        return "DOutputListTM{" +
                "VehicleNumber='" + VehicleNumber + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                ", DriverName='" + DriverName + '\'' +
                ", LeftTime='" + LeftTime + '\'' +
                '}';
    }
}
