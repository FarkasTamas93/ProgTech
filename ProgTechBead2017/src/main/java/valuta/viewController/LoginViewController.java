package valuta.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import valuta.*;
import valuta.model.Login;

import java.util.List;

/**
 * Created by Farkas Tamás on 2017.04.29..
 */
public class LoginViewController {

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void registerButton() {
        mainApp.showRegisterDialog();
    }

    @FXML
    public void loginButton() {
        loginButtonMethods();
    }

    private void loginButtonMethods() {
        if (loginCheck()) {
            LoggedUserService.setInstance(loggedUser());
            mainApp.showCenterView();
        }
    }

    private boolean loginCheck() {
        List<String> allUserList = JpaService.getJpaServiceInstance().getLoginServiceJPA().getAllUser();
        if (allUserList.contains(usernameField.getText())) {
            Login teszt = JpaService.getJpaServiceInstance().getLoginServiceJPA().findUserByName(usernameField.getText());
            if (teszt.getPassword().equals(passwordField.getText())) {
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Bad paswword");
                alert.setHeaderText("Invalid password!");
                alert.setContentText("Please correct your password");
                alert.showAndWait();
                return false;
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Unknown user");
        alert.setHeaderText("Invalid username");
        alert.setContentText("Please correct or register the account!");
        alert.showAndWait();
        return false;
    }

    private Login loggedUser() {
        return JpaService.getJpaServiceInstance().getLoginServiceJPA().findUserByName(usernameField.getText());
    }

}
