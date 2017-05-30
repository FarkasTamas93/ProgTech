package valuta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;
import valuta.model.ValutesFromFixerIo;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by Farkas Tamás on 2017.05.29..
 */
public class StatisticCalculationService {

    private static ObservableList<Double> last31DaySelect(ObservableList<JSONObject> last30DayJson,String name) {
        ObservableList<Double> list = FXCollections.observableArrayList();
        for (int i = 30; i >= 0; i--) {

            JSONObject temp = last30DayJson.get(i);
            list.add((Double) temp.get(name));
        }
        return list;
    }

    public static double getMinValue(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        ObservableList<Double> last7DayList=FXCollections.observableArrayList();
        for(int i=0;i<valueList.size();i++)
        {
            if(i>=valueList.size()-7)
            {
                last7DayList.add(valueList.get(i));
            }
        }
        return last7DayList.stream().min((o1, o2) -> Double.compare(o1,o2)).get().doubleValue();
    }

    public static double getMaxValue(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        ObservableList<Double> last7DayList=FXCollections.observableArrayList();
        for(int i=0;i<valueList.size();i++)
        {
            if(i>=valueList.size()-7)
            {
                last7DayList.add(valueList.get(i));
            }
        }

        return last7DayList.stream().max((o1, o2) -> Double.compare(o1, o2)).get().doubleValue();
    }

    public static double weeklyFirstValue(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        return valueList.get(valueList.size() - 7).doubleValue();
    }

    public static double changeFrom1Month(String name)
    {
        ValutesFromFixerIo value = ReadDatesFromWeb.valutesFromJson();
        String localdateString = LocalDate.now().minusMonths(1).toString();
        ValutesFromFixerIo value2 = ReadDatesFromWeb.valutesFromJsonByUrlName(localdateString);
        //((uj ertek /regi ertek)-1) *100
        return ((value2.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice() / value.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice())-1)*100;
    }

    public static double changeFrom3Month(String name)
    {
        ValutesFromFixerIo value = ReadDatesFromWeb.valutesFromJson();
        String localdateString = LocalDate.now().minusMonths(3).toString();
        ValutesFromFixerIo value2 = ReadDatesFromWeb.valutesFromJsonByUrlName(localdateString);
        //((uj ertek /regi ertek)-1) *100
        return ((value2.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice() / value.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice())-1)*100;
    }

    public static double changeFrom12Month(String name)
    {
        ValutesFromFixerIo value = ReadDatesFromWeb.valutesFromJson();
        String localdateString = LocalDate.now().minusMonths(12).toString();
        ValutesFromFixerIo value2 = ReadDatesFromWeb.valutesFromJsonByUrlName(localdateString);
        //((uj ertek /regi ertek)-1) *100
        return ((value2.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice() / value.getRatelist().get(ReadDatesFromWeb.getIndexFromActualCurrency(name)).getValuteRatePrice())-1)*100;
    }

    public static double thirtyDayMin(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        return valueList.stream().min((o1, o2) -> Double.compare(o1,o2)).get().doubleValue();
    }

    public static double thirtyDayMax(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        return valueList.stream().max((o1, o2) -> Double.compare(o1, o2)).get().doubleValue();
    }

    public static double thirtyDayAvg(ObservableList<JSONObject> last30DayJson,String name)
    {
        ObservableList<Double> valueList=last31DaySelect(last30DayJson, name);
        return (valueList.stream().mapToDouble(value ->value).sum())/valueList.size();
    }
}
