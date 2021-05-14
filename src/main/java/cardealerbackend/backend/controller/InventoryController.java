package cardealerbackend.backend.controller;

import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public List<Inventory> getInventory() {
        return inventoryService.getInventory();
    }

    @GetMapping(path = "/inventory/{carId}")
    public Inventory getCar(@PathVariable Long carId) {
        System.out.println("calling getCategory ==>");
        return inventoryService.getIndividualCar(carId);
    }

}
