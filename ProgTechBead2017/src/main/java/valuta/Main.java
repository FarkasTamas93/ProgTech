package valuta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import valuta.viewController.*;

import javax.persistence.Persistence;
import java.io.IOException;

/**
 * Created by Farkas Tamás on 2017.04.29..
 */
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    @Override
    public void init() throws Exception {
        JpaService.getJpaServiceInstance().setEntityManagerFactory(Persistence.createEntityManagerFactory("ValutaProgram"));
        JpaService.getJpaServiceInstance().setEntityManager(JpaService.getJpaServiceInstance().getEntityManagerFactory().createEntityManager());
        JpaService.getJpaServiceInstance().setLoginServiceJPA(new LoginServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
        JpaService.getJpaServiceInstance().setValutesServiceJPA(new ValutesServiceJPA(JpaService.getJpaServiceInstance().getEntityManager()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
        showLoginView();
    }

    public void initRootLayout() {
        try {
            // Load root layout from view file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayoutViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/LoginView.fxml"));
            AnchorPane loginView = (AnchorPane) loader.load();
            rootLayout.setCenter(loginView);

            //Beállítjuk a loginController osztályban a mainapp-ot
            LoginViewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showRegisterDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/RegisterDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RegisterDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCenterView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/CenterView.fxml"));
            AnchorPane centerView = (AnchorPane) loader.load();
            this.primaryStage.setMaximized(true);
            rootLayout.setCenter(centerView);
            //Beállítjuk a loginController osztályban a mainapp-ot
            CenterViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBuyValutes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/BuyValutesView.fxml"));
            AnchorPane buyValutesView = (AnchorPane) loader.load();
            this.rootLayout.setCenter(buyValutesView);
            //Beállítjuk a loginController osztályban a mainapp-ot
            BuyValutesViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSellValutes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/SellValutesView.fxml"));
            AnchorPane sellValutesView = (AnchorPane) loader.load();
            this.rootLayout.setCenter(sellValutesView);
            //Beállítjuk a loginController osztályban a mainapp-ot
            SellValutesViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBuyValutesForeign() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/BuyValutesForeign.fxml"));
            AnchorPane buylValutesForeignView = (AnchorPane) loader.load();
            this.rootLayout.setCenter(buylValutesForeignView);
            //Beállítjuk a loginController osztályban a mainapp-ot
            BuyValutesForeignViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        JpaService.getJpaServiceInstance().getEntityManager().close();
        JpaService.getJpaServiceInstance().getEntityManagerFactory().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
