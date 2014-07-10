package com.huaijie.tacho;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by fenghb on 14-7-1.
 */
public class TachoView extends ImageView {
    private static final String TAG = "TachoView";
    private float mTotalChangeDegree;
    private float mLastSpeed;
    private float mCurrentSpeed;
    private float mIncrement;
    private float mDegree;

    private int mDuration = 200;


    private Matrix mMatrix;

    private boolean isStop = true;

    private Bitmap mPointer;
    private Bitmap mDashboard;

    private int mPointerDegrees;


    /**
     * Define a offset amount of the pointer's top-left point to the top-left point of the dashboard view
     */
    private float mXOffset;
    private float mYOffset;

    /**
     * Define a coordinate of the pointer's center point related to itself
     */
    private float mCenterX;
    private float mCenterY;

    /**
     * Define a max radius that pointer move between.
     */
    private float mMaxDeflectionAngle;


    public TachoView(Context context) {
        this(context, null);
    }

    public TachoView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.tachoViewStyle);
    }

    public TachoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Resources resources = getResources();

        final int defaultXOffset = resources.getInteger(R.integer.default_center_x_offset);
        final int defaultYOffset = resources.getInteger(R.integer.default_center_y_offset);
        final Drawable defaultPointer = resources.getDrawable(R.drawable.dashboard_pointer);
        final Drawable defaultDashboard = resources.getDrawable(R.drawable.dashboard);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TachoView, defStyle, 0);

        mXOffset = typedArray.getInteger(R.styleable.TachoView_x_offset, defaultXOffset);
        Log.d(TAG, String.valueOf(mXOffset));
        mYOffset = typedArray.getInteger(R.styleable.TachoView_y_offset, defaultYOffset);
        mPointer = BitmapFactory.decodeResource(resources, R.drawable.dashboard_pointer);
        mDashboard = BitmapFactory.decodeResource(resources, R.drawable.dashboard);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMatrix = new Matrix();
        mMatrix.setRotate(0, 0, 0);
        mMatrix.postTranslate(0, 0);
        canvas.drawBitmap(mDashboard, mMatrix, null);
        canvas.drawBitmap(mPointer, mMatrix, null);
    }
}
