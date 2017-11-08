package app.rmutsv.sampuriwat.rmutsvservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;


import app.rmutsv.sampuriwat.rmutsvservice.MainActivity;
import app.rmutsv.sampuriwat.rmutsvservice.R;
import app.rmutsv.sampuriwat.rmutsvservice.utility.MyConstant;
import app.rmutsv.sampuriwat.rmutsvservice.utility.UploadNewUser;
import app.rmutsv.sampuriwat.rmutsvservice.utility.myAlert;

/**
 * Created by samPuriwat on 7/11/2560.
 */

public class RegisterFragment extends Fragment {
//    Explicit  (declare  variable)
    private String nameString, userString, passwordString, categoryString;
    private boolean aBoolean=true;

//    Manager
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toolbar Controller
        toolbarController();
//        Save Controller
        saveController();
//        Category Controller
        categoryController();
    }// main Method

    private void categoryController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragCategory);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                aBoolean = false;

                switch (i) {
                    case R.id.radBuyer:
                        categoryString = "Buyer";
                        break;
                    case R.id.radSaler:
                        categoryString = "Saler";
                        break;
                }

            }
        });
    }

    private void saveController() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Initial View
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passEditText = getView().findViewById(R.id.edtPassword);
                //EditText conPassEditText = getView().findViewById(R.id.edtConPassword);
//                Change Data type
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passEditText.getText().toString().trim();

//                Check Space
                if (nameString.equals("")||userString.equals("")||passwordString.equals("")) {
//                    Have Space
                    myAlert myalert = new myAlert(getActivity());
                    myalert.myDialog("Have Space","Please Fill All Every Blank");

                } else if (aBoolean) {
//                    Non choose Choice
                    myAlert myalert = new myAlert(getActivity());
                    myalert.myDialog("Non Choose Category", "Please Choose Category");

                } else {
//                    Choose Choice
                    uploadUserToServer();
                }

            }// onClick
        });
    }

    private void uploadUserToServer() {

        String tag = "8novV1";
        try {
            MyConstant myConstant = new MyConstant();
            UploadNewUser uploadNewUser = new UploadNewUser(getActivity());
            uploadNewUser.execute(nameString, categoryString,
                    userString, passwordString, myConstant.getUrlPostData());
            String result = uploadNewUser.get();
            Log.d(tag, "Result ==> " + result);


        } catch (Exception e) {
            Log.d(tag, "e ==>" + e.toString());
        }

    }

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        // Active Toolbar
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Active HomeButton on toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // back one step from previous
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;

    }
}// main class
