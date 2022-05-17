package br.senai.sp.agendaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.FragmentDestalheTarefaBinding;
import br.senai.sp.agendaapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.agendaapp.model.Tarefa;


public class DestalheTarefa extends Fragment {
    private FragmentDestalheTarefaBinding binding;
    // variavel para a tarefa a ser detalhada
    private Tarefa tarefa;
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDestalheTarefaBinding.inflate(inflater,container,false);
       //verificar se existe algo sendo passado no bundle
       if(getArguments() != null){
           //recupero a tarefa
           tarefa = (Tarefa) getArguments().getSerializable("tarefa");
           binding.tvTitle.setText(tarefa.getTitulo());
           SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
           binding.tvDate.setText(format.format(tarefa.getDataPrevista()));
           //if para ver se a tarefa esta concluida
           if(tarefa.isConcluida()){
               binding.tvTitle.setBackgroundColor(getResources().getColor(R.color.verde));
           }
       }
       binding.addSubTarefa.setOnClickListener(v->{
           Bundle bundle = new Bundle();
           bundle.putSerializable("tarefa", tarefa);
           NavHostFragment.findNavController(DestalheTarefa.this).navigate(R.id.action_destalheTarefa_to_itemTarefa,bundle);

       });


       return binding.getRoot();

    }
}