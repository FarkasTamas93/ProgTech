package valuta.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Observable;

/**
 * Created by Farkas Tamás on 2017.05.05..
 */

@Entity
@Table(name = "valutes")
public class ValutesToDatabase {

    private int eid;
    private StringProperty userName = new SimpleStringProperty(null);
    private DoubleProperty AUD = new SimpleDoubleProperty(0);
    private DoubleProperty BGN = new SimpleDoubleProperty(0);
    private DoubleProperty BRL = new SimpleDoubleProperty(0);
    private DoubleProperty CAD = new SimpleDoubleProperty(0);
    private DoubleProperty CHF = new SimpleDoubleProperty(0);
    private DoubleProperty CNY = new SimpleDoubleProperty(0);
    private DoubleProperty CZK = new SimpleDoubleProperty(0);
    private DoubleProperty DKK = new SimpleDoubleProperty(0);
    private DoubleProperty GBP = new SimpleDoubleProperty(0);
    private DoubleProperty HKD = new SimpleDoubleProperty(0);
    private DoubleProperty HRK = new SimpleDoubleProperty(0);
    private DoubleProperty HUF = new SimpleDoubleProperty(0);
    private DoubleProperty IDR = new SimpleDoubleProperty(0);
    private DoubleProperty ILS = new SimpleDoubleProperty(0);
    private DoubleProperty INR = new SimpleDoubleProperty(0);
    private DoubleProperty JPY = new SimpleDoubleProperty(0);
    private DoubleProperty KRW = new SimpleDoubleProperty(0);
    private DoubleProperty MXN = new SimpleDoubleProperty(0);
    private DoubleProperty MYR = new SimpleDoubleProperty(0);
    private DoubleProperty NOK = new SimpleDoubleProperty(0);
    private DoubleProperty NZD = new SimpleDoubleProperty(0);
    private DoubleProperty PHP = new SimpleDoubleProperty(0);
    private DoubleProperty PLN = new SimpleDoubleProperty(0);
    private DoubleProperty RON = new SimpleDoubleProperty(0);
    private DoubleProperty RUB = new SimpleDoubleProperty(0);
    private DoubleProperty SEK = new SimpleDoubleProperty(0);
    private DoubleProperty SGD = new SimpleDoubleProperty(0);
    private DoubleProperty THB = new SimpleDoubleProperty(0);
    private DoubleProperty TRY = new SimpleDoubleProperty(0);
    private DoubleProperty USD = new SimpleDoubleProperty(0);
    private DoubleProperty ZAR = new SimpleDoubleProperty(0);
    private DoubleProperty remainMoney = new SimpleDoubleProperty(10000);

    public ValutesToDatabase() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public Double getAUD() {
        return AUD.get();
    }

    public DoubleProperty AUDProperty() {
        return AUD;
    }

    public void setAUD(Double AUD) {
        this.AUD.set(AUD);
    }

    public Double getBGN() {
        return BGN.get();
    }

    public DoubleProperty BGNProperty() {
        return BGN;
    }

    public void setBGN(Double BGN) {
        this.BGN.set(BGN);
    }

    public Double getBRL() {
        return BRL.get();
    }

    public DoubleProperty BRLProperty() {
        return BRL;
    }

    public void setBRL(Double BRL) {
        this.BRL.set(BRL);
    }

    public Double getCAD() {
        return CAD.get();
    }

    public DoubleProperty CADProperty() {
        return CAD;
    }

    public void setCAD(Double CAD) {
        this.CAD.set(CAD);
    }

    public Double getCHF() {
        return CHF.get();
    }

    public DoubleProperty CHFProperty() {
        return CHF;
    }

    public void setCHF(Double CHF) {
        this.CHF.set(CHF);
    }

    public Double getCNY() {
        return CNY.get();
    }

    public DoubleProperty CNYProperty() {
        return CNY;
    }

    public void setCNY(Double CNY) {
        this.CNY.set(CNY);
    }

    public Double getCZK() {
        return CZK.get();
    }

    public DoubleProperty CZKProperty() {
        return CZK;
    }

    public void setCZK(Double CZK) {
        this.CZK.set(CZK);
    }

    public Double getDKK() {
        return DKK.get();
    }

    public DoubleProperty DKKProperty() {
        return DKK;
    }

    public void setDKK(Double DKK) {
        this.DKK.set(DKK);
    }

    public Double getGBP() {
        return GBP.get();
    }

    public DoubleProperty GBPProperty() {
        return GBP;
    }

    public void setGBP(Double GBP) {
        this.GBP.set(GBP);
    }

    public Double getHKD() {
        return HKD.get();
    }

    public DoubleProperty HKDProperty() {
        return HKD;
    }

    public void setHKD(Double HKD) {
        this.HKD.set(HKD);
    }

    public Double getHRK() {
        return HRK.get();
    }

    public DoubleProperty HRKProperty() {
        return HRK;
    }

    public void setHRK(Double HRK) {
        this.HRK.set(HRK);
    }

    public Double getHUF() {
        return HUF.get();
    }

    public DoubleProperty HUFProperty() {
        return HUF;
    }

    public void setHUF(Double HUF) {
        this.HUF.set(HUF);
    }

    public Double getIDR() {
        return IDR.get();
    }

    public DoubleProperty IDRProperty() {
        return IDR;
    }

    public void setIDR(Double IDR) {
        this.IDR.set(IDR);
    }

    public Double getILS() {
        return ILS.get();
    }

    public DoubleProperty ILSProperty() {
        return ILS;
    }

    public void setILS(Double ILS) {
        this.ILS.set(ILS);
    }

    public Double getINR() {
        return INR.get();
    }

    public DoubleProperty INRProperty() {
        return INR;
    }

    public void setINR(Double INR) {
        this.INR.set(INR);
    }

    public Double getJPY() {
        return JPY.get();
    }

    public DoubleProperty JPYProperty() {
        return JPY;
    }

    public void setJPY(Double JPY) {
        this.JPY.set(JPY);
    }

    public Double getKRW() {
        return KRW.get();
    }

    public DoubleProperty KRWProperty() {
        return KRW;
    }

    public void setKRW(Double KRW) {
        this.KRW.set(KRW);
    }

    public Double getMXN() {
        return MXN.get();
    }

    public DoubleProperty MXNProperty() {
        return MXN;
    }

    public void setMXN(Double MXN) {
        this.MXN.set(MXN);
    }

    public Double getMYR() {
        return MYR.get();
    }

    public DoubleProperty MYRProperty() {
        return MYR;
    }

    public void setMYR(Double MYR) {
        this.MYR.set(MYR);
    }

    public Double getNOK() {
        return NOK.get();
    }

    public DoubleProperty NOKProperty() {
        return NOK;
    }

    public void setNOK(Double NOK) {
        this.NOK.set(NOK);
    }

    public Double getNZD() {
        return NZD.get();
    }

    public DoubleProperty NZDProperty() {
        return NZD;
    }

    public void setNZD(Double NZD) {
        this.NZD.set(NZD);
    }

    public Double getPHP() {
        return PHP.get();
    }

    public DoubleProperty PHPProperty() {
        return PHP;
    }

    public void setPHP(Double PHP) {
        this.PHP.set(PHP);
    }

    public Double getPLN() {
        return PLN.get();
    }

    public DoubleProperty PLNProperty() {
        return PLN;
    }

    public void setPLN(Double PLN) {
        this.PLN.set(PLN);
    }

    public Double getRON() {
        return RON.get();
    }

    public DoubleProperty RONProperty() {
        return RON;
    }

    public void setRON(Double RON) {
        this.RON.set(RON);
    }

    public Double getRUB() {
        return RUB.get();
    }

    public DoubleProperty RUBProperty() {
        return RUB;
    }

    public void setRUB(Double RUB) {
        this.RUB.set(RUB);
    }

    public Double getSEK() {
        return SEK.get();
    }

    public DoubleProperty SEKProperty() {
        return SEK;
    }

    public void setSEK(Double SEK) {
        this.SEK.set(SEK);
    }

    public Double getSGD() {
        return SGD.get();
    }

    public DoubleProperty SGDProperty() {
        return SGD;
    }

    public void setSGD(Double SGD) {
        this.SGD.set(SGD);
    }

    public Double getTHB() {
        return THB.get();
    }

    public DoubleProperty THBProperty() {
        return THB;
    }

    public void setTHB(Double THB) {
        this.THB.set(THB);
    }

    public Double getTRY() {
        return TRY.get();
    }

    public DoubleProperty TRYProperty() {
        return TRY;
    }

    public void setTRY(Double TRY) {
        this.TRY.set(TRY);
    }

    public Double getUSD() {
        return USD.get();
    }

    public DoubleProperty USDProperty() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD.set(USD);
    }

    public Double getZAR() {
        return ZAR.get();
    }

    public DoubleProperty ZARProperty() {
        return ZAR;
    }

    public void setZAR(Double ZAR) {
        this.ZAR.set(ZAR);
    }

    public double getRemainMoney() {
        return remainMoney.get();
    }

    public DoubleProperty remainMoneyProperty() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney.set(remainMoney);
    }

    @Transient
    private ObservableList<DoubleProperty> valutesList = FXCollections.observableArrayList(
            Arrays.asList(AUD, BGN, BRL, CAD, CHF, CNY, CZK, DKK, GBP, HKD, HRK, HUF, IDR, ILS, INR, JPY, KRW, MXN, MYR, NOK, NZD, PHP, PLN, RON, RUB, SEK, SGD, THB, TRY, USD, ZAR));

    @Transient
    public ObservableList<DoubleProperty> getValutesList() {
        return valutesList;
    }

    @Transient
    public void setValutesList(ObservableList<DoubleProperty> valutesList) {
        this.valutesList = valutesList;
    }
}
