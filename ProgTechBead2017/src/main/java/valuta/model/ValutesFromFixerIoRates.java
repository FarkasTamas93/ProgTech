package valuta.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Farkas Tamás on 2017.05.14..
 */
public class ValutesFromFixerIoRates {
    private StringProperty valuteRateName;
    private DoubleProperty valuteRatePrice;

    public ValutesFromFixerIoRates(String valuteRateName, Double valuteRatePrice) {
        this.valuteRateName = new SimpleStringProperty(valuteRateName);
        this.valuteRatePrice = new SimpleDoubleProperty(valuteRatePrice);
    }

    public String getValuteRateName() {
        return valuteRateName.get();
    }

    public StringProperty valuteRateNameProperty() {
        return valuteRateName;
    }

    public void setValuteRateName(String valuteRateName) {
        this.valuteRateName.set(valuteRateName);
    }

    public double getValuteRatePrice() {
        return valuteRatePrice.get();
    }

    public DoubleProperty valuteRatePriceProperty() {
        return valuteRatePrice;
    }

    public void setValuteRatePrice(double valuteRatePrice) {
        this.valuteRatePrice.set(valuteRatePrice);
    }
}
