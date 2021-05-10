package com.project.tripplanner.backend.controller;

import com.project.tripplanner.backend.model.PlaceToStay;
import com.project.tripplanner.backend.model.PlaceToVisit;
import com.project.tripplanner.backend.repository.PlaceToStayRepository;
import com.project.tripplanner.backend.repository.PlaceToVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PlaceToStayController {

    @Autowired
    private PlaceToStayRepository placeToStayRepository;

//    @GetMapping("/")
//    public void home(){
//        System.out.println("welcome");
//    }

    @GetMapping(value = "/city={city_name}/places_to_stay")
    public ResponseEntity<List<PlaceToStay>> getAllPlacesToStay(@PathVariable String cityName){
        try{
            List<PlaceToStay> places = new ArrayList<>();

            places.addAll(placeToStayRepository.findByCityName(cityName));

            if(places.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(places,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/places_to_stay")
    public ResponseEntity<PlaceToStay> createPlace(@RequestBody PlaceToStay place){
        try{
            PlaceToStay placeToStay = placeToStayRepository.save(new PlaceToStay(place.getTitle(),place.getDescription()));
            return new ResponseEntity<>(placeToStay,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
