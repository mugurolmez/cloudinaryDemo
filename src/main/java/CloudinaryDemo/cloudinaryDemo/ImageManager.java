package CloudinaryDemo.cloudinaryDemo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImageManager implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    CloudinaryAdapter cloudinaryAdapter;

    public List<Image> list() {
        return this.imageRepository.findByOrderById();
    }

    public Optional<Image> getOne(int id) {
        return this.imageRepository.findById(id);
    }

    public void save(Image image) {

        this.imageRepository.save(image);
    }

    public void delete(int id) {
        this.imageRepository.deleteById(id);
    }

    public boolean exists(int id) {
        return this.imageRepository.existsById(id);
    }
}
