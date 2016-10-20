package Model;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Junior on 19-10-16.
 */
public class Bucket extends ArrayList<Stacktrace> {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bucket(File directoryBucket, int id) {
        this.id = id;

        File [] directories = directoryBucket.listFiles();

        // Pour chaque dossier de Stacktrace
        for(File directory : directories)
            this.add(new Stacktrace(directory.listFiles()[0], Integer.parseInt(directory.getName())));

    }
}
