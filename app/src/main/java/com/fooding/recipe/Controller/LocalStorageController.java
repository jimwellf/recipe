package com.fooding.recipe.Controller;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalStorageController {
    Activity activity;

    public LocalStorageController(Activity activity) {
        this.activity = activity;
    }

    public Boolean write(String fileName, String text) throws IOException {
        File file = new File(activity.getFilesDir(), fileName);
        FileOutputStream stream = null;

        try {
            stream = new FileOutputStream(file);
            stream.write(text.getBytes());
            stream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String read(String fileName) throws IOException {
        File file = new File(activity.getFilesDir(), fileName);
        int length = (int) file.length();
        byte[] bytes = new byte[length];

        try (FileInputStream in = new FileInputStream(file)) {
            int a = in.read(bytes);
            Log.d("result", "a: " + a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String(bytes);
    }

}
