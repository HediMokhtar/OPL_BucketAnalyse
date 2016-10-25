package Model;

import Main.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Junior on 19-10-16.
 */
@SuppressWarnings("serial")
public class Bucket extends ArrayList<Stacktrace> {

    private String bucketNumber;
    private Buckets buckets;
    
    private HashMap<String, Integer> functionNameRanking = new HashMap<String,Integer>();
    private ArrayList<String> functionNameProperty;
    private HashMap<String, Integer> fileNameRanking = new HashMap<String,Integer>();
    private ArrayList<String> fileNameProperty;
    private HashMap<String, Integer> libraryNameRanking = new HashMap<String,Integer>();
    private ArrayList<String> libraryNameProperty;


    
    public Bucket(Buckets buckets) {
    	super();
    	this.buckets = buckets;
    }

    public Bucket(String bucketNumber) {
        super();
        this.bucketNumber = bucketNumber;
    }
    
    public String getBucketNumber() {
        return bucketNumber;
    }
    
    public void setBucketNumber(String number){
    	this.bucketNumber = number;
    }
    
    public Buckets getBuckets(){
    	return this.buckets;
    }
    
    public HashMap<String, Integer> getFunctionNameRanking(){
    	return this.functionNameRanking;
    }
    
    public HashMap<String, Integer> getFileNameRanking(){
    	return this.fileNameRanking;
    }
    
    public HashMap<String, Integer> getLibraryNameRanking(){
    	return this.libraryNameRanking;
    }

    public synchronized ArrayList<String> getFunctionNameProperty(){
        functionNameRanking.remove("?? ");
        if(functionNameProperty == null && functionNameRanking != null) {
            functionNameProperty = new ArrayList<String>(Tools.sortByValue(functionNameRanking).keySet());;
        }
        return functionNameProperty;
    }

    public synchronized ArrayList<String> getFileNameProperty(){
        if(fileNameProperty == null && fileNameRanking != null) {
            fileNameProperty = new ArrayList<String>(Tools.sortByValue(fileNameRanking).keySet());;
        }
        return fileNameProperty;
    }

    public synchronized ArrayList<String> getLibraryNameProperty(){
        if(libraryNameProperty == null && libraryNameRanking != null) {
            libraryNameProperty = new ArrayList<String>(Tools.sortByValue(libraryNameRanking).keySet());;
        }
        return libraryNameProperty;
    }

    /**
     * Remplissage d'un bucket 
     * @param directoryOfStacktraces tout les dossiers au sein du Bucket
     * @param bucketNumber le nom du bucket
     */
    public void fill(File[] directoryOfStacktraces, String bucketNumber) {
        this.bucketNumber = bucketNumber;
        System.out.println("BUCKET : " + this.bucketNumber);
        for(File stackTraceDirectory : directoryOfStacktraces)
        {
            Stacktrace stackTrace = new Stacktrace(this);
            System.out.println("STACKTRACE : " + stackTraceDirectory.getName());
            stackTrace.fill(getStackTrace(stackTraceDirectory), stackTraceDirectory.getName());
            this.add(stackTrace);
        }

        System.out.println("Bucket : " + this.bucketNumber + "  --  Size : " + functionNameRanking.size() + " - " + fileNameRanking.size() + " - " + libraryNameRanking.size());
    }
    
    private static File getStackTrace(File bucket){
    	return bucket.listFiles()[0];
    }
    
    public boolean containFunctionName(String functionName){
    	return this.functionNameRanking.containsKey(functionName);
    }
    
    public boolean containFileName(String fileName){
    	return this.fileNameRanking.containsKey(fileName);
    }
    
    public boolean containLibraryName(String libraryName){
    	return this.libraryNameRanking.containsKey(libraryName);
    }
    
    /**
     * add allow us to evaluate how many key words of the crash were registered
     */
    public boolean add(Stacktrace stackTrace){
    	String currentFunctionName;
    	String currentFileName;
    	String currentLibraryName;
    	
    	for(SubStackTrace subStackTrace : stackTrace){
    		currentFunctionName = subStackTrace.getFunctionName();
        	currentFileName = subStackTrace.getFileName();
        	currentLibraryName = subStackTrace.getLibraryName();

            //System.out.println("CURRENT FUNCTION NAME : " + currentFunctionName);
            //System.out.println("CURRENT FILE NAME : " + currentFileName);
            //System.out.println("CURRENT LIBRARY NAME : " + currentLibraryName);

            if(currentFunctionName != null)
            {
                int count = functionNameRanking.containsKey(currentFunctionName) ? functionNameRanking.get(currentFunctionName) : 0;
                functionNameRanking.put(currentFunctionName, count + 1);
            }

            if(currentFileName != null)
            {
                int count = fileNameRanking.containsKey(currentFileName) ? fileNameRanking.get(currentFileName) : 0;
                fileNameRanking.put(currentFileName, count + 1);
            }

            if(currentLibraryName != null)
            {
                int count = libraryNameRanking.containsKey(currentLibraryName) ? libraryNameRanking.get(currentLibraryName) : 0;
                libraryNameRanking.put(currentLibraryName, count + 1);
            }
    	}

    	return super.add(stackTrace);
    }

    
    
    
}
