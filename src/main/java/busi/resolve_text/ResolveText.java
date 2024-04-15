package busi.resolve_text;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResolveText {

    /**
     * 功能：解析文件，并取得特定字符串后的值
     * @return List<Map<String,String>>
     */
    public static List<Map<String,String>> resolveTextContent(String path) {
        InputStream inputStream;
        Map<String,String> dataMap = new HashMap<>();
        List<Map<String,String>> dataList = new ArrayList<>();
        StringBuilder stringBuilderLeft = new StringBuilder();
        StringBuilder stringBuilderRight = new StringBuilder();
        String property;
        String value;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) {
                    break;
                }
                for (int i=0;i<line.length();i++){
                    if (line.charAt(i) == '='){
                        int left = i-1,right = i+1;
                        if (line.charAt(left) == ' '){
                            left--;
                        }
                        if (line.charAt(right) == ' '){
                            right++;
                        }
                        //读取属性
                        while (line.charAt(left) != ' ' && left > 0){
                            stringBuilderLeft.append(line.charAt(left));
                            left--;
                        }
                        //读取属性值
                        while (line.charAt(right) != ' ' && right < line.length()-1){
                            stringBuilderRight.append(line.charAt(right));
                            right++;
                        }
                        property = stringBuilderLeft.toString();
                        value = stringBuilderRight.toString();
                        //清空stringBuilderLeft、stringBuilderRight
                        stringBuilderLeft =new StringBuilder();
                        stringBuilderRight = new StringBuilder();
                        //装填属性和属性值
                        dataMap.put(property,value);
                        i = right;
                    }
                }
                dataList.add(dataMap);
                //清空dataMap,否则数据会混乱   具体为什么会混乱 待弄清
                dataMap = new HashMap<>();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }
}
