package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStacktrace;

/**
 * Created by Junior on 24-10-16.
 */
public class MatchAnalyzerCount extends Analyzer {

    public MatchAnalyzerCount(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");

        int deepOfMatch = 2;

        int functionMatchLimitOk = 1;
        int fileMatchLimitOk = 1;
        int libraryMatchLimitOk = 1;



        for (Bucket bucket : this.buckets) {

            int functionMatchCount = 0;
            int fileMatchCount = 0;
            int libraryMatchCount = 0;

            for (SubStacktrace subStackTrace : stackTrace) {


                //System.out.println(bucket.getFunctionNameProperty());
                if (bucket.getFunctionNameProperty() != null && bucket.getFunctionNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty().get(i))) {
                            functionMatchCount++;
                        }
                    }
                }


                //System.out.println(bucket.getFileNameProperty());
                if (bucket.getFileNameProperty() != null && bucket.getFileNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty().get(i)))
                        {
                            fileMatchCount++;
                        }
                    }
                }


                //System.out.println(bucket.getLibraryNameProperty());
                if (bucket.getLibraryNameProperty() != null && bucket.getLibraryNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getLibraryName() != null && subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty().get(i)))
                        {
                            libraryMatchCount++;
                        }
                    }
                }


                if (functionMatchCount >= functionMatchLimitOk && (fileMatchCount >= fileMatchLimitOk || libraryMatchCount >= libraryMatchLimitOk))
                    return bucketToReturn = bucket;
            }
        }

        return bucketToReturn;
    }
}
