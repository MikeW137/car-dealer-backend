package cardealerbackend.backend.repository;

import cardealerbackend.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
   // List<Image> findByImageId(Long imageId);
}
