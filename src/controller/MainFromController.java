package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import model.Vehicle;
import view.tm.DOutputListTM;
import view.tm.POutputListTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static controller.ManagementFromHomeController.dOutputListTM;
import static controller.ManagementFromHomeController.pOutputListTM;

public class MainFromController {
    public static ArrayList<Vehicle> TmpVehicleArrayList = AddVehicleFromController.vehicleArrayList;
    public static ArrayList<Driver> TmpDriverArrayList = AddDriverFromController.driverArrayList;
    public AnchorPane MainFromContext;
    public JFXComboBox cmbSelectVehicle;
    public JFXComboBox<String> cmbDriver;
    public Label LblDate;
    public Label lblVehicleType;
    public Label lblParkingSlot;
    public JFXButton btnParking;
    public JFXButton btnDelivery;
    public JFXTextField txtParkingSlotNo;
    String time;

    //--------------------------------SET CLOCK NEW THREAD & DATALOADER ----------------------------//
    public void initialize() {
        {
            Thread clock = new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy\n\t    HH:mm:ss a");
                            Date date = new Date();
                            String myString = formatter.format(date);
                            Platform.runLater(() -> {
                                LblDate.setText(myString);
                            });
                            sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            clock.start();
        }
        DataLoader();
    }

    //--------------------------------PARKING VEHICLE ----------------------------//
    public void ParkVehicleOnAction(ActionEvent actionEvent) {
        time = LblDate.getText();
        if (cmbSelectVehicle.getValue() != null) {
            POutputListTM parkedVehicleTM = new POutputListTM(cmbSelectVehicle.getValue().toString()
                    , lblVehicleType.getText(), Integer.parseInt(txtParkingSlotNo.getText()), time);

            pOutputListTM.add(parkedVehicleTM);
            // txtParkingSlotNo.clear();
            new Alert(Alert.AlertType.INFORMATION, lblVehicleType.getText() + " is Parked slot Number " + txtParkingSlotNo.getText() + ".", ButtonType.CLOSE).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Select Vehicle to Parking. ", ButtonType.CLOSE).show();
        }
    }


    //--------------------------------DELIVER VEHICLE ----------------------------//
    public void OnDeliverShiftOnAction(ActionEvent actionEvent) {
        time = LblDate.getText();
        if (cmbDriver.getValue() != null) {
            pOutputListTM.removeIf(temp -> temp.getVehicleNumber().equals(cmbSelectVehicle.getValue()));
            DOutputListTM onDeliver = new DOutputListTM(cmbSelectVehicle.getValue().toString()
                    , lblVehicleType.getText(), cmbDriver.getValue(), time);

            dOutputListTM.add(onDeliver);
            new Alert(Alert.AlertType.INFORMATION, lblVehicleType.getText() + " is Delivered in " + cmbDriver.getValue() + ".", ButtonType.CLOSE).show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Select Driver to Deliver " + lblVehicleType.getText() + ".", ButtonType.CLOSE).show();
        }

    }

    //--------------------------------MANAGEMENT WINDOW LOADING ----------------------------//
    public void ManagementLoginOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManagemantFrom.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        ManagementLoginController managementLogInFormController = fxmlLoader.getController();
        managementLogInFormController.setController(MainFromContext);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Login");
    }

    //-------------------------------- DATA LOADER ----------------------------//
    public void DataLoader() {
        //--------------------------------OBSERVABLES LIST - COMBO BOX LOAD VEHICLE ----------------------------//
        ObservableList<String> VehicleObList = FXCollections.observableArrayList();
        for (Vehicle temp : TmpVehicleArrayList) {
            VehicleObList.add(temp.getVehicleNumber());
        }
        cmbSelectVehicle.setItems(VehicleObList);

        //--------------------------------OBSERVABLES LIST - COMBO BOX LOAD DRIVER ----------------------------//
        ObservableList<String> DriverObList = FXCollections.observableArrayList();
        for (Driver temp : TmpDriverArrayList) {
            DriverObList.add(temp.getName());
        }
        cmbDriver.setItems(DriverObList);

        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtParkingSlotNo.clear();
            cmbSelectVehicle.setAccessibleText((String) newValue);
            for (Vehicle temp : TmpVehicleArrayList) {
                if (newValue.equals(temp.getVehicleNumber())) {
                    lblVehicleType.setText(temp.getVehicleType());
                    DisableAndEnableButtonOption(cmbSelectVehicle.getValue().toString());
                    if (!btnParking.isDisable()) {
                        try {
                            txtParkingSlotNo.setText(temp.park(temp.getVehicleNumber(), temp.getVehicleType()) + "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    //-------------------------------- DISABLE BUTTON (PARK & DELIVER) ----------------------------//
    public void DisableAndEnableButtonOption(String vehicleNumber) {
        btnParking.setDisable(false);
        btnDelivery.setDisable(false);
        for (POutputListTM temp : pOutputListTM) {
            if (temp.getVehicleNumber().equals(vehicleNumber)) {
                btnParking.setDisable(true);
            }
        }
        if (!btnParking.isDisable()) {
            btnDelivery.setDisable(true);
        }
    }

}
