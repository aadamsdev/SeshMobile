package com.aadamsdev.seshmobile.dialogs;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aadamsdev.seshmobile.R;
import com.aadamsdev.seshmobile.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andrewadams on 2017-12-05
 */
public class ProgressDialogFragment extends DialogFragment {
    private static final String ARG_MESSAGE = "message";
    private static final String ARG_MESSAGE_ID = "messageId";

    private String mMessage;
    private int mMessageId;

    @BindView(R.id.progress_label)
    TextView mProgressLabel;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMessage = getArguments().getString(ARG_MESSAGE);
        mMessageId = getArguments().getInt(ARG_MESSAGE_ID);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_progress, null);
        ButterKnife.bind(this, view);

//        mProgressLabel = (TextView)view.findViewById(R.id.progress_label);
        if (mMessage != null) {
            mProgressLabel.setText(mMessage);
        } else {
            mProgressLabel.setText(mMessageId);
        }

//        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.black), PorterDuff.Mode.SRC_IN);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        Dialog dialog = builder.create();
        DialogUtils.setModal(dialog);
        return dialog;
    }

    public static ProgressDialogFragment newInstance(String message) {
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProgressDialogFragment newInstance(int messageId) {
        Bundle args = new Bundle();
        args.putInt(ARG_MESSAGE_ID, messageId);

        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}