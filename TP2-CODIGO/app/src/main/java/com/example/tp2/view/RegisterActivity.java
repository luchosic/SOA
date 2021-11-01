package com.example.tp2.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.data.InternetConnection;
import com.example.tp2.data.SoaAPIErrorMessage;
import com.example.tp2.data.SoaAPIResponse;
import com.example.tp2.data.SoaAPIService;
import com.example.tp2.data.User;
import com.example.tp2.data.UserValidate;
import com.example.tp2.presenter.LoginPresenter;
import com.example.tp2.presenter.RegisterPresenter;
import com.google.gson.Gson;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {
    public Button volverBtn;
    public Button registrateButtom;
    public EditText editTextNombre;
    public EditText editTextApellido;
    public EditText editTextDNI;
    public EditText editTextCommission;
    public EditText editTextEmail;
    public EditText editTextPassword;
    public EditText editTextGrupo;
    String userEmail;

    public RegisterPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        userEmail = extras.getString("useremail");

        editTextNombre       = findViewById(R.id.nombreRegistro);
        editTextApellido     = findViewById(R.id.apellidoRegistro);
        editTextDNI          = findViewById(R.id.nrodocRegistro);
        editTextCommission   = findViewById(R.id.comisionRegistro);
        editTextEmail        = findViewById(R.id.mailRegistro);
        editTextPassword     = findViewById(R.id.passwordRegistro);
        editTextGrupo        = findViewById(R.id.grupoRegistro);
        volverBtn = (Button)findViewById(R.id.volverButton);
        registrateButtom = (Button)findViewById(R.id.registrateButtom);

        editTextEmail.setText(userEmail);

        presenter = new RegisterPresenter(this);


        registrateButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deshabilito boton para que no pueda volver a clickear
                registrateButtom.setEnabled(false);

                presenter.registerUser();
            }
        });


        volverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void registerSuccessful() {
        Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();

        //Voy a la pantalla de Login
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        i.putExtra("useremail", userEmail);
        startActivity(i);
    }

    public void registerFailure(String error) {
        Toast.makeText(getApplicationContext(), "Hubo un error: " + error, Toast.LENGTH_LONG).show();

        System.out.println("llego a la falla");

        //Habilito el boton nuevamente para que pueda intentar registrarse
        registrateButtom.setEnabled(true);

    }
}