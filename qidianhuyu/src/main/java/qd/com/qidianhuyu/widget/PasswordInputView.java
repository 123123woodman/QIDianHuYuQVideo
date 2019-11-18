package qd.com.qidianhuyu.widget;

import android.content.Context;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import qd.com.library.utils.EditTextUtils;
import qd.com.qidianhuyu.R;

public class PasswordInputView extends InputView {

    private ImageView mIvDeleteIcon;

    private boolean isHidePwdMode = true;

    public PasswordInputView(Context context) {
        super(context);
    }

    public PasswordInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initViews(AttributeSet attrs) {
        super.initViews(attrs);
        getEditText().setHint("请输入验证码");
        EditTextUtils.setEditTextMaxLength(getEditText(), 4);
    }

    /**
     * 左icon
     * @return
     */
    @Override
    protected ImageView[] getLeftIcons() {
        return null;
    }

    /**
     * 右icon
     * @return
     */
    @Override
    protected ImageView[] getRightIcons() {
        int paddingDelete = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getContext().getResources().getDisplayMetrics());
        mIvDeleteIcon = new ImageView(getContext());
        mIvDeleteIcon.setVisibility(INVISIBLE);
        mIvDeleteIcon.setPadding(paddingDelete, paddingDelete, paddingDelete, paddingDelete);
        mIvDeleteIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mIvDeleteIcon.setImageResource(R.drawable.ic_delete);
        mIvDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditText().setText("");
            }
        });
        return new ImageView[]{mIvDeleteIcon};
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        super.onFocusChange(v, hasFocus);
        if (hasFocus) {
            if (isEmpty()) {
                mIvDeleteIcon.setVisibility(INVISIBLE);
            } else {
                mIvDeleteIcon.setVisibility(VISIBLE);
            }
        } else {
            mIvDeleteIcon.setVisibility(INVISIBLE);
        }
    }

    /**
     * 验证码是否需要隐藏
     * @param isHidePwdMode
     */
    private void changePwdHideMode(boolean isHidePwdMode) {
        this.isHidePwdMode = isHidePwdMode;
        if (isHidePwdMode) {
            //隐藏密码
            getEditText().setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            //显示密码
            getEditText().setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        EditTextUtils.setTextWithSelection(getEditText(), getEditText().getText().toString());
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        if (isEmpty()) {
            mIvDeleteIcon.setVisibility(INVISIBLE);
        } else {
            mIvDeleteIcon.setVisibility(VISIBLE);
        }
    }
}
