package com.vini.grow.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys =
            @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "userUID",
                onDelete = CASCADE, onUpdate = CASCADE),
            indices = {@Index(value = {"userUID"}, unique = true)},
            tableName = "tasks_table")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String name;
    private String description;
    private String tags;
    private long userUID;
    private long startDatetime;
    private long stopDatetime;
    private boolean status;
    private long reward;

    public Task(String name, String description, String tags, long userUID, long startDatetime, long stopDatetime, boolean status, long reward) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.userUID = userUID;
        this.startDatetime = startDatetime;
        this.stopDatetime = stopDatetime;
        this.status = status;
        this.reward = reward;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getTags() {
        return tags;
    }
    public long getUserUID() {
        return userUID;
    }
    public long getStartDatetime() {
        return startDatetime;
    }
    public long getStopDatetime() {
        return stopDatetime;
    }
    public boolean isStatus() {
        return status;
    }
    public long getReward() {
        return reward;
    }
}
