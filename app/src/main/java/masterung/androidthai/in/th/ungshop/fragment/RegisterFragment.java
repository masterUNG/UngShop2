package masterung.androidthai.in.th.ungshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import masterung.androidthai.in.th.ungshop.MainActivity;
import masterung.androidthai.in.th.ungshop.R;
import masterung.androidthai.in.th.ungshop.utility.MasterAlert;
import masterung.androidthai.in.th.ungshop.utility.MasterConstant;
import masterung.androidthai.in.th.ungshop.utility.PostUserToServer;

public class RegisterFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();


    }   // Main Method

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemSave) {

//            To Do
            saveValueToServer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveValueToServer() {

//        Get Value From Edit Text
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText userEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

//        Change Data Type to String
        String nameString = nameEditText.getText().toString().trim();
        String userString = userEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();

//        Check Space
        if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {

            MasterAlert masterAlert = new MasterAlert(getActivity());
            masterAlert.normalDialog("Have Space",
                    "Please Fill Every Blank");

        } else {

            try {

                PostUserToServer postUserToServer = new PostUserToServer(getActivity());
                MasterConstant masterConstant = new MasterConstant();

                postUserToServer.execute(nameString, userString, passwordString,
                        masterConstant.getUrlAddUserString());

                String resultString = postUserToServer.get();
                Log.d("6JuneV1", "result ==> " + resultString);

                if (Boolean.parseBoolean(resultString)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {
                    MasterAlert masterAlert = new MasterAlert(getActivity());
                    masterAlert.normalDialog("Cannot Upload",
                            "Please Try again Upload False");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }   // if


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_register, menu);

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle("Please Fill Every Blank");

//        Show Navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}
