package valuta;

/**
 * Created by Farkas Tam�s on 2017.05.25..
 */
public class CalculationService {

    //Itt sz�molom ki hogy mennyi valut�m marad miut�n eladtam a megl�v�kb�l
    //Saj�t valuta - annyi valuta amennyit el fogok adni
    public static double remainingValutes(double actualValutePrice, double minusValutePrice) {
        double result = actualValutePrice - minusValutePrice;
//        return result;
        result = Math.round(result * 100);
        return result / 100;
    }

    public static double convertToEuro(double boughtValutePrice, double actualVaultePrice) {
        double convertToEuro = boughtValutePrice / actualVaultePrice;
//        return convertToEuro;
        convertToEuro=Math.round(convertToEuro * 100);
        return convertToEuro / 100;
    }

    //Kisz�molom hogy mennyi valut�m van �sszesen az adott p�nznemb�l �s ha t�bbet vonok ki mint amennyim van akkor 0-t fog �tadni �s valid�l�sn�l alert lesz
    //Ha t�bb valut�m vagy ugyanannyim van mint amennyit eladok akkor �tkonvert�lom eur�v� �s azzal t�rek vissza
    public static double calculationToBuyValute(double remainingMoney, double boughtValute, String selectedValute) {
        double result = 0.0;
        if (remainingMoney > 0) {
            Double actualValutaPrice = ReadDatesFromWeb.selectedValutePrice(selectedValute);
            result = remainingMoney - ((1 / actualValutaPrice) * boughtValute);
        }
//        return result;
        result = Math.round(result * 100);
        return result / 100;
    }

    public static double allRemainingMoneyConvertToOtherCurrency(String selectedCurrency, double remainingMoney) {
        double temp = remainingMoney * ReadDatesFromWeb.selectedValutePrice(selectedCurrency);
//        return temp;
        temp = Math.round(temp * 100);
        return  temp / 100;
    }

    public static double sellValuteCalculation(double allCurrencyValuta, double currencyToSell, double actualVaultePrice) {
        double result = 0.0;
        if (allCurrencyValuta - currencyToSell >= 0) {
            result = convertToEuro(currencyToSell, actualVaultePrice);
        }
//        return result;
        result = Math.round(result * 100);
        return  result / 100;
    }

    public static double buyValutaUseAllMoney(double firstValuta, double firstValutaPrice, String chooseRateName) {
        double result = convertToEuro(firstValuta, firstValutaPrice);
//        return result*ReadDatesFromWeb.selectedValutePrice(chooseRateName);
        result=Math.round(result * ReadDatesFromWeb.selectedValutePrice(chooseRateName) * 100);
        return  result / 100;
//        return result;
    }

    public static double buyValutaFromOtherValuta(String firstSelectedValuta, double firstValuta, String secondSelectedValuta, double remainingMoney) {
        double result = convertToEuro(firstValuta, ReadDatesFromWeb.selectedValutePrice(secondSelectedValuta));
        double temp = result * ReadDatesFromWeb.selectedValutePrice(firstSelectedValuta);
        double remaining = remainingMoney - temp;
        remaining = Math.round(remaining * 100);
        remaining=remaining/100;
//        return remaining;
        return remaining;
    }

    public static double afterBuyTheActualPriceSum(double remainingMoney, double boughtValuaPrice) {
        return remainingMoney + boughtValuaPrice;
    }
}
