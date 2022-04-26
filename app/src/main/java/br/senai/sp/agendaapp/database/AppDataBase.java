package br.senai.sp.agendaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.sp.agendaapp.model.Tarefa;

@Database(entities = {Tarefa.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    //atributo para acessar a database
    private static AppDataBase dataBase;
    //metodo para TarefaDao
    public abstract TarefaDao getTarefaDao();

    //metodo para acessar o atributo que acessa a database
    public static AppDataBase getDataBase(Context context){
        //verifica se não foi instaciada
        if (dataBase == null){
            //instancia a database
            dataBase = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"todolist").build();
        }
        //retorna a database
        return dataBase;
    }

}
