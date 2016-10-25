package Analyzer;

import Model.Bucket;
import Model.Stacktrace;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Junior on 24-10-16.
 */
public interface IAnalyzer {

	/**
	 * Return the bucket corresponding to the stacktrace depending of the analyze
	 * @param stackTrace 
	 * @return the bucket assigned
	 */
    Bucket searchBucket(Stacktrace stackTrace);
    
    /**
     * Transform the analyze result in a String corresponding to Mister Monperrus expectations
     * @param stackTraceFiles the stackTraceFiles in input
     * @return
     */
    ArrayList<String> monperrusEvalPrinter(File [] stackTraceFiles);

    void createAnalyzeResultFile(File [] stackTraceFiles);

    HashMap<String, ArrayList<String>> getAnalyzeResults();
}
