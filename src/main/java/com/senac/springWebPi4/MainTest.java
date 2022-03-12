package com.senac.springWebPi4;

import com.senac.springWebPi4.Utils.UtilsCriptografia;

public class MainTest {

    public static void main(String[] args) throws Exception {
        UtilsCriptografia uc = new UtilsCriptografia();
        String senha = "jamileYURIdanielMATHEUS010101";
        byte[] senhaCriptografada = uc.criptografar(senha.getBytes());
        String senhaDescriptografada = uc.descriptografar(senhaCriptografada);

        System.out.println("Senha: " + senha);
        System.out.println("Senha Criptografada: " + new String(senhaCriptografada));
        System.out.println("Senha Descriptografada: " + senhaDescriptografada);

    }
}
