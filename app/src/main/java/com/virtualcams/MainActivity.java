
package com.virtualcams;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    private static final int REQUEST_CODE_PICK_VIDEO = 102;
    private SharedPreferences prefs;
    private ImageView imageView;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("clone_settings", MODE_PRIVATE);

        imageView = findViewById(R.id.image_preview);
        videoView = findViewById(R.id.video_preview);

        Button pickImage = findViewById(R.id.pick_image_btn);
        Button pickVideo = findViewById(R.id.pick_video_btn);

        pickImage.setOnClickListener(v -> openFilePicker("image/*", REQUEST_CODE_PICK_IMAGE));
        pickVideo.setOnClickListener(v -> openFilePicker("video/*", REQUEST_CODE_PICK_VIDEO));

        checkPermissions();
    }

    private void openFilePicker(String type, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(type);
        startActivityForResult(Intent.createChooser(intent, "Выберите файл"), requestCode);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, /*@Nullable*/ Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            SharedPreferences.Editor editor = prefs.edit();
            if (requestCode == REQUEST_CODE_PICK_IMAGE) {
                editor.putString("image_uri", uri.toString());
                imageView.setImageURI(uri);
                imageView.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.GONE);
            } else if (requestCode == REQUEST_CODE_PICK_VIDEO) {
                editor.putString("video_uri", uri.toString());
                videoView.setVideoURI(uri);
                videoView.start();
                videoView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }
            editor.apply();
        }
    }
}
