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
    public void test() {
        File file = new File("E:\\hqz\\test\\test1\\test3.txt");
        System.out.println(file.length());
    }
}
