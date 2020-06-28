package com.company;

import ru.Mikhail.Plugin;

public class Main {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        String pluginsFolder = "Plugins";
        System.out.println("pluginsFolder = " + pluginsFolder);

        PluginManager plManager = new PluginManager(pluginsFolder);
        Plugin lPlugin = plManager.load("FirstPlugin", "ru.sbtJschool.FirstPlugin");
        lPlugin.printName();

        lPlugin = plManager.load("FirstPlugin2", "ru.sbtJschool.FirstPlugin");
        lPlugin.printName();

        lPlugin = plManager.load("SecondPlugin", "ru.sbtJschool.SecondPlugin");
        lPlugin.printName();

        System.out.println("Все загруженные плагины:");
        for (Class clazz : plManager.getPluginClasses()) {
            try {
                Plugin plugin = (Plugin)clazz.newInstance();
                plugin.printName();
                plugin.doUsefull();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
