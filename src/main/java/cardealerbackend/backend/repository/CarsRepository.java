package cardealerbackend.backend.repository;

import cardealerbackend.backend.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
   // List<Cars> findByCarId(Long carId);
    Cars findByMakeAndModelAndTrimAndYear(String make, String model, String trim, Integer year);
}
