
package com.virtualcams.camera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VirtualCameraService {

    private Camera camera;
    private SurfaceView dummySurfaceView;
    private SurfaceHolder holder;

    public VirtualCameraService(Context context) {
        dummySurfaceView = new SurfaceView(context);
        holder = dummySurfaceView.getHolder();
    }

    public void startCamera() {
        camera = Camera.open(0);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }
}
