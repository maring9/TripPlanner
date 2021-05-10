package com.project.tripplanner.backend.controller;

//import com.google.api.Http;
import com.project.tripplanner.backend.model.PlaceToVisit;
import com.project.tripplanner.backend.repository.PlaceToVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PlaceToVisitController {

    @Autowired
    private PlaceToVisitRepository placeToVisitRepository;

    @GetMapping("/city={city_name}/places_to_visit")
    public ResponseEntity<List<PlaceToVisit>> getAllPlacesToVisit(@PathVariable String city_name){
        try{
            List<PlaceToVisit> places = new ArrayList<>();

            places.addAll(placeToVisitRepository.findByCityName(city_name));

            if(places.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(places,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/user_id={user_id}/locationId={location_id}/updateLocationToVisit")
//    public void updateLocation(@PathVariable long location_id,long user_id){
//        Optional<PlaceToVisit> placeToVisit = placeToVisitRepository.findById(location_id);
//       // User user = userRepository.findById(user_id);
//        userRepository.updateBookmarksLocationToVisit(user_id,location_id);
//        if(placeToVisit.isBookmarked()){
//            placeToVisit.setBookmark(true);
//        }
//    }

    //for testing
    @PostMapping("/places_to_visit")
    public ResponseEntity<PlaceToVisit> createPlace(@RequestBody PlaceToVisit place){
        try{
            PlaceToVisit placeToVisit = placeToVisitRepository.save(new PlaceToVisit(place.getTitle(),place.getDescription()));
            return new ResponseEntity<>(placeToVisit,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
