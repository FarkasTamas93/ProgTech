package valuta;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.json.JSONException;
import org.json.JSONObject;
import valuta.model.ValutesFromFixerIo;
import valuta.model.ValutesFromFixerIoRates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;

/**
 * Created by Farkas Tamás on 2017.05.07..
 */
public class ReadDatesFromWeb {

    public static JSONObject readJsonFromUrl(String url) {
        JSONObject jsonObject = null;
        try (InputStream is = new URL(url).openStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder stringBuilder = new StringBuilder();
            int cp;
            while ((cp = bufferedReader.read()) != -1) {
                stringBuilder.append((char) cp);
            }
            String jsonText = stringBuilder.toString();
            jsonObject = new JSONObject(jsonText);

        } catch (IOException | JSONException e) {
//            e.printStackTrace();
            System.out.println("Tul gyors lekerdezes");
        }
        return jsonObject;
    }

    public static ValutesFromFixerIo valutesFromJson() {
        ValutesFromFixerIo proba = new ValutesFromFixerIo();
        JSONObject temp = readJsonFromUrl("http://api.fixer.io/latest");
        proba.setValuteBase(temp.getString("base"));
        proba.setValuteDate(LocalDate.parse(temp.get("date").toString()));
        JSONObject jsonValues = temp.getJSONObject("rates");

        Iterator<String> it = jsonValues.keys();
        while (it.hasNext()) {
            String key = it.next();
            Double value = jsonValues.getDouble(key);
            ValutesFromFixerIoRates rates = new ValutesFromFixerIoRates(key, value);
            proba.getRatelist().add(rates);
        }
        FXCollections.sort(proba.getRatelist(), ((o1, o2) -> o1.getValuteRateName().compareTo(o2.getValuteRateName())));
        return proba;
    }

    public static ObservableList<Double> last30DaySelect(String name) {
        ObservableList<Double> values = FXCollections.observableArrayList();
        String webPage = "http://api.fixer.io/";
        LocalDate actualDate = LocalDate.now();
        JSONObject temp = readJsonFromUrl(webPage + actualDate.toString());

        for (int i = 30; i >= 0; i--) {
            try {
                JSONObject jsonValues = temp.getJSONObject("rates");
                values.add(jsonValues.getDouble(name));
                actualDate = actualDate.minusDays(1);
                temp = readJsonFromUrl(webPage + actualDate.toString());
            } catch (JSONException | NullPointerException e) {
                System.out.println("Itt dob nullpointert last 30 Day");
//                e.printStackTrace();
            }
        }
        return values;
    }

    public static double selectedValutePrice(String name) {
        JSONObject temp = readJsonFromUrl("http://api.fixer.io/latest");
        JSONObject jsonValues = temp.getJSONObject("rates");
        return Double.parseDouble(jsonValues.get(name).toString());
    }


    public static ObservableList<JSONObject> jsonObjects30Day() {
        ObservableList<JSONObject> list = FXCollections.observableArrayList();
        String webPage = "http://api.fixer.io/";
        LocalDate actualDate = LocalDate.now();
        JSONObject temp = readJsonFromUrl(webPage + actualDate.toString());

        for (int i = 30; i >= 0; i--) {
            try {
                JSONObject jsonValues = temp.getJSONObject("rates");
                list.add(jsonValues);
                actualDate = actualDate.minusDays(1);
                temp = readJsonFromUrl(webPage + actualDate.toString());
            } catch (JSONException | NullPointerException e) {
                System.out.println("Itt dob nullpointert last 30 Day");
//                e.printStackTrace();
            }
        }
        return list;
    }

    public static int getIndexFromActualCurrency(String name) {
        ValutesFromFixerIo valute = valutesFromJson();

        if (name.equals("")) {
            return -1;
        }

        int index = 0;
        for (int i = 0; i < valute.getRatelist().size(); i++) {
            if (valute.getRatelist().get(i).getValuteRateName().equals(name)) {
                index = i;
            }
        }
        return index;
    }


}
