package com.richard.app;

import android.os.Bundle; 
import com.getcapacitor.BridgeActivity;
import com.richard.app.MockLocationsPlugin;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPlugin(MockLocationsPlugin.class);
        registerPlugin(VerifyAutomaticDateTime.class);
    }
 }
