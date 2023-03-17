package com.rinoarias.holoapi.controllers;

import com.rinoarias.holoapi.drivers.DriverFan;
import org.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/fan/")
public class FanController {
    private DriverFan controller;
    @GetMapping("files")
    public List<String> GetFiles()
    {
        controller = new DriverFan();
//        return List.of("Files", controller.fetchVideoList());
//
//        return obtenerListadoArchivos(controller.fetchVideoList());
        return List.of("respuesta", "Obteniendo archivos del dispositivo");
    }

    @GetMapping("playVideo/{videoID}")
    public List<String> selectVideo(@PathVariable("videoID") String videoID){
        controller = new DriverFan();
//        return List.of("respuesta", controller.playVideoWithId(videoID));
        return List.of("respuesta", "Reproduciendo video con el ID: " + videoID);
    }

    @GetMapping("playLoopMode")
    public List<String> selectLoopMode(){
        controller = new DriverFan();
//        return List.of("respuesta", controller.selectLoopPlaybackMode());
        return List.of("respuesta", "Reproduciendo videos en Loop Mode");
    }

    @GetMapping("playSingleVideoModel")
    public List<String> selectSinglePlayModel(){
        controller = new DriverFan();
//        return List.of("respuesta", controller.selectSingleVideoPlaybackMode());
        return List.of("respuesta", "Reproduciendo videos en SinglePlay Mode");
    }

    @GetMapping("player/lightDown")
    public ResponseEntity<String> deviceLightDown() throws JSONException {
        controller = new DriverFan();
        // Devolver la respuesta JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construir el JSON
        JSONObject miJSON = new JSONObject();
//
        try {
//            miJSON.put("respuesta", controller.mediaPlayerLightDown());
            JSONObject respuesta = miJSON.put("respuesta", "Bajando intensidad de luz en dispositivo");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<String>(miJSON.toString(), headers, HttpStatus.OK);
    }

    @GetMapping("subirBrillo")
    public String subirBrillo(){
        controller = new DriverFan();
//        System.out.println(controller.mediaPlayerLightUp());
        return "Subiendo brillo";
    }

    @GetMapping("imagenAnterior")
    public String imagenAnterior(){
        controller = new DriverFan();
//        System.out.println(controller.mediaPlayerPlaylast());
        return "Imagen Anterior";
    }

    @GetMapping("siguienteImagen")
    public String nextImage(){
        controller = new DriverFan();
//        System.out.println(controller.mediaPlayerPlayNext());
        return "Siguiente imagen";
    }

    @GetMapping("play")
    public String playImage(){
        controller = new DriverFan();
//        System.out.println(controller.mediaPlayerPlayStart());
        return "Play";
    }

    @GetMapping("pause")
    public String pauseImage(){
        controller = new DriverFan();
        System.out.println(controller.mediaPlayerPlayPause());
        return "Pause";
    }

    @GetMapping("test")
    public String test() {
        return "Esto es una prueba";
    }

    private List<String> obtenerListadoArchivos(String stringFileList){
        List<String> filenames = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d{6}\\.bin");
        Matcher matcher = pattern.matcher(stringFileList);

        while (matcher.find()) {
            filenames.add(matcher.group());
        }
        return filenames;
    }
}
