package br.senai.sp.agendaapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.FragmentCadTarefaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadTarefa#newInstance} factory method to
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        },year,month,day);
        // listener do botao data
        binding.btData.setOnClickListener(v -> {
          //abre o datepicker
            datapicker.show();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}