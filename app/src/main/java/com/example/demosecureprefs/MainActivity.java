package com.example.demosecureprefs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.demosecureprefs.databinding.ActivityMainBinding;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSimpan.setOnClickListener(v -> {
            SharedPreferences prefs = SharedPreferenceHelper.getNoEncryptedSpf(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();

            String name = Objects.requireNonNull(binding.txtNama.getText()).toString();

            editor.putString("user1", name);
            editor.apply();

            binding.txtNama.setText("");
        });

        binding.btnSimpanEnkripsi.setOnClickListener(v -> {
            SharedPreferences spf = SharedPreferenceHelper.getEncryptedSpf(getApplicationContext());
            SharedPreferences.Editor edit = Objects.requireNonNull(spf).edit();

            String name = Objects.requireNonNull(binding.txtNama.getText()).toString();

            edit.putString("user2", name);
            edit.apply();

            binding.txtNama.setText("");
        });

        binding.btnChangeActivity.setOnClickListener(v -> {
            startActivity(new Intent(this, ShowPrefActivity.class));
        });

    }
}
