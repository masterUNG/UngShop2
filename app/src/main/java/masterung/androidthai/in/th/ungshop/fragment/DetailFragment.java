package masterung.androidthai.in.th.ungshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import masterung.androidthai.in.th.ungshop.R;

public class DetailFragment extends Fragment {

    private String nameString, priceString, detailString, photoString;

    public static DetailFragment detailInstance(String nameString,
                                                String priceString,
                                                String detailString,
                                                String photoString) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", nameString);
        bundle.putString("Price", priceString);
        bundle.putString("Detail", detailString);
        bundle.putString("Photo", photoString);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        GetValue From Argument
        getValueFromArgument();

//        Show View
        showView();

//        Back Controller
        backController();

    }   // Main Method

    private void backController() {
        Button button = getView().findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    private void showView() {
        ImageView imageView = getView().findViewById(R.id.imvShowProduce);
        Picasso.get().load(photoString).into(imageView);

        TextView nameTextView = getView().findViewById(R.id.txtName);
        nameTextView.setText(nameString);

        TextView priceTextView = getView().findViewById(R.id.txtPrice);
        priceTextView.setText(priceString);

        TextView detailTextView = getView().findViewById(R.id.txtDetail);
        detailTextView.setText(detailString);
    }

    private void getValueFromArgument() {
        nameString = getArguments().getString("Name");
        priceString = getArguments().getString("Price");
        detailString = getArguments().getString("Detail");
        photoString = getArguments().getString("Photo");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
}
