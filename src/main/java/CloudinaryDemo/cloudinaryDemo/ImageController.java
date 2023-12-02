package CloudinaryDemo.cloudinaryDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = this.imageService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //postederken yaparken postmande http://localhost:8080/image/upload
    //post-body-form-data secip key=multipartFile file seçip resmin kısa yolu seçiliyor.

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("resim geçerli degil!", HttpStatus.BAD_REQUEST);
        }
        Map<?,?> result = cloudinaryService.upload(multipartFile);
        Image image = new Image((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        this.imageService.save(image);
        return new ResponseEntity<>("resim yükleme başarılı ", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        Optional<Image> imageOptional = imageService.getOne(id);
        if (imageOptional.isEmpty()) {
            return new ResponseEntity<>("resim bulunamadı", HttpStatus.NOT_FOUND);
        }
        Image image = imageOptional.get();
        String cloudinaryImageId = image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("resim Cloudinaryden silindi", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageService.delete(id);
        return new ResponseEntity<>("resim silindi", HttpStatus.OK);
    }
}
