package cardealerbackend.backend.controller;

import cardealerbackend.backend.model.Cars;
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

    @GetMapping(path = "/inventory/{inventoryId}")
    public Inventory getIndividualInventory(@PathVariable Long inventoryId) {
        return inventoryService.getIndividualInventory(inventoryId);
    }

    @PostMapping(path = "/inventory")
    public ResponseEntity<HashMap> createInventory(@RequestBody Inventory inventoryObject) {
        inventoryService.createInventory(inventoryObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Inventory with id: " + inventoryObject.getId() + " was successfully added.");
        responseMessage.put("result", inventoryObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);

    }

    @PutMapping("/inventory/{inventoryId}")
    public ResponseEntity<HashMap> updateInventory(@PathVariable(value = "inventoryId") Long inventoryId, @RequestBody Inventory inventoryObject) {
        inventoryService.updateInventory(inventoryId, inventoryObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Inventory with id: " + inventoryId + " was successfully updated.");
        responseMessage.put("result", inventoryObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{inventoryId}")
    public ResponseEntity<HashMap> deleteInventory(@PathVariable(value = "inventoryId") Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Inventory with id: " + inventoryId + " was successfully deleted.");
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);
    }

    @GetMapping(path = "/cars/{carId}")
    public Cars getIndividualCar(@PathVariable Long carId) {
        return inventoryService.getIndividualCar(carId);
    }

    @PostMapping(path = "/cars")
    public ResponseEntity<HashMap> createCars(@RequestBody Cars carsObject) {
        inventoryService.createCars(carsObject);
        HashMap responseMessage = new HashMap();
        responseMessage.put("status", "Car with id: " + carsObject.getId() + " was successfully added.");
        responseMessage.put("result", carsObject);
        return new ResponseEntity<HashMap>(responseMessage, HttpStatus.OK);

    }

}
