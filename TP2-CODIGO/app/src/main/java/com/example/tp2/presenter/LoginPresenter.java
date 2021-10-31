package com.example.tp2.presenter;

import android.util.Log;
import android.widget.Toast;

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

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    public void loginEnServer (){
        User user = new User();
        String TAG = "LoginActivity";

        DBInsertLogin model = new DBInsertLogin(activity, user);

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

                    activity.loginSuccessful();


                } else {
                    //Parseo la respuesta para poder mostrarla en la app
                    Gson gson = new Gson();
                    SoaAPIErrorMessage error = gson.fromJson(response.errorBody().charStream(), SoaAPIErrorMessage.class);
                    //Toast.makeText(activity.getApplicationContext(), "Hubo un error: " + error.getMsg(), Toast.LENGTH_LONG).show();
                    Log.e(TAG, response.errorBody().toString());
                    activity.loginFailure(error.getMsg());

                }
            }

            @Override
            public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                Toast.makeText(activity.getApplicationContext(), "Parece que el servidor no esta funcionando. Intente nuevamente.", Toast.LENGTH_LONG).show();
                Log.e(TAG, t.getMessage().toString());
                //Habilito el botón
                activity.loginButton.setEnabled(true);
                activity.registroBtn.setEnabled(true);
            }
        });
    }

}
