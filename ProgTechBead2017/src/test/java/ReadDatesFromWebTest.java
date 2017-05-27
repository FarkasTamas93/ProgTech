/**
 * Created by Farkas Tamás on 2017.05.26..
 */

import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import valuta.ReadDatesFromWeb;
import valuta.model.ValutesFromFixerIo;

import java.io.IOException;

public class ReadDatesFromWebTest {
    private static ReadDatesFromWeb date;
    @BeforeClass
    public static void initReadDatedFromWeb() {
        date = new ReadDatesFromWeb();
        System.out.println("Letrehozunk egy peldanyt a tesztek elott");
    }

    @Ignore
    @Test
    public void testName() throws Exception {
        System.out.println("hello");
    }

    @Test
    public void dateTest() {
        org.junit.Assert.assertNotNull(date);
    }

    @Test
    public void getIndexFromActualCurrencyTest() {
        int result = ReadDatesFromWeb.getIndexFromActualCurrency("AUD");
        org.junit.Assert.assertEquals(0, result);
        int result2 = ReadDatesFromWeb.getIndexFromActualCurrency("BGN");
        org.junit.Assert.assertEquals(1, result2);
        int result3 = ReadDatesFromWeb.getIndexFromActualCurrency("BRL");
        org.junit.Assert.assertEquals(2, result3);
        int result4 = ReadDatesFromWeb.getIndexFromActualCurrency("CAD");
        org.junit.Assert.assertEquals(3, result4);
        int result5 = ReadDatesFromWeb.getIndexFromActualCurrency("ZAR");
        org.junit.Assert.assertEquals(30, result5);
    }

    @Test
    public void jsonObjects30DayTest() {
        ObservableList<JSONObject> list = ReadDatesFromWeb.jsonObjects30Day();
        org.junit.Assert.assertEquals(31, list.size());
    }

    @Ignore
    @Test
    public void selectedValutePriceTest()
    {
        org.junit.Assert.assertEquals(1.5022,ReadDatesFromWeb.selectedValutePrice("AUD"),0.01);
    }

}

