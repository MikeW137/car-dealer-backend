package cardealerbackend.backend.controller;

import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/new")
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }



}
