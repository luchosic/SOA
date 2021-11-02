package com.example.tp2.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.tp2.data.Event;
import com.example.tp2.data.InternetConnection;
import com.example.tp2.data.SessionManager;
import com.example.tp2.data.TrustRequest;
import com.example.tp2.model.DBInsertLogin;
import com.example.tp2.view.LoginActivity;
import com.example.tp2.R;
import com.example.tp2.data.SoaAPIErrorMessage;
import com.example.tp2.data.SoaAPIResponse;
import com.example.tp2.data.SoaAPIService;
import com.example.tp2.data.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter {
    public LoginActivity activity;
    private SessionManager sessionManager;
    private TrustRequest trustRequest;
    Event eventoALoguear = new Event();

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    public void loginEnServer () {
        User user = new User();
        String TAG = "LoginActivity";

        DBInsertLogin model = new DBInsertLogin(activity, user);

        if (InternetConnection.isOnline(activity)) {

            this.sessionManager = new SessionManager(activity.getApplicationContext());

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(activity.getString(R.string.retrofit_server))
                    .build();

            SoaAPIService soaAPIService = retrofit.create(SoaAPIService.class);

            user.setEmail(activity.editTextEmail.getText().toString());
            user.setPassword(activity.editTextPassword.getText().toString());

            Call<SoaAPIResponse> call = soaAPIService.login(user);
            call.enqueue(new Callback<SoaAPIResponse>() {
                @Override
                public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {
                    if (response.isSuccessful()) {
                        Log.i(TAG, response.body().toString());

                        model.insertInDB();
                        sessionManager.storeTokens(response.body().getToken(), response.body().getToken_refresh());
                        sessionManager.storeEmail(user.getEmail());
                        activity.loginSuccessful();

                        //Logueo evento
                        trustRequest = new TrustRequest(activity.getApplicationContext());

                        eventoALoguear.setEnv("PROD");
                        eventoALoguear.setType_events("Logueo exitoso");
                        eventoALoguear.setDescription("Se ha logueado el usuario: " + user.getEmail());
                        trustRequest.registerEvent(eventoALoguear);

                    } else {
                        //Parseo la respuesta para poder mostrarla en la app
                        Gson gson = new Gson();
                        SoaAPIErrorMessage error = gson.fromJson(response.errorBody().charStream(), SoaAPIErrorMessage.class);
                        Log.e(TAG, response.errorBody().toString());
                        activity.loginFailure(error.getMsg());

                    }
                }

                @Override
                public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                    activity.loginFailure("Parece que el servidor no esta funcionando. Intente nuevamente.");
                    Log.e(TAG, t.getMessage().toString());
                }
            });
        }else{
            activity.loginFailure("No hay conexión a internet");
        }
    }

}
