package br.com.almavivasolutions.integrador.utils.dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvLoader {
    private static Dotenv dotenv;
    public static final String botEmail;
    public static final String botPass;
    
    static {
        dotenv = Dotenv.load();
        botEmail = dotenv.get("BOT_EMAIL");
        botPass = dotenv.get("BOT_PASS");
    }
}
