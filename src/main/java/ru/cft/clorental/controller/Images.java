package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.clorental.service.ImageService;

import java.io.File;

@RestController
@RequestMapping("images")
@Api(value = "images")
public class Images {
   final ImageService imageService;

   @Autowired
   public Images(ImageService imageService){
      this.imageService = imageService;
   }

   @GetMapping(value = "{id}.jpeg",
           produces = MediaType.IMAGE_JPEG_VALUE)
   @ApiOperation("image loader")
   public @ResponseBody byte[] imageLoading(@PathVariable Long id){
      return imageService.getById(id);
   }
}
