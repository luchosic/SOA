package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    Button registroBtn;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editTextEmail = findViewById(R.id.editTextTextPersonName);
        final EditText editTextPassword = findViewById(R.id.editTextTextPassword);
        String TAG = "LoginActivity";

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userEmail = extras.getString("useremail");

        editTextEmail.setText(userEmail);

        loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(getString(R.string.retrofit_server))
                        .build();

                SoaAPIService soaAPIService = retrofit.create(SoaAPIService.class);

                User user = new User();
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());

                Call<SoaAPIResponse> call = soaAPIService.login(user);
                call.enqueue(new Callback<SoaAPIResponse>() {
                    @Override
                    public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, response.body().toString());

                            //Guardo los tokens en el sharedPreferences
                            //sessionManager.storeTokens(response.body().getToken(), response.body().getToken_refresh());
                            //sessionManager.storeEmail(emailEditText.getText().toString());

                            Intent intent = new Intent(getApplicationContext(), PlacesPageActivity.class);
                            intent.putExtra("useremail", user.getEmail());
                            startActivity(intent);

                        } else {
                            //Parseo la respuesta para poder mostrarla en la app
                            Gson gson = new Gson();
                            SoaAPIErrorMessage error = gson.fromJson(response.errorBody().charStream(), SoaAPIErrorMessage.class);
                            Toast.makeText(getApplicationContext(), "Hubo un error: " + error.getMsg(), Toast.LENGTH_LONG).show();
                            Log.e(TAG, response.errorBody().toString());
                        }
                        //saco el loader
                        //loadingProgressBar.setVisibility(View.INVISIBLE);
                        Log.i(TAG, "Mensaje finalizado");
                        //Habilito los botones
                        loginButton.setEnabled(true);
                        registroBtn.setEnabled(true);
                    }

                    @Override
                    public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ups, el servidor no está operativo :(", Toast.LENGTH_LONG).show();
                        Log.e(TAG, t.getMessage().toString());
                        //loadingProgressBar.setVisibility(View.INVISIBLE);
                        //Habilito el botón
                        loginButton.setEnabled(true);
                        registroBtn.setEnabled(true);
                    }
                });




            }
        });


        registroBtn = (Button)findViewById(R.id.registroButton);

        registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("useremail", userEmail);
                startActivity(intent);
            }
        });
    }
}
