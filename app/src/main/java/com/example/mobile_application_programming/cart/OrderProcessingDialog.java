package com.example.mobile_application_programming.cart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.example.mobile_application_programming.R;

public class OrderProcessingDialog extends Dialog {
    public OrderProcessingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_order_processing);
        setCancelable(false);
    }
}