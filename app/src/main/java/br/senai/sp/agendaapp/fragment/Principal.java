package br.senai.sp.agendaapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
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

        //instanciar o database
        dataBase = AppDataBase.getDataBase(getActivity());

        // define o layout maneger do recycleView
        binding.reciyclerTarefas.setLayoutManager(new LinearLayoutManager(getActivity()));
        //executa a async Task
        new ReadTarefa().execute();

        // Inflate the layout for this fragment
        return binding.getRoot();

    }
    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>>{


        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            //guardando na variavel tarefa as tarefas do BD
            tarefas = dataBase.getTarefaDao().getAll();
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
           //instancia o adapter
            adapter = new TarefaAdapter(tarefas, getActivity(),listenerTarefa);
            // aolicar o adapter no recyclerView
            binding.reciyclerTarefas.setAdapter(adapter);

        }
    }
    //implementação para a interface OnTarefaClickListener
    private TarefaAdapter.OnTarefaClickListerner listenerTarefa = (view, tarefa) -> {
        Bundle bundle = new Bundle();
        // pendurar a tarefa no pacote
        bundle.putSerializable("tarefa", tarefa);
        // navega para o proximo fragment enviando o Bundle
        NavHostFragment.findNavController(Principal.this).navigate(R.id.action_principal_to_destalheTarefa, bundle);
    };


}