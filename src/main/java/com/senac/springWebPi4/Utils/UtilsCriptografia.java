package com.senac.springWebPi4.Utils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UtilsCriptografia {

    private final KeyGenerator gerador;
    private final SecretKey chaveDES;
    private final Cipher desCipher;

    public UtilsCriptografia() throws Exception {
        gerador = KeyGenerator.getInstance("DES");
        chaveDES = gerador.generateKey();
        desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    }

    public byte[] criptografar(byte[] valor) throws Exception {
        desCipher.init(Cipher.ENCRYPT_MODE, chaveDES);
        return desCipher.doFinal(valor);
    }

    public String descriptografar(byte[] valor) throws Exception {
        desCipher.init(Cipher.DECRYPT_MODE, chaveDES);
        return new String(desCipher.doFinal(valor));
    }
}
