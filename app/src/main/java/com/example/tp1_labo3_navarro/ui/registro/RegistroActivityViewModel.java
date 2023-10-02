package com.example.tp1_labo3_navarro.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1_labo3_navarro.Modelo.Usuario;
import com.example.tp1_labo3_navarro.request.ApiClient;
import com.example.tp1_labo3_navarro.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient apiClient;

    private RegistroActivity ra;

    private MutableLiveData<Usuario> usuarioM;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        apiClient = new ApiClient();

    }

    public LiveData<Usuario> getUsuarioM(){
        if(usuarioM==null){
            usuarioM = new MutableLiveData<Usuario>();
        }
        return usuarioM;
    }

    public void registrarUsuario(String nombre, String apellido, String dni,String mail, String clave) {
        Long dniLong = Long.parseLong(dni);
        Usuario usuario = new Usuario(nombre, apellido, dniLong, mail, clave);

        apiClient.registrar(context, usuario);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void leerUsuario(){

        Usuario usuario = apiClient.leer(context);
        usuarioM.setValue(usuario);
    }

}

