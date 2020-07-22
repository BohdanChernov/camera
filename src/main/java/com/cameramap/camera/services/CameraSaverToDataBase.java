package com.cameramap.camera.services;

import com.cameramap.camera.models.Camera;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CameraSaverToDataBase {
    void saveCamerasToDataBase(List<Camera> cameras);
}
