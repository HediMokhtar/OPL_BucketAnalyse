package Model;

import java.util.ArrayList;

/**
 * Created by Junior on 19-10-16.
 */
public class SubStackTrace extends ArrayList<String>{

    private int id;
    private String functionName;
    private String fileName;
    private String libraryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public SubStackTrace(String subStackTrace, int id) {

        this.id = id;

        String[] splitedSubStackTrace = null;
        //TODO split du string sur "word = "
        String regex = "";
        splitedSubStackTrace = subStackTrace.split(regex);

        for(String subStackTraceElement : splitedSubStackTrace)
            this.add(subStackTraceElement);

        //TODO split de la ligne
        String[] splitedFirstLine = null;
        String regex1 = "";
        splitedFirstLine = this.get(0).split(regex);

        //TODO Adapter en fonction de la façon dont on récupère les infos avec les regex
        this.functionName = splitedFirstLine[0];
        this.fileName = splitedFirstLine[1];
        this.libraryName = splitedFirstLine[2];
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
}
