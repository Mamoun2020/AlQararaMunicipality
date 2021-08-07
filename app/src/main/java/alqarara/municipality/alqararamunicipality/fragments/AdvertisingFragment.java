package alqarara.municipality.alqararamunicipality.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import alqarara.municipality.alqararamunicipality.R;
import alqarara.municipality.alqararamunicipality.adapters.ViewHolder;
import alqarara.municipality.alqararamunicipality.models.News;

public class AdvertisingFragment extends Fragment {
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    public AdvertisingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advertising, container, false);
        recyclerView= view.findViewById(R.id.advertising_fragment_recycler);
        recyclerView.setHasFixedSize(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("News");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FirebaseRecyclerAdapter<News, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<News, ViewHolder>(
                        News.class,
                        R.layout.item_news,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, News news, int position) {
                        viewHolder.setDetails(getActivity(), news.getImage(), news.getTitle());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                ImageView mImageIv = view.findViewById(R.id.item_iv_news);
                                TextView mtitleTv = view.findViewById(R.id.item_tv_news);
                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };

        //set adapter to recyclerview
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    // we will update at to save local and instance state...
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        String title="";
        String image;
        ArrayList<News> list = new ArrayList<>();
        for(News news :list) {
            title = news.getTitle();
            image = news.getImage();
            outState.putString("title", title.trim());
            outState.putString("image", image);

        }
        super.onSaveInstanceState(outState);
    }
}
