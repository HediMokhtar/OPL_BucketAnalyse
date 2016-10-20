package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.geom.AreaOp.AddOp;

/**
 * Created by Junior on 19-10-16.
 */
public class Bucket extends ArrayList<Stacktrace> {

    private int id;
    private HashMap<String, Integer> functionNameRanking = new HashMap<String,Integer>();
    private HashMap<String, Integer> fileNameRanking = new HashMap<String,Integer>();
    private HashMap<String, Integer> libraryNameRanking = new HashMap<String,Integer>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    

    public Bucket() {
    }

    public void fill(File directoryBucket, int id) {
        this.id = id;

        File [] directories = directoryBucket.listFiles();

        // Pour chaque dossier de Stacktrace
        for(File directory : directories)
        {
            Stacktrace stackTrace = new Stacktrace();
            stackTrace.fill(directory.listFiles()[0], Integer.parseInt(directory.getName()));
            this.add(stackTrace);
        }
    }
    
    public boolean add(Stacktrace stackTrace){
    	
    	String currentFunctionName;
    	String currentfileName;
    	String currentLibraryName;
    	
    	for(SubStackTrace subStackTrace : stackTrace){
    		
    		currentFunctionName = subStackTrace.getFunctionName();
        	currentfileName = subStackTrace.getFileName();
        	currentLibraryName = subStackTrace.getLibraryName();
        	
    		this.functionNameRanking.put(currentFunctionName, keyValue(functionNameRanking,currentFunctionName) + 1);
    		this.fileNameRanking.put(currentfileName, keyValue(fileNameRanking,currentfileName) + 1);
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
