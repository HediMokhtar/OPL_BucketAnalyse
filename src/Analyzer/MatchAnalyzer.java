package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStackTrace;

import java.util.HashMap;

/**
 * Created by Junior on 24-10-16.
 */
public class MatchAnalyzer extends Analyzer {

    private boolean isMatched = false;
    private int deepOfMatch = 7;

    public MatchAnalyzer(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");

        int functionMatchLimitOk = 5;
        int fileMatchLimitOk = 5;
        int libraryMatchLimitOk = 5;

        int functionMatchCount = 0;
        int fileMatchCount = 0;
        int libraryMatchCount = 0;

        for (Bucket bucket : this.buckets) {

            //Main.functionMap.put(bucket.getFunctionNameProperty(), keyValue(Main.functionMap, bucket.getFunctionNameProperty()) +1);
            //Main.fileMap.put(bucket.getFileNameProperty(), keyValue(Main.fileMap, bucket.getFileNameProperty()) +1);
            //Main.libraryMap.put(bucket.getLibraryNameProperty(), keyValue(Main.libraryMap, bucket.getLibraryNameProperty()) +1);

            for (SubStackTrace subStackTrace : stackTrace) {


                //System.out.println(bucket.getFunctionNameProperty());
                if (bucket.getFunctionNameProperty() != null && bucket.getFunctionNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty().get(i)))
                            functionMatchCount++;
                    }
                }


                //System.out.println(bucket.getFileNameProperty());
                if (bucket.getFileNameProperty() != null && bucket.getFileNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty().get(i)))
                            fileMatchCount++;
                    }
                }


                //System.out.println(bucket.getLibraryNameProperty());
                if (bucket.getLibraryNameProperty() != null && bucket.getLibraryNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getLibraryName() != null && subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty().get(i)))
                            libraryMatchCount++;
                    }
                }

                if (functionMatchCount >= functionMatchLimitOk && fileMatchCount >= fileMatchLimitOk || libraryMatchCount >= libraryMatchLimitOk) {
                    return bucketToReturn = bucket;
                }
            }
        }

        return bucketToReturn;
    }


    private int keyValue(HashMap<String, Integer> rankMap, String name) {
        if (rankMap.get(name) == null) {
            return 0;
        } else {
            return rankMap.get(name);
        }
    }
}
