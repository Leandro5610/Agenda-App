package br.senai.sp.agendaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.agendaapp.databinding.FragmentDestalheTarefaBinding;
import br.senai.sp.agendaapp.databinding.FragmentItemTarefaBinding;
import br.senai.sp.agendaapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.agendaapp.model.Tarefa;


public class ItemTarefa extends Fragment {

    private FragmentItemTarefaBinding binding;
    private Tarefa tarefa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentItemTarefaBinding.inflate(inflater,container,false);
        if(getArguments() != null){
            //recupero a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tvTitleSub.setText(tarefa.getTitulo());
        }
        return binding.getRoot();
    }
}