package valuta.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import valuta.JpaService;
import valuta.LoginServiceJPA;
import valuta.Main;
import valuta.ValutesServiceJPA;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Farkas Tamás on 2017.04.29..
 */
public class RegisterDialogController {
    @FXML
    TextField registerUsernameField;
    @FXML
    PasswordField registerUserPassword;
    @FXML
    PasswordField registerRePassword;
    @FXML
    TextField registerUserFirstName;
    @FXML
    TextField registerUserLastName;
    @FXML
    TextField registerUserEmail;
    @FXML
    TextField registerDate;

    private LocalDate date = LocalDate.now();

    private final static String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Stage dialogStage;
    private Main mainApp;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize()
    {
        initializeTextFields();
    }

    private void initializeTextFields()
    {
        registerUsernameField.setPromptText("Username");
        registerUserPassword.setPromptText("Password");
        registerRePassword.setPromptText("Repassword");
        registerUserFirstName.setPromptText("FirstName");
        registerUserLastName.setPromptText("LastName");
        registerUserEmail.setPromptText("Your Email Adress");
        registerDate.setText(date.toString());
    }
    @FXML
    private void OkButton() {
        okButtonMethods();
    }

    private void okButtonMethods()
    {
        if (isInputValid()) {
            LoginServiceJPA loginServiceJPA = JpaService.getJpaServiceInstance().getLoginServiceJPA();
            //Itt írom bele az adatbázisba a felhasználói adatokat a login tablaba
            JpaService.getJpaServiceInstance().getLoginServiceJPA().createLoginAccount(registerUsernameField.getText(), registerUserPassword.getText(), registerUserFirstName.getText(),
                    registerUserLastName.getText(), registerUserEmail.getText());
            ValutesServiceJPA valutesServiceJPA = JpaService.getJpaServiceInstance().getValutesServiceJPA();
            //Itt írom bele a valutes tablaba a usernevet és a valutáit
            valutesServiceJPA.createValutesToLoginAccount(registerUsernameField.getText());

            dialogStage.close();
        }
    }

    @FXML
    private void CancelButton() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        List<String> allUserList = null;
        List<String> allEmailList = null;

        LoginServiceJPA loginServiceJPA = JpaService.getJpaServiceInstance().getLoginServiceJPA();
        //Az adatbázisból beletöltöm az összes felhasználót hogy megnézzem létezik e már ilyen
        allUserList = loginServiceJPA.getAllUser();
        //Az adatbázisból beletöltöm az összes email címet hogy megnézzem létezik e már ilyen
        allEmailList = loginServiceJPA.getAllEmail();

        if (registerUsernameField.getText() == null || registerUsernameField.getText().trim().length() == 0) {
            errorMessage += "Not valid Username!\n";
        }
        if (registerUserPassword.getText() == null || registerUserPassword.getText().trim().length() == 0) {
            errorMessage += "Not valid Password!\n";
        }
        if (!(registerUserPassword.getText().equals(registerRePassword.getText()))) {
            errorMessage += "Different password!\n";
        }
        if (registerUserFirstName.getText() == null || registerUserFirstName.getText().trim().length() == 0) {
            errorMessage += "Not valid FirstName!\n";
        }
        if (registerUserLastName.getText() == null || registerUserLastName.getText().trim().length() == 0) {
            errorMessage += "Not valid LastName!\n";
        }
        if (!(registerUserEmail.getText().matches(EMAIL_PATTERN))) {
            errorMessage += "Bad email! Example: something@email.com\n";
        }
        if (allUserList.contains(registerUsernameField.getText())) {
            errorMessage += "Sorry this username is reserved\n";
        }
        if (allEmailList.contains(registerUserEmail.getText())) {
            errorMessage += "Sorry this email is reserved\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid field");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }

    }
}


