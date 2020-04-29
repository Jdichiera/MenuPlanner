package com.example.menuplanner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.menuplanner.entity.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT userName FROM users_table WHERE userName = :userName AND userPassword = :userPassword")
    String getUser(String userName, String userPassword);
}
