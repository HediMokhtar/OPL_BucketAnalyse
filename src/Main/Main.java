package Main;

import Analyzer.MatchAnalyzerBoolean;

import Analyzer.*;
import java.io.File;
import Model.Buckets;


/**
 * Created by Junior on 19-10-16.
 */
public class Main {

    /** Path en absolu ou en relatif vers le dossier contenant les stacktrace à placer dans un bucket. **/
    public static final String PATH_BUCKETS_TRAINING = "C:/data2/training";
    /** Path en absolu ou en relatif vers le dossier contenant les buckets déjà créé. **/
    public static final String PATH_BUCKETS_TESTING = "C:/data2/testing";


    public static void main (String [] arg){

    	/** Creation de l'espace d'entrainement et affichage du resultat de l'assimilation des donnees **/
        Buckets buckets = new Buckets(PATH_BUCKETS_TRAINING);
        System.out.println(buckets.toString());
        
        File stacktraceFile = new File(PATH_BUCKETS_TESTING);
        File[] stacktraceFiles = stacktraceFile.listFiles();

        long startTime;
        long endTime;

        /** Les différents analyseur que l'on exécute **/
        /** Chaque analyseur aura son fichier de résultat **/
        startTime = System.currentTimeMillis();
        Analyze(new NaiveAnalyzer(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("1. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new MatchAnalyzerBoolean(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("2. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new MatchAnalyzerCount(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("3. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new MatchAnalyzerGeneralCount(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("4. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new MatchAnalyzerMapCount(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("5. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new SimilarityAnalyzerV2(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("6. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new CombinationAnalyzer(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("7. Execution time " + (endTime - startTime) + " milliseconds");
        startTime = System.currentTimeMillis();
        Analyze(new HashAnalyzer(buckets), stacktraceFiles);
        endTime = System.currentTimeMillis();
        System.out.println("8. Execution time " + (endTime - startTime) + " milliseconds");




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