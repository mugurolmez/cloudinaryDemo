package CloudinaryDemo.cloudinaryDemo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByOrderById();
}
