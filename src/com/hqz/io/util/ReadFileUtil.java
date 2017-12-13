package com.hqz.io.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by hqzmss on 2017/12/13.
 */

public class ReadFileUtil {
    /**
     * 根据文件路径读取文件内容
     * @param filePath 文件路径
     * @return
     * @throws Exception
     */
    public static String readFile(String filePath) throws IOException {
        if(!(filePath != null && filePath.length() > 0)) {
            return null;
        }
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        String s;
        StringBuffer sb = new StringBuffer();
        while((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    /**
     * 根据File对象读取文件内容
     * @param file
     * @return
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        if(file == null) {
            return null;
        }
        BufferedReader in = new BufferedReader(new FileReader(file));
        String s;
        StringBuffer sb = new StringBuffer();
        while((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return  sb.toString();
    }
}
