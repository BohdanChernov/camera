package com.cameramap.camera.services;

import com.cameramap.camera.models.Camera;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

import java.util.List;

@Service
public interface NodeElementParser {
    List<Camera> getCamerasInList(NodeList placeMarks);
}
