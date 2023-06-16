package me.imsergioh.newgencore.instance.exception;

public class PluginException extends Exception {

    public PluginException(String errMessage) {
        super(errMessage);
        printStackTrace();
    }

}
