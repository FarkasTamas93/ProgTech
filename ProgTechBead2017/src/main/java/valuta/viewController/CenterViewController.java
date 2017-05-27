package valuta.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import valuta.*;
import valuta.model.ValutesFromFixerIo;
import valuta.model.ValutesFromFixerIoRates;
import valuta.model.ValutesToDatabase;

/**
 * Created by Farkas Tamás on 2017.05.05..
 */
public class CenterViewController {

    @FXML
    private TableView<ValutesFromFixerIoRates> valutaRateTable;
    @FXML
    private TableColumn<ValutesFromFixerIoRates, String> valutaRateName;
    @FXML
    private TableColumn<ValutesFromFixerIoRates, Double> valutaRatePrice;

    @FXML
    private TextField AUDMyValutes;
    @FXML
    private TextField BGNMyValutes;
    @FXML
    private TextField BRLMyValutes;
    @FXML
    private TextField CADMyValutes;
    @FXML
    private TextField CHFMyValutes;
    @FXML
    private TextField CNYMyValutes;
    @FXML
    private TextField CZKMyValutes;
    @FXML
    private TextField DKKMyValutes;
    @FXML
    private TextField GBPMyValutes;
    @FXML
    private TextField HKDMyValutes;
    @FXML
    private TextField HRKMyValutes;
    @FXML
    private TextField HUFMyValutes;
    @FXML
    private TextField IDRMyValutes;
    @FXML
    private TextField ILSMyValutes;
    @FXML
    private TextField INRMyValutes;
    @FXML
    private TextField JPYMyValutes;
    @FXML
    private TextField KRWMyValutes;
    @FXML
    private TextField MXNMyValutes;
    @FXML
    private TextField MYRMyValutes;
    @FXML
    private TextField NOKMyValutes;
    @FXML
    private TextField NZDMyValutes;
    @FXML
    private TextField PHPMyValutes;
    @FXML
    private TextField PLNMyValutes;
    @FXML
    private TextField RONMyValutes;
    @FXML
    private TextField RUBMyValutes;
    @FXML
    private TextField SEKMyValutes;
    @FXML
    private TextField SGDMyValutes;
    @FXML
    private TextField THBMyValutes;
    @FXML
    private TextField TRYMyValutes;
    @FXML
    private TextField USDMyValutes;
    @FXML
    private TextField ZARMyValutes;

    @FXML
    private TextField yourRemainingMoney;

    @FXML
    private Label welcomeLabel;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private NumberAxis yAxis;

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private ValutesToDatabase userValute;

    private ObservableList<JSONObject> jsonDates30Day;

    @FXML
    private void initialize() {

        initializeUserValute();
        initializeWelcomeUserText();
        initializeTableView();
        setTheTextFields();
//        initializeLineChartDates();
//        initializeLineChartTeszt();
        initializeLineChart();

    }

    private void initializeUserValute() {
//      Ezt bele kell tennem mert enélkül nem tudja kiolvasni az adatbazisbol scene valtas után az erteket
//      JpaService.getJpaServiceInstance().getEntityManagerFactory().getCache().evictAll();
        JpaService.getJpaServiceInstance().getEntityManager().clear();
        userValute = JpaService.getJpaServiceInstance().getValutesServiceJPA().findValutesByName(LoggedUserService.getInstance().getUserName());
    }

    private void initializeWelcomeUserText() {
        welcomeLabel.setText(LoggedUserService.getInstance().getUserName());
    }

    private void initializeTableView() {
        valutaRateTable.setItems(ReadDatesFromWeb.valutesFromJson().getRatelist());
        valutaRateName.setCellValueFactory(cellData -> cellData.getValue().valuteRateNameProperty());
        valutaRatePrice.setCellValueFactory(cellData -> cellData.getValue().valuteRatePriceProperty().asObject());
    }

    private void setTheTextFields() {
        AUDMyValutes.setText(Double.toString(userValute.getAUD()));
        BGNMyValutes.setText(Double.toString(userValute.getBGN()));
        BRLMyValutes.setText(Double.toString(userValute.getBRL()));
        CADMyValutes.setText(Double.toString(userValute.getCAD()));
        CHFMyValutes.setText(Double.toString(userValute.getCHF()));
        CNYMyValutes.setText(Double.toString(userValute.getCNY()));
        CZKMyValutes.setText(Double.toString(userValute.getCZK()));
        DKKMyValutes.setText(Double.toString(userValute.getDKK()));
        GBPMyValutes.setText(Double.toString(userValute.getGBP()));
        HKDMyValutes.setText(Double.toString(userValute.getHKD()));
        HRKMyValutes.setText(Double.toString(userValute.getHRK()));
        HUFMyValutes.setText(Double.toString(userValute.getHUF()));
        IDRMyValutes.setText(Double.toString(userValute.getIDR()));
        ILSMyValutes.setText(Double.toString(userValute.getILS()));
        INRMyValutes.setText(Double.toString(userValute.getINR()));
        JPYMyValutes.setText(Double.toString(userValute.getJPY()));
        KRWMyValutes.setText(Double.toString(userValute.getKRW()));
        MXNMyValutes.setText(Double.toString(userValute.getMXN()));
        MYRMyValutes.setText(Double.toString(userValute.getMYR()));
        NOKMyValutes.setText(Double.toString(userValute.getNOK()));
        NZDMyValutes.setText(Double.toString(userValute.getNZD()));
        PHPMyValutes.setText(Double.toString(userValute.getPHP()));
        PLNMyValutes.setText(Double.toString(userValute.getPLN()));
        RONMyValutes.setText(Double.toString(userValute.getRON()));
        RUBMyValutes.setText(Double.toString(userValute.getRUB()));
        SEKMyValutes.setText(Double.toString(userValute.getSEK()));
        SGDMyValutes.setText(Double.toString(userValute.getSGD()));
        THBMyValutes.setText(Double.toString(userValute.getTHB()));
        TRYMyValutes.setText(Double.toString(userValute.getTRY()));
        USDMyValutes.setText(Double.toString(userValute.getUSD()));
        ZARMyValutes.setText(Double.toString(userValute.getZAR()));
        yourRemainingMoney.setText(Double.toString(userValute.getRemainMoney()));
    }

    private void initializeLineChart() {

//        valutaRateTable.getSelectionModel().selectFirst();
        valutaRateTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            lineChart.getData().clear();
            if (newValue != null) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                ObservableList<Double> ertek = ReadDatesFromWeb.last30DaySelect(newValue.getValuteRateName());
                double min = ertek.stream().min((o1, o2) -> Double.compare(o1, o2)).get();
                double max = ertek.stream().max((o1, o2) -> Double.compare(o1, o2)).get();
                if (ertek.size() != 31) {
                    System.out.println("nincs 31 ertek");
                }
                for (int i = 0; i < 31; i++) {
                    yAxis.setAutoRanging(false);
                    yAxis.setLowerBound(min - 0.5);
                    yAxis.setUpperBound(max + 0.5);
                    yAxis.setTickUnit(0.2);
                    //Az idr értéke ezzel még valamit kezdeni kell
                    if (min > 14000) {
                        yAxis.setTickUnit(100);
                    }
                    lineChart.getXAxis().setAnimated(false);
                    series.getData().add(new XYChart.Data<>(Integer.toString(i), ertek.get(i)));
                }
                lineChart.getData().add(series);
            }
        });
    }

    private void initializeLineChartTeszt() {
        valutaRateTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            lineChart.getData().clear();
            if (newValue != null) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                ObservableList<Double> ertek = ReadDatesFromWeb.last30DaySelect(newValue.getValuteRateName());
                double min = ertek.stream().min((o1, o2) -> Double.compare(o1, o2)).get();
                double max = ertek.stream().max((o1, o2) -> Double.compare(o1, o2)).get();
                if (ertek.size() != 31) {
                    System.out.println("nincs 31 ertek");
                }
                for (int i = 0; i < 31; i++) {
                    yAxis.setAutoRanging(false);
                    yAxis.setLowerBound(min - 0.5);
                    yAxis.setUpperBound(max + 0.5);
                    yAxis.setTickUnit(0.2);
                    //Az idr értéke ezzel még valamit kezdeni kell
                    if (min > 14000) {
                        yAxis.setTickUnit(100);
                    }
                    lineChart.getXAxis().setAnimated(false);
                    series.getData().add(new XYChart.Data<>(Integer.toString(i), ertek.get(i)));
                }
                lineChart.getData().add(series);
            }
        });
    }

    private void initializeLineChartDates() {
        jsonDates30Day = ReadDatesFromWeb.jsonObjects30Day();
    }

    private ObservableList<Double> last30DaySelect(String name) {
        ObservableList<Double> list = FXCollections.observableArrayList();
        for (int i = 30; i >= 0; i--) {

            JSONObject temp = jsonDates30Day.get(i);
            list.add((Double) temp.get(name));
        }
        return list;
    }


    @FXML
    private void logOutButton() {
        mainApp.showLoginView();
    }

    @FXML
    private void buyButton() {
        mainApp.showBuyValutes();
    }

    @FXML
    private void sellValuteButton() {
        mainApp.showSellValutes();
    }

    @FXML

    private void buyValuteForeignExchangeButton() {
        mainApp.showBuyValutesForeign();
    }
}
