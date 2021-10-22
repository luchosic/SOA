package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tp2.data.InternetConnection;
import com.example.tp2.data.SoaAPIErrorMessage;
import com.example.tp2.data.SoaAPIResponse;
import com.example.tp2.data.SoaAPIService;
import com.example.tp2.data.User;
import com.example.tp2.data.UserValidate;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {

    private InternetConnection internetConnection;

    Button volverBtn;
    Button registrateButtom;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String userEmail = extras.getString("useremail");

        final EditText editTextNombre       = findViewById(R.id.nombreRegistro);
        final EditText editTextApellido     = findViewById(R.id.apellidoRegistro);
        final EditText editTextDNI          = findViewById(R.id.nrodocRegistro);
        final EditText editTextCommission   = findViewById(R.id.comisionRegistro);
        final EditText editTextEmail        = findViewById(R.id.mailRegistro);
        final EditText editTextPassword     = findViewById(R.id.passwordRegistro);
        final EditText editTextGrupo        = findViewById(R.id.grupoRegistro);

        editTextEmail.setText(userEmail);

        if(InternetConnection.isOnline(this)){
            Log.e(TAG, "Connected to Internet");
        } else
            Log.e(TAG, "NOT Connected to Internet");

        volverBtn = (Button)findViewById(R.id.volverButton);

        volverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registrateButtom = (Button)findViewById(R.id.registrateButtom);

        registrateButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //aca iria el código de cuando apretas REGISTRATE.
                Log.e(TAG, "Iniciando Registro de usuario en WebService de la Cátedra");

                User user = new User();

                //Mapeamos los datos del usuario en nuestra clase.
                user.setEnv(getString(R.string.APIEnvoriment));
                user.setName(editTextNombre.getText().toString());
                user.setLastname(editTextApellido.getText().toString());
                user.setDni(Long.parseLong(editTextDNI.getText().toString()));
                user.setCommission(Long.parseLong(editTextCommission.getText().toString()));
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                user.setGroup(Integer.parseInt(editTextGrupo.getText().toString()));

                //validamos el usuario en cuestión
                UserValidate userValidate = user.validate();

                if (userValidate.isSuccess()) {

                    //iniciamos la conexión con el server
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(getString(R.string.retrofit_server))
                            .build();

                    SoaAPIService soaService = retrofit.create(SoaAPIService.class);

                    Call<SoaAPIResponse> call = soaService.register(user);
                    call.enqueue(new Callback<SoaAPIResponse>() {
                        @Override
                        public void onResponse(Call<SoaAPIResponse> call, Response<SoaAPIResponse> response) {
                            if (response.isSuccessful()) {

                                //Limpio los input
                                editTextNombre.setText("");
                                editTextApellido.setText("");
                                editTextDNI.setText("");
                                editTextCommission.setText("");
                                editTextEmail.setText("");
                                editTextPassword.setText("");

                                //Guardo los tokens en el sharedPreferences
                                //sessionManager.storeTokens(response.body().getToken(), response.body().getToken_refresh());
                                //sessionManager.storeEmail(editTextEmail.getText().toString());

                                Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();
                                Log.i(TAG, response.body().toString());

                                finish();

                                //Voy a la pantalla de Login
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                i.putExtra("useremail", userEmail);
                                startActivity(i);

                            } else {
                                //Parseo la respuesta para poder mostrarla en la app
                                Gson gson = new Gson();
                                SoaAPIErrorMessage error = gson.fromJson(response.errorBody().charStream(), SoaAPIErrorMessage.class);
                                Toast.makeText(getApplicationContext(), "Hubo un error: " + error.getMsg(), Toast.LENGTH_LONG).show();
                                Log.e(TAG, response.errorBody().toString());

                            }
                            Log.i(TAG, "Mensaje finalizado");
                            //Apago el loader
                            //loadingProgressBar.setVisibility(View.INVISIBLE);
                            //Habilito el botón nuevamente
                            //btnRegister.setEnabled(true);
                        }

                        @Override
                        public void onFailure(Call<SoaAPIResponse> call, Throwable t) {
                            //Apago el loader
                            //loadingProgressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Parece que hubo un problema en el servidor, intentelo nuevamente.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, t.getMessage().toString());

                            //Habilito el botón nuevamente
                            //btnRegister.setEnabled(true);
                        }
                    });



                } else {
                    Toast.makeText(getApplicationContext(), userValidate.getMsg(), Toast.LENGTH_LONG).show();
                }

            }
        });




    }


}