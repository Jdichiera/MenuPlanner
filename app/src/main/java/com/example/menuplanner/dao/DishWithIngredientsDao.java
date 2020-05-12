//package com.example.menuplanner.dao;
//
//import androidx.room.Dao;
//import androidx.room.Query;
//import androidx.room.Transaction;
//
//import com.example.menuplanner.application.MenuPlanner;
//import com.example.menuplanner.entity.DishWithIngredientsJoin;
//
//import java.util.List;
//
//@Dao
//public interface DishWithIngredientsDao {
//    @Transaction
//    @Query("SELECT * FROM " + MenuPlanner.DISH_INGREDIENT_JOIN)
//    List<DishWithIngredientsJoin> getDishWithIngredients();
//}
