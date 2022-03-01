package view.tm;

import javafx.scene.control.Button;
import model.Driver;

public class DriverTM {
    private String name;
    private String nic;
    private String drivingLicenseNo;
    private String address;
    private String contact;
    private Button btn;

    public DriverTM() {
    }

    public DriverTM(String name, String nic, String drivingLicenseNo, String address, String contact, Button btn) {
        this.name = name;
        this.nic = nic;
        this.drivingLicenseNo = drivingLicenseNo;
        this.address = address;
        this.contact = contact;
        this.btn = btn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "DriverTM{" +
                "name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", drivingLicenseNo='" + drivingLicenseNo + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", btn=" + btn +
                '}';
    }
}