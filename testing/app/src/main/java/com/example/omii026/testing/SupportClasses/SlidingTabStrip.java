package com.example.omii026.testing.SupportClasses;/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.example.omii026.testing.R;


class SlidingTabStrip extends LinearLayout {

    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 0;
    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 0x26;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 3;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0xFF33B5E5;

    private final int mBottomBorderThickness;
    private final Paint mBottomBorderPaint;

    private final int mSelectedIndicatorThickness;
    private final Paint mSelectedIndicatorPaint;

    private final int mDefaultBottomBorderColor;
    private float density=0;
    private Bitmap bitmap1 = null, bitmap2 = null, bitmap3 = null;

    private int mSelectedPosition;
    private float mSelectionOffset;

    private SlidingTabLayout.TabColorizer mCustomTabColorizer;
    private final SimpleTabColorizer mDefaultTabColorizer;
    private Paint paint;

    SlidingTabStrip(Context context) {
        this(context, null);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.chat_activity_icon);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.chat_tab_icon);
        bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.chat_projects);
        density = getResources().getDisplayMetrics().density;
        Matrix matrix = new Matrix();
        matrix.postSkew(0.5f, 0f);
//            bitmap.setWidth((int) (45*getResources().getDisplayMetrics().density));
        bitmap1 = Bitmap.createScaledBitmap(bitmap1, Math.round(20 * density), Math.round(20 * density), true);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, Math.round(20 * density), Math.round(20 * density), true);
        bitmap3 = Bitmap.createScaledBitmap(bitmap3, Math.round(20 * density), Math.round(20 * density), true);

        bitmap1 = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), matrix, true);
        bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
        bitmap3 = Bitmap.createBitmap(bitmap3, 0, 0, bitmap3.getWidth(), bitmap3.getHeight(), matrix, true);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }

    SlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        final float density = getResources().getDisplayMetrics().density;

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorForeground, outValue, true);
        final int themeForegroundColor = outValue.data;

        mDefaultBottomBorderColor = setColorAlpha(themeForegroundColor,
                DEFAULT_BOTTOM_BORDER_COLOR_ALPHA);

        mDefaultTabColorizer = new SimpleTabColorizer();
        mDefaultTabColorizer.setIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR);

        mBottomBorderThickness = (int) (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * density);
        mBottomBorderPaint = new Paint();
        mBottomBorderPaint.setColor(mDefaultBottomBorderColor);

//        mSelectedIndicatorThickness = (int) (SELECTED_INDICATOR_THICKNESS_DIPS * density);
        mSelectedIndicatorThickness = (int) (8 * density);
        mSelectedIndicatorPaint = new Paint();
    }

    void setCustomTabColorizer(SlidingTabLayout.TabColorizer customTabColorizer) {
        mCustomTabColorizer = customTabColorizer;
        invalidate();
    }

    void setSelectedIndicatorColors(int... colors) {
        // Make sure that the custom colorizer is removed
        mCustomTabColorizer = null;
        mDefaultTabColorizer.setIndicatorColors(colors);
        invalidate();
    }

    void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int height = getHeight();
        final int childCount = getChildCount();
        final SlidingTabLayout.TabColorizer tabColorizer = mCustomTabColorizer != null
                ? mCustomTabColorizer
                : mDefaultTabColorizer;

        // Thick colored underline below the current selection
        if (childCount > 0) {
            View selectedTitle = getChildAt(mSelectedPosition);
            int left = selectedTitle.getLeft();
            int right = selectedTitle.getRight();
            int color = tabColorizer.getIndicatorColor(mSelectedPosition);

            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
                int nextColor = tabColorizer.getIndicatorColor(mSelectedPosition + 1);
                if (color != nextColor) {
                    color = blendColors(nextColor, color, mSelectionOffset);
                }

                // Draw the selection partway between the tabs
                View nextTitle = getChildAt(mSelectedPosition + 1);
                left = (int) (mSelectionOffset * nextTitle.getLeft() +
                        (1.0f - mSelectionOffset) * left);
                right = (int) (mSelectionOffset * nextTitle.getRight() +
                        (1.0f - mSelectionOffset) * right);
            }

            mSelectedIndicatorPaint.setColor(color);
            canvas.skew(-0.5f, 0);
            paint.setTextSkewX(0.5f);
            paint.setTextSize(26f);
//            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            paint.setColor(Color.parseColor("#ffffff"));
//            Log.d("POSITION", "" + mSelectedPosition);
            if (mSelectedPosition == 0) {
                canvas.drawRect(left - 10, 0, right + 10,
                        height, mSelectedIndicatorPaint);
            } else if (mSelectedPosition == 1) {
                canvas.drawRect(left + 20, 0, right + 20,
                        height, mSelectedIndicatorPaint);
            } else if (mSelectedPosition == 2) {
                canvas.drawRect(left + 20, 0, right + 27,
                        height, mSelectedIndicatorPaint);
            }
            canvas.drawBitmap(bitmap1, (getWidth() / 6) -35*density, 10*density, mSelectedIndicatorPaint);
            canvas.drawBitmap(bitmap2, (getWidth() / 2) - 25*density, 10*density, mSelectedIndicatorPaint);
            canvas.drawBitmap(bitmap3, (getWidth() - getWidth() / 6) - 40*density, 10*density, mSelectedIndicatorPaint);
            canvas.drawText("Activity", (getWidth() / 6) , 7*(density)+ getHeight() / 2, paint);
            canvas.drawText("Chat",  (getWidth() / 2)+15, 7*(density)+ getHeight() / 2, paint);
            canvas.drawText("Projects", (getWidth() - getWidth() / 6), 7*(density) + getHeight() / 2, paint);

        }
        // Thin underline along the entire bottom edge
        canvas.drawRect(0, height - mBottomBorderThickness, getWidth(), height, mBottomBorderPaint);

    }

    /**
     * Set the alpha value of the {@code color} to be the given {@code alpha} value.
     */
    private static int setColorAlpha(int color, byte alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    /**
     * Blend {@code color1} and {@code color2} using the given ratio.
     *
     * @param ratio of which to blend. 1.0 will return {@code color1}, 0.5 will give an even blend,
     *              0.0 will return {@code color2}.
     */
    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }

    private static class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {
        private int[] mIndicatorColors;

        @Override
        public final int getIndicatorColor(int position) {
            return mIndicatorColors[position % mIndicatorColors.length];
        }

        void setIndicatorColors(int... colors) {
            mIndicatorColors = colors;
        }
    }
}