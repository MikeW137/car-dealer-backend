package cardealerbackend.backend;

import cardealerbackend.backend.model.Cars;
import cardealerbackend.backend.model.Inventory;
import cardealerbackend.backend.repository.CarsRepository;
import cardealerbackend.backend.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {

    private InventoryRepository inventoryRepository;
    private CarsRepository carsRepository;
    @Test
    void contextLoads() {
    }

    @Autowired
    public void setInventoryRepository(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Autowired
    public void setCarsRepository(CarsRepository carsRepository){
        this.carsRepository = carsRepository;
    }

    @Test
    public void testGetInventory(){
        Inventory inventory = new Inventory(1L, 111L, "yellow", "32HW", 2200L);
        Inventory found = inventoryRepository.findByVin(111L);
        assertEquals(inventory.toString(), found.toString());
    }

    @Test
    public void testGetCar(){
        Cars car = new Cars(1L, "Ford", "Escape", 2020, "GT", "3L", true);
        Cars found = carsRepository.findById(1L).get();
        
    }
}
