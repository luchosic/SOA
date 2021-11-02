package com.example.tp2.data;

import android.util.Patterns;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("env")
    private String env;

    @SerializedName("name")
    private String name;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("dni")
    private Long dni;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("commission")
    private Long commission;

    @SerializedName("group")
    private int group;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    /**
     * Valida los datos de un usuario antes de mandar a registrarlo
     * @return
     */
    public UserValidate validate(){

        UserValidate userValidate = new UserValidate();

        if(this.name.isEmpty()){
            userValidate.setMsg("El nombre está vacio");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(this.lastname.isEmpty()){
            userValidate.setMsg("El apellido está vacio");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(this.dni == 0){
            userValidate.setMsg("El DNI es inválido");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(!(String.valueOf(this.dni).matches("[0-9]+"))){
            userValidate.setMsg("El DNI debe contener SOLO números");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(this.email).matches()){
            userValidate.setMsg("El email tiene un formato inválido");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(this.password.isEmpty()){
            userValidate.setMsg("La contraseña está vacía");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(this.password.length() < 8){
            userValidate.setMsg("La contraseña debe tener como mínimo 8 caracteres");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(!(String.valueOf(this.commission).equals("2900") && !(String.valueOf(this.commission).equals("3900")))){
            userValidate.setMsg("La comisión debe ser 2900-Martes o 3900-Miercoles");
            userValidate.setSuccess(false);
            return userValidate;
        }

        if(!String.valueOf(this.group).matches("[0-9]+")){
            userValidate.setMsg("El grupo debe ser númerico");
            userValidate.setSuccess(false);
            return userValidate;
        }

        this.email = this.email.trim();

        userValidate.setSuccess(true);
        userValidate.setMsg("Los datos son correctos");
        return userValidate;

    }
}
