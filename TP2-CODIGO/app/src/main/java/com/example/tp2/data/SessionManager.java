package com.example.tp2.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private final Context context;
    private final SharedPreferences sharedPrefs;
    private static final long token_expired_miliseconds = 30000; // 15 minutos en milisegundos

    //Constructor
        public SessionManager(Context context) {
            this.context = context;
            this.sharedPrefs = context.getSharedPreferences("sharedPreferences_Token", Context.MODE_PRIVATE);
        }

    //Metodo para almacenar token en el shared preferences para que perdure el dato
        public void storeTokens(String token, String refreshToken) {
            this.sharedPrefs.edit().putString("token", token).commit();
            this.sharedPrefs.edit().putString("refreshToken", refreshToken).commit();

            Date dateToken = new Date();
            this.sharedPrefs.edit().putLong("timeToken", dateToken.getTime()).commit();
        }

    //Método para obtener el token almacenado.
        public Map<String, String> getTokens() {
            Map<String, String> tokens = new HashMap<String, String>();
            tokens.put("token", this.sharedPrefs.getString("token", ""));
            tokens.put("refreshToken", this.sharedPrefs.getString("refreshToken", ""));

            return tokens;
        }


    public boolean isTokenExpired() {

        String token = this.sharedPrefs.getString("token", "");
        String refreshToken = this.sharedPrefs.getString("refreshToken", "");
        Long tokenTime = this.sharedPrefs.getLong("timeToken", 0);


        //Validación del token
        if(tokenTime == 0 || token.isEmpty() || refreshToken.isEmpty()){
            return true;
        }

        Date now = new Date();
        Long diff_in_ms = now.getTime() - tokenTime;
        return (diff_in_ms >= token_expired_miliseconds);
    }

    //public boolean userHasTokens(){
    //    String token = this.sharedPrefs.getString("token", "");
    //    String refreshToken = this.sharedPrefs.getString("refreshToken", "");
    //
    //    return !token.isEmpty() && !refreshToken.isEmpty();
    //}

    ///**
    // * Pone en blanco los tokens. Se utiliza para el logout
    // */
    //public void endSession(){
    //    this.sharedPrefs.edit().putString("token", "").commit();
    //    this.sharedPrefs.edit().putString("refreshToken", "").commit();
    //}

    /**
     * Metodo para almacenar el mail para que luego sea mostrado dentro de la app
     */
    public void storeEmail(String userEmail){
        this.sharedPrefs.edit().putString("userEmail", userEmail.split("@")[0].trim()).commit();
    }

    public String getEmail(){
        return this.sharedPrefs.getString("userEmail", "");
    }
}