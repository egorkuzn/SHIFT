package ru.cft.clorental.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.cft.clorental.repos.ImagesRepo;

import java.io.File;

@Service
public class ImageService {
    final ImagesRepo imagesRepo;

    public ImageService(ImagesRepo imagesRepo){
        this.imagesRepo = imagesRepo;
    }

    public byte[] getById(Long id) {
        return imagesRepo.findFirstById(id).imageFile;
    }
}
