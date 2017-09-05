package com.aidar.task;

import com.aidar.dto.VisitDto;
import com.aidar.model.Location;
import com.aidar.model.User;
import com.aidar.model.Visit;
import com.aidar.repository.LocationRepository;
import com.aidar.repository.UserRepository;
import com.aidar.repository.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
public class DownloadDataTask {

    private final static class UsersDto {
        private List<User> users;

        private List<User> getUsers() {
            return users;
        }

        private void setUsers(List<User> users) {
            this.users = users;
        }
    }

    private final static class LocationsDto {
        private List<Location> locations;

        private List<Location> getLocations() {
            return locations;
        }

        private void setLocations(List<Location> locations) {
            this.locations = locations;
        }
    }

    private final static class VisitsDto {
        private List<VisitDto> visits;

        private List<VisitDto> getVisits() {
            return visits;
        }

        private void setVisits(List<VisitDto> visits) {
            this.visits = visits;
        }
    }

    private final ObjectMapper objectMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final VisitRepository visitRepository;

    @Autowired
    public DownloadDataTask(EntityManager entityManager, UserRepository userRepository,
                            LocationRepository locationRepository, VisitRepository visitRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.visitRepository = visitRepository;
        objectMapper = new ObjectMapper();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void downloadData() throws IOException {
        ZipFile zipFile = new ZipFile("/tmp/data/data.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry e = entries.nextElement();
            InputStream in = zipFile.getInputStream(e);
            String fileName = e.getName();
            if (fileName.startsWith("users")) {
                List<User> users = objectMapper.readValue(in, UsersDto.class).getUsers();
                userRepository.save(users);
            } else if (fileName.startsWith("locations")) {
                List<Location> locations = objectMapper.readValue(in, LocationsDto.class).getLocations();
                locationRepository.save(locations);
            } else if (fileName.startsWith("visits")) {
                List<VisitDto> visitDto = objectMapper.readValue(in, VisitsDto.class).getVisits();
                List<Visit> visits = new LinkedList<>();
                visitDto.forEach(v -> {
                    Location location = entityManager.getReference(Location.class, v.getLocation());
                    User user = entityManager.getReference(User.class, v.getUser());
                    visits.add(new Visit(v, location, user));
                });
                visitRepository.save(visits);
            }
        }
    }

}
