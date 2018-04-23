package com.demo.fashion.web.rest.vm;

/**
 * @author bietdoicamtu.
 *         Created on 4/23/2018
 */
public class FileDTO {
    private String fileName;
    private byte[] fileData;
    private String contentType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
