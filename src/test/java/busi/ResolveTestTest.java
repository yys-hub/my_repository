package busi;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

import static busi.resolve_text.ResolveText.resolveTextContent;

public class ResolveTestTest extends TestCase {

    public void testResolveTextContent() {
        String path = "C:\\Users\\86176\\Desktop/test.txt";
        String specialStr = "c";
        List<Map<String,String>> resultList;
        resultList = resolveTextContent(path);
        for (Map<String, String> stringStringMap : resultList) {
            String s = stringStringMap.get(specialStr);
            System.out.println(s);
        }
    }
}