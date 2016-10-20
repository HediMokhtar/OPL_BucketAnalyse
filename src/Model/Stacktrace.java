package Model;

import Main.Tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Junior on 19-10-16.
 */
public class Stacktrace extends ArrayList<SubStackTrace>{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stacktrace() {

    }

    public void fill(File fileStacktrace, int id) {
        this.id = id;

        try {
            String stacktrace = Tools.readFile(fileStacktrace.getPath(), Charset.defaultCharset());

            String[] splitedStrackTrace = null;
            //TODO split du fichier sur "#??"
            String regex = "";
            splitedStrackTrace = stacktrace.split(regex);

            for(String subStackTraceString : splitedStrackTrace) {
                //TODO split pour récupérer l'id
                regex = "";
                int idSub = Integer.parseInt(subStackTraceString.split(regex)[0]);

                SubStackTrace subStackTrace = new SubStackTrace();
                subStackTrace.fill(subStackTraceString, idSub);
                this.add(subStackTrace);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
