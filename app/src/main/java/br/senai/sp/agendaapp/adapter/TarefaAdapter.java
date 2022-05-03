package br.senai.sp.agendaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.agendaapp.R;
import br.senai.sp.agendaapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {
    private List<Tarefa>tarefas;
    //variavel para context
    private Context context;

    //construtor para receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto){
        this.tarefas= lista;
        this.context = contexto;

    }


    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.adpter_tarefa, parent, false);
        //retorna um novo viewholder com a view
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
      long dataAtual= Calendar.getInstance().getTimeInMillis();
        // pegar a tarefa pela position
        Tarefa t = tarefas.get(position);
        holder.tvTitulo.setText(t.getTitulo());
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.status_finalizada);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.verde));
        }
        else{

            holder.tvStatus.setText(R.string.status_aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.amarelo));
        }
        // formata a data de Long para String
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(format.format(t.getDataPrevista()));
    }

    @Override
    //retorna a quantidade dde elementos a serem exibidos na lista
    public int getItemCount() {
        if(tarefas != null) {
            return tarefas.size();
        }
        return  0;
    }

    //Classe viewholder para mapear os componentes do xml
    class TarefaViewHolder extends RecyclerView.ViewHolder{
        //variaveis para representar os componetes do XML
        TextView tvTitulo,tvData,tvStatus;

        public TarefaViewHolder(View v){
            //chama o condtrutor seuper class
            super(v);
            // passar para as variaveis os componentes do XML
            tvTitulo = v.findViewById(R.id.text_view_titulo_tarefa);
            tvData = v.findViewById(R.id.text_view_data_tarefa);
            tvStatus = v.findViewById(R.id.text_view_status_tarefa);

        }



    }

}
