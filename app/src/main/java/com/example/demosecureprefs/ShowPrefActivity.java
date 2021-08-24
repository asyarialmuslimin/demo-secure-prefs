package com.example.demosecureprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.demosecureprefs.databinding.ActivityShowPrefBinding;

import java.util.Objects;

public class ShowPrefActivity extends AppCompatActivity {

    ActivityShowPrefBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPrefBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // No Encrypted SharedPreference
        SharedPreferences spf = SharedPreferenceHelper.getNoEncryptedSpf(getApplicationContext());
        String noEncryptedData = spf.getString("user1", "Not Found");
        binding.txtNoEncrypt.setText(noEncryptedData);

        // EncryptedSharedPreference
        SharedPreferences encryptedSpf = SharedPreferenceHelper.getEncryptedSpf(getApplicationContext());
        String encryptedData = Objects.requireNonNull(encryptedSpf).getString("user2", "Not Found");
        binding.txtEncrypt.setText(encryptedData);
    }
}