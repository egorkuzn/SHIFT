package ru.cft.clorental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.cft.clorental.repos.ImagesRepo;
import ru.cft.clorental.repos.model.ImageEntity;

import java.io.IOException;

@Service
public class ImageLoaderService {

    private final ImagesRepo imagesRepo;

    @Autowired
    public ImageLoaderService(ImagesRepo imagesRepo) {
        this.imagesRepo = imagesRepo;
    }

    String path = "http://irental.ddns.net/images";


    public ImageEntity generate(MultipartFile imageFile) {
        ImageEntity imageEntity = new ImageEntity();

        try {
            imageEntity.imageFile = imageFile.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        imagesRepo.save(imageEntity);
        imageEntity.imageURL = path + "/" + imageEntity.id + ".jpeg";
        return imageEntity;
    }
}
