package com.example.projekat;

import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.SwitchPreference;
import android.widget.Toast;

public class SettingsActivity extends PreferenceActivity {

    SharedPreferences.Editor editor;

    SwitchPreference orderBy;
    EditTextPreference refreshRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        editor = getSharedPreferences(MyApplication.PREFERENCES, MODE_PRIVATE).edit();

        orderBy = (SwitchPreference) findPreference("switch_mails_order");
        refreshRate = (EditTextPreference) findPreference("edit_mails_refresh");

        orderBy.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (orderBy.isChecked()){
                    editor.putBoolean("orderByDesc", false);
                    editor.apply();

                    orderBy.setChecked(false);
                } else {
                    editor.putBoolean("orderByDesc", true);
                    editor.apply();

                    orderBy.setChecked(true);
                }

                return false;
            }
        });

        refreshRate.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int number = Integer.parseInt(newValue.toString()) * 1000;

                editor.putInt("refreshRate", number);
                editor.apply();

                refreshRate.setText(newValue.toString());

                Toast.makeText(
                        SettingsActivity.this,
                        "Success!",
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}
