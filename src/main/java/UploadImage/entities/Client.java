package UploadImage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "clients") 
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "imageURL")
    private String imageURL;
}
