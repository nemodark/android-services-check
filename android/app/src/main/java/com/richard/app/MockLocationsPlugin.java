package com.richard.app;

// import org.apache.cordova.CallbackContext;
// import org.apache.cordova.CordovaPlugin;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;

import org.json.JSONArray;
import org.json.JSONException;

@CapacitorPlugin(name = "MockLocations")
public class MockLocationsPlugin extends Plugin {
    
    @PluginMethod()
    public void checkMockLocation(PluginCall call) throws JSONException {
        boolean isMock = false;
        String action = call.getString("action");
        Location location = new Location("dummyprovider");
        JSObject result = new JSObject();
        if (action.equals("check")) {
            isMock = location.isFromMockProvider();

            result.put("is_mock", isMock);

            call.resolve(result);
        }
    }
}
