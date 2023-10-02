package com.example.tp1_labo3_navarro.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tp1_labo3_navarro.Modelo.Usuario;
import com.example.tp1_labo3_navarro.R;
import com.example.tp1_labo3_navarro.databinding.ActivityMainBinding;
import com.example.tp1_labo3_navarro.ui.registro.RegistroActivity;
import com.example.tp1_labo3_navarro.ui.registro.RegistroActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        mv.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                //putExtra("usuario", usuario)
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();
            }
        });


        binding.BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.loginUsuario(binding.EtUsuario.getText().toString(),binding.EtClave.getText().toString());

            }
        });

        binding.BtRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

    }
}