package com.senac.springWebPi4;

import com.senac.springWebPi4.Utils.UtilsPassword;

public class MainTest {

    public static void main(String[] args) {
        
        String senha = "{ABC-DEF-123-456}";
        String senhaCriptografada = UtilsPassword.criptografar(senha);
        
        boolean testeA = UtilsPassword.isSenhaCorreta("{ABC-DEF-123-456}", senhaCriptografada); // Passando senha correta
        boolean testeB = UtilsPassword.isSenhaCorreta("{789-D21F-141-456}", senhaCriptografada); // Passando senha inccorreta
        
        System.out.println("Resultado teste A: " + (testeA ? "Senha correta." : "Senha incorreta."));
        System.out.println("Resultado teste B: " + (testeB ? "Senha correta." : "Senha incorreta."));
    }
}
