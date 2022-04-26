package br.senai.sp.agendaapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.senai.sp.agendaapp.model.Tarefa;

@Dao
public interface TarefaDao {
    @Insert
    void insert(Tarefa tarefa);
    @Update
    void update(Tarefa tarefa);
    @Delete
    void delete(Tarefa tarefa);
    @Query("select * from tarefa")
    List<Tarefa> getAll();

}
