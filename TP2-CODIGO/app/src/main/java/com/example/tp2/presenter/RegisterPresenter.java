package com.example.tp2.presenter;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.tp2.R;
import com.example.tp2.data.Event;
import com.example.tp2.data.InternetConnection;
import com.example.tp2.data.SoaAPIErrorMessage;
import com.example.tp2.data.SoaAPIResponse;
import com.example.tp2.data.SoaAPIService;
import com.example.tp2.data.TrustRequest;
import com.example.tp2.data.User;
import com.example.tp2.data.UserValidate;
import com.example.tp2.view.LoginActivity;
import com.example.tp2.view.RegisterActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPresenter {
    public RegisterActivity activity;
    private TrustRequest trustRequest;
    Event eventoALoguear = new Event();

    public RegisterPresenter(RegisterActivity activity) {
        this.activity = activity;
    }


    public void registerUser(){
        final String TAG = "RegisterActivity";

        User user = new User();

        //Mapeamos los datos del usuario en nuestra clase.
        user.setEnv(activity.getString(R.string.APIEnvoriment));
        user.setName(activity.editTextNombre.getText().toString());
        user.setLastname(activity.editTextApellido.getText().toString());
        user.setEmail(activity.editTextEmail.getText().toString());
        user.setPassword(activity.editTextPassword.getText().toString());

        if(activity.editTextDNI.getText().toString().isEmpty())
            user.setDni(Long.parseLong("0"));
        else
            user.setDni(Long.parseLong(activity.editTextDNI.getText().toString()));

        if(activity.editTextCommission.getText().toString().isEmpty())
            user.setCommission(Long.parseLong("0"));
        else
            user.setCommission(Long.parseLong(activity.editTextCommission.getText().toString()));

        if(activity.editTextGrupo.getText().toString().isEmpty())
            user.setGroup(Integer.parseInt("0"));
        else
            user.setGroup(Integer.parseInt(activity.editTextGrupo.getText().toString()));



        //validamos el usuario en cuestión
        UserValidate userValidate = user.validate();

        if (userValidate.isSuccess()) {

            if(InternetConnection.isOnline(activity)) {

                //iniciamos la conexión con el server
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(activity.getString(R.string.retrofit_server))
                        .build();

                SoaAPIService soaService = retrofit.create(SoaAPIService.class);

                Call<SoaAPIResponse> call = soaService.register(user);
                call.enqueue(new Callback<SoaAPIResponse>() {
                    @Override
                    public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {
                        if (response.isSuccessful()) {

                            //Limpio los input
                            activity.editTextNombre.setText("");
                            activity.editTextApellido.setText("");
                            activity.editTextDNI.setText("");
                            activity.editTextCommission.setText("");
                            activity.editTextEmail.setText("");
                            activity.editTextPassword.setText("");

                            //Guardo los tokens en el sharedPreferences
                            //sessionManager.storeTokens(response.body().getToken(), response.body().getToken_refresh());
                            //sessionManager.storeEmail(editTextEmail.getText().toString());

                            Log.i(TAG, response.body().toString());

                            activity.registerSuccessful();

                            //Logueo evento
                            trustRequest = new TrustRequest(activity.getApplicationContext());

                            eventoALoguear.setEnv("PROD");
                            eventoALoguear.setType_events("Registro exitoso");
                            eventoALoguear.setDescription("Se ha registrado el usuario: " + user.getEmail());
                            trustRequest.registerEvent(eventoALoguear);

                        } else {
                            //Parseo la respuesta para poder mostrarla en la app
                            Gson gson = new Gson();
                            SoaAPIErrorMessage error = gson.fromJson(response.errorBody().charStream(), SoaAPIErrorMessage.class);
                            Log.e(TAG, response.errorBody().toString());
                            activity.registerFailure(error.getMsg());

                        }
                        Log.i(TAG, "Mensaje finalizado");
                    }

                    @Override
                    public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                        activity.registerFailure("Parece que hubo un problema en el servidor, intentelo nuevamente.");
                        Log.e(TAG, t.getMessage().toString());
                    }
                });

            }else {
                activity.registerFailure("No hay conexión a internet.");
            }

        } else {
            activity.registerFailure(userValidate.getMsg());
        }
    }


}
