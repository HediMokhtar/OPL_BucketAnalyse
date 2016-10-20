package Main;

import Model.Bucket;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Junior on 19-10-16.
 */
public class Main {

    public static String PATH_BUCKETS = "./nautilus/nautilus-training";

    public static void main (String [] arg){

        ArrayList<Bucket> buckets = new ArrayList<>();

        // Pour chaque dossier de Stacktrace
        for(File directory : new File(PATH_BUCKETS).listFiles())
            buckets.add(new Bucket(directory.listFiles()[0], Integer.parseInt(directory.getName())));
    }
}
