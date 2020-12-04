package com.example.bikeride.viewModel;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bikeride.BR;
import com.example.bikeride.view.activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginViewModel extends BaseObservable {

    private String login;
    private String password;

    private boolean errorFlag;
    private boolean isLoginEmpty;
    private boolean isPasswordEmpty;

    public LoginViewModel() {
        errorFlag = false;
        isLoginEmpty = true;
        isPasswordEmpty= true;

        login = "";
        password = "";
    }

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        setIsLoginEmpty(login.equals(""));
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        setIsPasswordEmpty(password.equals(""));
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public boolean getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
        notifyPropertyChanged(BR.errorFlag);
    }

    @Bindable
    public boolean getIsLoginEmpty() {
        return isLoginEmpty;
    }

    public void setIsLoginEmpty(boolean isLoginEmpty) {
        this.isLoginEmpty = isLoginEmpty;
        notifyPropertyChanged(BR.isLoginEmpty);
    }

    @Bindable
    public boolean getIsPasswordEmpty() {
        return isPasswordEmpty;
    }

    public void setIsPasswordEmpty(boolean isPasswordEmpty) {
        this.isPasswordEmpty = isPasswordEmpty;
        notifyPropertyChanged(BR.isPasswordEmpty);
    }

    public void login(Object activity) {
        if (activity instanceof LoginActivity) {
            RequestQueue queue = Volley.newRequestQueue((LoginActivity)activity);
            String url = "http://26.241.146.215:8000/auth";

            JSONObject postData = new JSONObject();
            try {
                postData.put("login", login);
                postData.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response);
                    //TODO обработать ответ от сервера
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    setErrorFlag(true);
                }
            });

            queue.add(jsonObjectRequest);
        }
    }
}
