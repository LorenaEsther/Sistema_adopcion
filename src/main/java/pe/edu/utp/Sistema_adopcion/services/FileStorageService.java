package pe.edu.utp.Sistema_adopcion.services;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    public String storeFile(MultipartFile file, String uploadDir) throws IOException {
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + "/" + uniqueFilename;
        file.transferTo(new File(filePath));
        return uniqueFilename;  // Devuelve el nombre del archivo almacenado
    }

}
