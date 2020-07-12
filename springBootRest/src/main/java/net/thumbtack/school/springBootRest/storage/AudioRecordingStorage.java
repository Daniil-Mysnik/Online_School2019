package net.thumbtack.school.springBootRest.storage;

import org.springframework.stereotype.Component;

@Component
public class AudioRecordingStorage implements DataStorage {
    @Override
    public String save(String path) {
        System.out.println("The audio file was saved along the way: " + "new" + path);
        return "new" + path;
    }

}
