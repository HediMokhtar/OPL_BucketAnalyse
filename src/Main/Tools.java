package Main;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Junior on 19-10-16.
 */
public class Tools {
    static public String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    static public BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    static synchronized public String getKeyOfMaxValue(HashMap<String, Integer> map)
    {
        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry != null && (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)) {
                maxEntry = entry;
            }
        }

        if(maxEntry != null)
            return maxEntry.getKey();
        else
            return null;
    }
}
