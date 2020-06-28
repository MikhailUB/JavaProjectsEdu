package com.company;

import ru.Mikhail.Plugin;
import java.io.*;
import java.net.*;
import java.util.*;

public class PluginManager {
    private final String pluginRootDirectory;
    private List<Class> pluginClasses = new ArrayList<>();

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws IllegalAccessException, InstantiationException {
        File[] jars = getJars(pluginRootDirectory + "\\" + pluginName);
        for (File jarFile : jars) {
            try {
                URL jarUrl = jarFile.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl});
                Class<?> plClass = classLoader.loadClass(pluginClassName);
                pluginClasses.add(plClass);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return (Plugin) pluginClasses.get(pluginClasses.size() - 1).newInstance();
    }

    private File[] getJars(String path)
    {
        File pluginDir = new File(path);
        File[] jars = pluginDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".jar");
            }
        });
        return jars != null ? jars : new File[0];
    }

    public List<Class> getPluginClasses() {
        return pluginClasses;
    }
}
