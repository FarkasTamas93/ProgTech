package valuta.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import org.json.JSONObject;
import valuta.CalculationService;
import valuta.Main;
import valuta.ReadDatesFromWeb;
import valuta.StatisticCalculationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Farkas Tamás on 2017.05.28..
 */
public class StatisticViewController {

    @FXML
    private Label weeklyMinLabel;

    @FXML
    private Label weeklyMaxLabel;

    @FXML
    private Label weeklyStartPriceLabel;

    @FXML
    private Label changeFrom1MonthLabel;

    @FXML
    private Label changeFrom3MonthLabel;

    @FXML
    private Label changeFrom12MonthLabel;

    @FXML
    private Label thirtyDayMinLabel;

    @FXML
    private Label thirtyDayMaxLabel;

    @FXML
    private Label thirtyDayAvgLabel;

    @FXML
    private MenuButton menuButton;

    private List<String> RatesNames = ReadDatesFromWeb.valutesFromJson().getRatelist().stream().map(o1 -> o1.getValuteRateName()).collect(Collectors.toList());

    private ObservableList<JSONObject> last31DayPriceJson = ReadDatesFromWeb.jsonObjects30Day();

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void initialize()
    {
        intitializeMenu();
    }

    public void intitializeMenu() {
        for (int i = 0; i < RatesNames.size(); i++) {
            menuButton.getItems().addAll(new MenuItem(RatesNames.get(i)));
        }

        for (int i = 0; i < menuButton.getItems().size(); i++) {
            menuButton.getItems().get(i).setOnAction(event -> {
                menuButton.setText(((MenuItem) event.getSource()).getText());
                initializeLabels(((MenuItem) event.getSource()).getText());
            });
        }
    }

    public void initializeLabels(String name)
    {
        weeklyMinLabel.setText(Double.toString(StatisticCalculationService.getMinValue(last31DayPriceJson, name)));
        weeklyMaxLabel.setText(Double.toString(StatisticCalculationService.getMaxValue(last31DayPriceJson, name)));
        weeklyStartPriceLabel.setText(Double.toString(StatisticCalculationService.weeklyFirstValue(last31DayPriceJson, name)));
        changeFrom1MonthLabel.setText(String.format("%.2f",StatisticCalculationService.changeFrom1Month(name))+" %");
        changeFrom3MonthLabel.setText(String.format("%.2f",StatisticCalculationService.changeFrom3Month(name))+" %");
        changeFrom12MonthLabel.setText(String.format("%.2f",StatisticCalculationService.changeFrom12Month(name))+" %");
        thirtyDayMinLabel.setText(Double.toString(StatisticCalculationService.thirtyDayMin(last31DayPriceJson, name)));
        thirtyDayMaxLabel.setText(Double.toString(StatisticCalculationService.thirtyDayMax(last31DayPriceJson, name)));
        thirtyDayAvgLabel.setText(String.format("%.4f",StatisticCalculationService.thirtyDayAvg(last31DayPriceJson, name)));
    }


}
