package code.tenx.tests;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {
@Query("SELECT * FROM todo_table")
LiveData<List<ToDo>> getAllToDo();
@Query("DELETE FROM todo_table")
    void deleteAll();
@Insert
    void insertTodo(ToDo todo);
@Delete
    void deleteToDo(ToDo todo);
@Update
    void updateToDo(ToDo todo);

}
