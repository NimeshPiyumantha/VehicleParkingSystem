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
import model.Bus;
import model.CargoLorry;
import model.Van;
import model.Vehicle;
import util.ValidationUtil;
import view.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddVehicleFromController implements Initializable {
    public static ObservableList<VehicleTM> obsListVehicle1 = FXCollections.observableArrayList();
    public static ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();

    //----------------------------------------------------ADD VEHICLE DETAILS------------------------------------------------------------------------//
    static {

        vehicleArrayList.add(new Bus("NA-3434", "Bus", 3500, 60));
        vehicleArrayList.add(new Van("KA-4563", "Van", 1000, 7));
        vehicleArrayList.add(new Van("58-3567", "Van", 1500, 4));
        vehicleArrayList.add(new Van("GF-4358", "Van", 800, 4));
        vehicleArrayList.add(new Van("CCB-3568", "Van", 1800, 8));
        vehicleArrayList.add(new Van("LM-6679", "Van", 1500, 4));
        vehicleArrayList.add(new Van("QA-3369", "Van", 1800, 6));
        vehicleArrayList.add(new CargoLorry("KB-3668", "Cargo Lorry", 2500, 2));
        vehicleArrayList.add(new CargoLorry("JJ-9878", "Cargo Lorry", 3000, 2));
        vehicleArrayList.add(new CargoLorry("GH-5772", "Cargo Lorry", 4000, 3));
        vehicleArrayList.add(new CargoLorry("XY-4456", "Cargo Lorry", 3500, 2));
        vehicleArrayList.add(new CargoLorry("YQ-3536", "Cargo Lorry", 2000, 2));
        vehicleArrayList.add(new CargoLorry("CBB-3566", "Cargo Lorry", 2500, 2));
        vehicleArrayList.add(new CargoLorry("QH-3444", "Cargo Lorry", 5000, 4));

    }

    //----------------------------------------------------------------------------------------------------------------//
    public AnchorPane AddVehicleContext;

    public TextField txtVehicleNumber;
    public TextField txtMWeight;
    public TextField txtNoPassengers;
    public TableView tblVehicle;
    public ComboBox cmbVehicleType;
    public TableColumn<Object, Object> colVehicleNo;
    public TableColumn<Object, Object> colMaxWeight;
    public TableColumn<Object, Object> colNoOfPassenger;
    public TableColumn<Object, Object> colVehicleType;
    public JFXButton btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //--------------------------------CHECK TEXT FILED ----------------------------//
        btnSave.setDisable(true);
        storeValidations();

        //--------------------------------SET VALUES TABLE ----------------------------//
        cmbVehicleType.getItems().addAll("Van", "Bus", "CargoLorry");
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colMaxWeight.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
        colNoOfPassenger.setCellValueFactory(new PropertyValueFactory<>("NoPassengers"));
        loadAllVehicle();

    }

    //--------------------------------LOAD ALL VEHICLE DETAILS TABLE ----------------------------//
    private void loadAllVehicle() {
        for (Vehicle V1 : vehicleArrayList) {
            obsListVehicle1.add(new VehicleTM(V1.getVehicleNumber(), V1.getVehicleType(), V1.getMaxWeight(), V1.getNoPassengers()));
        }
        tblVehicle.setItems(obsListVehicle1);
    }

    //--------------------------------LOAD VEHICLE DETAILS AND REMOVE DETAILS----------------------------//
    private void loadAllVehicleDetails() {
        ObservableList<Vehicle> observableList = FXCollections.observableArrayList();
        if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("CargoLorry")) {
            for (Vehicle vCargoLorry : vehicleArrayList) {
                observableList.add(new CargoLorry(vCargoLorry.getVehicleNumber(), vCargoLorry.getVehicleType(), vCargoLorry.getMaxWeight(), vCargoLorry.getNoPassengers()));
            }
            tblVehicle.setItems(observableList);
        }
        if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("Van")) {
            for (Vehicle vVan : vehicleArrayList) {
                observableList.add(new Van(vVan.getVehicleNumber(), vVan.getVehicleType(), vVan.getMaxWeight(), vVan.getNoPassengers()));
            }
            tblVehicle.setItems(observableList);
        }
        if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("Bus")) {
            for (Vehicle vBus : vehicleArrayList) {
                observableList.add(new Bus(vBus.getVehicleNumber(), vBus.getVehicleType(), vBus.getMaxWeight(), vBus.getNoPassengers()));
            }
            tblVehicle.setItems(observableList);
        }
    }

    //-------------------------------------SAVE VEHICLE DETAILS ----------------------------//
    public void SaveVehicleOnAction(ActionEvent actionEvent) {
        if (vehicleArrayList.size() == 14) {
            new Alert(Alert.AlertType.WARNING, "You Cannot Add Vehicles!\n\t\t Company Vehicles are Full...", ButtonType.CLOSE).show();
        } else {
            //----------------------------------------SAVE VAN------------------------------------------------//
            if (vehicleArrayList.size() == 5) {
                new Alert(Alert.AlertType.WARNING, "You Cannot Add Vehicles!\n\t\t Company Vehicles are Full...", ButtonType.CLOSE).show();
            } else {
                if (cmbVehicleType.getSelectionModel().getSelectedItem().toString().equals("Van")) {
                    Van van = new Van(txtVehicleNumber.getText(), cmbVehicleType.getValue().toString(),
                            Double.parseDouble(txtMWeight.getText()), Integer.parseInt(txtNoPassengers.getText()));
                    txtVehicleNumber.clear();
                    txtMWeight.clear();
                    txtNoPassengers.clear();

                    if (vehicleArrayList.add(van)) {
                        loadAllVehicleDetails();
                        new Alert(Alert.AlertType.CONFIRMATION, cmbVehicleType.getValue() + " is Saved successfully.", ButtonType.CLOSE).show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, cmbVehicleType.getValue() + " is Try Again...", ButtonType.CLOSE).show();
                    }
                }
            }
            //----------------------------------------SAVE BUS------------------------------------------------//
            if (cmbVehicleType.getSelectionModel().getSelectedItem().toString().equals("Bus")) {
                Bus bus = new Bus(txtVehicleNumber.getText(), cmbVehicleType.getValue().toString(),
                        Double.parseDouble(txtMWeight.getText()), Integer.parseInt(txtNoPassengers.getText()));
                txtVehicleNumber.clear();
                txtMWeight.clear();
                txtNoPassengers.clear();

                if (vehicleArrayList.add(bus)) {
                    loadAllVehicleDetails();
                    new Alert(Alert.AlertType.CONFIRMATION, cmbVehicleType.getValue() + " is Saved successfully.", ButtonType.CLOSE).show();
                } else {
                    new Alert(Alert.AlertType.WARNING, cmbVehicleType.getValue() + " is Try Again...", ButtonType.CLOSE).show();
                }
            }
            //----------------------------------------SAVE CARGO-LORRY------------------------------------------------//
            if (cmbVehicleType.getSelectionModel().getSelectedItem().toString().equals("CargoLorry")) {
                CargoLorry cargoLorry = new CargoLorry(txtVehicleNumber.getText(), cmbVehicleType.getValue().toString(),
                        Double.parseDouble(txtMWeight.getText()), Integer.parseInt(txtNoPassengers.getText()));
                txtVehicleNumber.clear();
                txtMWeight.clear();
                txtNoPassengers.clear();

                if (vehicleArrayList.add(cargoLorry)) {
                    loadAllVehicleDetails();
                    new Alert(Alert.AlertType.CONFIRMATION, cmbVehicleType.getValue() + " is Saved successfully.", ButtonType.CLOSE).show();
                } else {
                    new Alert(Alert.AlertType.WARNING, cmbVehicleType.getValue() + " is Try Again...", ButtonType.CLOSE).show();
                }
            }
        }
    }

    //--------------------------------CANCEL DETAILS FROM ----------------------------//
    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AddVehicleContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementFromHome.fxml"))));

    }

    //--------------------------------CHECK TEXT FILED ----------------------------//
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern VNumber = Pattern.compile("^[A-Z 0-9]{2,3}-[0-9]{4}$");
    Pattern MWeight = Pattern.compile("^([0-9]{3,10})$");
    Pattern NoPassenger = Pattern.compile("^([0-9]{1,2})$");

    private void storeValidations() {
        map.put(txtVehicleNumber, VNumber);
        map.put(txtMWeight, MWeight);
        map.put(txtNoPassengers, NoPassenger);
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
