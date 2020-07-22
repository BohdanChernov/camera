package com.cameramap.camera.services;

import com.cameramap.camera.dao.CameraDAO;
import com.cameramap.camera.models.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraSaverToDataBaseImpl implements CameraSaverToDataBase{
    private CameraDAO cameraDAO;

    @Autowired
    public CameraSaverToDataBaseImpl(CameraDAO cameraDAO) {
        this.cameraDAO = cameraDAO;
    }

    public void saveCamerasToDataBase(List<Camera> cameras) {
        List<Camera> existingCameras = cameraDAO.findAll();
        for (Camera camera : cameras) {
            if (!isInDataBaseSameCoordinatePoints(existingCameras, camera)) {
                cameraDAO.save(camera);
            }
        }
    }

    public boolean isInDataBaseSameCoordinatePoints(List<Camera> existingCameras, Camera cameraToCheck) {
        boolean isInDataBase = false;
        for (Camera existingCamera : existingCameras) {
            if (existingCamera.getLatitude() == cameraToCheck.getLatitude()
                    && existingCamera.getLongitude() == cameraToCheck.getLongitude()) {
                isInDataBase = true;
            }
        }
        return isInDataBase;
    }
}
