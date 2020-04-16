package com.comp231.mypam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import java.text.NumberFormat;

public class CalculatorActivity extends AppCompatActivity {

    ScrollView mortgageScrollView;
    ScrollView loanScrollView;
    ScrollView investmentScrollView;

    RadioGroup optionGroup;

    Button mortgageCalculateButton;
    Button loanCalculateButton;
    Button investmentCalculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        InitializeView();
        SetListener();
    }

    public void InitializeView()
    {
        mortgageScrollView = findViewById(R.id.mortgageScrollView);
        loanScrollView = findViewById(R.id.loanScrollView);
        investmentScrollView = findViewById(R.id.investmentScrollView);

        optionGroup = findViewById(R.id.optionsGroup);

        mortgageCalculateButton = findViewById(R.id.mortgageCalculateButton);
        loanCalculateButton = findViewById(R.id.loanCalculateButton);
        investmentCalculateButton = findViewById(R.id.investmentCalculateButton);
    }

    public void SetListener()
    {
        final NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance();

        optionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.mortgageRadioButton){
                    mortgageScrollView.setVisibility(View.VISIBLE);
                    mortgageCalculateButton.setVisibility(View.VISIBLE);
                    loanScrollView.setVisibility(View.GONE);
                    loanCalculateButton.setVisibility(View.GONE);
                    investmentScrollView.setVisibility(View.GONE);
                    investmentCalculateButton.setVisibility(View.GONE);
                }else if(checkedId == R.id.loanRadioButton){
                    mortgageScrollView.setVisibility(View.GONE);
                    mortgageCalculateButton.setVisibility(View.GONE);
                    loanScrollView.setVisibility(View.VISIBLE);
                    loanCalculateButton.setVisibility(View.VISIBLE);
                    investmentScrollView.setVisibility(View.GONE);
                    investmentCalculateButton.setVisibility(View.GONE);
                }else {
                    mortgageScrollView.setVisibility(View.GONE);
                    mortgageCalculateButton.setVisibility(View.GONE);
                    loanScrollView.setVisibility(View.GONE);
                    loanCalculateButton.setVisibility(View.GONE);
                    investmentScrollView.setVisibility(View.VISIBLE);
                    investmentCalculateButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mortgageCalculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                EditText housePrice = findViewById(R.id.mortgageHousePriceInputEditText);
                EditText downPayment = findViewById(R.id.mortgageDownPaymentInputEditText);
                EditText loanTerm = findViewById(R.id.mortgageLoanTermInputEditText);
                EditText annualInterestRate = findViewById(R.id.mortgageInterestRateInputEditText);

                EditText monthlyPayment = findViewById(R.id.mortgageMonthlyPaymentResultEditText);
                EditText loanAmount = findViewById(R.id.mortgageLoanAmountResultEditText);
                EditText downPaymentResult = findViewById(R.id.mortgageDownPaymentResultEditText);
                EditText totalInterest = findViewById(R.id.mortgageTotalInterestResultEditText);
                EditText totalPayment = findViewById(R.id.mortgageTotalPaymentResultEditText);

                double monthlyPaymentResult = calculateMonthlyPaymentForMortgage(Integer.parseInt(housePrice.getText().toString()), Integer.parseInt(downPayment.getText().toString()), Integer.parseInt(loanTerm.getText().toString()), Double.parseDouble(annualInterestRate.getText().toString()));
                monthlyPayment.setText(currencyFormat.format(monthlyPaymentResult));
                loanAmount.setText(currencyFormat.format(Integer.parseInt(housePrice.getText().toString())-Integer.parseInt(downPayment.getText().toString())));
                downPaymentResult.setText(currencyFormat.format(Integer.parseInt(downPayment.getText().toString())));
                totalInterest.setText(currencyFormat.format(monthlyPaymentResult * Double.parseDouble(loanTerm.getText().toString()) * 12 - (Double.parseDouble(housePrice.getText().toString())-Double.parseDouble(downPayment.getText().toString()))));
                totalPayment.setText(currencyFormat.format(monthlyPaymentResult * Double.parseDouble(loanTerm.getText().toString()) * 12));
            }
        });

        loanCalculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                EditText loanAmount = findViewById(R.id.loanLoanAmountInputEditText);
                EditText loanTerm = findViewById(R.id.loanLoanTermInputEditText);
                EditText annualInterestRate = findViewById(R.id.loanAnnualInterestRateInputEditText);

                EditText monthlyPayment = findViewById(R.id.loanMonthlyPaymentResultEditText);
                EditText totalPayment = findViewById(R.id.loanTotalPaymentResultEditText);
                EditText totalInterest = findViewById(R.id.loanTotalInterestResultEditText);

                double monthlyPaymentResult = calculateMonthlyPaymentForLoan(Integer.parseInt(loanAmount.getText().toString()), Integer.parseInt(loanTerm.getText().toString()), Double.parseDouble(annualInterestRate.getText().toString()));
                monthlyPayment.setText(currencyFormat.format(monthlyPaymentResult));
                totalPayment.setText(currencyFormat.format(monthlyPaymentResult * Double.parseDouble(loanTerm.getText().toString()) * 12));
                totalInterest.setText(currencyFormat.format(monthlyPaymentResult * Double.parseDouble(loanTerm.getText().toString()) * 12 - Double.parseDouble(loanAmount.getText().toString())));
            }
        });

        investmentCalculateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                EditText startingAmount = findViewById(R.id.investmentStartingAmountInputEditText);
                EditText investLength = findViewById(R.id.investmentInvestLengthInputEditText);
                EditText annualInterestRate = findViewById(R.id.investmentAnnualInterestRateInputEditText);

                EditText endBalance = findViewById(R.id.investmentEndBalanceResultEditText);
                EditText startingAmountResult = findViewById(R.id.investmentStartingAmountResultEditText);
                EditText totalInterest = findViewById(R.id.investmentTotalInterestResultEditText);

                double endBalanceResult = calculateEndBalanceForInvestment(Integer.parseInt(startingAmount.getText().toString()), Integer.parseInt(investLength.getText().toString()), Double.parseDouble(annualInterestRate.getText().toString()));
                endBalance.setText(currencyFormat.format(endBalanceResult));
                startingAmountResult.setText(currencyFormat.format(Integer.parseInt(startingAmount.getText().toString())));
                totalInterest.setText(currencyFormat.format(endBalanceResult - Double.parseDouble(startingAmount.getText().toString())));
            }
        });
    }

    public static double calculateMonthlyPaymentForMortgage(int housePrice, int downPayment, int termInYears, double interestRate) {
        int loanAmount = housePrice - downPayment;
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = termInYears * 12;
        double monthlyPayment = (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
        return monthlyPayment;
    }

    public static double calculateMonthlyPaymentForLoan(int loanAmount, int termInYears, double interestRate) {
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = termInYears * 12;
        double monthlyPayment = (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -termInMonths));
        return monthlyPayment;
    }

    public static double calculateEndBalanceForInvestment(int startingAmount, int investLength, double interestRate) {
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int lengthInMonths = investLength * 12;
        double endBalance = (startingAmount) * Math.pow(1+monthlyRate, lengthInMonths);
        return endBalance;
    }
}
