package com.vini.grow;
import android.content.Context;

/*
    Singleton class used to retrieve resourses from string.XML
    How to use: set the context with getApplicationContext() as soon as possible in order to set the
    context before other methods are used
 */
class ResourcesProvider {
    private static final ResourcesProvider ourInstance = new ResourcesProvider();
    private Context applicationContext;

    static ResourcesProvider getInstance() {
        return ourInstance;
    }

    private ResourcesProvider() {
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String getStringResource(int resourceID){
        return applicationContext.getString(resourceID);
    }
}
