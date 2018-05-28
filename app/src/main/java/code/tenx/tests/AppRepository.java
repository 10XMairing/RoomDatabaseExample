package code.tenx.tests;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class AppRepository {


    private static String LOG_TAG = "AppRepository";
    private ToDoDao mToDoDao;
    private LiveData<List<ToDo>> mListToDo;
    AppRepository(Application application){
        Log.d(LOG_TAG, "Constructor called");
        AppRoomDatabase db = AppRoomDatabase.getAppDatabase(application);
        mToDoDao = db.toDoDao();
        mListToDo = mToDoDao.getAllToDo();
    }

    LiveData<List<ToDo>> getAllTodos(){
        Log.d(LOG_TAG, "getAllTodos called");
        return mListToDo;
    }

    public void insertTodo(ToDo toDo){
        Log.d(LOG_TAG, "toDO called");
            new insertAsyncTask(mToDoDao).execute(toDo);
    }

    public void deleteAll(){
        Log.d(LOG_TAG, "toDO called");
        new deleteAsyncTask(mToDoDao).execute();
    }


    private static class insertAsyncTask extends AsyncTask<ToDo, Void, Void>{

        private ToDoDao mToDoDao;
        insertAsyncTask(ToDoDao dao){
            Log.d(LOG_TAG, "insertAsyncTask called");
            this.mToDoDao = dao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            Log.d(LOG_TAG, "doInBackground called");
            mToDoDao.insertTodo(toDos[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void>{

        private ToDoDao dao;

        deleteAsyncTask(ToDoDao dao){
            this.dao =dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
