package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

public class HashAnalyzer extends Analyzer {

	
	public HashAnalyzer(Buckets buckets){
		super(buckets);
	}
	
	@Override
	public Bucket searchBucket(Stacktrace stackTrace) {
		// TODO Auto-generated method stub
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
    	for(Stacktrace bucketStacktrace : bucket){
    		if(bucketStacktrace.size() == stackTrace.size()){
    			return true;
    		}
    	}
    	return false;
    }
	
}
