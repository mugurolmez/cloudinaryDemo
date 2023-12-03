package CloudinaryDemo.cloudinaryDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudinaryDemoApplication {

	public static <SingletonManager> void main(String[] args) {
		SpringApplication.run(CloudinaryDemoApplication.class, args);
	}

}
