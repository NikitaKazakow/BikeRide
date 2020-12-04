package com.example.bikeride.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.bikeride.R;
import com.example.bikeride.databinding.ActivityLoginBinding;
import com.example.bikeride.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    private void bind() {
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new LoginViewModel();
        binding.setViewModel(viewModel);
    }

    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

}