package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		
	public ArrayList<String> monperrusEvalPrinter(File [] stackTraceFiles){
		Stacktrace stacktraceTesting;
		ArrayList<String> results = new ArrayList<>();
		for(File stackTraceTest : stackTraceFiles) {
			stacktraceTesting = new Stacktrace();
			stacktraceTesting.fill(stackTraceTest, stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4));

			String result = "";
			result += Integer.parseInt(stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4));
			result +=  "  ->  ";
			result +=  this.searchBucket(stacktraceTesting).getBucketNumber() + "\n";

			results.add(result);
		}

		return results;
	}
	
	public void createAnalyzeResultFile(File [] stackTraceFiles){

		try {
			FileWriter output = new FileWriter(this.analyzeResultFile, true);
			for (String s : monperrusEvalPrinter(stackTraceFiles)) {
				output.write(s);
			}
			output.close();
		}
		catch (IOException e)
		{
			;
		}
	}
	
	public HashMap<String, ArrayList<String>> getAnalyzeResults(){
		return new HashMap();
	}
	
}
