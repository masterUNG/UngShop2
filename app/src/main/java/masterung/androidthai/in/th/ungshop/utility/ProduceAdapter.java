package masterung.androidthai.in.th.ungshop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import masterung.androidthai.in.th.ungshop.R;

public class ProduceAdapter extends BaseAdapter{

    private Context context;
    private String[] nameStrings, priceStrings, detailStrings, photoStrings;

    public ProduceAdapter(Context context,
                          String[] nameStrings,
                          String[] priceStrings,
                          String[] detailStrings,
                          String[] photoStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.priceStrings = priceStrings;
        this.detailStrings = detailStrings;
        this.photoStrings = photoStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_produce, parent, false);

        TextView nameTextView = view.findViewById(R.id.txtName);
        TextView priceTextView = view.findViewById(R.id.txtPrice);
        TextView detailTextView = view.findViewById(R.id.txtDetail);
        ImageView imageView = view.findViewById(R.id.imvShowProduce);

        nameTextView.setText(nameStrings[position]);
        priceTextView.setText(priceStrings[position] + " Bath");
        detailTextView.setText(detailStrings[position]);

        Picasso.get().load(photoStrings[position]).into(imageView);

        return view;
    }
}
