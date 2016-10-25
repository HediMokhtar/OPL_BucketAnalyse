package Main;

import Analyzer.IAnalyzer;
import Analyzer.MatchAnalyzerGeneralCount;
import Model.Buckets;
import Model.Stacktrace;

import java.io.File;
import java.util.HashMap;


/**
 * Created by Junior on 19-10-16.
 */
public class Main {
	
    public static final String PATH_BUCKETS_TRAINING = "./nautilus/nautilus-training";
    public static final String PATH_BUCKETS_TESTING = "./nautilus/nautilus-testing";

    public static HashMap<String, Integer> functionMap = new HashMap<String,Integer>();
    public static HashMap<String, Integer> fileMap = new HashMap<String,Integer>();
    public static HashMap<String, Integer> libraryMap = new HashMap<String,Integer>();

    public static void main (String [] arg){

    	//Creation de l'espace d'entrainement et affichage du resultat de l'assimilation des donnees
    	
        Buckets buckets = new Buckets(PATH_BUCKETS_TRAINING);
        System.out.println(buckets.toString());
        
        File stacktraceFile = new File(PATH_BUCKETS_TESTING);
        File[] stacktraceFiles = stacktraceFile.listFiles();

        //Analyze(new MatchAnalyzerBoolean(buckets), stacktraceFiles);
        //Analyze(new MatchAnalyzerCount(buckets), stacktraceFiles);
        Analyze(new MatchAnalyzerGeneralCount(buckets), stacktraceFiles);
    }


    public static void Analyze (IAnalyzer analyzer, File[] stacktraceFiles)
    {
        System.out.println("=====================================================");
        System.out.println("TESTING with : " + analyzer.getClass());
        System.out.println("=====================================================");
        Stacktrace stacktraceTesting;
        for(File stackTraceTest : stacktraceFiles) {
            stacktraceTesting = new Stacktrace();
            stacktraceTesting.fill(stackTraceTest, stackTraceTest.getName().substring(0, stackTraceTest.getName().length()-4));
            System.out.print(analyzer.monperrusEvalPrinter(stackTraceTest, stacktraceTesting));
        }
    }
}
