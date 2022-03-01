package model;

public class Driver {
    private String name;
    private String nic;
    private String drivingLicenseNo;
    private String address;
    private String contact;

    public Driver() {
    }

    public Driver(String name, String nic, String drivingLicenseNo, String address, String contact) {
        this.name = name;
        this.nic = nic;
        this.drivingLicenseNo = drivingLicenseNo;
        this.address = address;
        this.contact = contact;
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

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", nic='" + nic + '\'' +
                ", drivingLicenseNo='" + drivingLicenseNo + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
