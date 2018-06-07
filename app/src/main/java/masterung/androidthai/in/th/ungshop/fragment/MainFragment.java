package masterung.androidthai.in.th.ungshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import masterung.androidthai.in.th.ungshop.R;
import masterung.androidthai.in.th.ungshop.utility.GetAllData;
import masterung.androidthai.in.th.ungshop.utility.MasterAlert;
import masterung.androidthai.in.th.ungshop.utility.MasterConstant;

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

//        Login Controller
        loginController();


    }   // Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                MasterAlert masterAlert = new MasterAlert(getActivity());

                if (userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    masterAlert.normalDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));
                } else {
//                    No Space
                    try {

                        MasterConstant masterConstant = new MasterConstant();

                        GetAllData getAllData = new GetAllData(getActivity());
                        getAllData.execute(masterConstant.getUrlGetAllUserString());

                        String resultJSON = getAllData.get();
                        Log.d("6JuneV1", "JSON ==> " + resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);

                        boolean b = true; //True ==> UserFalse
                        String nameString = null;
                        String truePasswordSting = null;

                        for (int i=0; i<jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {

                                b = false;
                                nameString = jsonObject.getString("Name");
                                truePasswordSting = jsonObject.getString("Password");


                            }

                        }   // for

//                        Check User
                        if (b) {
//                            User False
                            masterAlert.normalDialog("User False",
                                    "No " + userString + " in my Database");
                        } else if (passwordString.equals(truePasswordSting)) {
//                            Password True
                            Toast.makeText(getActivity(), "Welcome " + nameString, Toast.LENGTH_SHORT).show();

                        } else {
//                            Password False
                            masterAlert.normalDialog("Password False",
                                    "Please Try Again Password False");
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Replace Fragment
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }
}
