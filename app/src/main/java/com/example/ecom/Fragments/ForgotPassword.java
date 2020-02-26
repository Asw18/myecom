package com.example.ecom.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassword extends Fragment {
    private EditText edtResetEmail;
    private Button btnReset;
    private TextView txtGoback;
    private FrameLayout frameLayout;
    private FirebaseAuth firebaseAuth;
    private ViewGroup viewGroup;
    private ProgressBar progressBar;
    private ImageView iconEmail;
    private TextView txtEmail;


    public ForgotPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
       edtResetEmail = view.findViewById(R.id.edt_reset_email);
       btnReset = view.findViewById(R.id.btnReset);
       txtGoback = view.findViewById(R.id.txtGoback);
       frameLayout = getActivity().findViewById(R.id.register_framelayout);
       viewGroup = view.findViewById(R.id.linearLayout_forgot_password);
       iconEmail = view.findViewById(R.id.iconEmail);
       txtEmail = view.findViewById(R.id.textEmail);
       progressBar = view.findViewById(R.id.progressBar_fotgot);
       firebaseAuth = FirebaseAuth.getInstance();
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtResetEmail.addTextChangedListener(new TextWatcher() {
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

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(viewGroup);
                btnReset.setEnabled(false);
                btnReset.setTextColor(Color.argb(50,255,255,255));
                progressBar.setVisibility(View.VISIBLE);
                iconEmail.setVisibility(View.VISIBLE);

                firebaseAuth.sendPasswordResetEmail(edtResetEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            txtEmail.setVisibility(View.VISIBLE);
                            txtEmail.setTextColor(getResources().getColor(R.color.colorGreen));
                            txtEmail.setText("Mail to reset password sent to your mail address!!");
                            iconEmail.setImageDrawable(getResources().getDrawable(R.drawable.icon_email_green));
                            TransitionManager.beginDelayedTransition(viewGroup);

                            

                        }else {



                            txtEmail.setTextColor(getResources().getColor(R.color.colorPrimary));
                            txtEmail.setText(task.getException().getMessage());
                            iconEmail.setImageDrawable(getResources().getDrawable(R.drawable.icon_email_red));
                            TransitionManager.beginDelayedTransition(viewGroup);
                            txtEmail.setVisibility(View.VISIBLE);

                        }
                        progressBar.setVisibility(View.GONE);
                        btnReset.setEnabled(true);
                        btnReset.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                });
            }
        });

        txtGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
                fragmentTransaction.replace(frameLayout.getId(),new SigninFragment());
                fragmentTransaction.commit();

            }
        });
    }

    private void checkInputs() {
        if (!TextUtils.isEmpty(edtResetEmail.getText())){
            btnReset.setEnabled(true);
            btnReset.setTextColor(getResources().getColor(R.color.colorAccent));
        }else {
            btnReset.setEnabled(false);
            btnReset.setTextColor(Color.argb(50,255,255,255));
        }
    }
}
