package mozdevz.org.devfestmaputo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mozdevz.org.devfestmaputo.Adapters.AdapterTicket;
import mozdevz.org.devfestmaputo.Config.Constantes;
import mozdevz.org.devfestmaputo.Model.Ticket;
import mozdevz.org.gdgmaputo.R;

/**
 * Created by SJ on 11/14/2017.
 */

public class TicketDialog extends DialogFragment {
    private RecyclerView mRecyclerView;
    // this method create view for your Dialog
    private DatabaseReference mRootRef;
    private FirebaseRecyclerAdapter<Ticket, AdapterTicket> mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private float scale;
    private int width, height, roundPixels;
    Context c;

    public TicketDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate layout with recycler view
        View v = inflater.inflate(R.layout.ticket_dialog, container, false);

        c = getContext();
        scale = c.getResources().getDisplayMetrics().density;
        width = c.getResources().getDisplayMetrics().widthPixels - (int) (14 * scale + 0.5f);
        height = (width / 16) * 9;

        roundPixels = (int) (2 * scale + 0.5f);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mRootRef = FirebaseDatabase.getInstance().getReference().child(Constantes.FIREBASE_TICKET);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Ticket, AdapterTicket>(
                Ticket.class,
                R.layout.item_ticket,
                AdapterTicket.class,
                mRootRef.child(Constantes.FIREBASE_ELEMENTES)) {
            @Override
            protected void populateViewHolder(AdapterTicket holder, Ticket model, int position) {
                final DatabaseReference postRef = getRef(position);
                final String postKey = postRef.getKey();

                final String title = model.getName();
                final String info = model.getInfo();
                final String ends = model.getEnds();
                final String stars = model.getStarts();
                final int price = model.getPrice();
                final boolean soldOut = model.isSoldOut();
                final String currency = model.getCurrency();

                holder.txtName.setText(title);
                holder.txtTime.setText(stars+ " - "+ ends);
                holder.txtInfo.setText(info);
                holder.txtPrice.setText(price +" "+ currency);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(soldOut!=true){
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(Constantes.FIREBASE_BUY_TICKET));
                            startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), "Bilhetes Esgotados", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        };

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);
        //setadapter
        //get your recycler view and populate it.
        return v;
    }
}