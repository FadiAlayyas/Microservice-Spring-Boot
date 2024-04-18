package com.programming.inventoryservice.seed;

import com.programming.inventoryservice.model.Inventory;
import com.programming.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;


public class DatabaseSeeder implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    public DatabaseSeeder(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedInventory();
    }

    private void seedInventory() {
        if (inventoryRepository.count() == 0) {
            Inventory inventory1 = new Inventory();
            inventory1.setQuantity(5);
            inventory1.setSkuCode("iphone_13");

            Inventory inventory2 = new Inventory();
            inventory2.setQuantity(0);
            inventory2.setSkuCode("iphone_14");

            inventoryRepository.save(inventory1);
            inventoryRepository.save(inventory2);
        }
    }
}
