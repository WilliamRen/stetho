package com.facebook.stetho.sample;

import java.util.ArrayList;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.DumperPluginsProvider;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;

public class SampleApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    final Context context = this;
    Stetho.initialize(
        Stetho.newInitializerBuilder(context)
            .enableDumpapp(new SampleDumperPluginsProvider(context))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
            .build());
  }

  private static class SampleDumperPluginsProvider implements DumperPluginsProvider {
    private final Context mContext;

    public SampleDumperPluginsProvider(Context context) {
      mContext = context;
    }

    @Override
    public Iterable<DumperPlugin> get() {
      ArrayList<DumperPlugin> plugins = new ArrayList<DumperPlugin>();
      for (DumperPlugin defaultPlugin : Stetho.defaultDumperPluginsProvider(mContext).get()) {
        plugins.add(defaultPlugin);
      }
      plugins.add(new HelloWorldDumperPlugin());
      return plugins;
    }
  }
}
