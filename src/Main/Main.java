package Main;

import Model.Bucket;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by Junior on 19-10-16.
 */
public class Main {

    public static float COUNT_TOTAL_SUBSTACKTRACE = 0;
    public static float COUNT_TOTAL_SUBSTACKTRACE_OK = 0;
    public static float COUNT_TOTAL_STACKTRACE = 0;
    public static float COUNT_TOTAL_STACKTRACE_OK = 0;
    public static String PATH_BUCKETS_TRAINING = "./nautilus/nautilus-training";
    public static String PATH_BUCKETS_TESTING = "./nautilus/nautilus-testing";

    public static void main (String [] arg){

        ArrayList<Bucket> buckets = new ArrayList<>();

        // Pour chaque dossier de Stacktrace
        for(File directory : new File(PATH_BUCKETS_TRAINING).listFiles()) {
            Bucket bucket = new Bucket();
            System.out.println("BUCKET : " + directory.getName());
            bucket.fill(directory.listFiles(), Integer.parseInt(directory.getName()));
            buckets.add(bucket);
        }

        System.out.println("SubStacktrace OK : " + Tools.round((COUNT_TOTAL_SUBSTACKTRACE_OK/COUNT_TOTAL_SUBSTACKTRACE) * 100, 2) + "%");
        System.out.println("Stacktrace OK : " + Tools.round((COUNT_TOTAL_STACKTRACE_OK/COUNT_TOTAL_STACKTRACE) * 100, 2) + "%");
        System.out.println("Stacktrace OK : " + Tools.round(COUNT_TOTAL_STACKTRACE_OK, 0));
        System.out.println("Stacktrace : " + Tools.round(COUNT_TOTAL_STACKTRACE, 0));
        System.out.println("Stacktrace NOT OK : " + Tools.round(COUNT_TOTAL_STACKTRACE-COUNT_TOTAL_STACKTRACE_OK, 0));

        //TODO Boucle création thread
    }
}
