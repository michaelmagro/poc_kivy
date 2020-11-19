package org.kivy.android;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import org.test.myapp.ServiceService;

public class GenericBroadcastReceiver extends BroadcastReceiver {

    GenericBroadcastReceiverCallback listener;

    public GenericBroadcastReceiver(GenericBroadcastReceiverCallback listener) {
        super();
        this.listener = listener;
    }

    public void onReceive(Context context, Intent intent) {
        String package_root = context.getFilesDir().getAbsolutePath();
        String app_root =  package_root + "/app";
        Intent ix = new Intent(context, ServiceService.class);
        ix.putExtra("androidPrivate", package_root);
        ix.putExtra("androidArgument", app_root);
        ix.putExtra("serviceEntrypoint", "./service/main.py");
        ix.putExtra("pythonName", "myservice");
        ix.putExtra("pythonHome", app_root);
        ix.putExtra("pythonPath", package_root);
        ix.putExtra("pythonServiceArgument", app_root+":"+app_root+"/lib");
        ix.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startService(ix);
    }
}
