package com.example.cakev3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cake_orders")
public class CakeOrder {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String flavor;
    public String size;
    public String icing;
    public String topping;
    public int totalPrice;
    public long timestamp;

    public CakeOrder(String flavor, String size, String icing, String topping, int totalPrice, long timestamp) {
        this.flavor = flavor;
        this.size = size;
        this.icing = icing;
        this.topping = topping;
        this.totalPrice = totalPrice;
        this.timestamp = timestamp;
    }
}
