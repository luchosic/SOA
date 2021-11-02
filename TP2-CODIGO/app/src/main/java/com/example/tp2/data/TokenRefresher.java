package com.example.tp2.data;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.Date;

public class TokenRefresher {

    final long tokenWaitingTime = 600000; //Actualizo el token cada 10 minutos.
    SessionManager sessionManager;
    Context context;
    TrustRequest trustRequest;

    public TokenRefresher(Context context) {
        this.context = context;
        this.sessionManager = new SessionManager(this.context);
        this.trustRequest = new TrustRequest(this.context);
    }

    public void  ejecutarThread(){
        final Handler mHandler = new Handler();

        new Thread(new Runnable() {

            Long time = new Date().getTime();

            @Override
            public void run () {

                Runnable refresh = new Runnable() {
                    @Override
                    public void run(){
                        Toast.makeText(context.getApplicationContext(),"Token actualizado!",Toast.LENGTH_LONG).show();
                    }
                };

                while (true){
                    Long currentTime = new Date().getTime();

                    if((currentTime - time) >= tokenWaitingTime){
                        time = currentTime;

                        if(sessionManager.isTokenExpired()){
                            trustRequest.refreshToken();
                            mHandler.post(refresh);
                        }
                    }
                }
            }
        }).start();

}
}