package com.mercadolibre.android.mlbusinesscomponents.components.common.dividingline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import com.mercadolibre.android.mlbusinesscomponents.R;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.ScaleUtils;

public class MLBusinessDividingLineView extends View {

    private final Paint linePaint = new Paint();
    private boolean hasTriangle;

    private static final int DEFAULT_SIZE = 11;

    public MLBusinessDividingLineView(final Context context) {
        super(context);
        initDividingLineView(context, null);
    }

    public MLBusinessDividingLineView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        initDividingLineView(context, attrs);
    }

    public MLBusinessDividingLineView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDividingLineView(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MLBusinessDividingLineView(final Context context, @Nullable final AttributeSet attrs,
        final int defStyleAttr,
        final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDividingLineView(context, attrs);
    }

    private void initDividingLineView(final Context context, @Nullable final AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MLBusinessDividingLineView,
            0, 0);

        hasTriangle =
            typedArray.getBoolean(R.styleable.MLBusinessDividingLineView_hasTriangle, false);

        typedArray.recycle();

        final int dividerColor = ContextCompat.getColor(context, R.color.divider_line_gray);
        linePaint.setColor(dividerColor);
        linePaint.setStrokeWidth(ScaleUtils.getPxFromDp(context, 1f));
        setBackgroundColor(ContextCompat.getColor(context, R.color.ui_meli_white));
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, measureHeight(heightMeasureSpec));
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int measurement = preferred;

        switch (MeasureSpec.getMode(measureSpec)) {
        case MeasureSpec.EXACTLY:
            measurement = specSize;
            break;
        case MeasureSpec.AT_MOST:
            measurement = Math.min(preferred, specSize);
        case MeasureSpec.UNSPECIFIED:
            break;
        }

        return measurement;
    }

    private int measureHeight(int measureSpec) {
        int preferred = (int) ScaleUtils.getPxFromDp(getContext(), DEFAULT_SIZE);
        return getMeasurement(measureSpec, preferred);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        float averageHeight;

        if (hasTriangle) {
            averageHeight = 0f;
            final float mediumLength = getWidth() / 2f;

            final float maximumTrianglePoint =
                (averageHeight + ScaleUtils.getPxFromDp(getContext(), 10));
            final float factor = ScaleUtils.getPxFromDp(getContext(), 7.67f);

            // Draw triangle
            canvas.drawLine((mediumLength - factor), averageHeight, mediumLength,
                maximumTrianglePoint, linePaint);
            canvas.drawLine(mediumLength, maximumTrianglePoint, (mediumLength + factor),
                averageHeight, linePaint);

            linePaint.setStrokeWidth(ScaleUtils.getPxFromDp(getContext(), 1.5f));

            //Initial line
            canvas.drawLine(0, averageHeight, (mediumLength - factor), averageHeight, linePaint);

            //Final line
            canvas.drawLine((mediumLength + factor), averageHeight, getWidth(), averageHeight,
                linePaint);
        } else {
            averageHeight = getHeight() / 2;
            canvas.drawLine(0, averageHeight, getWidth(), averageHeight, linePaint);
        }
    }
}