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
 * Created by Farkas Tamás on 2017.05.22..
 */
public class SellValutesViewController {

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
    private Label currencyName;

    @FXML
    private TextField valutesToSellOutTextfield;

    @FXML
    private TextField remainingMoneyTextfield;

    @FXML
    private TextField afterSellValutesTextfield;

    @FXML
    private TextField remainingValutesAfterSell;

    @FXML
    private TextField afterSellRemainingMoney;

    private List<String> RatesNames = ReadDatesFromWeb.valutesFromJson().getRatelist().stream().map(o1 -> o1.getValuteRateName()).collect(Collectors.toList());

    private ValutesToDatabase actualUserValutes;

    private Main mainApp;

    private String chooseRateName = "";

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize() {
        initializeActualUserValute();
        initializeTables();
        intitializeMenuButton();
        initializeRemainingMoney();

    }

    public void intitializeMenuButton() {
        for (int i = 0; i < RatesNames.size(); i++) {
            menuButton.getItems().addAll(new MenuItem(RatesNames.get(i)));
        }

        for (int i = 0; i < menuButton.getItems().size(); i++) {
            menuButton.getItems().get(i).setOnAction(event -> {
                menuButton.setText(((MenuItem) event.getSource()).getText());
                chooseRateName = ((MenuItem) event.getSource()).getText();
                currencyName.setText(" "+chooseRateName);
            });
        }
    }

    public void initializeActualUserValute() {
        actualUserValutes = JpaService.getJpaServiceInstance().getValutesServiceJPA().findValutesByName(LoggedUserService.getInstance().getUserName());
    }

    private void initializeTables() {
        valuteTableView.setItems(ReadDatesFromWeb.valutesFromJson().getRatelist());
        valuteRateNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().valuteRateNameProperty());
        valuteRatePriceTableColumn.setCellValueFactory(cellData -> cellData.getValue().valuteRatePriceProperty().asObject());
        userValuteTableView.setItems(actualUserValutes.getValutesList());
        userValutePieceTableColumn.setCellValueFactory(cellData -> cellData.getValue().asObject());
    }

    private void initializeRemainingMoney() {
        remainingMoneyTextfield.setText(Double.toString(actualUserValutes.getRemainMoney()));
    }


    public void updateActualUserValutes() {
        String selectedValuteRateName = chooseRateName;
        switch (selectedValuteRateName) {
            case "AUD":
                actualUserValutes.setAUD(actualUserValutes.getAUD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "BGN":
                actualUserValutes.setBGN(actualUserValutes.getBGN() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "BRL":
                actualUserValutes.setBRL(actualUserValutes.getBRL() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "CAD":
                actualUserValutes.setCAD(actualUserValutes.getCAD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "CHF":
                actualUserValutes.setCHF(actualUserValutes.getCHF() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "CNY":
                actualUserValutes.setCNY(actualUserValutes.getCNY() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "CZK":
                actualUserValutes.setCZK(actualUserValutes.getCZK() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "DKK":
                actualUserValutes.setDKK(actualUserValutes.getDKK() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "GBP":
                actualUserValutes.setGBP(actualUserValutes.getGBP() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "HKD":
                actualUserValutes.setHKD(actualUserValutes.getHKD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "HRK":
                actualUserValutes.setHRK(actualUserValutes.getHRK() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "HUF":
                actualUserValutes.setHUF(actualUserValutes.getHUF() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "IDR":
                actualUserValutes.setIDR(actualUserValutes.getIDR() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "ILS":
                actualUserValutes.setILS(actualUserValutes.getILS() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "INR":
                actualUserValutes.setINR(actualUserValutes.getINR() - actualUserValutes.getAUD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "JPY":
                actualUserValutes.setKRW(actualUserValutes.getJPY() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "MXN":
                actualUserValutes.setMXN(actualUserValutes.getMXN() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "MYR":
                actualUserValutes.setMYR(actualUserValutes.getMYR() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "NOK":
                actualUserValutes.setNOK(actualUserValutes.getNOK() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "NZD":
                actualUserValutes.setNZD(actualUserValutes.getNZD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "PHP":
                actualUserValutes.setPHP(actualUserValutes.getPHP() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "PLN":
                actualUserValutes.setPLN(actualUserValutes.getPLN() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "RON":
                actualUserValutes.setRON(actualUserValutes.getRON() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "RUB":
                actualUserValutes.setRUB(actualUserValutes.getRUB() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "SEK":
                actualUserValutes.setSEK(actualUserValutes.getSEK() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "SGD":
                actualUserValutes.setSGD(actualUserValutes.getSGD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "THB":
                actualUserValutes.setTHB(actualUserValutes.getTHB() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "TRY":
                actualUserValutes.setTRY(actualUserValutes.getTRY() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "USD":
                actualUserValutes.setUSD(actualUserValutes.getUSD() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
            case "ZAR":
                actualUserValutes.setZAR(actualUserValutes.getZAR() - Double.parseDouble(valutesToSellOutTextfield.getText()));
                actualUserValutes.setRemainMoney(Double.parseDouble(afterSellRemainingMoney.getText()));
                break;
        }
    }

    @FXML
    public void okButton() {

        if (okButtonIsValid()) {
            updateActualUserValutes();
            JpaService.getJpaServiceInstance().getValutesServiceJPA().updateValuteToDatabase(actualUserValutes);
            mainApp.showCenterView();
        }
    }

    @FXML
    public void cancelButton() {
        mainApp.showCenterView();
    }

    @FXML
    public void checkButton() {
        if (isValid()) {
            setTextFields();
        }
    }

    public void setTextFields() {
        double result = CalculationService.sellValuteCalculation(userValuteTableView.getItems()
                        .get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateName)).getValue()
                , Double.parseDouble(valutesToSellOutTextfield.getText()),
                valuteTableView.getItems().get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateName)).getValuteRatePrice());

        if (result <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setHeaderText("You don't have enough valute to sell");
            alert.setContentText("Sorry your valutes is not enought to sell the given amount values");
            alert.setTitle("Not enought valutes");
            afterSellValutesTextfield.setText("");
            afterSellRemainingMoney.setText("");
            remainingValutesAfterSell.setText("");
            alert.showAndWait();
        } else {
            afterSellRemainingMoney.setText(Double.toString(actualUserValutes.getRemainMoney() + result));
            afterSellValutesTextfield.setText(Double.toString(result));
            remainingValutesAfterSell.setText(Double.toString(
                    CalculationService.remainingValutes(userValuteTableView.getItems()
                                    .get(ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateName))
                                    .getValue(),
                            Double.parseDouble(valutesToSellOutTextfield.getText()))));
            valutesToSellOutTextfield.setDisable(true);
            menuButton.setDisable(true);
        }
    }

    @FXML
    public void editSellButton() {
        afterSellRemainingMoney.setText("");
        afterSellValutesTextfield.setText("");
        remainingValutesAfterSell.setText("");
        valutesToSellOutTextfield.setText("");
        valutesToSellOutTextfield.setDisable(false);
        menuButton.setDisable(false);
    }

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

        if (chooseRateName.length() == 0) {
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

    public boolean okButtonIsValid() {
        if (isValid() == true) {
            double result;
            try {
                result = Double.parseDouble(afterSellValutesTextfield.getText());
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

    //Kiszámolom hogy mennyi valutám van összesen az adott pénznembõl és ha többet vonok ki mint amennyim van akkor 0-t fog átadni és validálásnál alert lesz
    //Ha több valutám vagy ugyanannyim van mint amennyit eladok akkor átkonvertálom euróvá és azzal térek vissza
//    public double calculation() {
//        Double result = 0.0;
//        int index = ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateName);
//        Double userValutesPiece = userValuteTableView.getItems().get(index).getValue();
//        Double sellValuta = Double.parseDouble(valutesToSellOutTextfield.getText());
//        if (userValutesPiece - sellValuta >= 0) {
//            result = convertToEuro();
//        }
//        return result;
//    }

    //Az aktuális valutát amit eladok átkonvertálom euróvá és ezt fogom hozzáadni a saját tõkémhez
    //lekérem mennyit akarok eladni / az adott pénznem aktuális értéke
//    public double convertToEuro() {
//        int index = getIndex(chooseRateName);
//        double convertToEuro = Double.parseDouble(valutesToSellOutTextfield.getText()) / valuteTableView.getItems().get(index).getValuteRatePrice();
//        return convertToEuro;
//    }

    //Itt számolom ki hogy mennyi valutám marad miután eladtam a meglévõkbõl
    //Saját valuta - annyi valuta amennyit el fogok adni
//    public Double remainingValutes() {
//        int index = ReadDatesFromWeb.getIndexFromActualCurrency(chooseRateName);
//        Double result = userValuteTableView.getItems().get(index).getValue() - Double.parseDouble(valutesToSellOutTextfield.getText());
//        return result;
//    }

    //Lekérem a táblában lévõ index számát a kiválasztott pénznem alapján
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

}
