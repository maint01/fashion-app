package com.demo.fashion.service.util;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bietdoicamtu.
 *         Created on 4/23/2018
 */
public class FileUploadUtils {
    public static String saveFile(String pathUpload, byte[] fileData, String fileName) throws IOException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS");
        String folderUpload = pathUpload + sdfDate.format(new Date());
        File folder = new File(folderUpload);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String path = folder.getAbsolutePath()+ File.separator + fileName.substring(0, fileName.indexOf(".")) +
            "_" + sdfTime.format(new Date()) + fileName.substring(fileName.indexOf("."), fileName.length());
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(fileData);
        fos.close();
        return file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("content")-1);
    }
}
