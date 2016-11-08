package Main;

import Analyzer.IAnalyzer;
import Analyzer.SimilarityAnalyzer;
import Model.Buckets;

import java.io.File;


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

        long startTime = System.currentTimeMillis();

        /** Les différents analyseur que l'on exécute **/
        /** Chaque analyseur aura son fichier de résultat **/
        //Analyze(new MatchAnalyzerBoolean(buckets), stacktraceFiles);
        //Analyze(new MatchAnalyzerCount(buckets), stacktraceFiles);
        //Analyze(new MatchAnalyzerGeneralCount(buckets), stacktraceFiles);
        //Analyze(new MatchAnalyzerMapCount(buckets), stacktraceFiles);
        Analyze(new SimilarityAnalyzer(buckets), stacktraceFiles);

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