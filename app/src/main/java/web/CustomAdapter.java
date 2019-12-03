package web;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.videosview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Videos;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Videos> dataList;
    private Context context;
    LayoutInflater layoutInflater;
    //adapter's constructor
    public CustomAdapter(Context context, List<Videos>dataList){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.films_items, parent, false);
        Log.d("onCreateViewHolder", "onCreateViewHolder");
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // was after getImageSmall
        Picasso.get().load(dataList.get(position).getImageSmall()).placeholder(R.drawable.no_image).into(holder.smlImage);
//        Picasso.get().load(dataList.get(position).getImageLarge()).placeholder(R.drawable.no_image_large).into(holder.bigImage);
//        Picasso.get().load(dataList.get(position).getThumb()).placeholder(R.drawable.no_image).into(holder.thumbImage);
        holder.title.setText(dataList.get(position).getSubtitle());
        holder.studio.setText(dataList.get(position).getStudio());
        Log.d("onBindViewHolder", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    //In this class I'll create ViewHolder
    public class CustomViewHolder extends RecyclerView.ViewHolder{
    ImageView smlImage;
    ImageView bigImage;
    ImageView thumbImage;
    TextView title;
    TextView studio;
    //View's Holder constructor
    public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            smlImage = (ImageView) itemView.findViewById(R.id.imgview_small);
//            bigImage = (ImageView) itemView.findViewById(R.id.imgview_large);
//            thumbImage = (ImageView) itemView.findViewById(R.id.thumb);
            title = (TextView) itemView.findViewById(R.id.titleview);
            studio = (TextView) itemView.findViewById(R.id.studioview);
        }
    }
}
