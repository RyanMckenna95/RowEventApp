package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ie.rowingevent.R;
import models.Entries;

/**
 * Created by mcken on 24/04/2018.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private Context context;
    private List<Entries> entriesList;

    public AdapterList(Context context, List<Entries> entriesList) {
        this.context = context;
        this.entriesList = entriesList;
    }

    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_entry_list, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(AdapterList.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventTitle, boatCat,ageCat,price,boatVal,ageVal,priceVal;
        Button delete;

        public ViewHolder(final View itemView, final Context ctx) {
            super(itemView);
            context=ctx;

            eventTitle=itemView.findViewById(R.id.EventTitle);
            boatCat=itemView.findViewById(R.id.boatsCat);
            ageCat=itemView.findViewById(R.id.ageCat);
            price=itemView.findViewById(R.id.price);
            delete=itemView.findViewById(R.id.deleatBtn);


        }

        @Override
        public void onClick(View view) {

        }
    }
}
