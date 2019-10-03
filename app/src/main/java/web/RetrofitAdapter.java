package web;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.videosview.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import model.Movie;
import retrofit2.Retrofit;
public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    //LayoutInflater class create from XML file some View file;
    private LayoutInflater inflater;
    //this list will be used for data which inside model
    private ArrayList<Movie> dataModelArrayList;
    //create constructor for RetrofitAdapter with LayoutInflater and ArrayList
    public RetrofitAdapter(Context ctx, ArrayList<Movie> dataModelArrayList){
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //inflate from my resource (XML film items, my view)
        View view = inflater.inflate(R.layout.films_items, parent, false);
        //based on this view create holder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    //this method will be called for update recycleView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(dataModelArrayList.get(position).getImageSmall()).into(holder.smlImage);
        Picasso.get().load(dataModelArrayList.get(position).getImageLarge()).into(holder.bigImage);
        holder.title.setText(dataModelArrayList.get(position).getSubtitle());
        holder.studio.setText(dataModelArrayList.get(position).getStudio());
    }
//return size of the ArrayList from the Model
    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
//create inner class inside Retrofit Adapter with ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView smlImage;
        ImageView bigImage;
        TextView title;
        TextView studio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            smlImage = (ImageView)itemView.findViewById(R.id.imgview_small);
            bigImage = (ImageView)itemView.findViewById(R.id.imgview_large);
            title = (TextView)itemView.findViewById(R.id.titleview);
            studio = (TextView)itemView.findViewById(R.id.studioview);

        }
    }
}