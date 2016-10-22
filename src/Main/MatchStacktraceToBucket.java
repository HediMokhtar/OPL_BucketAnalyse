package Main;

import Model.Bucket;
import Model.Stacktrace;
import Model.SubStackTrace;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Junior on 21-10-16.
 */
public class MatchStacktraceToBucket implements Runnable {

    private Stacktrace stacktrace;
    private Bucket bucket;
    private CountDownLatch latch;
    private boolean isMatched = false;

    public MatchStacktraceToBucket(Stacktrace stackTrace, Bucket bucket, CountDownLatch latch)
    {
        this.stacktrace = stackTrace;
        this.bucket = bucket;
        this.latch = latch;
    }

    @Override
    public void run() {

        boolean isFunctionMatched = false;
        boolean isLibraryMatched = false;
        boolean isFileNameMatched = false;

        for(SubStackTrace subStackTrace : stacktrace)
        {
            if (subStackTrace.getFunctionName() != null && subStackTrace.getFunctionName().equalsIgnoreCase(bucket.getFunctionNameProperty()))
                isFunctionMatched = true;
            if (subStackTrace.getFileName() != null && subStackTrace.getFileName().equalsIgnoreCase(bucket.getFileNameProperty()))
                isFileNameMatched = true;
            if (subStackTrace.getLibraryName() != null &&  subStackTrace.getLibraryName().equalsIgnoreCase(bucket.getLibraryNameProperty()))
                isLibraryMatched = true;
        }


        if(isFunctionMatched && isFileNameMatched && isLibraryMatched)
            isMatched = true;

        if(isMatched)
            System.out.println(stacktrace.getId() + " -> " + bucket.getBucketNumber());


        latch.countDown();
    }

    public boolean isMatched() {
        return isMatched;
    }
}
