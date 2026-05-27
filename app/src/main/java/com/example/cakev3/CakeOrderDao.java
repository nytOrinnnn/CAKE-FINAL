package com.example.cakev3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CakeOrderDao {
    @Insert
    void insertOrder(CakeOrder order);

    @Query("SELECT * FROM cake_orders ORDER BY timestamp DESC")
    List<CakeOrder> getAllOrders();

    @Query("DELETE FROM cake_orders")
    void deleteAllOrders();
}
