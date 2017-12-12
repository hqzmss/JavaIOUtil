package com.hqz.io.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: hqz
 * Date: 2017/12/10
 * Time: 11:01
 */

class DirFilter implements FilenameFilter{
    private Pattern pattern;
    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
