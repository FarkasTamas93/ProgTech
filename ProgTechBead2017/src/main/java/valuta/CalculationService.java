package valuta;

/**
 * Created by Farkas Tamás on 2017.05.25..
 */
public class CalculationService {

    //Itt számolom ki hogy mennyi valutám marad miután eladtam a meglévõkbõl
    //Saját valuta - annyi valuta amennyit el fogok adni
    public static double remainingValutes(double actualValutePrice,double minusValutePrice) {
        Double result = actualValutePrice-minusValutePrice;
        return result;
    }

    public static double convertToEuro(double boughtValutePrice,double actualVaultePrice) {
        double convertToEuro = boughtValutePrice / actualVaultePrice;
        return convertToEuro;
    }

    //Kiszámolom hogy mennyi valutám van összesen az adott pénznembõl és ha többet vonok ki mint amennyim van akkor 0-t fog átadni és validálásnál alert lesz
    //Ha több valutám vagy ugyanannyim van mint amennyit eladok akkor átkonvertálom euróvá és azzal térek vissza
    public static double calculationToBuyValute(double remainingMoney, double boughtValute,String selectedValute) {
        Double result = 0.0;
        if (remainingMoney > 0) {
            Double actualValutaPrice = ReadDatesFromWeb.selectedValutePrice(selectedValute);
            result = remainingMoney - (1 / actualValutaPrice) *  boughtValute;
        }
        return result;
    }

    public static double allRemainingMoneyConvertToOtherCurrency(String selectedCurrency,double remainingMoney)
    {
        double temp = remainingMoney*ReadDatesFromWeb.selectedValutePrice(selectedCurrency);
        return temp;
    }

    public static double sellValuteCalculation(double allCurrencyValuta,double currencyToSell,double actualVaultePrice)
    {
        Double result = 0.0;
        if (allCurrencyValuta - currencyToSell >= 0) {
            result = convertToEuro(currencyToSell,actualVaultePrice);
        }
        return result;
    }

    public static double buyValutaUseAllMoney(double firstValuta,double firstValutaPrice,String chooseRateName)
    {
        double result=convertToEuro(firstValuta,firstValutaPrice);
        return result*ReadDatesFromWeb.selectedValutePrice(chooseRateName);
    }

    public static double buyValutaFromOtherValuta(String firstSelectedValuta,double firstValuta,String secondSelectedValuta,double remainingMoney)
    {
        double result = convertToEuro(firstValuta, ReadDatesFromWeb.selectedValutePrice(secondSelectedValuta));
        double temp = result * ReadDatesFromWeb.selectedValutePrice(firstSelectedValuta);
        double remaining = remainingMoney - temp;
        remaining=Math.round(remaining);
        return remaining;
    }

    public static double afterBuyTheActualPriceSum(double remainingMoney,double boughtValuaPrice)
    {
        return remainingMoney+boughtValuaPrice;
    }
}
