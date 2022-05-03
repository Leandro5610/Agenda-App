package br.senai.sp.agendaapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.database.AppDataBase;
import br.senai.sp.agendaapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.agendaapp.model.Tarefa;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CadTarefa extends Fragment {
    private FragmentCadTarefaBinding binding;
    //variavel do datepicker
    DatePickerDialog datapicker;
    //variaveis para dia mes e ano
    int year,month,day;
    //variavel que pega a data atual
    Calendar dataAtual;
    //variavel para a data formatada
    String dataEscolhida="";
    //variavel para acessar a database
    AppDataBase dataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancciar a AppDataBase
        dataBase = AppDataBase.getDataBase(getActivity());

        binding= FragmentCadTarefaBinding.inflate(inflater,container,false);
        //instacia o calendar
        dataAtual = Calendar.getInstance();
        //descobre o dia mes e aono atuais
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);
        //instacia datepicker
        datapicker = new DatePickerDialog(getContext(), (view,ano,mes,dia)->{
            //cai aqui toda a vez clica no ok do datepickerdialog
            //passa para as variaveis globais
            year =ano;
            month = mes;
            day = dia;

            //formatar a string d data escolhida
            dataEscolhida = String.format("%02d/%02d/%04d",day, month+1, year);
                // jojgar a string no botao
            binding.btData.setText(dataEscolhida);
        },year,month,day);
        // listener do botao data
        binding.btData.setOnClickListener(v -> {
          //abre o datepicker
            datapicker.show();
        });


        binding.btSave.setOnClickListener(v ->{
            //validar os campos

            if(binding.editTitulo.getText().toString().isEmpty()){
                Toast.makeText(getContext(),R.string.erroTarefa, Toast.LENGTH_LONG).show();
                binding.editTitulo.requestFocus();

            }
            if (dataEscolhida.isEmpty()){
                Toast.makeText(getContext(),R.string.erroData, Toast.LENGTH_LONG).show();
            }
            else{
                //cria um objeto tarefa
                Tarefa tarefa = new Tarefa();

                //popular tarefa
                tarefa.setTitulo(binding.editTitulo.getText().toString());
                tarefa.setDescricao(binding.editDesc.getText().toString());

                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year,month,day);
                //passar para a tarefa as os milesegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                //cria outro calendar para data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                //salvar a tarefa no bAnco de dados
                new InsertTarefa().execute(tarefa);

            }

        });

    // Inflate the layout for this fragment
        return binding.getRoot();
    }
    //clsse para task de inserir a tarefa
        private class InsertTarefa extends AsyncTask<Tarefa, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.w("PASSOu","OnPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w("PASSOU","onProgressUpdate");

        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU","doInBackground");
            //extrair a tarefa do vetor de Tarefas
            Tarefa t = tarefas[0];
            try {
                //tenta inserir
                dataBase.getTarefaDao().insert(t);
                return "Ok";
            }catch (Exception e){
                e.printStackTrace();
                //retorna a mensagem de erro caso tenha dado erro
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String msg) {
            if(msg.equals("Ok")){
                Log.w("RESULTADO","Deu Certo");
                //acionar o botão de voltar
                getActivity().onBackPressed();


            }else {
                Log.w("RESULTADO",msg);
                Toast.makeText(getContext(),"RUIM"+msg,Toast.LENGTH_SHORT).show();
            }


        }
    }

}