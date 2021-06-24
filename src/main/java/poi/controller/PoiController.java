package poi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import poi.core.GeoLocation;
import poi.core.PoiManager;
import poi.core.PointOfInterest;

import java.net.URI;

@RestController
@RequestMapping(path = "poi")
public class PoiController {

    // TODO: Implement your API here.  You'll need to have Spring inject the
    //      PoiManager object.  To do so, add a constructor that takes it as
    //      a parameter.  Note that the BeanConfig class is already set up to
    //      provide the object to the Spring framework as a Bean.

    private PoiManager poiManager;

    public PoiController(PoiManager poiManger){
        this.poiManager = poiManger;
    }

    @GetMapping(value = "{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public PointOfInterest getPoi(@PathVariable("id") int id){
        PointOfInterest poi = poiManager.getPoi(id);
        if(poi == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return poi;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createPoi(@RequestBody PointOfInterest poi){
        int id = poiManager.createPoi(poi.getName(), poi.getAddress(), poi.getTags(), poi.getLocation());

        PointOfInterest newPoi = poiManager.getPoi(id);
        return ResponseEntity.created(URI.create("/poi/" + id)).build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        if(poiManager.getPoi(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        poiManager.deletePoi(id);
    }

    @GetMapping(path = "near-me", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List poiNearMe(@RequestParam("lat") double latitude, @RequestParam("long") double longitude, @RequestParam("radius") double radius, @RequestParam(name = "q", required = false) String search){
        GeoLocation loc = new GeoLocation(latitude, longitude);
        return poiManager.nearMe(latitude, longitude, radius, search);
    }

}
