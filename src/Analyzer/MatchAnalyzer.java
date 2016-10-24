package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStackTrace;

/**
 * Created by Junior on 24-10-16.
 */
public class MatchAnalyzer extends Analyzer{

    private boolean isMatched = false;

    public MatchAnalyzer(Buckets buckets) {
        super(buckets);
    }

    @Override
    public Bucket searchBucket(Stacktrace stackTrace) {

        Bucket bucketToReturn = new Bucket("0");

        boolean isFunctionMatched = false;
        boolean isLibraryMatched = false;
        boolean isFileNameMatched = false;

        for(Bucket bucket : this.buckets){
            for(SubStackTrace subStackTrace : stackTrace)
            {
                if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty()))
                    isFunctionMatched = true;
                if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty()))
                    isFileNameMatched = true;
                if (subStackTrace.getLibraryName() != null &&  subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty()))
                    isLibraryMatched = true;
            }
            if(isFunctionMatched && isFileNameMatched && isLibraryMatched)
                bucketToReturn = bucket;
        }

        return bucketToReturn;
    }
}
