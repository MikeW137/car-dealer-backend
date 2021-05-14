package cardealerbackend.backend.service;

import cardealerbackend.backend.exception.InformationExistException;
import cardealerbackend.backend.exception.InformationNotFoundException;
import cardealerbackend.backend.model.Cars;
import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.repository.CarsRepository;
import cardealerbackend.backend.repository.ImageRepository;
import cardealerbackend.backend.repository.InventoryRepository;
import cardealerbackend.backend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private ImageRepository imageRepository;
    private CarsRepository carsRepository;

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    @Autowired
    public void setCarsRepository(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    //Inventory Logic
    public Inventory getIndividualInventory(Long inventoryId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findById(inventoryId).get();
        if (inventory == null) {
            throw new InformationNotFoundException("Inventory with id " + inventoryId + " not found");
        } else {
            return inventory;
        }
    }

    public Inventory createInventory(Inventory inventoryObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findByVin(inventoryObject.getVin());
        if (inventory != null) {
            throw new InformationExistException("Inventory with vin " + inventory.getVin() + " already exists");
        } else {
            return inventoryRepository.save(inventoryObject);
        }
    }

    public Inventory updateInventory(Long inventoryId, Inventory inventoryObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) {
            if (inventoryObject.getVin().equals(inventory.get().getVin())) {
                System.out.println("Vin is the same");
                throw new InformationExistException("Vin " + inventory.get().getVin() + " already exists");
            } else {
                Inventory updateInventory = inventoryRepository.findById(inventoryId).get();
                updateInventory.setVin(inventoryObject.getVin());
                updateInventory.setColor(inventoryObject.getColor());
                updateInventory.setFuelEfficiency(inventoryObject.getFuelEfficiency());
                updateInventory.setPrice(inventoryObject.getPrice());
                return inventoryRepository.save(updateInventory);
            }
        } else {
            throw new InformationNotFoundException("Car with" + inventoryId + " not found");
        }
    }
    public Optional<Inventory> deleteInventory(Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) {
            inventoryRepository.deleteById(inventoryId);
            return inventory;
        } else {
            throw new InformationNotFoundException("Car with id " + inventoryId + " not found");
        }
    }

    //Car Logic
    public Cars getIndividualCar(Long carId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cars car = carsRepository.findById(carId).get();
        if (car == null) {
            throw new InformationNotFoundException("Car with id " + carId + " not found");
        } else {
            return car;
        }
    }

    public Cars createCars(Cars carsObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cars car = carsRepository.findByMakeAndModelAndTrim(carsObject.getMake(), carsObject.getModel(), carsObject.getTrim());
        if (car != null) {
            throw new InformationExistException("Car with make/model/trim " + car.getId() + " already exists");
        } else {
            return carsRepository.save(carsObject);
        }
    }

}
