package Model;

import Main.Main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public SubStackTrace() {
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

        //Cette boucle et tout le traitement fait permet de remettre correctement un string trop découpé par le spit précédent.
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
        Main.COUNT_TOTAL_SUBSTACKTRACE++;
        Main.COUNT_TOTAL_SUBSTACKTRACE_OK++;
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
            Main.COUNT_TOTAL_SUBSTACKTRACE_OK--;
            Stacktrace.notOk();
        }

    }
}
