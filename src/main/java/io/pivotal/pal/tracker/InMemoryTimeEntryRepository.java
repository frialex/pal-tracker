package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    HashMap<Long, TimeEntry> data = new HashMap<>();
    Long lastId = 0L;

    @Override
    public TimeEntry create(TimeEntry timeToCreate) {
        Long id  = lastId + 1;
        lastId = id;
        data.put(id, timeToCreate);
        timeToCreate.setId(id);
        return timeToCreate;
    }

    @Override
    public TimeEntry find(long id) {
        TimeEntry time = data.get(id);
        return time;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(data.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry updatedTime) {
        TimeEntry current = data.remove(id);
        if(current == null) return null;

        updatedTime.setId(id);
        data.put(id, updatedTime);
        return updatedTime;
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }
}
