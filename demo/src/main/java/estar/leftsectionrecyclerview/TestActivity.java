package estar.leftsectionrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import estar.leftsection.BaseSectionAdapter;
import estar.leftsection.BaseViewHolder;
import estar.leftsection.LeftSectionRecyclerView;

public class TestActivity extends AppCompatActivity {
    private LeftSectionRecyclerView sectionRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test);
        sectionRecyclerView = findViewById(R.id.sectionRecyclerView);
        sectionRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));

        List<ImageInfo> list = new ArrayList<>();
        list.add(new ImageInfo(true, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));

        list.add(new ImageInfo(true, "图组2"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(true, "图组3"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(true, "图组4"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(true, "图组5"));
        list.add(new ImageInfo(true, "图组6"));
        list.add(new ImageInfo(true, "图组7"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));
        list.add(new ImageInfo(false, "图组1"));

        int width = getResources().getDisplayMetrics().widthPixels;
        final int itemWidth = (int) (width - getResources().getDimension(R.dimen.dp_50));

        BaseSectionAdapter adapter = null;
        sectionRecyclerView.setAdapter(adapter = new BaseSectionAdapter<ImageInfo, BaseViewHolder>(R.layout.item_image, list) {


            @Override
            protected void convert(BaseViewHolder helper, ImageInfo item) {
               int m =  helper.getAdapterPosition()%5;
               int imageId = R.drawable.xiongmao_1;
               switch (m){
                   case 0:
                       imageId = R.drawable.xiongmao_1;
                       break;
                   case 1:
                       imageId = R.drawable.xiongmao_2;
                       break;
                   case 2:
                       imageId = R.drawable.xiongmao_3;
                       break;
                   case 3:
                       imageId = R.drawable.xiongmao_4;
                       break;
                   case 4:
                       imageId = R.drawable.xiongmao_5;
                       break;
               }

                Glide.with(helper.itemView).load(imageId).apply(new RequestOptions().override(340,340).centerCrop()).into((ImageView) helper.getView(R.id.image));
            }

        });


        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("test","positon="+position);
            }
        });
    }
}
