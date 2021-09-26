package ru.gb.androidstart.calculator;

import android.os.Bundle;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {
    private RadioButton dayRadioButton;
    private RadioButton nightRadioButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initializeViews();
        buttonsSetup();
        setClickListeners();
    }

    private void initializeViews() {
        dayRadioButton = findViewById(R.id.day_theme_radio_button);
        nightRadioButton = findViewById(R.id.night_theme_radio_button);
    }

    private void buttonsSetup() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            dayRadioButton.setChecked(true);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            nightRadioButton.setChecked(true);
        }
    }

    private void setClickListeners() {
        dayRadioButton.setOnClickListener(v -> changeTheme(dayRadioButton));
        nightRadioButton.setOnClickListener(v -> changeTheme(nightRadioButton));
    }

    private void changeTheme(RadioButton radioButton) {
        switch (radioButton.getId()) {
            case R.id.day_theme_radio_button:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.night_theme_radio_button:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
}
