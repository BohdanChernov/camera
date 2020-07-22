package com.cameramap.camera.dao;

import com.cameramap.camera.models.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraDAO extends JpaRepository<Camera, Long> {

}
