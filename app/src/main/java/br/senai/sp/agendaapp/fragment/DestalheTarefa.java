package br.senai.sp.agendaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.databinding.FragmentDestalheTarefaBinding;
import br.senai.sp.agendaapp.databinding.FragmentPrincipalBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestalheTarefa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestalheTarefa extends Fragment {
    private FragmentDestalheTarefaBinding binding;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDestalheTarefaBinding.inflate(inflater,container,false);

       return binding.getRoot();

    }
}