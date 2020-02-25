package com.example.ecom.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecom.HomeScreen;
import com.example.ecom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment implements View.OnClickListener {

    private TextView txtDontHaveAcct;
    private FrameLayout frameLayout;
    private ImageButton btnClose;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignin;
    private TextView txtForgotPassword;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;


    public SigninFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_signin, container, false);
       frameLayout = getActivity().findViewById(R.id.register_framelayout);
       txtDontHaveAcct = view.findViewById(R.id.text_dont_have_account);
       btnClose = view.findViewById(R.id.signin_close_button);
       edtEmail = view.findViewById(R.id.edittext_emailid_signin);
       edtPassword = view.findViewById(R.id.edittext_password_signin);
       btnSignin = view.findViewById(R.id.button_signin);
       txtForgotPassword = view.findViewById(R.id.textview_forgot_password);
       progressBar = view.findViewById(R.id.progressbar_signin);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtDontHaveAcct.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(edtEmail.getText())){
            if (!TextUtils.isEmpty(edtPassword.getText())){
                btnSignin.setEnabled(true);
                btnSignin.setTextColor(getResources().getColor(R.color.colorAccent));
            }else {
                btnSignin.setEnabled(false);
                btnSignin.setTextColor(Color.argb(50,255,255,255));
            }
        }else {
            btnSignin.setEnabled(false);
            btnSignin.setTextColor(Color.argb(50,255,255,255));
        }

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){

            case  R.id.text_dont_have_account:
                setFragment(new SignupFragment());
                break;

            case  R.id.signin_close_button:
                startActivity(new Intent(getActivity(),HomeScreen.class));
                getActivity().finish();
                break;

            case  R.id.button_signin:
                checkEmailAndPassword();
                break;

            case  R.id.textview_forgot_password:
               setFragment(new ForgotPassword());
                break;

                default:
                    break;
        }
    }



    private void checkEmailAndPassword() {

        if (Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()){
            if(edtPassword.length() >=8){
                progressBar.setVisibility(View.VISIBLE);
                btnSignin.setEnabled(false);
                btnSignin.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getActivity(), HomeScreen.class));
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            btnSignin.setEnabled(true);
                            btnSignin.setTextColor(getResources().getColor(R.color.colorAccent));
                        }
                    }
                });
            }else {
                Toast.makeText(getActivity(), "incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getActivity(), "incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }
}
