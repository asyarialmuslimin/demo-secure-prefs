package com.example.demosecureprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

public class SharedPreferenceHelper {
    static SharedPreferences getNoEncryptedSpf(Context context) {
        return context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    @Nullable
    static SharedPreferences getEncryptedSpf(Context context) {
        try {
            KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                    MasterKey.DEFAULT_MASTER_KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build();
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyGenParameterSpec(spec)
                    .build();
            return EncryptedSharedPreferences.create(
                    context,
                    "session",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
