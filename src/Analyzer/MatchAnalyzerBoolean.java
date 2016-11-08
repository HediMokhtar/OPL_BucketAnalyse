package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStacktrace;

import java.util.HashMap;

/**
 * Created by Junior on 24-10-16.
 */
public class MatchAnalyzerBoolean extends Analyzer{

    private boolean isMatched = false;

    public MatchAnalyzerBoolean(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");

        boolean isFunctionMatched = false;
        boolean isLibraryMatched = false;
        boolean isFileNameMatched = false;

        for(Bucket bucket : this.buckets){

            //Main.functionMap.put(bucket.getFunctionNameProperty(), keyValue(Main.functionMap, bucket.getFunctionNameProperty()) +1);
            //Main.fileMap.put(bucket.getFileNameProperty(), keyValue(Main.fileMap, bucket.getFileNameProperty()) +1);
            //Main.libraryMap.put(bucket.getLibraryNameProperty(), keyValue(Main.libraryMap, bucket.getLibraryNameProperty()) +1);

            for(SubStacktrace subStackTrace : stackTrace)
            {
                //System.out.println(bucket.getFunctionNameProperty());
                if(bucket.getFunctionNameProperty() != null && bucket.getFunctionNameProperty().size() > 0)
                {

                    if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty().get(0)))
                        isFunctionMatched = true;
                }
                else
                    isFunctionMatched = true;

                //System.out.println(bucket.getFileNameProperty());
                if(bucket.getFileNameProperty() != null && bucket.getFileNameProperty().size() > 0)
                {
                    if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty().get(0)))
                        isFileNameMatched = true;
                }
                else
                    isFunctionMatched = true;

                //System.out.println(bucket.getLibraryNameProperty());
                if(bucket.getLibraryNameProperty() != null && bucket.getLibraryNameProperty().size() > 0)
                {
                    if (subStackTrace.getLibraryName() != null &&  subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty().get(0)))
                        isLibraryMatched = true;
                }
                else
                    isFunctionMatched = true;

            }
            if(isFunctionMatched && isFileNameMatched || isLibraryMatched)
                return bucketToReturn = bucket;
        }

        return bucketToReturn;
    }


    private int keyValue(HashMap<String, Integer> rankMap, String name){
        if (rankMap.get(name) == null){
            return 0;
        }
        else{
            return rankMap.get(name);
        }
    }
}
