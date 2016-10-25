package Analyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

public abstract class Analyzer implements IAnalyzer {

	 Buckets buckets;
	 File analyzeResultFile;

	public Analyzer(Buckets buckets){
		this.buckets = buckets;
		String path = this.getClass().getSimpleName() + ".txt";
		if ((new File(path).exists())){
			(new File(path)).delete();
		}
		analyzeResultFile = new File(path);
	}

	public abstract Bucket searchBucket(Stacktrace stackTrace);
		
	public String monperrusEvalPrinter(File stackTraceFile, Stacktrace stackTrace){
		String result = "";
		result += Integer.parseInt(stackTraceFile.getName().substring(0, stackTraceFile.getName().length()-4));
		result +=  "  ->  ";
		result +=  this.searchBucket(stackTrace).getBucketNumber() + "\n";
		return result;
	}
	
	public void createAnalyzeResultFile(File stackTraceFile, Stacktrace stackTrace) throws IOException{
		
		FileWriter output = new FileWriter(this.analyzeResultFile, true);
		String analyzeResult = monperrusEvalPrinter(stackTraceFile, stackTrace);
		output.write(analyzeResult);
        output.close();
	}
	
	public HashMap<String, ArrayList<String>> getAnalyzeResults(){
		return new HashMap();
	}
	
}
