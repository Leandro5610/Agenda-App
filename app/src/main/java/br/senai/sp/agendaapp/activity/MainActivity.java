package br.senai.sp.agendaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AppBarConfiguration barConfiguration;

    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //define como content view a raiz do binding
        setContentView(binding.getRoot());

        //associa a barra do aplicativo a navgation
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        barConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, barConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,barConfiguration)|| super.onSupportNavigateUp();
    }
}