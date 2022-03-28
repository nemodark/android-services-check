package com.richard.app;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "VerifyAutomaticDateTime")
public class VerifyAutomaticDateTime extends Plugin{


    @PluginMethod()
    public void isAutomaticChecked(PluginCall call) throws Settings.SettingNotFoundException {
        Integer dateTime, timezone;

        JSObject result = new JSObject();

        boolean isAutomatic = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dateTime = Settings.Global.getInt(getContext().getContentResolver(), Settings.Global.AUTO_TIME, 0);
            timezone = Settings.Global.getInt(getContext().getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);
        } else {
            dateTime = Settings.System.getInt(
                    getContext().getContentResolver(),
                    Settings.System.AUTO_TIME,
                    0
            );

            timezone = Settings.System.getInt(
                    getContext().getContentResolver(),
                    Settings.System.AUTO_TIME_ZONE,
                    0
            );
        }

        if (dateTime == 0 || timezone == 0) {
            result.put("is_enabled", false);
            isAutomatic = false;
        } else {
            result.put("is_enabled", true);
            isAutomatic = true
        }

        call.resolve(isAutomatic);
    }




}
