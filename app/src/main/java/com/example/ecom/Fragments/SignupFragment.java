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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    private TextView textAlreadyHaveAccount;
    private FrameLayout frameLayout;
    private EditText edtEmailId;
    private EditText edtFullName;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private Button btnSignUp;
    private ImageButton btnClose;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        textAlreadyHaveAccount = view.findViewById(R.id.text_already_have_account);
        frameLayout = getActivity().findViewById(R.id.register_framelayout);
        edtEmailId = view.findViewById(R.id.edittext_emailid_signup);
        edtFullName = view.findViewById(R.id.edittext_fullname);
        edtPassword = view.findViewById(R.id.edittext_password);
        edtConfirmPassword = view.findViewById(R.id.edittext_confirm_password);
        btnClose = view.findViewById(R.id.signup_close_button);
        btnSignUp = view.findViewById(R.id.button_signup);
        progressBar = view.findViewById(R.id.progressBar_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SigninFragment());
            }
        });

        edtEmailId.addTextChangedListener(new TextWatcher() {
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
        edtFullName.addTextChangedListener(new TextWatcher() {
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
        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();

            }
        });


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeScreen.class));
                getActivity().finish();

            }
        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs() {

        if (!TextUtils.isEmpty(edtEmailId.getText())){
            if (!TextUtils.isEmpty(edtFullName.getText())){
                if (!TextUtils.isEmpty(edtPassword.getText()) && edtPassword.length() >= 8){
                    if (!TextUtils.isEmpty(edtConfirmPassword.getText())){
                        btnSignUp.setEnabled(true);
                        btnSignUp.setTextColor(getResources().getColor(R.color.colorAccent));
                    }else {
                        btnSignUp.setEnabled(false);
                        btnSignUp.setTextColor(Color.argb(50,255,255,255));
                    }

                }else {
                    btnSignUp.setEnabled(false);
                    btnSignUp.setTextColor(Color.argb(50,255,255,255));
                }
            }else {
                btnSignUp.setEnabled(false);
                btnSignUp.setTextColor(Color.argb(50,255,255,255));
            }
        }else {
            btnSignUp.setEnabled(false);
            btnSignUp.setTextColor(Color.argb(50,255,255,255));
        }
    }

    private void checkEmailAndPassword(){
        String email = edtEmailId.getText().toString().trim();
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if (edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){

                progressBar.setVisibility(View.VISIBLE);
                btnSignUp.setEnabled(false);
                btnSignUp.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.createUserWithEmailAndPassword(email,edtConfirmPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Map<Object,String> userData = new HashMap<>();
                            userData.put("fullname",edtFullName.getText().toString());
                            firebaseFirestore.collection("USERS").add(userData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(getActivity(), HomeScreen.class));
                                        getActivity().finish();
                                    }else {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        btnSignUp.setEnabled(true);
                                        btnSignUp.setTextColor(getResources().getColor(R.color.colorAccent));
                                        Toast.makeText(getActivity(), task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }else {
                            progressBar.setVisibility(View.INVISIBLE);
                            btnSignUp.setEnabled(true);
                            btnSignUp.setTextColor(getResources().getColor(R.color.colorAccent));
                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }else {
                edtConfirmPassword.setError("Password dosen't match");
            }
        }else {
            edtEmailId.setError("Invalid Email!");
        }
    }
}
