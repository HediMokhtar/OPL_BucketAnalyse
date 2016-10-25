package Analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Bucket;
import Model.Stacktrace;

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
     * @param stackTraceFile the stackTraceFiles in input
     * @param stackTrace
     * @return
     */
    String monperrusEvalPrinter(File stackTraceFile, Stacktrace stackTrace);
    
    void createAnalyzeResultFile(File stackTraceFile, Stacktrace stackTrace) throws IOException;
    
   /**
    * 
    * @return
    
    HashMap<String, ArrayList<String>> getAnalyzeResults(); 
    */
}
