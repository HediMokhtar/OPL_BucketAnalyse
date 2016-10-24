package Main;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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


        if(map.size() > 0) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry != null && (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)) {
                    maxEntry = entry;
                }
            }
            return maxEntry.getKey();
        }
        else
            return null;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
