package valuta.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import valuta.*;
import valuta.model.ValutesToDatabase;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Farkas Tamás on 2017.05.20..
 */
public class BuyValutesViewController {

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private MenuButton menuButton;

    @FXML
    private TextField userValutaBuyTextField;

    @FXML
    private TextField afterBoughtRemainingMoney;

    @FXML
    private TextField remainingMoneyTextField;

    @FXML
    private Label selectedValute;

    @FXML
    private Label actualPriceLabel;

    @FXML
    private Button allMoneyButton;

    private ValutesToDatabase actualUserValutes;

    private List<String> RatesNames = ReadDatesFromWeb.valutesFromJson().getRatelist().stream().map(o1 -> o1.getValuteRateName()).collect(Collectors.toList());

    @FXML
    private void initialize() {

        intitializeMenuButton();
        initializeActualUserValute();
        initializeRemainingMoney();
    }

    public void intitializeMenuButton() {
        //Itt toltom fel a menuitemeknek a nevét
        for (int i = 0; i < RatesNames.size(); i++) {
            menuButton.getItems().addAll(new MenuItem(RatesNames.get(i)));
        }

        //Itt választom ki melyik pénznemre nyomott a menubarba
        for (int i = 0; i < menuButton.getItems().size(); i++) {
            menuButton.getItems().get(i).setOnAction(event -> {
                selectedValute.setText(((MenuItem) event.getSource()).getText());
                actualPriceLabel.setText(Double.toString(ReadDatesFromWeb.selectedValutePrice(((MenuItem) event.getSource()).getText())));
                menuButton.setText(((MenuItem) event.getSource()).getText());
            });
        }
    }

    public void initializeActualUserValute() {

        actualUserValutes = JpaService.getJpaServiceInstance().getValutesServiceJPA().findValutesByName(LoggedUserService.getInstance().getUserName());

    }

    public void initializeRemainingMoney() {
        remainingMoneyTextField.setText(Double.toString(actualUserValutes.getRemainMoney()));
    }

    public void updateActualUserValutes() {
        String selectedValuteRateName = selectedValute.getText();
        switch (selectedValuteRateName) {
            case "AUD":
                actualUserValutes.setAUD(actualUserValutes.getAUD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "BGN":
                actualUserValutes.setBGN(actualUserValutes.getBGN() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "BRL":
                actualUserValutes.setBRL(actualUserValutes.getBRL() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "CAD":
                actualUserValutes.setCAD(actualUserValutes.getCAD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "CHF":
                actualUserValutes.setCHF(actualUserValutes.getCHF() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "CNY":
                actualUserValutes.setCNY(actualUserValutes.getCNY() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "CZK":
                actualUserValutes.setCZK(actualUserValutes.getCZK() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "DKK":
                actualUserValutes.setDKK(actualUserValutes.getDKK() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "GBP":
                actualUserValutes.setGBP(actualUserValutes.getGBP() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "HKD":
                actualUserValutes.setHKD(actualUserValutes.getHKD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "HRK":
                actualUserValutes.setHRK(actualUserValutes.getHRK() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "HUF":
                actualUserValutes.setHUF(actualUserValutes.getHUF() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "IDR":
                actualUserValutes.setIDR(actualUserValutes.getIDR() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "ILS":
                actualUserValutes.setILS(actualUserValutes.getILS() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "INR":
                actualUserValutes.setINR(actualUserValutes.getINR() + actualUserValutes.getAUD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "JPY":
                actualUserValutes.setKRW(actualUserValutes.getJPY() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "MXN":
                actualUserValutes.setMXN(actualUserValutes.getMXN() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "MYR":
                actualUserValutes.setMYR(actualUserValutes.getMYR() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "NOK":
                actualUserValutes.setNOK(actualUserValutes.getNOK() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "NZD":
                actualUserValutes.setNZD(actualUserValutes.getNZD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "PHP":
                actualUserValutes.setPHP(actualUserValutes.getPHP() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "PLN":
                actualUserValutes.setPLN(actualUserValutes.getPLN() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "RON":
                actualUserValutes.setRON(actualUserValutes.getRON() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "RUB":
                actualUserValutes.setRUB(actualUserValutes.getRUB() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "SEK":
                actualUserValutes.setSEK(actualUserValutes.getSEK() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "SGD":
                actualUserValutes.setSGD(actualUserValutes.getSGD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "THB":
                actualUserValutes.setTHB(actualUserValutes.getTHB() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "TRY":
                actualUserValutes.setTRY(actualUserValutes.getTRY() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "USD":
                actualUserValutes.setUSD(actualUserValutes.getUSD() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
            case "ZAR":
                actualUserValutes.setZAR(actualUserValutes.getZAR() + Double.parseDouble(userValutaBuyTextField.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterBoughtRemainingMoney.getText()));
                break;
        }
    }


    @FXML
    private void okButton() {
        okButtonMethods();
    }

    private void okButtonMethods() {
        if (okButtonIsValid()) {
            updateActualUserValutes();
            JpaService.getJpaServiceInstance().getValutesServiceJPA().updateValuteToDatabase(actualUserValutes);
            mainApp.showCenterView();
//            ValutesToDatabase temp = JpaService.getJpaServiceInstance().getEntityManager().find(ValutesToDatabase.class,actualUserValutes.getEid());
//            JpaService.getJpaServiceInstance().getEntityManager().getTransaction().begin();
//            temp.setAUD(123456);
//            JpaService.getJpaServiceInstance().getEntityManager().getTransaction().commit();
        }
    }

    @FXML
    private void cancelButton() {
        mainApp.showCenterView();
    }

    public boolean okButtonIsValid() {

        if (isValid() == true) {
            double result;
            try {
                result = Double.parseDouble(afterBoughtRemainingMoney.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Please check your money");
                alert.setContentText("Press the CheckRemainingMoney button to audit you have enough money to buy valutes");
                alert.setTitle("Error");
                alert.showAndWait();
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isValid() {
        if (userValutaBuyTextField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter a number how much valutes would you buy");
            alert.setTitle("Error");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(userValutaBuyTextField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter a number");
            alert.setTitle("Error");
            alert.showAndWait();
            return false;
        }

        if (selectedValute.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Missing ValuteName");
            alert.setContentText("Please choose which valute would you buy");
            alert.setTitle("Missing Data");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void checkRemainingMoneyButton() {
        if (isValid()) {
            setTextFields();
        }
    }

    public void setTextFields() {
        double result = CalculationService.calculationToBuyValute(Double.parseDouble(remainingMoneyTextField.getText()), Double.parseDouble(userValutaBuyTextField.getText()), selectedValute.getText());
        if (result < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Out of money:\n you need " + Math.abs(result) + " Euro");
            alert.setContentText("Sorry your money is not enought to buy the given amount values");
            alert.setTitle("Not enought money");
            afterBoughtRemainingMoney.setText("");
            alert.showAndWait();
        } else {
            afterBoughtRemainingMoney.setText(Double.toString(result));
            userValutaBuyTextField.setDisable(true);
            menuButton.setDisable(true);
            allMoneyButton.setDisable(true);
        }
    }
//    public double calculation() {
//        Double result = 0.0;
//        Double userRemainingMoney = Double.parseDouble(remainingMoneyTextField.getText());
//        Double boughtValuta = Double.parseDouble(userValutaBuyTextField.getText());
//        if (userRemainingMoney > 0) {
//            Double actualValutaPrice = ReadDatesFromWeb.selectedValutePrice(selectedValute.getText());
//            result = userRemainingMoney - (1 / actualValutaPrice) * boughtValuta;
//        }
//        return result;
//    }

    @FXML
    public void editBuyTextField() {
        afterBoughtRemainingMoney.setText("");
        userValutaBuyTextField.setText("");
        userValutaBuyTextField.setDisable(false);
        menuButton.setDisable(false);
        allMoneyButton.setDisable(false);
    }

//    public double calculation2()
//    {
//        if(selectedValute.getText().length()==0)
//        {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.initOwner(mainApp.getPrimaryStage());
//            alert.setHeaderText("Missing ValuteName");
//            alert.setContentText("Please choose which valute would you buy");
//            alert.setTitle("Missing Data");
//            alert.showAndWait();
//            return 0;
//        }
//        double temp = Double.parseDouble(remainingMoneyTextField.getText())*ReadDatesFromWeb.selectedValutePrice(selectedValute.getText());
//
//        return temp;
//    }

    private boolean allInIsvalid() {
        if (selectedValute.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Missing ValuteName");
            alert.setContentText("Please choose which valute would you buy");
            alert.setTitle("Missing Data");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    @FXML
    public void allInButton() {
        allInButtonMethod();
    }

    private void allInButtonMethod() {
        if (allInIsvalid() == true) {
            Double result = CalculationService.allRemainingMoneyConvertToOtherCurrency(selectedValute.getText(), Double.parseDouble(remainingMoneyTextField.getText()));
            userValutaBuyTextField.setText(Double.toString(result));
        }
    }
}
