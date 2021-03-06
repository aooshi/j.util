package org.aooshi.j.util;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

public class PathHelper {

    /**
     * 生成一个二级路径信息,常用于文件存储
     *
     * @return
     *      path1 / path2 / filename
     */
    public static String createSecondPath() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        Long time = System.currentTimeMillis();
        String timeString = time.toString();

        String timemd5 = DigestUtils.md5Hex(timeString);

        String path1 = timemd5.substring(10, 12);
        String path2 = timemd5.substring(20, 22);

        String path = path1 + '/' + path2 + '/' + uuid + timemd5.substring(0, 8);

        return path;
    }

    /**
     * 生成一个二级路径信息,常用于文件存储
     *
     * @param suffix
     *  后缀
     * @return
     *      new String[] { path, name, suffix }
     */
    public static String[] createSecondPath(String suffix) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        Long time = System.currentTimeMillis();
        String timeString = time.toString();

        String timemd5 = DigestUtils.md5Hex(timeString);

        String path1 = timemd5.substring(10, 12);
        String path2 = timemd5.substring(20, 22);

        //String path = path1 + '/' + path2 + '/' + uuid + timemd5.substring(0, 8);
        String path = path1 + '/' + path2;
        String name = uuid + timemd5.substring(0, 8);

        //
        return new String[]{
                path,
                name,
                suffix
        };
    }

    /**
     * 获取一个二级路径文件路径，输入值为null时将发生异常
     *
     * @param fileExtension
     *  后缀
     * @return
     *  获取到的结果
     */
    public static String getSecondPath(String fileExtension) {
        if (StringHelper.isEmpty(fileExtension)) {
            //throw exception
            //throw new NullArgumentException("fileExtension");
            fileExtension.toLowerCase();
        }

        String path = PathHelper.createSecondPath();
        String fullpath = path + fileExtension;

        return fullpath;
    }

    /**
     * 获取文件路径，输入值为null时将发生异常
     *
     * @param filename
     *  文件名
     * @return
     *  处理结果
     */
    public static String getFileSecondPath(String filename) {
        String fileExtension = getExtension(filename);
        return getSecondPath(fileExtension);
    }

    /**
     * 获取一个文件的后缀名
     *
     * @param filename
     *  文件名
     * @return
     *  获取到的结果
     */
    public static String getExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1)
            return "";

        //last char
        if (index == filename.length() - 1)
            return "";

        String suffix = filename.substring(index);
        return suffix;
    }


    /**
     * 获取一个不含后缀的文件名
     *
     * @param filename 文件名
     * @return
     *  获取到的结果
     */
    public static String getFileName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1)
            return filename;

        String suffix = filename.substring(0, index);
        return suffix;
    }

    /**
     * 获取一个路径中的文件名
     *
     * @param filepath 文件路径
     * @return
     *  获取到的结果
     */
    public static String getFileNameByPath(String filepath) {
        int index = filepath.lastIndexOf("/");
        if (index == -1)
            return null;

        //last char
        if (index == filepath.length() - 1)
            return "";

        return filepath.substring(index + 1);
    }

    /**
     * 获取一个路径中的路径部份
     *
     * @param filepath 文件路径
     * @return
     *  输出
     */
    public static String getDirectoryByPath(String filepath) {
        int index = filepath.lastIndexOf("/");
        if (index == -1)
            return null;

        //last char
        if (index == filepath.length() - 1)
            return filepath;

        return filepath.substring(0,index + 1);
    }



}
