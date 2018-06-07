package masterung.androidthai.in.th.ungshop.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import masterung.androidthai.in.th.ungshop.R;
import masterung.androidthai.in.th.ungshop.utility.GetAllData;
import masterung.androidthai.in.th.ungshop.utility.MasterConstant;
import masterung.androidthai.in.th.ungshop.utility.ProduceAdapter;

public class ListProduceFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create ListView
        createListView();
    }

    private void createListView() {

        ListView listView = getView().findViewById(R.id.listViewProduce);
        MasterConstant masterConstant = new MasterConstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(masterConstant.getUrlGetAllFoodString());
            String jsonString = getAllData.get();
            Log.d("7JuneV1", "JSON ==> " + jsonString);

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] nameStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];
            String[] detailStrings = new String[jsonArray.length()];
            String[] photoStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("NameFood");
                priceStrings[i] = jsonObject.getString("Price");
                detailStrings[i] = jsonObject.getString("Detail");
                photoStrings[i] = jsonObject.getString("ImagePath");

            }

            ProduceAdapter produceAdapter = new ProduceAdapter(getActivity(),
                    nameStrings, priceStrings, detailStrings, photoStrings);
            listView.setAdapter(produceAdapter);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_produce, container, false);
        return view;
    }
}
