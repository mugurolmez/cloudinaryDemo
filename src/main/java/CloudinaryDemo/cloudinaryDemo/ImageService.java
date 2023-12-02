package CloudinaryDemo.cloudinaryDemo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ImageService {

     List<Image> list();

     Optional<Image> getOne(int id);

     void save(Image image);

     void delete(int id);

     boolean exists(int id);
}
