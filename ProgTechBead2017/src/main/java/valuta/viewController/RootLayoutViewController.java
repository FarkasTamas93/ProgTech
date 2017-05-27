package valuta.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import valuta.Main;

/**
 * Created by Farkas Tamás on 2017.05.26..
 */
public class RootLayoutViewController {

    @FXML
    private MenuBar menuBar;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize()
    {
        menuBar.getMenus().get(0).setOnAction(event -> {
            System.exit(0);
        });

        menuBar.getMenus().get(1).setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Program");
            alert.setHeaderText("A programot keszitette : Farkas Tamas");
            alert.setContentText("Elerhetoseg: \n Hajduszoboszlo 4200 Telefon:0000-0000 Email:valami@email.hu");
            alert.initOwner(mainApp.getPrimaryStage());
            alert.showAndWait();
        });
    }
}
