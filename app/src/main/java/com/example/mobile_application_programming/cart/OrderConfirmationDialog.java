package com.example.mobile_application_programming.cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import com.example.mobile_application_programming.R;

public class OrderConfirmationDialog extends Dialog {
    public OrderConfirmationDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_order_confirmation);
        setCancelable(false);

        Button okButton = findViewById(R.id.confirmationOkButton);
        okButton.setOnClickListener(v -> dismiss());
    }
}