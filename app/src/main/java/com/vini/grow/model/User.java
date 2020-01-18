package com.vini.grow.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table", indices = {@Index(value = {"uid"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private long uid;
    private String fisrtName;
    private String familyName;
    private String nickName;
    private String email;
    private long coins;

    public User(String fisrtName, String familyName, String nickName, String email, long coins) {
        this.fisrtName = fisrtName;
        this.familyName = familyName;
        this.nickName = nickName;
        this.email = email;
        this.coins = coins;
    }

    public void setUid(long uid) { this.uid = uid; }
    public long getUid() {
        return uid;
    }

    public String getFisrtName() {
        return fisrtName;
    }
    public String getFamilyName() {
        return familyName;
    }
    public String getNickName() {
        return nickName;
    }
    public String getEmail() { return email; }
    public long getCoins() { return coins; }
}
