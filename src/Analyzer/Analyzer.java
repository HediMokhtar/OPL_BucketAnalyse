package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

import java.io.File;

public abstract class Analyzer implements IAnalyzer {

	 Buckets buckets;

	public Analyzer(Buckets buckets){
		this.buckets = buckets;
	}

	public abstract Bucket searchBucket(Stacktrace stackTrace);
		
	public String monperrusEvalPrinter(File stackTraceFile, Stacktrace stackTrace){
		String result = "";
		result += Integer.parseInt(stackTraceFile.getName().substring(0, stackTraceFile.getName().length()-4));
		result +=  "  ->  ";
		result +=  this.searchBucket(stackTrace).getBucketNumber() + "\n";
		return result;
	}
}
