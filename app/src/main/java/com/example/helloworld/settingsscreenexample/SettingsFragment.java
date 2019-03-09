package com.example.helloworld.settingsscreenexample;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {

        //Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences , rootKey);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {



        //saving boolean data

        if(key.equals("notificationsKey")){
            boolean on = sharedPreferences.getBoolean("notificationsKey", false);
            Log.e("SettingsFragment" , "Value of key : " + on);
            if(on){
                Toast.makeText(getActivity(), "Checkbox enabled", Toast.LENGTH_SHORT).show();
               Log.e("SettingsFragment" , "Checkbox enabled");
            }else {
                Toast.makeText(getActivity(), "checkbox unchecked", Toast.LENGTH_SHORT).show();
               Log.e("SettingsFragment" , "checkbox unchecked");
            }
            //saving string data
        }else if(key.equals("nameKey")){
            String name = sharedPreferences.getString("nameKey"," ");
            Toast.makeText(getActivity(), "Your name is :"+name, Toast.LENGTH_SHORT).show();
        }else if(key.equals("volumeKey")){
            int volumeLevel = sharedPreferences.getInt("volumeKey",0);
            Toast.makeText(getActivity(), "Volume Level is:"+String.valueOf(volumeLevel), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
