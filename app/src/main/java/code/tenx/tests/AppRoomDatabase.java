package code.tenx.tests;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
        public abstract ToDoDao toDoDao();
        private static String LOG_TAG = "AppRoomDatabase";

        private static AppRoomDatabase INSTANCE;

        public static AppRoomDatabase getAppDatabase(final Context context){
            Log.d(LOG_TAG, "getAppDatabaseBase called");
            if(INSTANCE == null){
                synchronized (AppRoomDatabase.class){
                    if(INSTANCE==null){
                        INSTANCE = Room.databaseBuilder(context,AppRoomDatabase.class, "todo_da").fallbackToDestructiveMigration().build();
                    }
                }
            }
            return INSTANCE;
        }


}
