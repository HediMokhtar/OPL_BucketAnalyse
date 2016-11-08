package Analyzer;

import Main.Tools;
import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStacktrace;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Junior on 25-10-16.
 */
public class MatchAnalyzerMapCount extends Analyzer {

    public MatchAnalyzerMapCount(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");
        HashMap<Bucket, Integer> bucketMatchCount = new HashMap<>();

        int deepOfMatch = 10;

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

                bucketMatchCount.put(bucket, generalCount);
            }
        }

        return (new ArrayList<>(Tools.sortByValue(bucketMatchCount).keySet())).get(0);
    }
}
