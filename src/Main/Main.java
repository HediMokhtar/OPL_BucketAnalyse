package Main;

import Analyzer.NaiveAnalyzer;
import Model.Buckets;
import Model.Stacktrace;

import java.io.File;


/**
 * Created by Junior on 19-10-16.
 */
public class Main {
	
    public static final String PATH_BUCKETS_TRAINING = "./nautilus/nautilus-training";
    public static final String PATH_BUCKETS_TESTING = "./nautilus/nautilus-testing";

    public static void main (String [] arg){

    	//Creation de l'espace d'entrainement et affichage du resultat de l'assimilation des donnees
    	
        Buckets buckets = new Buckets(PATH_BUCKETS_TRAINING);
        System.out.println(buckets.toString());
        
        File stacktraceFile = new File(PATH_BUCKETS_TESTING);
        File[] stacktraceFiles = stacktraceFile.listFiles();
        
        System.out.println("=====================================================");
        System.out.println("TESTING");
        System.out.println("=====================================================");
        
        NaiveAnalyzer algo = new NaiveAnalyzer(buckets);
        
        Stacktrace stacktraceTesting;
        for(File stackTraceTest : stacktraceFiles) {
        	
           stacktraceTesting = new Stacktrace();
           stacktraceTesting.fill(stackTraceTest, Integer.parseInt(stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4)));
           System.out.print(algo.monperrusEvalPrinter(stackTraceTest, stacktraceTesting));
            
        }
        
        /**
        //TODO Boucle creation thread
        final CountDownLatch latch = new CountDownLatch(buckets.size());
        for(File stacktraceFile : new File(PATH_BUCKETS_TESTING).listFiles()) {
            Stacktrace stacktrace = new Stacktrace();
            stacktrace.fill(stacktraceFile, Integer.parseInt(stacktraceFile.getName().substring(0, stacktraceFile.getName().length()-4)));
            for(Bucket bucket : buckets)
            {
                    Thread thread = new Thread(new MatchStacktraceToBucket(stacktrace, bucket, latch));
                    thread.start();
            }
        }

        try{
            latch.await();
            System.out.println("All analyze of each bucket is done");
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }**/ 
    }
}
