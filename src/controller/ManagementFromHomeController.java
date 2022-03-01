package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.tm.DOutputListTM;
import view.tm.POutputListTM;

import java.io.IOException;

public class ManagementFromHomeController {
    public static ObservableList<POutputListTM> pOutputListTM = FXCollections.observableArrayList();
    public static ObservableList<DOutputListTM> dOutputListTM = FXCollections.observableArrayList();

    public AnchorPane ManagementFromHomeContext;
    public Pane ParkingPaneContext;
    public TableView<POutputListTM> tblParking;
    public TableColumn<Object, Object> colVehicleNumber;
    public TableColumn<Object, Object> colVehicleType;
    public TableColumn<Object, Object> colParkingSlot;
    public TableColumn<Object, Object> colParkedTime;
    public JFXComboBox<String> cmbSelection;
    public TableView<DOutputListTM> tblDeliver;
    public TableColumn<Object, Object> colDVehicleNumber;
    public TableColumn<Object, Object> colDVehicleType;
    public TableColumn<Object, Object> colDriverName;
    public TableColumn<Object, Object> colLeaveTime;
    public JFXButton btnAddVehicle;
    public JFXButton btnLogout;
    public JFXButton btnAddDriver;

    //--------------------------------LOAD TABLE  ----------------------------//
    public void initialize() {
        cmbSelection.getItems().addAll("In Parking", "On Deliver");
        cmbSelection.setValue("In Parking");

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory<>("ParkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory<>("ParkedTime"));

        colDVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colDVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("DriverName"));
        colLeaveTime.setCellValueFactory(new PropertyValueFactory<>("LeftTime"));
        tblParking.setItems(pOutputListTM);
        tblDeliver.setItems(dOutputListTM);

    }

    //--------------------------------ADD VEHICLE FROM LOAD ----------------------------//
    public void AddVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ManagementFromHomeContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddVehicleFrom.fxml"))));

    }

    //-------------------------------- LOGOUT FROM ----------------------------//
    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ManagementFromHomeContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainFrom.fxml"))));
    }

    //--------------------------------ADD DRIVER FROM LOAD ----------------------------//
    public void AddDriverOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ManagementFromHomeContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddDriverFrom.fxml"))));
    }

    //--------------------------------HIDE BUTTONS ----------------------------//
    public void SelectionCmbOnAction(ActionEvent actionEvent) {
        if (cmbSelection.getValue().equals("On Deliver")) {
            btnLogout.setVisible(false);
            tblParking.setVisible(false);
            tblDeliver.setVisible(true);
        } else {
            btnLogout.setVisible(true);
            tblDeliver.setVisible(false);
            tblParking.setVisible(true);
        }
    }
}

