package com.example.think.eduhelper;

import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class ProfileSetting extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        if (findViewById(R.id.fragment_container_preference) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getFragmentManager().beginTransaction().replace(R.id.fragment_container_preference, new Profile()).commit();
        }
        toolbar = findViewById(R.id.default_toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public static class Profile extends PreferenceFragment {
        private Preference editTextPreference;
        public SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.profilepreference);
            preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        android.preference.Preference preference = findPreference(key);
                        if(!(preference instanceof MultiSelectListPreference)) {
                            preference.setSummary(sharedPreferences.getString(key, "message perconversion"));
                        }
                }
            };
        }


        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
            readInformation();
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        }

        public void readInformation(){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        }
    }
}

