package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    public void ClickOnButton(View view) {
        TextView sumFromUser = findViewById(R.id.getSumFromUser);
        TextView termFromUser = findViewById(R.id.getTermFromUser);
        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView payoutEndTerm = findViewById(R.id.payoutEndTerm);
        TextView totalOverpayment = findViewById(R.id.totalOverpayment);

        // Ловит числа от пользователя
        double sum, term;
        sum = Integer.parseInt(sumFromUser.getText().toString());
        term = Integer.parseInt(termFromUser.getText().toString());

        double userPercent = 5, result, monthly, percent;
        percent = userPercent / 12 / 100;
        result = percent * Math.pow(1 + percent, term) / (Math.pow(1 + percent, term) - 1);
        monthly = result * sum;
        double overpayment = monthly * term - sum;
        double totalPayout = overpayment + sum;
        @SuppressLint("DefaultLocale") String percentStr = String.format("%.0f", userPercent);

        String monthlyFormat = new DecimalFormat( "###,###.##" ).format(monthly);
        String totalOverpaymentFormat = new DecimalFormat( "###,###.##" ).format(overpayment);
        String totalPayoutFormat = new DecimalFormat( "###,###.##" ).format(totalPayout);

        resultLabel.setText("Payment " + monthlyFormat + " per month at a rate " + percentStr + "%");
        totalOverpayment.setText("Payout at the end of the term " + totalOverpaymentFormat);
        payoutEndTerm.setText("Payout at the end of the term " + totalPayoutFormat);
    }
}