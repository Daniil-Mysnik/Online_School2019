package net.thumbtack.school.springdi.storage;

import org.springframework.stereotype.Component;

@Component
public class VideoRecordingStorage implements DataStorage {

    @Override
    public String save(String path) {
        System.out.println("The video file was saved along the way: " + "new" + path);
        return "new" + path;
    }

}
