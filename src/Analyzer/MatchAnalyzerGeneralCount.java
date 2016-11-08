package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStacktrace;

/**
 * Created by Junior on 25-10-16.
 */
public class MatchAnalyzerGeneralCount extends Analyzer {

    public MatchAnalyzerGeneralCount(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");

        int deepOfMatch = 1;
        int generalMatchLimit = 2;


        for (Bucket bucket : this.buckets) {


            int generalCount = 0;

            for (SubStacktrace subStackTrace : stackTrace) {

                //System.out.println(bucket.getFunctionNameProperty());
                if (bucket.getFunctionNameProperty() != null && bucket.getFunctionNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty().get(i))) {
                            generalCount++;
                        }
                    }
                }


                //System.out.println(bucket.getFileNameProperty());
                if (bucket.getFileNameProperty() != null && bucket.getFileNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty().get(i)))
                        {
                            generalCount++;
                        }
                    }
                }


                //System.out.println(bucket.getLibraryNameProperty());
                if (bucket.getLibraryNameProperty() != null && bucket.getLibraryNameProperty().size() >= deepOfMatch) {
                    for (int i = 0; i < deepOfMatch; i++) {
                        if (subStackTrace.getLibraryName() != null && subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty().get(i)))
                        {
                            generalCount++;
                        }
                    }
                }

                if (generalCount >= generalMatchLimit)
                    return bucketToReturn = bucket;
            }
        }

        return bucketToReturn;
    }
}
