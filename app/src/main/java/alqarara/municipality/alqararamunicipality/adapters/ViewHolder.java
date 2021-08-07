package alqarara.municipality.alqararamunicipality.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import alqarara.municipality.alqararamunicipality.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;


    public ViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

    }

    //set details to recycler view row
    public void setDetails(Context ctx, String image, String title){
        //Views
        ImageView mImageIv = mView.findViewById(R.id.item_iv_news);
        TextView mtitleTv = mView.findViewById(R.id.item_tv_news);

        //set data to views
        Picasso.get().load(image).into(mImageIv);
        mtitleTv.setText(title);

    }

    private ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View  view, int position);
    }

    public void setOnClickListener(ClickListener clickListener){
        mClickListener = clickListener;
    }
}
