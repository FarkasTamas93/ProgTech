package valuta.viewController;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import valuta.*;
import valuta.model.ValutesFromFixerIo;
import valuta.model.ValutesFromFixerIoRates;
import valuta.model.ValutesToDatabase;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Raenna on 2017.05.23..
 */
public class BuyValutesForeignViewController {

    @FXML
    private TableView<ValutesFromFixerIoRates> valuteTableView;

    @FXML
    private TableColumn<ValutesFromFixerIoRates, String> valuteRateNameTableColumn;

    @FXML
    private TableColumn<ValutesFromFixerIoRates, Double> valuteRatePriceTableColumn;

    @FXML
    private TableView<DoubleProperty> userValuteTableView;

    @FXML
    private TableColumn<DoubleProperty, Double> userValutePieceTableColumn;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuButton menuButton2;

    @FXML
    private Button allMoneyButton;

    @FXML
    private Label selectMenuText1;

    @FXML
    private Label selectMenuText2;

    @FXML
    private TextField remainingMoneyTextfield;

    @FXML
    private TextField valutesToSellOutTextfield;

    @FXML
    private TextField remainingValutesAfterSell;

    @FXML
    private TextField afterBuyTheActualPrice;

    private List<String> ratesNames = ReadDatesFromWeb.valutesFromJson().getRatelist().stream().map(o1 -> o1.getValuteRateName()).collect(Collectors.toList());

    private Main mainApp;

    private String chooseRateNameFrom = "";

    private String chooseRateNameTo = "";

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private ValutesToDatabase actualUserValutes;


    @FXML
    private void initialize() {
        initializeActualUserValute();
        initializeTables();
        intitializeMenuButton();
        initializeRemainingMoney();
    }

    private void initializeRemainingMoney() {
        remainingMoneyTextfield.setText(Double.toString(actualUserValutes.getRemainMoney()));
    }

    public void intitializeMenuButton() {
        for (int i = 0; i < ratesNames.size(); i++) {
            menuButton.getItems().addAll(new MenuItem(ratesNames.get(i)));
            menuButton2.getItems().addAll(new MenuItem(ratesNames.get(i)));
        }

        for (int i = 0; i < menuButton.getItems().size(); i++) {
            menuButton.getItems().get(i).setOnAction(event -> {
                menuButton.setText(((MenuItem) event.getSource()).getText());
                chooseRateNameFrom = ((MenuItem) event.getSource()).getText();
                selectMenuText1.setText(" "+chooseRateNameFrom);
            });
        }

        for (int i = 0; i < menuButton2.getItems().size(); i++) {
            menuButton2.getItems().get(i).setOnAction(event -> {
                menuButton2.setText(((MenuItem) event.getSource()).getText());
                chooseRateNameTo = ((MenuItem) event.getSource()).getText();
                selectMenuText2.setText(" "+chooseRateNameTo);
            });
        }

    }

    private void initializeTables() {
        ValutesFromFixerIo data = ReadDatesFromWeb.valutesFromJson();
        valuteTableView.setItems(data.getRatelist());
        valuteRateNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().valuteRateNameProperty());
        valuteRatePriceTableColumn.setCellValueFactory(cellData -> cellData.getValue().valuteRatePriceProperty().asObject());
        userValuteTableView.setItems(actualUserValutes.getValutesList());
        userValutePieceTableColumn.setCellValueFactory(cellData -> cellData.getValue().asObject());
    }

    public void initializeActualUserValute() {
        actualUserValutes = JpaService.getJpaServiceInstance().getValutesServiceJPA().findValutesByName(LoggedUserService.getInstance().getUserName());
    }

    @FXML
    private void cancelButton() {
        mainApp.showCenterView();
    }

    @FXML
    private void okButton()
    {
        okButtonMethods();
    }

    private void okButtonMethods()
    {
        if(okButtonIsValid()) {
            updateActualUserValutes();
            updateActualUserValutes2();
            JpaService.getJpaServiceInstance().getValutesServiceJPA().updateValuteToDatabase(actualUserValutes);
            mainApp.showCenterView();
        }
    }

    public boolean okButtonIsValid() {

        if(isValid() == true) {
            double result;
            try {
                result = Double.parseDouble(remainingValutesAfterSell.getText());
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

//    public int getIndex(String rateName) {
//        if (rateName.equals("")) {
//            return -1;
//        }
//
//        int index = 0;
//        for (int i = 0; i < valuteTableView.getItems().size(); i++) {
//            if (valuteTableView.getItems().get(i).getValuteRateName().equals(rateName)) {
//                index = i;
//            }
//        }
//        return index;
//    }

    public boolean isValid() {
        if (valutesToSellOutTextfield.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter a number how much valutes would you sell");
            alert.setTitle("Error");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(valutesToSellOutTextfield.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Wrong data");
            alert.setContentText("Please enter a valid number");
            alert.setTitle("Error");
            alert.showAndWait();
            return false;
        }

        if (chooseRateNameFrom.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("Missing ValuteName");
            alert.setContentText("Please choose which valute would you sell");
            alert.setTitle("Missing Data");
            alert.showAndWait();
            return false;
        }
        if (chooseRateNameTo.length() == 0) {
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
    private void checkButton() {
        if (isValid()) {
            double result = CalculationService.buyValutaFromOtherValuta(chooseRateNameFrom,
                    Double.parseDouble(valutesToSellOutTextfield.getText()),
                    chooseRateNameTo,
                    userValuteTableView.getItems().get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateNameFrom)).getValue());

            if (result < 0.0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Not enough Valute to buy");
                alert.setContentText("Sorry your money is not enought to buy the given amount values");
                alert.setTitle("Not enought money");
                remainingValutesAfterSell.setText("");
                afterBuyTheActualPrice.setText("");
                alert.showAndWait();
            } else {
                remainingValutesAfterSell.setText(Double.toString(result));
                afterBuyTheActualPrice.setText(Double.toString(CalculationService.afterBuyTheActualPriceSum(userValuteTableView.getItems().get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateNameTo)).getValue(), Double.parseDouble(valutesToSellOutTextfield.getText()))));
                valutesToSellOutTextfield.setDisable(true);
                menuButton.setDisable(true);
                menuButton2.setDisable(true);
                allMoneyButton.setDisable(true);
//            }
            }
        }
    }

    @FXML
    public void editBuyButton() {
        afterBuyTheActualPrice.setText("");
        remainingValutesAfterSell.setText("");
        valutesToSellOutTextfield.setText("");
        valutesToSellOutTextfield.setDisable(false);
        menuButton.setDisable(false);
        menuButton2.setDisable(false);
        allMoneyButton.setDisable(false);
    }

    @FXML
    public void allInButton()
    {
        setBuyTextField();
    }

    private void setBuyTextField()
    {
        valutesToSellOutTextfield.setText(
                Double.toString(CalculationService.buyValutaUseAllMoney(userValuteTableView.getItems()
                                .get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateNameFrom)).getValue(),
                        valuteTableView.getItems().get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateNameFrom)).getValuteRatePrice(),
                        chooseRateNameTo)));
    }

    public void updateActualUserValutes() {
        String selectedValuteRateName = chooseRateNameTo;
        switch (selectedValuteRateName) {
            case "AUD":
                actualUserValutes.setAUD(actualUserValutes.getAUD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "BGN":
                actualUserValutes.setBGN(actualUserValutes.getBGN() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "BRL":
                actualUserValutes.setBRL(actualUserValutes.getBRL() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "CAD":
                actualUserValutes.setCAD(actualUserValutes.getCAD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "CHF":
                actualUserValutes.setCHF(actualUserValutes.getCHF() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "CNY":
                actualUserValutes.setCNY(actualUserValutes.getCNY() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "CZK":
                actualUserValutes.setCZK(actualUserValutes.getCZK() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "DKK":
                actualUserValutes.setDKK(actualUserValutes.getDKK() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "GBP":
                actualUserValutes.setGBP(actualUserValutes.getGBP() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "HKD":
                actualUserValutes.setHKD(actualUserValutes.getHKD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "HRK":
                actualUserValutes.setHRK(actualUserValutes.getHRK() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "HUF":
                actualUserValutes.setHUF(actualUserValutes.getHUF() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "IDR":
                actualUserValutes.setIDR(actualUserValutes.getIDR() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "ILS":
                actualUserValutes.setILS(actualUserValutes.getILS() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "INR":
                actualUserValutes.setINR(actualUserValutes.getINR() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "JPY":
                actualUserValutes.setKRW(actualUserValutes.getJPY() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "MXN":
                actualUserValutes.setMXN(actualUserValutes.getMXN() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "MYR":
                actualUserValutes.setMYR(actualUserValutes.getMYR() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "NOK":
                actualUserValutes.setNOK(actualUserValutes.getNOK() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "NZD":
                actualUserValutes.setNZD(actualUserValutes.getNZD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "PHP":
                actualUserValutes.setPHP(actualUserValutes.getPHP() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "PLN":
                actualUserValutes.setPLN(actualUserValutes.getPLN() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "RON":
                actualUserValutes.setRON(actualUserValutes.getRON() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "RUB":
                actualUserValutes.setRUB(actualUserValutes.getRUB() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "SEK":
                actualUserValutes.setSEK(actualUserValutes.getSEK() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "SGD":
                actualUserValutes.setSGD(actualUserValutes.getSGD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "THB":
                actualUserValutes.setTHB(actualUserValutes.getTHB() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "TRY":
                actualUserValutes.setTRY(actualUserValutes.getTRY() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "USD":
                actualUserValutes.setUSD(actualUserValutes.getUSD() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
            case "ZAR":
                actualUserValutes.setZAR(actualUserValutes.getZAR() + Double.parseDouble(valutesToSellOutTextfield.getText()));
                break;
        }
    }

    public void updateActualUserValutes2() {
        String selectedValuteRateName = chooseRateNameFrom;
        switch (selectedValuteRateName) {
            case "AUD":
                actualUserValutes.setAUD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "BGN":
                actualUserValutes.setBGN(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "BRL":
                actualUserValutes.setBRL(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "CAD":
                actualUserValutes.setCAD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "CHF":
                actualUserValutes.setCHF(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "CNY":
                actualUserValutes.setCNY(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "CZK":
                actualUserValutes.setCZK(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "DKK":
                actualUserValutes.setDKK(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "GBP":
                actualUserValutes.setGBP(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "HKD":
                actualUserValutes.setHKD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "HRK":
                actualUserValutes.setHRK(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "HUF":
                actualUserValutes.setHUF(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "IDR":
                actualUserValutes.setIDR(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "ILS":
                actualUserValutes.setILS(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "INR":
                actualUserValutes.setINR(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "JPY":
                actualUserValutes.setKRW(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "MXN":
                actualUserValutes.setMXN(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "MYR":
                actualUserValutes.setMYR(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "NOK":
                actualUserValutes.setNOK(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "NZD":
                actualUserValutes.setNZD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "PHP":
                actualUserValutes.setPHP(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "PLN":
                actualUserValutes.setPLN(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "RON":
                actualUserValutes.setRON(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "RUB":
                actualUserValutes.setRUB(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "SEK":
                actualUserValutes.setSEK(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "SGD":
                actualUserValutes.setSGD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "THB":
                actualUserValutes.setTHB(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "TRY":
                actualUserValutes.setTRY(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "USD":
                actualUserValutes.setUSD(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
            case "ZAR":
                actualUserValutes.setZAR(Double.parseDouble(remainingValutesAfterSell.getText()));
                break;
        }
    }

}
