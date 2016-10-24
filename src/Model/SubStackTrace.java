package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Junior on 19-10-16.
 */
@SuppressWarnings("serial")
public class SubStackTrace extends ArrayList<String>{

    private int id;
    private String functionName;
    private String fileName;
    private String libraryName;
	private Stacktrace stacktrace;
	private boolean haveStackTrace;

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public SubStackTrace() {
    	super();
    	this.haveStackTrace = false;
    }
    
    public SubStackTrace(int id, String functionName, String fileName, String libraryName) {
    	this.id = id;
    	this.functionName = functionName;
    	this.fileName = fileName;
    	this.libraryName = libraryName;
    	this.haveStackTrace = false;
    }

    public SubStackTrace(Stacktrace stacktrace) {
		super();
		this.stacktrace = stacktrace;
		this.haveStackTrace = stacktrace.haveBucket();
	}
    
    public Stacktrace getStackTrace(){
    	return this.stacktrace;
    }
    
    public Bucket getBucket(){
    	return this.getStackTrace().getBucket();
    }
    
    public Buckets getBuckets(){
    	return this.getBucket().getBuckets();
    }

    
	/**
     *
     * @return True = Library; False = File; Null = Other
     */
    public boolean isFromLibraryOrFile(){
        if(!libraryName.equalsIgnoreCase(""))
            return true;
        else if(!fileName.equalsIgnoreCase(""))
            return false;
        else
            return Boolean.parseBoolean(null);
    }

    public void fill(String subStackTrace, int id) {
        this.id = id;

        String[] splitedSubStackTrace = null;
        splitedSubStackTrace = subStackTrace.split("(?=\\t{0,500}.{0,500} = )");
        boolean first = true;
        boolean firstLocal = false;
        String string = "";

        //Cette boucle et tout le traitement fait permet de remettre correctement un string trop decoupe par le spit precedent.
        for(String subStackTraceLine : splitedSubStackTrace) {
            if(subStackTraceLine.length() <= 2) {
                if(first)
                    firstLocal = true;;

                if (!first && subStackTraceLine.equalsIgnoreCase("\t"))
                {
                    firstLocal = true;
                }else
                {
                    if(!subStackTraceLine.equalsIgnoreCase("\t") && !subStackTraceLine.equalsIgnoreCase("\n"))
                        string = string.concat(subStackTraceLine);

                    first = false;
                }
            }
            else if(firstLocal)
            {
                string = string.concat(subStackTraceLine);
                firstLocal = false;
                this.add(string);
                string = "";
            }
            else
                this.add(subStackTraceLine);
        }


        // Cette regex permet de récupérer les infos de nom de fonction + nom de fichier avec numéro de ligne ou nom de librairie + le cas particulier de ligns terminanant par "in ?? ()"
        Pattern pattern = Pattern.compile(" (.*)\\(.*\\).*at (.*.:[0-9]*)| in (.*)\\(.*\\) from (.*)|( in .*)\\(.*\\)|.* in \\?\\? \\(\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(splitedSubStackTrace[0]);
        if(this.haveStackTrace == true){
        	this.getBuckets().incrementTotalSubstacktrace();
        	this.getBuckets().incrementTotalOkSubstacktrace();
        }
        //System.out.print(splitedSubStackTrace[0]);
        if(matcher.find()) {
            // Si on a à faire a Fonction + Fichier
            if( matcher.group(1) != null)
            {
                this.functionName = matcher.group(1);
                if(functionName.split(Pattern.quote(" in ")).length > 1)
                    functionName = functionName.split(Pattern.quote(" in "))[1];
                this.fileName =  matcher.group(2);
            }
            // Si on a à faire a Fonction + Librairie
            else if(matcher.group(3) != null)
            {
                this.functionName = matcher.group(3);
                this.libraryName = matcher.group(4);
            }
            //System.out.println("FUNCTION : " + functionName + " ----- FILE : " + fileName  + " ----- LIBRARY : " + libraryName + "\n");
        }
        else {
            System.out.println("Regex fail for the the SubStacktrace first : " + splitedSubStackTrace[0]);
            if(this.haveStackTrace == true){
            	this.getBuckets().decrementTotalOkSubstacktrace();
            	this.getStackTrace().notOk();
            }
        }

    }
}
