package Analyzer;

import Model.Bucket;
import Model.Buckets;
import Model.Stacktrace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public abstract class Analyzer implements IAnalyzer {

	protected Buckets buckets;
	private File analyzeResultFile;
	private IAnalyzer THIS = null;
	private int stacktraceAnalyzed = 0;

	public Analyzer(Buckets buckets){
		this.buckets = buckets;
		String path = this.getClass().getSimpleName() + ".txt";
		if ((new File(path).exists())){
			(new File(path)).delete();
		}
		analyzeResultFile = new File(path);
		THIS = this;
	}

    public Analyzer(){
        String path = this.getClass().getSimpleName() + ".txt";
        if ((new File(path).exists())){
            (new File(path)).delete();
        }
        analyzeResultFile = new File(path);
        THIS = this;
    }

	public abstract Bucket searchBucket(Stacktrace stackTrace);
		
	public ArrayList<String> monperrusEvalPrinter(File [] stackTraceFiles) {
		ArrayList<String> results = new ArrayList<>();
		CountDownLatch latch = new CountDownLatch(stackTraceFiles.length);

		for(File stackTraceTest : stackTraceFiles) {
			//Thread thread = new Thread(new Runnable() {
				//public void run() {
                    try {
					Stacktrace stacktraceTesting = new Stacktrace();
					stacktraceTesting.fill(stackTraceTest, stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4));

					String result = "";
					result += Integer.parseInt(stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4));
					result +=  "  ->  ";
                    IAnalyzer analyzer = null;

                    analyzer = THIS.getClass().newInstance();

                    analyzer.setBuckets(THIS.getBuckets());
					result +=  analyzer.searchBucket(stacktraceTesting).getBucketNumber() + "\n";

					results.add(result);

					stacktraceAnalyzed++;
					System.out.println("Analyzed Stacktrace : " + stacktraceAnalyzed + "/" + stackTraceFiles.length);
					//latch.countDown();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
				}
			//});
			//thread.start();
		//}

		//try{
		//	latch.await();
		//}catch(InterruptedException ie) {
		//	ie.printStackTrace();
		//}

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

    public void setBuckets(Buckets buckets) {
        this.buckets = buckets;
    }

    public Buckets getBuckets() {
        return buckets;
    }
}
