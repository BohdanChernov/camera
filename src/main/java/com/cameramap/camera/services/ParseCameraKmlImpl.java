package com.cameramap.camera.services;

import com.cameramap.camera.models.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ParseCameraKmlImpl implements ParseCameraKml {
    private final String FILE_PATH_TO_PARSE = "src\\main\\resources\\files\\locations_of_cameras.kml";
    private File inputFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document document;
    private NodeList placeMarks;
    private NodeElementParser nodeElementParser;
    private CameraSaverToDataBase cameraSaverToDataBase;

    @Autowired
    public ParseCameraKmlImpl(NodeElementParser nodeElementParser, CameraSaverToDataBase cameraSaverToDataBase) {
        this.nodeElementParser = nodeElementParser;
        this.cameraSaverToDataBase = cameraSaverToDataBase;
    }

    public void parseKmlAndSaveToDataBase() {
        initializeInitialElements();
        initializeDocumentBuilder();
        parseInputFileToDocument();
        getPlaceMarksFromDocument();
        getCamerasAndSaveToDataBase();
    }

    public void initializeInitialElements() {
        inputFile = new File(FILE_PATH_TO_PARSE);
        dbFactory = DocumentBuilderFactory.newInstance();
    }

    public void initializeDocumentBuilder() {
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void parseInputFileToDocument() {
        try {
            document = dBuilder.parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPlaceMarksFromDocument() {
        placeMarks = document.getElementsByTagName("Placemark");
    }

    public void getCamerasAndSaveToDataBase() {
        List<Camera> cameras = nodeElementParser.getCamerasInList(placeMarks);
        cameraSaverToDataBase.saveCamerasToDataBase(cameras);
    }


}
