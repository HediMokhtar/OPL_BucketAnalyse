package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStackTrace;

public class NaiveAnalyzer extends Analyzer{


	public NaiveAnalyzer(Buckets buckets) {
		super(buckets);
	}

	public Bucket searchBucket(Stacktrace stackTrace){
		for(Bucket bucket : this.buckets){
			if(stackTraceMatch(stackTrace, bucket)){
				return bucket;
			}
		}
		return new Bucket(buckets);
	}
	
	private static boolean stackTraceMatch(Stacktrace stackTrace, Bucket bucket){
    	for(SubStackTrace subStackTrace : stackTrace){
    		if(isPartOfSubStackTraceInTheBucket(subStackTrace, bucket)){
    			return true;
    		}
    	}
    	return false;
    }
    
    private static boolean isPartOfSubStackTraceInTheBucket(SubStackTrace subStackTrace, Bucket bucket){
    	if(bucket.containFunctionName(subStackTrace.getFunctionName()) && bucket.containFileName(subStackTrace.getFileName())){
    		return true;
    	}
    	if(bucket.containFunctionName(subStackTrace.getFunctionName()) && bucket.containLibraryName(subStackTrace.getLibraryName())){
    		return true;
    	}
    	return false;
    }
}
	
	

