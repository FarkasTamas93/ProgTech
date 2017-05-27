package valuta.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Created by Farkas Tamás on 2017.05.14..
 */
public class ValutesFromFixerIo {
    private StringProperty valuteBase = new SimpleStringProperty();
    private ObjectProperty<LocalDate> valuteDate = new SimpleObjectProperty<>();
    private ObservableList<ValutesFromFixerIoRates> ratelist = FXCollections.observableArrayList();

    public ObservableList<ValutesFromFixerIoRates> getRatelist() {
        return ratelist;
    }

    public void setRatelist(ObservableList<ValutesFromFixerIoRates> ratelist) {
        this.ratelist = ratelist;
    }

    public String getValuteBase() {
        return valuteBase.get();
    }

    public StringProperty valuteBaseProperty() {
        return valuteBase;
    }

    public void setValuteBase(String valuteBase) {
        this.valuteBase.set(valuteBase);
    }

    public LocalDate getValuteDate() {
        return valuteDate.get();
    }

    public ObjectProperty<LocalDate> valuteDateProperty() {
        return valuteDate;
    }

    public void setValuteDate(LocalDate valuteDate) {
        this.valuteDate.set(valuteDate);
    }

    public ValutesFromFixerIo() {

    }

//    public ValutesFromFixerIo(String valuteBase, LocalDate valuteDate, ObservableList<ValutesFromFixerIoRates> ratelist) {
//        this.valuteBase = new SimpleStringProperty(valuteBase);
//        this.valuteDate = valuteDate;
//        this.ratelist = ratelist;
//    }
}
