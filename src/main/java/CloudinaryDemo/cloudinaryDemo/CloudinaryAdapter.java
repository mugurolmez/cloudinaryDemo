package CloudinaryDemo.cloudinaryDemo;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Service
@Transactional
public class CloudinaryAdapter implements CloudinaryService {

    Cloudinary cloudinary;

    public CloudinaryAdapter(){
        Map<String ,String > valuesMap=new HashMap<>();
        valuesMap.put("cloud_name","ddoosppta");
        valuesMap.put("api_key","993621911244416");
        valuesMap.put("api_secret","5YH6QvWKUkkMm4uj309hF6yRD8U");
        cloudinary=new Cloudinary(valuesMap);

    }

    public Map<?,?> upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<?,?> result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }

        return result;
    }

    public Map<?,?> delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    public File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
