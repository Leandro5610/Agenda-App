package br.senai.sp.agendaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //define como content view a raiz do binding
        setContentView(binding.getRoot());
    }
}