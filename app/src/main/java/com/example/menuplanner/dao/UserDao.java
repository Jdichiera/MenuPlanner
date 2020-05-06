package com.example.menuplanner.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.menuplanner.application.MenuPlanner;
import com.example.menuplanner.entity.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(User user);

    @Update
    void update(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM " + MenuPlanner.USERS_TABLE)
    void deleteAllUsers();

    @Query("SELECT COUNT(userId) FROM users_table WHERE userName = :username AND userPassword = :password")
    int validateLogin(String username, String password);
}
