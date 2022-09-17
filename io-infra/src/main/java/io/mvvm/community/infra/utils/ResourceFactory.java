package io.mvvm.community.infra.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: Pan
 **/
public class ResourceFactory {

    public static InputStream getStream(String classPath) throws IOException {
        Resource resource = getResource(classPath);
        return resource.getInputStream();
    }

    public static File getFile(String classPath) throws IOException {
        Resource resource = getResource(classPath);
        return resource.getFile();
    }

    public static Resource getResource(String classPath) {
        ResourceLoader loader = new DefaultResourceLoader();
        return getResourceLoader().getResource(classPath);
    }

    public static ResourceLoader getResourceLoader() {
        return new DefaultResourceLoader();
    }
}