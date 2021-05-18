package cardealerbackend.backend.service;

import cardealerbackend.backend.exception.InformationExistException;
import cardealerbackend.backend.exception.InformationNotFoundException;
import cardealerbackend.backend.model.Cars;
import cardealerbackend.backend.model.Image;
import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.repository.CarsRepository;
import cardealerbackend.backend.repository.ImageRepository;
import cardealerbackend.backend.repository.InventoryRepository;
import cardealerbackend.backend.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
       // MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findById(inventoryId).get();
        if (inventory == null) {
            throw new InformationNotFoundException("Inventory with id " + inventoryId + " not found");
        } else {
            return inventory;
        }
    }

    public Inventory createInventory(Long carId, Inventory inventoryObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Inventory inventory = inventoryRepository.findByVin(inventoryObject.getVin());
        Cars car = carsRepository.findById(carId).get();
        inventoryObject.setCar(car);
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
     //   MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cars car = carsRepository.findById(carId).get();
        if (car == null) {
            throw new InformationNotFoundException("Car with id " + carId + " not found");
        } else {
            return car;
        }
    }

    public List<Inventory> searchInventory(Long carId) {
        return inventoryRepository.findByCarsId(carId);
    }
    public List<Cars> getCars() {
        return carsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Cars createCars(Cars carsObject) {
    //    MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cars car = carsRepository.findByMakeAndModelAndTrimAndYear(carsObject.getMake(), carsObject.getModel(), carsObject.getTrim(), carsObject.getYear());
        if (car != null) {
            throw new InformationExistException("Car with make/model/trim/year " + car.getId() + " already exists");
        } else {
            return carsRepository.save(carsObject);
        }
    }

    public Cars updateCars(Long carId, Cars carsObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cars cars = carsRepository.findById(carId).get();
        if (cars != null) {
            if (cars.equals(carsObject)) {
                throw new InformationExistException("Car with " + cars.getId() + " already exists");
            } else {
                Cars updateCars = carsRepository.findById(carId).get();
                updateCars.setMake(carsObject.getMake());
                updateCars.setModel(carsObject.getModel());
                updateCars.setYear(carsObject.getYear());
                updateCars.setEngine(carsObject.getEngine());
                updateCars.setTrim(carsObject.getTrim());
                return carsRepository.save(updateCars);
            }
        } else {
            throw new InformationNotFoundException("Car with" + carId + " not found");
        }
    }

    public Optional<Cars> deleteCars(Long carId) {
        Optional<Cars> cars = carsRepository.findById(carId);
        if (cars.isPresent()) {
            carsRepository.deleteById(carId);
            return cars;
        } else {
            throw new InformationNotFoundException("Car with id " + carId + " not found");
        }
    }

    public Image createImage(Long inventoryId, Image imageObject) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Image image = imageRepository.findByImageUrl(imageObject.getImageUrl());
        Optional inventory = inventoryRepository.findById(inventoryId);
        imageObject.setInventory((Inventory) inventory.get());
        if (image != null) {
            throw new InformationExistException("Image with id " + image.getId() + " already exists");
        } else {
            return imageRepository.save(imageObject);
        }
    }

    public Optional<Image> deleteImage(Long imageId) {
        Optional<Image> image = imageRepository.findById(imageId);
        if (image.isPresent()) {
            imageRepository.deleteById(imageId);
            return image;
        } else {
            throw new InformationNotFoundException("Car with id " + imageId + " not found");
        }
    }
}
