package com.example.mobile_application_programming.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.mobile_application_programming.R;

public class ContactActivity extends AppCompatActivity {

    private LinearLayout faqContainer;
    private TextInputEditText subjectInput;
    private TextInputEditText messageInput;
    private MaterialButton sendButton;

    // FAQ data structure
    private final String[][] faqs = {
        {"How do I track my order?", "You can track your order in the 'My Orders' section of your account. Click on the specific order to view its current status and tracking information."},
        {"What is your return policy?", "We offer a 30-day return policy for most items. Products must be unused and in their original packaging. Contact us to initiate a return."},
        {"How long does shipping take?", "Standard shipping typically takes 3-5 business days. Express shipping options are available at checkout for faster delivery."},
        {"Do you ship internationally?", "Yes, we ship to select international destinations. Shipping costs and delivery times vary by location."},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize views
        Toolbar toolbar = findViewById(R.id.toolbar);
        faqContainer = findViewById(R.id.faqContainer);
        subjectInput = findViewById(R.id.subjectInput);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        // Setup toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setup FAQs
        setupFAQs();

        // Setup send button
        sendButton.setOnClickListener(v -> handleMessageSend());
    }

    private void setupFAQs() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (String[] faq : faqs) {
            View faqView = inflater.inflate(R.layout.item_faq, faqContainer, false);
            
            TextView questionText = faqView.findViewById(R.id.questionText);
            TextView answerText = faqView.findViewById(R.id.answerText);
            
            questionText.setText(faq[0]);
            answerText.setText(faq[1]);

            // Setup click listener to expand/collapse
            faqView.setOnClickListener(v -> {
                if (answerText.getVisibility() == View.VISIBLE) {
                    answerText.setVisibility(View.GONE);
                } else {
                    answerText.setVisibility(View.VISIBLE);
                }
            });

            faqContainer.addView(faqView);
        }
    }

    private void handleMessageSend() {
        String subject = subjectInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        if (subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Implement message sending logic here
        // This could involve sending to an API, email service, etc.

        Toast.makeText(this, "Message sent successfully!", Toast.LENGTH_SHORT).show();
        
        // Clear inputs
        subjectInput.setText("");
        messageInput.setText("");
    }
}