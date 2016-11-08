package Main;

import Analyzer.*;
import Model.Buckets;

import java.io.File;
import java.util.HashMap;


/**
 * Created by Junior on 19-10-16.
 */
public class Main {
	
    public static final String PATH_BUCKETS_TRAINING = "C:/data2/training";
    public static final String PATH_BUCKETS_TESTING = "C:/data2/testing";

    public static HashMap<String, Integer> functionMap = new HashMap<String,Integer>();
    public static HashMap<String, Integer> fileMap = new HashMap<String,Integer>();
    public static HashMap<String, Integer> libraryMap = new HashMap<String,Integer>();

    public static void main (String [] arg){

    	//Creation de l'espace d'entrainement et affichage du resultat de l'assimilation des donnees
    	
        Buckets buckets = new Buckets(PATH_BUCKETS_TRAINING);
        System.out.println(buckets.toString());
        
        File stacktraceFile = new File(PATH_BUCKETS_TESTING);
        File[] stacktraceFiles = stacktraceFile.listFiles();

        long startTime = System.currentTimeMillis();

        Analyze(new MatchAnalyzerBoolean(buckets), stacktraceFiles);
        Analyze(new MatchAnalyzerCount(buckets), stacktraceFiles);
        Analyze(new MatchAnalyzerGeneralCount(buckets), stacktraceFiles);
        Analyze(new MatchAnalyzerMapCount(buckets), stacktraceFiles);
        //Analyze(new SimilarityAnalyzer(buckets), stacktraceFiles);

        long endTime = System.currentTimeMillis();

        System.out.println("Execution time " + (endTime - startTime) + " milliseconds");
    }


    public static void Analyze (IAnalyzer analyzer, File[] stacktraceFiles)
    {
        System.out.println("=====================================================");
        System.out.println("TESTING with : " + analyzer.getClass());
        System.out.println("=====================================================");

        analyzer.createAnalyzeResultFile(stacktraceFiles);

        System.out.println("====================== FINISH =======================");

    }
}