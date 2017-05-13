package com.jmarkstar.sesion4.util;

/**
 * Created by jmarkstar on 12/05/2017.
 */

public class StringUtils {

    public static boolean validarFormatoEmail(String email){
        return email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+");
    }
}
