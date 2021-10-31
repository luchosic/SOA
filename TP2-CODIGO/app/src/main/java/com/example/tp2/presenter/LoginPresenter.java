package com.example.tp2.presenter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.tp2.view.LoginActivity;
import com.example.tp2.R;
import com.example.tp2.data.SoaAPIErrorMessage;
import com.example.tp2.data.SoaAPIResponse;
import com.example.tp2.data.SoaAPIService;
import com.example.tp2.data.User;
import com.example.tp2.model.MyOpenHelper;
import com.google.gson.Gson;

import java.util.Date;

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

                    MyOpenHelper dbHelper = new MyOpenHelper(activity.getApplicationContext());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    if (db != null) {

                        Date date1=java.util.Calendar.getInstance().getTime();

                        ContentValues cv = new ContentValues();
                        cv.put("username", user.getEmail());
                        cv.put("date", String.valueOf(date1));
                        db.insert("user_login", null, cv);

                        //El siguiente codigo es para visualizar lo que se esta guardando en la base de datos.
                        //Eliminarlo para entegar
                        Cursor c = db.rawQuery("SELECT _id, username, date FROM user_login", null);

                        if (c != null) {
                            c.moveToFirst();
                            do {
                                //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                                @SuppressLint("Range") String user = c.getString(c.getColumnIndex("username"));
                                @SuppressLint("Range") String date = c.getString(c.getColumnIndex("date"));
                                System.out.println("Username: " + user + " DATE: " + date);
                            } while (c.moveToNext());
                        }

                        //Cerramos el cursor y la conexion con la base de datos
                        c.close();
                    }

                    db.close();

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
