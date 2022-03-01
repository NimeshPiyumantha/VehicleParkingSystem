package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import util.ValidationUtil;
import view.tm.DriverTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class AddDriverFromController implements Initializable {
    public static ArrayList<Driver> driverArrayList = new ArrayList<>();

    //----------------------------------------------------ADD DRIVER DETAILS------------------------------------------------------------------------//
    static {
        driverArrayList.add(new Driver("Sumith Kumara", "7835348345V", "B6474845", "Panadura", "0725637456"));
        driverArrayList.add(new Driver("Amila Pathirana", "8826253734V", "B3354674", "Galle", "0717573583"));
        driverArrayList.add(new Driver("Jithmal Perera", "9283289272V", "B3674589", "Horana", "0772452457"));
        driverArrayList.add(new Driver("Sumith Dissanayaka", "9425245373V", "B8366399", "Kaluthara", "0782686390"));
        driverArrayList.add(new Driver("Sumanasiri Herath ", "8976544373V", "B3537538", "Beruwala", "0772534436"));
        driverArrayList.add(new Driver("Awantha Fernando", "9173537839V", "B3554789", "Colombo 5", "0714534356"));
        driverArrayList.add(new Driver("Charith Sudara", "9573536833V", "B6835836", "Baththaramulla", "0771536662"));
        driverArrayList.add(new Driver("Prashan Dineth", "9362426738V", "B2683536", "Wadduwa", "0715353434"));
        driverArrayList.add(new Driver("Chethiya Dilan", "9162353436V", "B6836836", "Panadura", "0772436737"));
        driverArrayList.add(new Driver("Dushantha Perera", "9255556343V", "B3334435", "Matara", "0777245343"));
        driverArrayList.add(new Driver("Sumith Udayanga", "8735354355V", "B3573783", "Galle", "0703635442"));
        driverArrayList.add(new Driver("Dinesh Udara", " 9026344373V", " B5343783", "Hettimulla", " 0713456878"));
        driverArrayList.add(new Driver("Udana Chathuranga", "9692653338V", "B7888632", "Kottawa", " 0772442444"));
        driverArrayList.add(new Driver("Mohommad Riaz", "9124537733V", "B3638537", "Kaluthara", "0777544222"));
        driverArrayList.add(new Driver("Sandun Kumara", "9563524267V", "B2263333", "Panadura", "0772325544"));
        driverArrayList.add(new Driver("Priyanga Perera", "9135343537V", "B3853753", "Matara", "0723344562"));
    }

    //--------------------------------------------------------------------------------------------------------------------------------------------------//
    public AnchorPane AddDriverContext;
    public TextField txtDriverName;
    public TextField txtNic;
    public TextField txtDrivingLicenseNo;
    public TextField txtAddress;
    public TextField txtContactNo;
    public JFXButton btnSave;
    public TableView<DriverTM> tblDriver;
    public TableColumn<Object, Object> colDriverName;
    public TableColumn<Object, Object> colDriverNic;
    public TableColumn<Object, Object> colDrivingLicenseNo;
    public TableColumn<Object, Object> colAddress;
    public TableColumn<Object, Object> colContactNo;
    public TableColumn<Object, Object> colOption;
    public JFXButton btnNew;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--------------------------------CHECK TEXT FILED ----------------------------//
        btnSave.setDisable(true);
        storeValidations();
        //--------------------------------SET VALUES TABLE ----------------------------//
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDriverNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDrivingLicenseNo.setCellValueFactory(new PropertyValueFactory<>("drivingLicenseNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllDriverDetails();

        tblDriver.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }
                });

    }

    //--------------------------------DATA SET TO UPDATE----------------------------//
    private void setData(DriverTM tm) {
        btnSave.setText("Update Details");
        txtDriverName.setText(tm.getName());
        txtNic.setText(tm.getNic());
        txtDrivingLicenseNo.setText(tm.getDrivingLicenseNo());
        txtAddress.setText(tm.getAddress());
        txtContactNo.setText(tm.getContact());
    }

    //--------------------------------LOAD DRIVER DETAILS ----------------------------//
    private void loadAllDriverDetails() {
        //table load
        ObservableList<DriverTM> obsListDriver = FXCollections.observableArrayList();
        for (Driver D1 : driverArrayList) {
            Button btn = new Button("Remove");
            obsListDriver.add(new DriverTM(D1.getName(), D1.getNic(), D1.getDrivingLicenseNo(), D1.getAddress(), D1.getContact(), btn));
            btn.setOnAction((e) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        txtNic.getText() + " is " + txtDriverName.getText() + ". Are You Sure Delete ?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)) {
                    //delete
                    driverArrayList.remove(D1);
                    loadAllDriverDetails();
                    new Alert(Alert.AlertType.CONFIRMATION, txtDriverName.getText() + " is Delete successfully.", ButtonType.CLOSE).show();
                }
            });
        }
        tblDriver.setItems(obsListDriver);
    }

    //-------------------------------------SAVE DRIVER DETAILS ----------------------------//
    public void SaveDriverOnAction(ActionEvent actionEvent) {
        if (driverArrayList.size() == 16) {
            new Alert(Alert.AlertType.WARNING, "You Cannot Add Drivers!\n\t\t Company Drivers are Full...", ButtonType.CLOSE).show();
        } else {
            if (btnSave.getText().equals("Save Driver")) {
                //save
                Driver v1 = new Driver(
                        txtDriverName.getText(),
                        txtNic.getText(),
                        txtDrivingLicenseNo.getText(),
                        txtAddress.getText(),
                        txtContactNo.getText()
                );
                new Alert(Alert.AlertType.CONFIRMATION, txtDriverName.getText() + " is Saved Driver Detail.", ButtonType.CLOSE).show();
                driverArrayList.add(v1);
                loadAllDriverDetails();

                //--------------------------------UPDATE DRIVER DETAILS ----------------------------//
            } else {
                //update
                for (Driver tm : driverArrayList) {
                    if (tm.getNic().equals(txtNic.getText())) {
                        tm.setName(txtDriverName.getText());
                        tm.setDrivingLicenseNo(txtDrivingLicenseNo.getText());
                        tm.setAddress(txtAddress.getText());
                        tm.setContact(txtContactNo.getText());
                        new Alert(Alert.AlertType.CONFIRMATION, txtDriverName.getText() + " is Updated Driver Detail.", ButtonType.CLOSE).show();
                        loadAllDriverDetails();
                        return;
                    }
                }
            }
        }
    }

    //--------------------------------ADD DRIVER DETAILS ----------------------------//
    public void AddNewDriverOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Driver");
    }

    //--------------------------------CANCEL DETAILS FROM ----------------------------//
    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AddDriverContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementFromHome.fxml"))));
    }

    //--------------------------------CHECK TEXT FILED ----------------------------//
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern DriverName = Pattern.compile("^([A-Z a-z]{1,30})$");
    Pattern Nic = Pattern.compile("^([0-9V]{11,12})$");
    Pattern DL = Pattern.compile("^([B0-9]{8})$");
    Pattern address = Pattern.compile("^([A-Za-z]{4,40})$");
    Pattern ContactNo = Pattern.compile("^([0-9]{10})$");

    private void storeValidations() {
        map.put(txtDriverName, DriverName);
        map.put(txtNic, Nic);
        map.put(txtDrivingLicenseNo, DL);
        map.put(txtAddress, address);
        map.put(txtContactNo, ContactNo);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Add").showAndWait();
            }
        }
    }
}