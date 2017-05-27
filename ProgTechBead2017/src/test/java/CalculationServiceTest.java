/**
 * Created by Farkas Tamás on 2017.05.26..
 */

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import valuta.CalculationService;
import valuta.ReadDatesFromWeb;
public class CalculationServiceTest {

    @Ignore
    @Test
    public void testName(){
        System.out.println("hello");
    }

    @Test
    public void remainingValutesTest()
    {
        org.junit.Assert.assertEquals(3.0, CalculationService.remainingValutes(10.0, 7.0), 0);
    }

    @Test
    public void convertToEuroTest()
    {
        org.junit.Assert.assertEquals(0.6666666666666666,CalculationService.convertToEuro(10, 15),0);
    }

    @Test
    public void calculationToBuyValuteTest()
    {
        org.junit.Assert.assertEquals(0,CalculationService.calculationToBuyValute(0, 110, "HUF"),0);

    }

    @Ignore
    @Test
    public void calculationToBuyValuteTest2()
    {
        double hufPrice=307.93;
        org.junit.Assert.assertEquals(199.67525086870393,CalculationService.calculationToBuyValute(200,100,"HUF"),0);
    }

    @Ignore
    @Test
    public void allRemainingMoneyConvertToOtherCurrencyTest()
    {
        double hufPrice=307.93;
        org.junit.Assert.assertEquals(30793,CalculationService.allRemainingMoneyConvertToOtherCurrency("HUF", 100),0);
    }

    @Test
    public void sellValuteCalculationTest()
    {
        org.junit.Assert.assertEquals(0,CalculationService.sellValuteCalculation(100, 101, 300),0);
    }

    @Test
    public void sellValuteCalculationTest2()
    {   //100/300
        org.junit.Assert.assertEquals(0.3333333333333333,CalculationService.sellValuteCalculation(100,100,300),0);
    }
}
