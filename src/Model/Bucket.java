package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static Main.Tools.getKeyOfMaxValue;

/**
 * Created by Junior on 19-10-16.
 */
@SuppressWarnings("serial")
public class Bucket extends ArrayList<Stacktrace> {

    private String bucketNumber;
    private Buckets buckets;
    
    private HashMap<String, Integer> functionNameRanking = new HashMap<String,Integer>();
    private String functionNameProperty;
    private HashMap<String, Integer> fileNameRanking = new HashMap<String,Integer>();
    private String fileNameProperty;
    private HashMap<String, Integer> libraryNameRanking = new HashMap<String,Integer>();
    private String libraryNameProperty;


    
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

    public synchronized String getFunctionNameProperty(){
        if(functionNameProperty == null && functionNameRanking != null)
            functionNameProperty = getKeyOfMaxValue(functionNameRanking);
        return functionNameProperty;
    }

    public synchronized String getFileNameProperty(){
        if(fileNameProperty == null && fileNameRanking != null)
            fileNameProperty = getKeyOfMaxValue(fileNameRanking);
        return fileNameProperty;
    }

    public synchronized String getLibraryNameProperty(){
        if(libraryNameProperty == null && libraryNameRanking != null)
            libraryNameProperty = getKeyOfMaxValue(libraryNameRanking);
        return libraryNameProperty;
    }

    /**
     * Remplissage d'un bucket 
     * @param directoryOfStacktraces tout les dossiers au sein du Bucket
     * @param bucketNumber le nom du bucket
     */
    public void fill(File[] directoryOfStacktraces, String bucketNumber) {
        this.bucketNumber = bucketNumber;
        
        for(File stackTraceDirectory : directoryOfStacktraces)
        {
            Stacktrace stackTrace = new Stacktrace(this);
            System.out.println("STACKTRACE : " + stackTraceDirectory.getName());
            stackTrace.fill(getStackTrace(stackTraceDirectory), stackTraceDirectory.getName());
            this.add(stackTrace);
        }
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
    	String currentfileName;
    	String currentLibraryName;
    	
    	for(SubStackTrace subStackTrace : stackTrace){
    		
    		currentFunctionName = subStackTrace.getFunctionName();
        	currentfileName = subStackTrace.getFileName();
        	currentLibraryName = subStackTrace.getLibraryName();

            if(currentFunctionName != null && !currentFunctionName.equalsIgnoreCase("null"))
    		    this.functionNameRanking.put(currentFunctionName, keyValue(functionNameRanking,currentFunctionName) + 1);
            if(currentfileName != null && !currentfileName.equalsIgnoreCase("null"))
    		    this.fileNameRanking.put(currentfileName, keyValue(fileNameRanking,currentfileName) + 1);
            if(currentLibraryName != null && !currentLibraryName.equalsIgnoreCase("null"))
    		    this.libraryNameRanking.put(currentLibraryName, keyValue(libraryNameRanking,currentLibraryName) + 1);
    	}

    	return super.add(stackTrace);
    }
    
    private int keyValue(HashMap<String, Integer> rankMap, String name){
    	if (rankMap.get(name) == null){
    		return 0;
    	}
    	else{
    		return rankMap.get(name);
    	}
    }
    
    
    
}
