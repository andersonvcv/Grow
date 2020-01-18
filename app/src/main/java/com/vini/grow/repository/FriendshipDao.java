package com.vini.grow.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vini.grow.model.Friendship;

import java.util.List;

@Dao
public interface FriendshipDao {

    @Insert
    void insert(Friendship friend);
    @Update
    void update(Friendship friend);
    @Delete
    void delete(Friendship friend);

    @Query("SELECT * FROM friendships_table WHERE userUID=:userUID")
    LiveData<List<Friendship>> getAllFriends(long userUID);
}
