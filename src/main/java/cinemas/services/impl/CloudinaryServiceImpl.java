package cinemas.services.impl;

import cinemas.services.CloudinaryService;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class CloudinaryServiceImpl implements CloudinaryService {
    private Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url").toString();
    }
}
