package com.zhd.admin.androldlrden.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.zhd.admin.androldlrden.R;

import java.util.ArrayList;

/**
 * @author zhangfan
 * @desc 按钮水波纹效果
 */

public class WaterRippleView extends android.support.v7.widget.AppCompatTextView {
    /**
     * 波纹生成时的半径
     */
    private float mWaveRadiusMin;
    /**
     * 波纹消失前的半径
     */
    private float mWaveRadiusMax;
    /**
     * 每条波纹持续时间
     */
    private long mWaveDuration;
    private Paint mPaint;
    /**
     * 中间圆圈的画笔
     */
    private Paint mCenterCirclePaint;
    /**
     * 画笔是否为stroke模式（即线条）
     */
    private boolean stroke = false;
    /**
     * 波纹颜色
     */
    private int mWaveColor;
    /**
     * 波纹停止的颜色
     */
    private int mWaveStopColor;
    /**
     * 波纹动画效果
     */
    private Interpolator mInterpolator = new DecelerateInterpolator();
    private boolean mIsRunning;
    private int mStopCircleColor;
    private int mCenterCircleRadius;
    /**
     * 动画集合
     */
    private ArrayList<ValueAnimator> mAnimators;

    public WaterRippleView(Context context) {
        this(context, null);
    }

    public WaterRippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterRippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //执行初始化
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaterRippleView, 0, defStyleAttr);
        mWaveColor = typedArray.getColor(R.styleable.WaterRippleView_color, Color.BLUE);
        mStopCircleColor = getResources().getColor(R.color.income_top);//设置停止状态下的颜色--默认值
        mWaveStopColor = typedArray.getColor(R.styleable.WaterRippleView_stopColor, Color.BLUE);//设置停止状态下的额颜色--xml
        mWaveDuration = typedArray.getInteger(R.styleable.WaterRippleView_duration, 3000);
        stroke = typedArray.getBoolean(R.styleable.WaterRippleView_stroke, false);//是否开启水波纹效果
        typedArray.recycle();//释放掉TypedArray
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        mPaint.setDither(true);

        mPaint.setStrokeWidth(3);//设置线的粗细
        mAnimators = new ArrayList<>();
        mCenterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCenterCirclePaint.setStyle(Paint.Style.FILL);
        mCenterCirclePaint.setColor(mStopCircleColor);
        if (stroke) {
            mPaint.setStyle(Paint.Style.STROKE);
        } else {
            mPaint.setStyle(Paint.Style.FILL);
        }
    }

    private ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(mWaveRadiusMin, mWaveRadiusMax);
        animator.setDuration(mWaveDuration);
        animator.setRepeatCount(-1);
        animator.setInterpolator(mInterpolator);
        return animator;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        mCenterCircleRadius = (int) (size / 2 * 0.8);
        mWaveRadiusMin = mCenterCircleRadius;
        mWaveRadiusMax = size / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int cX = getMeasuredWidth() / 2;
        int cY = getMeasuredHeight() / 2;
        if (mIsRunning) {
            if (mAnimators.size() > 0) {
                for (ValueAnimator mAnimator : mAnimators) {
                    if (mAnimator != null) {
                        float animatorValue = (float) mAnimator.getAnimatedValue();
                        //设置透明度
                        int alpha = getAlpha(animatorValue);
                        mPaint.setAlpha(alpha);
                        //画水波纹
                        canvas.drawCircle(cX, cY, animatorValue, mPaint);
                    }
                }
            }
        }
        canvas.drawCircle(cX, cY, mCenterCircleRadius, mCenterCirclePaint);
        postInvalidate();
        super.onDraw(canvas);
    }

    private void createAnimators() {
        for (int i = 0; i < 3; i++) {
            mAnimators.add(createAnimator());
        }
    }

    //开启动画
    public void start() {
        if (mIsRunning) {
            return;
        }
        mIsRunning = true;
        mPaint.setColor(mWaveColor);
        mCenterCirclePaint.setColor(mWaveColor);
        if (mAnimators.size() == 0) {
            createAnimators();
        }
        final int size = mAnimators.size();
        for (int i = 0; i < size; i++) {
            final int index = i;
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mIsRunning && index < size) {
                        mAnimators.get(index).start();
                    }
                }
            }, (long) (mWaveDuration * index * 1f / size));
        }
        postInvalidate();
    }

    //是否开启水波纹
    public boolean isStart() {
        for (ValueAnimator mAnimator : mAnimators) {
            if (mAnimator == null && !mAnimator.isRunning()) {
                return false;
            }
        }
        return true;
    }

    //关闭动画
    public void stop() {
        mIsRunning = false;
        mPaint.setColor(mStopCircleColor);
        mCenterCirclePaint.setColor(mStopCircleColor);
        for (ValueAnimator mAnimator : mAnimators) {
            if (mAnimator != null) {
                mAnimator.cancel();

            }
        }
        mAnimators.clear();
    }

    //设置水波纹颜色
    public void setColor(int color) {
        mWaveColor = color;
        postInvalidate();
    }

    //设计水波纹持续时间
    public void setDuration(long duration) {
        mWaveDuration = duration;
        postInvalidate();
    }

    //是否画笔stroke
    public void setStroke(boolean stroke) {
        this.stroke = stroke;
        postInvalidate();
    }

    public boolean isStroke() {
        return stroke;
    }


    //获取水波纹透明度
    private int getAlpha(float mRadius) {
        int alpha = 1;
        if (mWaveRadiusMax > 0) {
            alpha = (int) ((1 - (mRadius - mWaveRadiusMin) / (mWaveRadiusMax - mWaveRadiusMin)) * 127);
        }
        return alpha;
    }

}
