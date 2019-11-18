package qd.com.qidianhuyu.widget;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import qd.com.library.utils.EditTextUtils;
import qd.com.qidianhuyu.R;

public class AccountInputView extends InputView {

    private ImageView mIvDeleteIcon;

    public AccountInputView(Context context) {
        super(context);
    }

    public AccountInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initViews(AttributeSet attrs) {
        super.initViews(attrs);
        getEditText().setHint("请输入手机号");
        EditTextUtils.changeCursorColor(getEditText(), getResources().getColor(R.color.gray));
        EditTextUtils.setEditTextMaxLength(getEditText(), 11);
    }

    /**
     * 左icon
     * @return
     */
    @Override
    protected ImageView[] getLeftIcons() {
        return null;
    }

    @Override
    protected ImageView[] getRightIcons() {
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getContext().getResources().getDisplayMetrics());
        mIvDeleteIcon = new ImageView(getContext());
        mIvDeleteIcon.setVisibility(INVISIBLE);
        mIvDeleteIcon.setPadding(padding, padding, padding, padding);
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
