package code.tenx.tests;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class AppViewModel extends AndroidViewModel {

    private static String LOG_TAG = "AppViewModel";
    private AppRepository mRepo;
    private LiveData<List<ToDo>> mListToDo;

    public AppViewModel(@NonNull Application application) {
        super(application);
        Log.d(LOG_TAG, "constructor called");
        mRepo = new AppRepository(application);
        mListToDo = mRepo.getAllTodos();
    }


    LiveData<List<ToDo>> getAllToDos(){
        Log.d(LOG_TAG, "getAlltodos called");
        return mListToDo;
    }
    public void insertTodo(ToDo todo){
        Log.d(LOG_TAG, "insertToDo called");
        mRepo.insertTodo(todo);
    }

    public void deleteAll(){
        Log.d(LOG_TAG, "deleteAll called");
        mRepo.deleteAll();
    }
}
