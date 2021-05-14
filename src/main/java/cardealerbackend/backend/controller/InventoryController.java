package cardealerbackend.backend.controller;

import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping(path = "/inventory")
    public ResponseEntity<HashMap> createInventory(@RequestBody Inventory inventoryObject) {
        inventoryService.createInventory(inventoryObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "car with id: " + inventoryObject.getId() + " was successfully added.");
        responseMessage.put("result", inventoryObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);

    }

    @PutMapping("/inventory/{inventoryId}")
    public ResponseEntity<HashMap> updateInventory(@PathVariable(value = "inventoryId") Long inventoryId, @RequestBody Inventory inventoryObject) {
        inventoryService.updateInventory(inventoryId, inventoryObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Car with id: " + inventoryId + " was successfully updated.");
        responseMessage.put("result", inventoryObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

}
