package com.example.tp2.data;

import android.content.Context;
import java.util.Date;

public class TokenRefresher extends Thread{

    final long tokenWaitingTime = 600000; //milisegundos de espera para volver a consultar si el token está expirado
    SessionManager sessionManager;
    Context context;
    TrustRequest trustRequest;

    public TokenRefresher(Context context) {
        this.context = context;
        this.sessionManager = new SessionManager(this.context);
        this.trustRequest = new TrustRequest(this.context);
    }

    /**
     * Metodo que queda ejecutando en loop inifinito por el while true
     */
    public void run(){
        Long time = new Date().getTime();

        while (true && !this.isInterrupted()){
            Long currentTime = new Date().getTime();

            if((currentTime - time) >= tokenWaitingTime){ // Verifico el token cada 500 ms (medio segudo)
                time = currentTime;

                if(this.sessionManager.isTokenExpired()){
                    this.trustRequest.refreshToken();
                }
            }
        }
    }

}