package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepo;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepo = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created  = timeEntryRepo.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(created, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry te = timeEntryRepo.find(timeEntryId);

        HttpStatus responseCode;
        if(te == null){
            responseCode = HttpStatus.NOT_FOUND;
        } else {
            responseCode = HttpStatus.OK;
        }

        return new ResponseEntity<TimeEntry>(te, responseCode);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepo.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntries, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry updatedTime) {
        TimeEntry updated = timeEntryRepo.update(timeEntryId, updatedTime);

        HttpStatus responseCode;
        if(updated == null){
            responseCode = HttpStatus.NOT_FOUND;
        } else {
            responseCode = HttpStatus.OK;
        }

        return new ResponseEntity<TimeEntry>(updated, responseCode);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepo.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
