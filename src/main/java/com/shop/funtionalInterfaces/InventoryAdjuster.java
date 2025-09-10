package com.shop.funtionalInterfaces;

import com.shop.model.Inventory;
import com.shop.model.Product;

@FunctionalInterface
public interface InventoryAdjuster {
    int adjust(Inventory inventory, Product product, int delta);
}
