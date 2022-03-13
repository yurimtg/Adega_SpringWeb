package com.senac.springWebPi4.Utils;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class UtilsPassword {

    private static final BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

    public static String criptografar(String senha) {
        return encryptor.encryptPassword(senha);
    }
    
    public static boolean isSenhaCorreta(String senha, String senhaCriptografada) {
        return encryptor.checkPassword(senha, senhaCriptografada);
    }
}
