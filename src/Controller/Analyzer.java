package Controller;

import java.io.File;

import Model.Bucket;
import Model.Stacktrace;

public abstract class Analyzer {
	
	public abstract Bucket searchBucket(Stacktrace stackTrace);
		
	public String monperrusEvalPrinter(File stackTraceFile, Stacktrace stackTrace){
		String result = "";
		result += Integer.parseInt(stackTraceFile.getName().substring(0, stackTraceFile.getName().length()-4));
		result +=  "  ->  ";
		result +=  this.searchBucket(stackTrace).getBucketNumber() + "\n";
		return result;
	}
	
}
