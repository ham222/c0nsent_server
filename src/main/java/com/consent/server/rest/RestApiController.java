package com.consent.server.rest;

import com.consent.server.model.Action;
import com.consent.server.services.interfaces.ActionService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class RestApiController{

    @Autowired
    ActionService actionService;

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHello()
    {
        return new ResponseEntity<>("Hello world!", HttpStatus.OK);
    }

    @PutMapping(path="/event", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createEvent(
            @RequestPart(value = "jsonData") String json,
            @RequestPart(value = "image")MultipartFile image
    ){
        Action action;
        //Read response from JSON
        try{
            action = new Gson().fromJson(json, Action.class);

        }catch (JsonSyntaxException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Put image into DTO and process it further

        try {
            action.setPhoto(image.getBytes());
            actionService.processAction(action);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value= "/event", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> getEvent(){
        final ByteArrayResource inputStream;
        try {
            inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                    "/home/images/test1.jpeg"
            )));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentLength(0)
                    .body(null);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(inputStream.contentLength())
                .body(inputStream);

    }

    /*
    @PostMapping(path = "/logSession", produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    */


}
