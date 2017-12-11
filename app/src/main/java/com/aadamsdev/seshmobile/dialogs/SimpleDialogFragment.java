package com.aadamsdev.seshmobile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.aadamsdev.seshmobile.utils.DialogUtils;

/**
 * Created by andrewadams on 2017-12-05
 */
public class SimpleDialogFragment extends DialogFragment {
    private static final String ARG_MESSAGE_TEXT = "messageText";
    private static final String ARG_MESSAGE_ID = "messageId";
    private static final String ARG_MODAL = "modal";
    private static final String ARG_TITLE = "title";
    private static final String ARG_TITLE_ID = "titleId";

    public static SimpleDialogFragment newInstance(int messageId) {
        return newInstance(0, messageId);
    }

    public static SimpleDialogFragment newInstance(int titleId, int messageId) {
        return newInstance(titleId, messageId, false);
    }

    public static SimpleDialogFragment newInstance(int titleId, int messageId, boolean modal) {
        Bundle args = new Bundle();
        args.putInt(ARG_MESSAGE_ID, messageId);
        args.putBoolean(ARG_MODAL, modal);
        args.putInt(ARG_TITLE_ID, titleId);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleDialogFragment newInstance(String messageText) {
        return newInstance(0, messageText);
    }

    public static SimpleDialogFragment newInstance(int titleId, String messageText) {
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE_TEXT, messageText);
        args.putInt(ARG_TITLE_ID, titleId);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleDialogFragment newInstance(String title, int messageId) {
        Bundle args = new Bundle();
        args.putInt(ARG_MESSAGE_ID, messageId);
        args.putString(ARG_TITLE, title);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleDialogFragment newInstance(String title, String messageText) {
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE_TEXT, messageText);
        args.putString(ARG_TITLE, title);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnDismissListener {
        void onDismiss(DialogInterface dialog);
    }

    private OnDismissListener mOnDismissListener;
    private String mMessageText;
    private int mMessageId;
    private boolean mModal;
    private String mTitle;
    private int mTitleId;

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mMessageText = bundle.getString(ARG_MESSAGE_TEXT, "");
        mMessageId = bundle.getInt(ARG_MESSAGE_ID);
        mModal = mOnDismissListener != null || bundle.getBoolean(ARG_MODAL);
        mTitle = bundle.getString(ARG_TITLE, "");
        mTitleId = bundle.getInt(ARG_TITLE_ID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
            .setPositiveButton(android.R.string.ok, null);

        if (mTitleId != 0) {
            builder.setTitle(mTitleId);
        } else {
            builder.setTitle(mTitle);
        }

        if (mMessageId != 0) {
            builder.setMessage(mMessageId);
        } else {
            builder.setMessage(mMessageText);
        }

        Dialog dialog = builder.create();
        if (mModal) {
            DialogUtils.setModal(dialog);
        }
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(dialog);
        }
    }
}