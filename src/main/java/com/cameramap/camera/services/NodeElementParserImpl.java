package com.cameramap.camera.services;

import com.cameramap.camera.models.Camera;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeElementParserImpl implements NodeElementParser {

    public List<Camera> getCamerasInList(NodeList placeMarks) {
        List cameras = new ArrayList<>();
        for (int i = 0; i < placeMarks.getLength(); i++) {
            Node placeMark = placeMarks.item(i);
            String name = getNameFromElement((Element) placeMark);
            String coordinates = getCoordinatesFromElement((Element) placeMark);
            double longitude = getLongitude(coordinates);
            double latitude = getLatitude(coordinates);
            cameras.add(new Camera(name, latitude, longitude));
        }
        return cameras;
    }

    public String getNameFromElement(Element element) {
        return element
                .getElementsByTagName("name")
                .item(0)
                .getTextContent()
                .trim();
    }

    public String getCoordinatesFromElement(Element element) {
        return element
                .getElementsByTagName("coordinates")
                .item(0)
                .getTextContent()
                .trim();
    }

    public double getLongitude(String coordinates) {
        String[] elements = coordinates.split(",");
        return Double.parseDouble(elements[0]);
    }

    public double getLatitude(String coordinates) {
        String[] elements = coordinates.split(",");
        return Double.parseDouble(elements[1]);
    }
}
