package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;
import Model.SubStacktrace;

public class NaiveAnalyzer extends Analyzer{


	public NaiveAnalyzer(Buckets buckets) {
		super(buckets);
	}

	public Bucket searchBucket(Stacktrace stackTrace){

		Bucket bucketToReturn = new Bucket("0");
		this.potentialBuckets.put(stackTrace, new Buckets());
		for(Bucket bucket : this.buckets){
			if(stackTraceMatch(stackTrace, bucket)){
				bucketToReturn = bucket;
				this.potentialBuckets.get(stackTrace).add(bucket);
			}
		}
		return bucketToReturn;
	}
	
	private static boolean stackTraceMatch(Stacktrace stackTrace, Bucket bucket){
    	for(SubStacktrace subStackTrace : stackTrace){
    		if(isPartOfSubStackTraceInTheBucket(subStackTrace, bucket)){
    			return true;
    		}
    	}
    	return false;
    }
    
    private static boolean isPartOfSubStackTraceInTheBucket(SubStacktrace subStackTrace, Bucket bucket){
    	if(bucket.containFunctionName(subStackTrace.getFunctionName()) && bucket.containFileName(subStackTrace.getFileName())){
    		return true;
    	}
    	if(bucket.containFunctionName(subStackTrace.getFunctionName()) && bucket.containLibraryName(subStackTrace.getLibraryName())){
    		return true;
    	}
    	return false;
    }	
}
	
	

