package com.demo.fashion.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String folderUpload;

    public String getFolderUpload() {
        return folderUpload;
    }

    public void setFolderUpload(String folderUpload) {
        this.folderUpload = folderUpload;
    }
}
