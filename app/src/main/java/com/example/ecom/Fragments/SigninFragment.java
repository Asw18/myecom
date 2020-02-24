package com.example.ecom.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ecom.R;

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

                break;

            case  R.id.button_signin:

                break;

                default:
                    break;
        }
    }
}
