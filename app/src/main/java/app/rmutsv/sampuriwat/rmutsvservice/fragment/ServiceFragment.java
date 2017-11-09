package app.rmutsv.sampuriwat.rmutsvservice.fragment;

import android.content.DialogInterface;
import android.mtp.MtpConstants;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import app.rmutsv.sampuriwat.rmutsvservice.MainActivity;
import app.rmutsv.sampuriwat.rmutsvservice.MyServiceActivity;
import app.rmutsv.sampuriwat.rmutsvservice.R;
import app.rmutsv.sampuriwat.rmutsvservice.utility.DeleteData;
import app.rmutsv.sampuriwat.rmutsvservice.utility.EditData;
import app.rmutsv.sampuriwat.rmutsvservice.utility.GetAllData;
import app.rmutsv.sampuriwat.rmutsvservice.utility.ListViewAdepter;
import app.rmutsv.sampuriwat.rmutsvservice.utility.MyConstant;

/**
 * Created by lenovo on 9/11/2560.
 */

public class ServiceFragment extends Fragment {

    //ServiceFragment serviceFragment = new ServiceFragment();
    public static ServiceFragment serviceInstance(String[] strings) {
        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("Login", strings);
        serviceFragment.setArguments(bundle);

        return serviceFragment;


    } // serviceInstance

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//      (Fragment)  Transfer data from Activity
        String[] strings = getArguments().getStringArray("Login");
        Log.d("9novV1", "Login(1) on ServiceFragment ==> " + strings[1]);

//        Create Toolbar
        createToolbar(strings[1]);

//        Create ListView
        createListView();


    }

    private void createListView() {
        ListView listView = getView().findViewById(R.id.livUser);
        MyConstant myConstant = new MyConstant();
        try {
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetAllUser());
            String resultJSON = getAllData.get();
            Log.d("9novV1", "JSON ==>" + resultJSON);

            JSONArray jsonArray = new JSONArray(resultJSON);
            final String[] nameStrings = new String[jsonArray.length()];
            String[] categoryStrings = new String[jsonArray.length()];
            String[] userStrings = new String[jsonArray.length()];
            String[] passwordStrings = new String[jsonArray.length()];

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                categoryStrings[i] = jsonObject.getString("Category");
                userStrings[i] = jsonObject.getString("User");
                passwordStrings[i] = jsonObject.getString("Password");

            }// for
            ListViewAdepter listViewAdepter = new ListViewAdepter(getActivity(),
                    nameStrings,
                    categoryStrings,
                    userStrings,
                    passwordStrings);

            listView.setAdapter(listViewAdepter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    confirmDialog(nameStrings[i]);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void confirmDialog(final String nameString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle("You Choose " + nameString);
        builder.setMessage("What do you want ? ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteDataWhereName(nameString);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editDataWhereName(nameString);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void editDataWhereName(String nameString) {
        try {
            MyConstant myConstant = new MyConstant();
            EditData editData = new EditData(getActivity());
            editData.execute(nameString, myConstant.getUrlEditData());
            if (Boolean.parseBoolean(editData.get())) {
                Toast.makeText(getActivity(), "Edit Success", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(getActivity(), "Edit Error", Toast.LENGTH_SHORT).show();
            }
            createListView();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void deleteDataWhereName(String nameString) {
        try {
            MyConstant myConstant = new MyConstant();
            DeleteData deleteData = new DeleteData(getActivity());
            deleteData.execute(nameString, myConstant.getUrlDeleteData());

            if (Boolean.parseBoolean(deleteData.get())) {
                Toast.makeText(getActivity(), "Delete Success", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(getActivity(), "Delete Error", Toast.LENGTH_SHORT).show();
            }
            createListView();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void createToolbar(String strTitle) {
        Toolbar toolbar = getView().findViewById(R.id.toolbarService);
//        Active Toolbar
        ((MyServiceActivity) getActivity()).setSupportActionBar(toolbar);
        ((MyServiceActivity)getActivity()).getSupportActionBar().setTitle(strTitle);
        ((MyServiceActivity)getActivity()).getSupportActionBar().setSubtitle("Who Logged: ");


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        return view;

    }
}// main Class
