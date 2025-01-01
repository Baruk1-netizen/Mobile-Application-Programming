package com.example.mobile_application_programming.cart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable; // Add this line
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View; // Add this line
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.mobile_application_programming.R;
import com.google.android.material.button.MaterialButton;

public class OrderConfirmationDialog extends DialogFragment {
    private OnDismissListener onDismissListener;
    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_order_confirmation, null);
        
        MaterialButton continueShoppingButton = view.findViewById(R.id.continueShoppingButton);
        continueShoppingButton.setOnClickListener(v -> dismiss());
        
        builder.setView(view);
        Dialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        
        return dialog;
    }
    
    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }
    
    public void setOnDismissListener(OnDismissListener listener) {
        this.onDismissListener = listener;
    }
    
    public interface OnDismissListener {
        void onDismiss();
    }
}
