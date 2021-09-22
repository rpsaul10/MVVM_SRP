package com.example.mvvm_example;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;


import com.example.mvvm_example.view_model.MultiplyViewModel;

import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private EditText firstN, secondN;
    private TextView result, history;
    private MultiplyViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewControl();
    }

    private void viewControl() {
        viewModel = new ViewModelProvider(this).get(MultiplyViewModel.class);
        firstN = findViewById(R.id.et_fn);
        secondN = findViewById(R.id.et_sn);
        result = findViewById(R.id.tv_result);
        Button btnMul = findViewById(R.id.btn_mul);
        history = findViewById(R.id.tv_his);

        btnMul.setOnClickListener(view ->
                viewModel.doMultiply(firstN.getText().toString(), secondN.getText().toString()));

        final Observer<String> resultObserver = s -> result.setText(s);

        final Observer<LinkedList<String>> historyObserver = strings -> {
            StringBuilder str = new StringBuilder();
            for(String s: strings) {
                str.append(String.format("%s\n", s));
            }
            history.setText(str.toString());
        };

        viewModel.getResult().observe(this, resultObserver);
        viewModel.getHistory().observe(this, historyObserver);
    }
}
