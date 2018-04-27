package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ie.rowingevent.R;

/**
 * Created by mcken on 27/04/2018.
 */
public class EntriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView eventTitle, boatCat,ageCat,price,boatVal,ageVal,priceVal;
    public Button delete;

    public EntriesViewHolder(final View itemView) {
        super(itemView);
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


