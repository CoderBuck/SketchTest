package me.buck.sketchtest;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panpf.sketch.Sketch;
import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.zoom.ImageZoomer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.next)   Button          mNext;
    @BindView(R.id.rotate) Button          mRotate;
    @BindView(R.id.image)  SketchImageView mImage;

    int[] imgList = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3
    };

    private int curDisplayImgIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mImage.setZoomEnabled(true);
        mImage.getOptions().setDecodeGifImage(true);
        Sketch.with(this).displayFromResource(imgList[0], mImage)
                .decodeGifImage()
                .commit();
    }

    @OnClick(R.id.next)
    public void onNextClicked() {
        curDisplayImgIndex++;
        curDisplayImgIndex = curDisplayImgIndex % 3;
        Sketch.with(this).displayFromResource(imgList[curDisplayImgIndex], mImage)
                .decodeGifImage()
                .commit();
    }

    @OnClick(R.id.rotate)
    public void onRotateClicked() {
        ImageZoomer zoomer = mImage.getZoomer();
        if (zoomer != null) {
            float scale = zoomer.getZoomScale();
            Log.d(TAG, "onRotateClicked: scale = " + scale);
            zoomer.setZoomDuration(500);
            zoomer.rotateBy(90);
            zoomer.zoom(scale);
        }
    }
}
