package com.vini.grow.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vini.grow.model.Friendship;
import com.vini.grow.model.Task;
import com.vini.grow.model.User;

@Database(entities = {User.class, Task.class, Friendship.class}, version = 1)
public abstract class GrowDatabase extends RoomDatabase {

    public static final String DB_NAME = "GrowDatabase.db";
    private static GrowDatabase singletonInstance;

    public abstract UserDao getUserDao();
    public abstract TaskDao getTaskDao();
    public abstract FriendshipDao getFriendshipDao();

    // GrowDatabase Singleton Lazy-Initialized
    public static synchronized GrowDatabase getInstance(Context context){
        if (singletonInstance == null)
            singletonInstance = Room.databaseBuilder(context.getApplicationContext(),
                    GrowDatabase.class, DB_NAME).fallbackToDestructiveMigration().build(); //deletes all with migration
//        .addCallback(rooCallback)
        return singletonInstance;
    }



//    // SampleData to test
//    //TODO: DELETE
//    private static RoomDatabase.Callback rooCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            new PopulateDBAsyncTask(singletonInstance).execute();
//            super.onCreate(db);
//        }
//    };
//
//    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
//        private UserDao userDao;
//        private TaskDao taskDao;
//        private FriendshipDao friendshipDao;
//
//        private PopulateDBAsyncTask(GrowDatabase growDatabase){
//            userDao = growDatabase.getUserDao();
//            taskDao = growDatabase.getTaskDao();
//            friendshipDao = growDatabase.getFriendshipDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            userDao.insert(new User("Anderson", "Valério", "Anderson",
//                                    "andersonvcv@gmail.com", 0));
//            userDao.insert(new User("Felipe", "Lima", "flp",
//                    "flp@gmail.com", 0));
//            taskDao.insert(new Task("Programando","Programando Aplicativo",
//                                    "coding", 0,0,1,true,
//                                    10));
//            friendshipDao.insert(new Friendship(0,1));
//            return null;
//        }
//    }
}
