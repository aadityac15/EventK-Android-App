package androidproject.chokshi.testeventk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.EventItemViewHolder> {

    private List<Event> myEventList;

    //Constructor
    public EventsRecyclerAdapter(List<Event> myEventList) {
        this.myEventList = myEventList;
    }

    //Item Count
    @Override
    public int getItemCount() {
        return myEventList.size();
    }

    @NonNull
    @Override
    public EventItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View individualEventRowView = layoutInflater.inflate(R.layout.individual_eventrow_layout, viewGroup, false);
        EventItemViewHolder eventItemViewHolder = new EventItemViewHolder(individualEventRowView);
        return eventItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventItemViewHolder eventItemViewHolder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-dd-yyyy hh:mm aa", Locale.getDefault());
        Event event = myEventList.get(position);
        final String eventaddress = event.getAddress();
        final String description = event.getDescription();
        final String imgUrl = event.getImgUrl();
        final String title = event.getTitle();
        final Date eventDate = event.getEventDate();
        final String eventdateStr = simpleDateFormat.format(eventDate);
        final double latitude = event.getLatitude();
        final double longitude = event.getLongitude();
        eventItemViewHolder.txtTitle.setText(title);
        eventItemViewHolder.txtDate.setText(eventdateStr);
        eventItemViewHolder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EventInfoActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("eventAddress", eventaddress);
                intent.putExtra("imgurl", imgUrl);
                intent.putExtra("eventdate", eventdateStr);
                v.getContext().startActivity(intent);
            }
        });
        Glide.with(eventItemViewHolder.itemView).load(imgUrl).into(eventItemViewHolder.imgThumbnail);
    }

    //View Holder
    public static class EventItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView txtTitle, txtDate;

        public EventItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }


}
