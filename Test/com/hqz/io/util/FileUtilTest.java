package com.hqz.io.util;

import org.testng.annotations.Test;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: hqz
 * Date: 2017/12/10
 * Time: 11:07
 */

public class FileUtilTest {
    @Test
    public void test() throws Exception {
        StringReader in = new StringReader("java");
        int c;
        while ((c = in.read()) != -1) {
            System.out.println((char) c);
        }
    }

    public String read(String filename, String key) throws  Exception {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();
        while((s = in.readLine()) != null) {
            if(s.indexOf(key) != -1)
                sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }
}
