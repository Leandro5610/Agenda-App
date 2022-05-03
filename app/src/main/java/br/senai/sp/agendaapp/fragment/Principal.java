package br.senai.sp.agendaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.adapter.TarefaAdapter;
import br.senai.sp.agendaapp.database.AppDataBase;
import br.senai.sp.agendaapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.agendaapp.model.Tarefa;


public class Principal extends Fragment {
    private FragmentPrincipalBinding binding;
    private AppDataBase dataBase;
    private TarefaAdapter adapter;
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPrincipalBinding.inflate(inflater,container,false);

        //botao adicionar taref
        binding.addTarefa.setOnClickListener(view -> {
            NavHostFragment.findNavController(Principal.this).navigate(R.id.action_principal_to_cadTarefa);

        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}