package cardealerbackend.backend.service;

import cardealerbackend.backend.exception.InformationExistException;
import cardealerbackend.backend.exception.InformationNotFoundException;
import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.repository.CarsRepository;
import cardealerbackend.backend.repository.ImageRepository;
import cardealerbackend.backend.repository.InventoryRepository;
import cardealerbackend.backend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public Inventory getIndividualCar(Long carId) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findById(carId).get();
        if (inventory == null) {
            throw new InformationNotFoundException("Car with id " + carId + " not found");
        } else {
            return inventory;
        }
    }

    public Inventory createInventory(Inventory inventoryObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findByVin(inventoryObject.getVin());
        if (inventory != null) {
            throw new InformationExistException("Car with vin " + inventory.getVin() + " already exists");
        } else {
            return inventoryRepository.save(inventoryObject);
        }
    }
    

}
