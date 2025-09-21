package com.solvd.shop.funtionalInterfaces;

import com.solvd.shop.model.Inventory;
import com.solvd.shop.model.Product;

@FunctionalInterface
public interface InventoryAdjuster {
    int adjust(Inventory inventory, Product product, int delta);
}
