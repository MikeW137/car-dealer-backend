package cardealerbackend.backend.repository;

import cardealerbackend.backend.model.Image;
import cardealerbackend.backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findByVin(Long vinId);
    List<Inventory> findByCarsId(Long carsId);
}



//public interface BookRepository extends JpaRepository<Book, Long> {
//    Book findByTitle(String titleName);
//    List<Book> findByUserId(Long userId);
