package com.cameramap.camera.controllers;

import com.cameramap.camera.dao.CameraDAO;
import com.cameramap.camera.models.Camera;
import com.cameramap.camera.services.ParseCameraKml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GetCamerasCoordinates {
    @Autowired
    private ParseCameraKml parseCameraKml;
    @Autowired
    private CameraDAO cameraDAO;

    @GetMapping
    public String test(){
        return "Hello world";
    }

    @GetMapping("/getCamerasCoordinates")
    public List<Camera> getCamerasCoordinates() {
        return cameraDAO.findAll();
    }

    @GetMapping("/parseKml")
    public void parserKml() {
        parseCameraKml.parseKmlAndSaveToDataBase();
    }
}
