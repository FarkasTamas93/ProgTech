package valuta;

import valuta.model.Login;

/**
 * Created by Farkas Tamás on 2017.05.14..
 */
public class LoggedUserService {
    private static Login instance = new Login();

    public static Login getInstance() {
        return instance;
    }

    public static void setInstance(Login instance) {
        LoggedUserService.instance = instance;
    }
}
