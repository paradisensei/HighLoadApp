package com.aidar.controller;

import com.aidar.repository.LocationRepository;
import com.aidar.repository.UserRepository;
import com.aidar.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController
public class MainController {

    private final static String NOT_FOUND = "404";

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public MainController(UserRepository userRepository, LocationRepository locationRepository,
                          VisitRepository visitRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.visitRepository = visitRepository;
    }

    @GetMapping("/{entity}/{id}")
    public Object getUser(@PathVariable("entity") String entity,
                          @PathVariable("id") String stringId) {
        switch (entity) {
            case "users":
                return getEntity(userRepository, stringId);
            case "locations":
                return getEntity(locationRepository, stringId);
            default:
                return getEntity(visitRepository, stringId);
        }
    }

    private Object getEntity(JpaRepository<?, Long> repository, String stringId) {
        try {
            Long id = Long.valueOf(stringId);
            Object entity = repository.findOne(id);
            return entity != null ? entity : NOT_FOUND;
        } catch (NumberFormatException e) {
            return NOT_FOUND;
        }
    }

}
