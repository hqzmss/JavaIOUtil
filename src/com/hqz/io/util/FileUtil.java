package com.hqz.io.util;

import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: hqz
 * Date: 2017/12/10
 * Time: 10:54
 */

public class FileUtil {
    /**
     *列出目录下的所有文件名，不包含子文件(方法一)
     * @param directory 文件目录
     * @param regex 需要过滤的正则表达式
     * @return
     */
    public static String[] getCurrentFileNames1(String directory, String regex) {
        if(directory == null || directory.length() <=0) {
            return null;
        }
        File path = new File(directory);
        String[] list;
        if(regex == null || regex.length() <=0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(regex));
        }
        return list;
    }

    /**
     *列出目录下的所有文件名，不包含子文件(方法二：匿名内部类)
     * @param directory 文件目录
     * @param regex 需要过滤的正则表达式
     * @return
     */
    public static String[] getCurrentFileNames2(String directory, String regex) {
        if(directory == null || directory.length() <= 0) {
            return null;
        }
        File path = new File(directory);
        String[] list;
        if(regex == null || regex.length() <= 0) {
            list = path.list();
        } else {
            list = path.list(filter(regex));
        }
        return list;
    }

    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    /**
     *列出目录下的所有文件名，不包含子文件(方法三：匿名内部类)
     * @param directory 文件目录
     * @param regex 需要过滤的正则表达式
     * @return
     */
    public static String[] getCurrentFileName3(String directory, final String regex) {
        if(directory == null || directory.length() <=0 ) return null;
        File path = new File(directory);
        String[] list;
        if(regex == null || regex.length() <= 0) {
            list = path.list();
        } else {
            list = path.list(
                    new FilenameFilter() {
                        private Pattern pattern = Pattern.compile(regex);
                        @Override
                        public boolean accept(File dir, String name) {
                            return pattern.matcher(name).matches();
                        }
                    }
            );
        }

        return list;
    }

    /**
     * 产生由本地目录中的文件构成的File对象数组
     * @param dir 本地目录的File对象
     * @param regex 正则表达式
     * @return File[]
     */
    public static File[] getCurrentFiles(File dir, final String regex) {
        return dir.listFiles(
                new FilenameFilter() {
                    private Pattern pattern = Pattern.compile(regex);
                    @Override
                    public boolean accept(File dir, String name) {
                        return pattern.matcher(new File(name).getName()).matches();
                    }
                }
        );
    }

    /**
     * 产生由本地目录中的文件构成的File对象数组
     * @param path 本地目录的路径
     * @param regex 正则表达式
     * @return File[]
     */
    public static File[] getCurrentFiles(String path, final String regex) {
        return getCurrentFiles(new File(path), regex);
    }

    /**
     * 获取路径下的所有目录与文件的File对象
     * @param path 路径
     * @param regex 正则表达式
     * @return
     */
    public static Map<String, List<File>> getAllFiles(String path, String regex) {
        TreeInfo info = TreeInfo.recurseDirs(new File(path), regex);
        Map<String, List<File>> map = new HashMap<>();
        map.put("files", info.getFiles());
        map.put("dirs", info.getDirs());
        return map;
    }

    /**
     * 获取目录下的所有文件与子目录及其文件
     * @param dir 目录
     * @param regex 正则表达式
     * @return
     */
    public static Map<String, List<File>> getAllFiles(File dir, String regex) {
        TreeInfo info = TreeInfo.recurseDirs(dir, regex);
        Map<String, List<File>> map = new HashMap<>();
        map.put("files", info.getFiles());
        map.put("dirs", info.getDirs());
        return map;
    }

    /**
     * 获取路径下的所有目录及其文件
     * @param path 路径
     * @return
     */
    public static Map<String, List<File>> getAllFiles(String path) {
        TreeInfo info = TreeInfo.recurseDirs(new File(path), ".*");
        Map<String, List<File>> map = new HashMap<>();
        map.put("files", info.getFiles());
        map.put("dirs", info.getDirs());
        return map;
    }

    /**
     * 获取目录下的所有子目录及其文件
     * @param dir 目录
     * @return
     */
    public static Map<String, List<File>> getAllFiles(File dir) {
        TreeInfo info = TreeInfo.recurseDirs(dir, ".*");
        Map<String, List<File>> map = new HashMap<>();
        map.put("files", info.getFiles());
        map.put("dirs", info.getDirs());
        return map;
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        public List<File> getFiles() {
            return files;
        }
        public List<File> getDirs() {
            return dirs;
        }
        public Iterator<File> iterator() {
            return files.iterator();
        }
        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        static TreeInfo recurseDirs(File startDir, String regex) {
            TreeInfo result = new TreeInfo();
            for(File item : startDir.listFiles()) {
                if(item.isDirectory()) {
                    result.dirs.add(item);
                    result.addAll(recurseDirs(item, regex));
                } else if(item.getName().matches(regex)) {
                    result.files.add(item);
                }
            }
            return result;
        }
    }
}
