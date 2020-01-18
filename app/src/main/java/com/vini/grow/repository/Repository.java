package com.vini.grow.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.vini.grow.model.Friendship;
import com.vini.grow.model.Task;
import com.vini.grow.model.User;
import com.vini.grow.util.ResourcesProvider;

import java.util.List;

public class Repository {
    private UserDao userDao;
    private TaskDao taskDao;
    private FriendshipDao friendshipDao;

    private LiveData<List<User>> allUsers;
    private LiveData<List<Task>> allUserTasks;
    private LiveData<List<Friendship>> allUserFriends;

    public Repository(){
        GrowDatabase growDatabase = GrowDatabase.getInstance(ResourcesProvider.getInstance().gettApplicationContext());

        userDao = growDatabase.getUserDao();
        taskDao = growDatabase.getTaskDao();
        friendshipDao = growDatabase.getFriendshipDao();

        allUsers = userDao.getAllUsers();
    }

    // DataBase wrappers
    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }
    public void insert(Task task){
        new InsertTaskAsyncTask(taskDao).execute(task);
    }
    public void insert(Friendship friendship){
        new InsertFriendshipAsyncTask(friendshipDao).execute(friendship);
    }

    public void update(User user){
        new UpdateUserAsyncTask(userDao).execute(user);
    }
    public void update(Task task){
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }
    public void update(Friendship friendship){
        new UpdateFriendshipAsyncTask(friendshipDao).execute(friendship);
    }

    public void delete(User user){
        new DeleteUserAsyncTask(userDao).execute(user);
    }
    public void delete(Task task){
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }
    public void delete(Friendship friendship){
        new DeleteFriendshipAsyncTask(friendshipDao).execute(friendship);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
    public LiveData<List<Task>> getAllUserTasks(){
        return allUserTasks;
    }
    public LiveData<List<Friendship>> getAllFriends(long userUID){
        return allUserFriends;
    }


    // Async Tasks
    //TODO: implement generics
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;
        public InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }
    private static class InsertFriendshipAsyncTask extends AsyncTask<Friendship, Void, Void>{
        private FriendshipDao friendshipDao;
        public InsertFriendshipAsyncTask(FriendshipDao friendshipDao) {
            this.friendshipDao = friendshipDao;
        }

        @Override
        protected Void doInBackground(Friendship... friendships) {
            friendshipDao.insert(friendships[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }
    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;
        public UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }
    private static class UpdateFriendshipAsyncTask extends AsyncTask<Friendship, Void, Void>{
        private FriendshipDao friendshipDao;
        public UpdateFriendshipAsyncTask(FriendshipDao friendshipDao) {
            this.friendshipDao = friendshipDao;
        }

        @Override
        protected Void doInBackground(Friendship... friendships) {
            friendshipDao.update(friendships[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }
    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao taskDao;
        public DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
    private static class DeleteFriendshipAsyncTask extends AsyncTask<Friendship, Void, Void>{
        private FriendshipDao friendshipDao;
        public DeleteFriendshipAsyncTask(FriendshipDao friendshipDao) {
            this.friendshipDao = friendshipDao;
        }

        @Override
        protected Void doInBackground(Friendship... friendships) {
            friendshipDao.delete(friendships[0]);
            return null;
        }
    }
}
