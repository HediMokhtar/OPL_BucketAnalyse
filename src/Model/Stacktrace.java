package Model;

import Main.Main;
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
public class Stacktrace extends ArrayList<SubStackTrace>{

    private int id;
    private static boolean first = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stacktrace() {
        first = true;
    }

    public void fill(File fileStacktrace, int id) {
        this.id = id;

        try {
            String stacktrace = Tools.readFile(fileStacktrace.getPath(), Charset.defaultCharset());
            Main.COUNT_TOTAL_STACKTRACE++;
            Main.COUNT_TOTAL_STACKTRACE_OK++;
            String[] splitedStrackTrace = null;
            splitedStrackTrace = stacktrace.split(Pattern.quote("#"));
            ArrayList<String> splitedStrackTraceList = new ArrayList(Arrays.asList(splitedStrackTrace));
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
                SubStackTrace subStackTrace = new SubStackTrace();
                subStackTrace.fill(subStackTraceString, idSub);
                //System.out.println();
                this.add(subStackTrace);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void notOk()
    {
        if(first)
            Main.COUNT_TOTAL_STACKTRACE_OK--;
    }
}
