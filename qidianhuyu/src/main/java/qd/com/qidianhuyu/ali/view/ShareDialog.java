package qd.com.qidianhuyu.ali.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import qd.com.qidianhuyu.R;
import qd.com.qidianhuyu.ali.view.base.BaseChooser;

/**
 * 分享dialog
 */
public class ShareDialog extends BaseChooser {
    private LinearLayout mDeleteView;
    private boolean isDeleteVisible;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_share, container);
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        view.findViewById(R.id.alivc_little_iv_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mItemSelectedListenr!=null){
                    mItemSelectedListenr.onDownloadClick();
                }
            }
        });
        view.findViewById(R.id.alivc_little_tv_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mItemSelectedListenr != null) {
                    mItemSelectedListenr.onDownloadClick();
                }
            }
        });
        mDeleteView = view.findViewById(R.id.alivc_little_share_delete);
        mDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mItemSelectedListenr != null) {
                    mItemSelectedListenr.onDeleteClick();
                }
            }
        });
        if (isDeleteVisible) {
            mDeleteView.setVisibility(View.VISIBLE);
        } else {
            mDeleteView.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.QUDemoFullStyle);

    }

    private OnItemSelectedListener mItemSelectedListenr;
    public interface OnItemSelectedListener {
        void onDownloadClick();

        void onDeleteClick();
    }

    public void setItemSelectedListenr(OnItemSelectedListener mItemSelectedListenr) {
        this.mItemSelectedListenr = mItemSelectedListenr;
    }
    public void setDeleteVisible(boolean isVisible) {
        isDeleteVisible = isVisible;
        if (mDeleteView == null) {
            return;
        }
        if (isVisible) {
            mDeleteView.setVisibility(View.VISIBLE);
        } else {
            mDeleteView.setVisibility(View.GONE);
        }

    }
}
