package com.vini.grow.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "userUID",
                onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "friendUID",
                onDelete = CASCADE, onUpdate = CASCADE)},
        indices = { @Index(value = {"userUID"}, unique = true),
                    @Index(value = {"friendUID"}, unique = true)},
        tableName = "friendships_table")
public class Friendship {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private long userUID;
    private long friendUID;

    public Friendship(long userUID, long friendUID) {
        this.userUID = userUID;
        this.friendUID = friendUID;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
    public long getUid() {
        return uid;
    }

    public long getUserUID() {
        return userUID;
    }
    public long getFriendUID() {
        return friendUID;
    }
}
