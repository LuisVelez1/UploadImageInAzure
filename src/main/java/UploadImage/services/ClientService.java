package UploadImage.services;

import UploadImage.entities.Client;
import UploadImage.repository.ClientRepository;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BlobServiceClient blobServiceClient;

    @PersistenceContext
    private EntityManager entityManager;

    private final String containerName = "images";

    public String uploadImageAndGetUrl(MultipartFile file) throws IOException {
        String blobName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        var blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);

        blobClient.upload(inputStream, file.getSize(), true);
        blobClient.setHttpHeaders(new BlobHttpHeaders().setContentType(file.getContentType()));

        return blobClient.getBlobUrl();
    }

    public Client registerClient(String email, String imageUrl) {
        Client client = new Client();
        client.setEmail(email);
        client.setImageURL(imageUrl);
        return clientRepository.save(client);
    }
}
