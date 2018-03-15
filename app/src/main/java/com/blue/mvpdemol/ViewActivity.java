package com.blue.mvpdemol;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    public View normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        normal = findViewById(R.id.normal);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //enterPictureInPictureMode(new PictureInPictureParams.Builder().build());
        }
    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        if (isInPictureInPictureMode){
            normal.setVisibility(View.GONE);
        }else {
            normal.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (isInPictureInPictureMode()){
                Toast.makeText(this,"continue",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btn_mini(View view) {

        WindowManager.LayoutParams attributes = getWindow().getAttributes();

        Display defaultDisplay = getWindowManager().getDefaultDisplay();

        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);

        int widthPixels = outMetrics.widthPixels;

        attributes.alpha=0;
        attributes.dimAmount=0.5f;
        attributes.width= (int) (widthPixels*0.6);
        getWindow().setAttributes(attributes);
    }
}
