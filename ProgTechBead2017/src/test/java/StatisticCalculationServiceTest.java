import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import valuta.ReadDatesFromWeb;
import valuta.StatisticCalculationService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Farkas Tamás on 2017.05.29..
 */
public class StatisticCalculationServiceTest {

    private List<String> ratesNames = ReadDatesFromWeb.valutesFromJson().getRatelist().stream().map(o1 -> o1.getValuteRateName()).collect(Collectors.toList());

    private ObservableList<JSONObject> last31DayPriceJson = ReadDatesFromWeb.jsonObjects30Day();

    @Test
    public void isnotNullTest()
    {
        org.junit.Assert.assertFalse(last31DayPriceJson.isEmpty());
    }

    @Test
    public void isnotNullTest2()
    {
        org.junit.Assert.assertTrue(! ratesNames.isEmpty());
    }
    @Ignore
    @Test
    public void getMinValueTest()
    {
        org.junit.Assert.assertEquals(1.4939, StatisticCalculationService.getMinValue(last31DayPriceJson, "AUD"), 0.1);
    }

    @Test
    public void getMaxValueTest()
    {
        org.junit.Assert.assertEquals(1.5045, StatisticCalculationService.getMaxValue(last31DayPriceJson, "AUD"),0.1);
    }

    @Test
    public void changeFrom1MonthTest()
    {
        //((uj ertek /regi ertek)-1) *100  uj="AUD":1.5045 regi="AUD":1.4629
        org.junit.Assert.assertEquals(-2.765038218677296, StatisticCalculationService.changeFrom1Month("AUD"),0.1);
    }

    @Test
    public void thirtyDayAvgTest()
    {
        org.junit.Assert.assertEquals(1.4852354838709678, StatisticCalculationService.thirtyDayAvg(last31DayPriceJson, "AUD"), 0.1);
    }


}
