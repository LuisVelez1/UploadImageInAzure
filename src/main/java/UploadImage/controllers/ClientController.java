package UploadImage.controllers;

import UploadImage.entities.Client;
import UploadImage.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/registerClient")
    public ResponseEntity<Client> registerClient(@RequestParam String email, @RequestParam("file") MultipartFile file){
        try{
            String imageURL = clientService.uploadImageAndGetUrl(file);
            Client savedClient = clientService.registerClient(email, imageURL);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
