
package com.virtualcams.clone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class Cloner {

    private Context context;

    public Cloner(Context context) {
        this.context = context;
    }

    public void launchClone(String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        } else {
            Toast.makeText(context, "Не удалось найти приложение: " + packageName, Toast.LENGTH_SHORT).show();
        }
    }
}
