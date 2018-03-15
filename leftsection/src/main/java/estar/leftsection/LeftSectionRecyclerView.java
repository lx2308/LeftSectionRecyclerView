package estar.leftsection;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xueliang on 2018/3/14.
 */

public class LeftSectionRecyclerView extends RecyclerView {

    private Map<Integer, Integer> spanSizeMap = new HashMap<>();
    private int spanCount;

    private int bgLineColor = -1;
    private int itemTextColor = -1;
    private float lineStrokeWidth = -1;
    private float itemTextSize = -1;

    private float decTopMargin = -1;
    private float decLeftMargin = -1;
    private float decRightMargin = -1;
    private float textMarginHor = -1;
    private float textMarginVer = -1;


    private SectionItemDecoration decoration;

    public LeftSectionRecyclerView(Context context) {
        this(context, null);
    }

    public LeftSectionRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftSectionRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LeftSectionRecyclerView, defStyle, 0);

            bgLineColor = a.getColor(R.styleable.LeftSectionRecyclerView_bgLineColor,-1);
            itemTextColor = a.getColor(R.styleable.LeftSectionRecyclerView_itemTextColor,-1);
            lineStrokeWidth = a.getDimension(R.styleable.LeftSectionRecyclerView_lineStorkeWidth,-1);
            itemTextSize = a.getDimension(R.styleable.LeftSectionRecyclerView_itemTextSize,-1);

            decTopMargin = a.getDimension(R.styleable.LeftSectionRecyclerView_decTopMargin,-1);
            decLeftMargin = a.getDimension(R.styleable.LeftSectionRecyclerView_decLeftMargin,-1);
            decRightMargin = a.getDimension(R.styleable.LeftSectionRecyclerView_decRightMargin,-1);
            textMarginHor = a.getDimension(R.styleable.LeftSectionRecyclerView_textMarginHor,-1);
            textMarginVer = a.getDimension(R.styleable.LeftSectionRecyclerView_textMarginVer,-1);

            a.recycle();

        }

        decoration = new SectionItemDecoration(context);


        if (bgLineColor!=-1) setBgLineColor(bgLineColor);
        if (itemTextColor!=-1) setItemTextColor(itemTextColor);
        if (lineStrokeWidth!=-1) setLineStrokeWidth(lineStrokeWidth);
        if (itemTextSize!=-1) setItemTextSize(itemTextSize);
        if (decTopMargin!=-1) setDecTopMargin(decTopMargin);
        if (decLeftMargin!=-1) setDecLeftMargin(decLeftMargin);
        if (decRightMargin!=-1) setDecRightMargin(decRightMargin);
        if (textMarginVer!=-1) setTextMarginVer(textMarginVer);
        if (textMarginHor!=-1) setTextMarginHor(textMarginHor);

        addItemDecoration(decoration);

    }

    public void setBgLineColor(int bgLineColor) {
        this.bgLineColor = bgLineColor;
        decoration.setBgLineColor(bgLineColor);
    }

    public void setItemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        decoration.setItemTextColor(itemTextColor);
    }

    public void setLineStrokeWidth(float lineStrokeWidth) {
        this.lineStrokeWidth = lineStrokeWidth;
        decoration.setLineStrokeWidth(lineStrokeWidth);
    }

    public void setItemTextSize(float itemTextSize) {
        this.itemTextSize = itemTextSize;
        decoration.setItemTextSize(itemTextSize);
    }

    public void setDecTopMargin(float decTopMargin) {
        this.decTopMargin = decTopMargin;
        decoration.setDecTopMargin(decTopMargin);
    }

    public void setDecLeftMargin(float decLeftMargin) {
        this.decLeftMargin = decLeftMargin;
        decoration.setDecLeftMargin(decLeftMargin);
    }

    public void setDecRightMargin(float decRightMargin) {
        this.decRightMargin = decRightMargin;
        decoration.setDecRightMargin(decRightMargin);
    }

    public void setTextMarginHor(float textMarginHor) {
        this.textMarginHor = textMarginHor;
        decoration.setTextMarginHor(textMarginHor);
    }

    public void setTextMarginVer(float textMarginVer) {
        this.textMarginVer = textMarginVer;
        decoration.setTextMarginVer(textMarginVer);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (!(layout instanceof GridLayoutManager)) {
            throw new IllegalArgumentException("you must user GridLayoutManager");
        }
        super.setLayoutManager(layout);

        GridLayoutManager manager = (GridLayoutManager) layout;
        spanCount = manager.getSpanCount();
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return spanSizeMap.get(position);
            }
        });

    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        BaseSectionAdapter sectionAdapter = (BaseSectionAdapter) adapter;
        int step = 0;
        for (int i = 0; i < sectionAdapter.getItemCount(); i++) {
            int loc = (++step) % spanCount;
            int spanSize = 1;

            if (i + 1 < sectionAdapter.getItemCount()) {
                SupperSection nextSupperSection = sectionAdapter.getItem(i + 1);
                if (nextSupperSection.isSection()) {
                    spanSize = spanCount + 1 - loc;
                    step = 0;
                }
            }
            if (spanSize>spanCount) {
                spanSize = 1;
            }
            spanSizeMap.put(i, spanSize);
        }


    }

    @Override
    public void addItemDecoration(ItemDecoration decor) {
        super.addItemDecoration(decor);
    }
}
