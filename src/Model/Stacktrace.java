package Model;

import Main.Tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Junior on 19-10-16.
 */
@SuppressWarnings("serial")
public class Stacktrace extends ArrayList<SubStackTrace>{

    private int stackTraceNumber;
    private boolean first = true;
    private Bucket bucket;
    private boolean haveBucket;
    
    public int getStackTraceNumber() {
        return stackTraceNumber;
    }

    public Stacktrace() {
    	super();
        first = true;
        this.haveBucket = false;
    }
    
    public Stacktrace(Bucket bucket) {
    	super();
        first = true;
        this.bucket = bucket;
        this.haveBucket = true;
    }
    
    public Bucket getBucket(){
    	return this.bucket;
    }
    
    public Buckets getBuckets(){
    	return this.getBucket().getBuckets();
    }

    public boolean haveBucket(){
    	return this.haveBucket;
    }
    
    /**
     * Remplissage de l'arrayList de substacktrace
     * 1) On coupe la stacktrace avec le caractere # pour obtenir chaque exception
     * 2) Sur chaque exception on recupere le nombre de l'exception
     * 3) si le reste de la chaine n'est pas nul notre substacktrace est crée
     * @param fileStacktrace
     * @param stackTraceNumber
     */
    public void fill(File fileStacktrace, int stackTraceNumber) {
        this.stackTraceNumber = stackTraceNumber;

        try {
            String stacktrace = Tools.readFile(fileStacktrace.getPath(), Charset.defaultCharset());
            if(this.haveBucket == true){
            	this.getBuckets().incrementTotalStackTrace();
            	this.getBuckets().incrementTotalOkStackTrace();
            }
            String[] splitedStrackTrace = stacktrace.split(Pattern.quote("#"));
            ArrayList<String> splitedStrackTraceList = new ArrayList<String>(Arrays.asList(splitedStrackTrace));
            if(splitedStrackTraceList.get(0).equalsIgnoreCase(""))
                splitedStrackTraceList.remove(0);

            for(String subStackTraceString : splitedStrackTraceList) {
                int idSub = -1;
                Pattern pattern = Pattern.compile("([0-9]*)");
                Matcher matcher = pattern.matcher(subStackTraceString);
                if(matcher.find()) {
                    if(!matcher.group(1) .equalsIgnoreCase(""))
                        idSub = Integer.parseInt(matcher.group(1));
                    else
                        System.out.println(subStackTraceString);
                }
                else
                    System.out.println("Regex fail for the id of the SubStacktrace : " + subStackTraceString);

                //System.out.println("SUBSTACKTRACE : " + idSub + "\n" + subStackTraceString);
                SubStackTrace subStackTrace = new SubStackTrace(this);
                subStackTrace.fill(subStackTraceString, idSub);
                //System.out.println();
                this.add(subStackTrace);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notOk()
    {
        if(first & this.haveBucket == true)
        	this.getBuckets().decrementTotalOkStackTrace();
    }
}
