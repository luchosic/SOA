package com.example.tp2.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.tp2.R;
import java.util.Map;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrustRequest {
    private static final String TAG = "TrustRequest";
    private Retrofit retrofit;
    private SoaAPIService soaService;
    private Context context;
    private SessionManager sessionManager;

    public TrustRequest(Context context) {

        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getResources().getString(R.string.retrofit_server))
                .build();

        this.soaService = retrofit.create(SoaAPIService.class);
        this.context = context;
        this.sessionManager = new SessionManager(this.context);
    }

    public void registerEvent(Event event) {
        Map<String, String> tokens = this.sessionManager.getTokens();

        Call<SoaAPIResponse> call = soaService.event(event, "Bearer " + tokens.get("token"));

        call.enqueue(new Callback<SoaAPIResponse>() {
            @Override
            public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se registro el evento" + response.body().toString());
                } else {
                    Log.e(TAG, "No se pudo registrar el evento." + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                Log.e(TAG, "Error al intentar registrar un evento: " + t.getMessage().toString());
            }
        });

    }

    public void refreshToken() {
        Map<String, String> tokens = this.sessionManager.getTokens();

        Call<SoaAPIResponse> call = soaService.refresh_token("Bearer " + tokens.get("refreshToken"));

        call.enqueue(new Callback<SoaAPIResponse>() {
            @Override
            public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "Token refrescado correctamente!!");
                    sessionManager.storeTokens(response.body().getToken(), response.body().getToken_refresh());
                } else {
                    Log.e(TAG, "Problema al intentar refrescar el token funcion success: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                Log.e(TAG, "Problema al intentar refrescar el token falla server: " + t.getMessage().toString());
            }
        });
    }
}