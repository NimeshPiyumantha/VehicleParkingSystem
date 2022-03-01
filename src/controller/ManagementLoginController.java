package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ManagementLoginController implements Initializable {
    public AnchorPane ManagementLoginContext;
    public AnchorPane anchorPane;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public JFXButton btnLogin;
    int attempts = 0;
    //--------------------------------CHECK TEXT FILED ----------------------------//
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern UserName = Pattern.compile("^([Management]{10})$");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogin.setDisable(true);
        storeValidations();

    }

    private void storeValidations() {
        map.put(txtUserName, UserName);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnLogin);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Login").showAndWait();
            }
        }
    }

    //--------------------------------LOGIN FROM LOAD ----------------------------//
    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        attempts++;
        if (attempts <= 5) {
            //login(username & password are correct)
            if (txtUserName.getText().equals("Management") && txtPassword.getText().equals("1234")) {
                //AdminLoginMenu load
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ManagementFromHome.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                Stage stage1 = (Stage) anchorPane.getScene().getWindow();
                Stage stage2 = (Stage) ManagementLoginContext.getScene().getWindow();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage1.close();
                stage2.close();
                stage.show();
            }

            //login(username is correct password are incorrect)
            else if (txtUserName.getText().equals("Management") && !txtPassword.getText().equals("1234")) {
                new Alert(Alert.AlertType.WARNING, "Your password is wrong!\nYou Have 5 time trys and this is " + attempts + ".").show();
            }

            //login(username are incorrect password are correct)
            else if (!txtUserName.getText().equals("Management") && txtPassword.getText().equals("1234")) {
                new Alert(Alert.AlertType.WARNING, "Your username is wrong!\nYou Have 5 time trys and this is " + attempts + ".").show();
            } else {
                //try again
                new Alert(Alert.AlertType.WARNING, "Your username and password is incorrect.!\nYou Have 5 time trys and this is " + attempts + ".").show();
            }
        } else {
            txtUserName.setEditable(false);
            txtPassword.setEditable(false);
            new Alert(Alert.AlertType.WARNING, "Account is Temporarily Disabled or You Did not Sign in Correctly. ").show();
        }
    }

    //--------------------------------CANCEL FROM----------------------------//
    public void CancelOnAction(ActionEvent actionEvent) {
        txtUserName.setText("");
        txtPassword.setText("");


    }

    //--------------------------------SET AnchorPane ----------------------------//
    public void setController(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

}
