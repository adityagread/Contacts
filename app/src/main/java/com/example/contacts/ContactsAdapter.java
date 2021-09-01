package com.example.contacts;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final Context context;
    LayoutInflater inflator;
    List<Contacts> contactsList;

    public ContactsAdapter(Context context, List<Contacts> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflator.from(context).inflate(R.layout.contact_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind data
        holder.person_name.setText(contactsList.get(position).getName());
        holder.person_position.setText(contactsList.get(position).getPosition());

        Glide.with(holder.person_image.getContext()).load(contactsList.get(position).getImage_url()).into(holder.person_image);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView person_name, person_position;
        com.google.android.material.imageview.ShapeableImageView person_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            person_name = itemView.findViewById(R.id.contact_name);
            person_position = itemView.findViewById(R.id.contact_position);
            person_image = itemView.findViewById(R.id.contact_image);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int mposition = getLayoutPosition();
            Intent intent = new Intent(itemView.getContext(),PersonDetailActivity.class);
            intent.putExtra("name",contactsList.get(mposition).getName());
            intent.putExtra("position",contactsList.get(mposition).getPosition());
            intent.putExtra("image_url",contactsList.get(mposition).getImage_url());
            v.getContext().startActivity(intent);

        }
    }

}
