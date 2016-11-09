package com.namestore.alicenote.dialog;

import android.app.Activity;
import android.app.Dialog;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import com.namestore.alicenote.R;

/**
 * Created by kienht on 9/11/2016.
 */

public class DialogNotice {

    public void showDialog(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_notice);

        TextView text = (TextView) dialog.findViewById(R.id.tvDialogContent);
        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.btnDone);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}

