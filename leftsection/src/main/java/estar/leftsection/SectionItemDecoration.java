package estar.leftsection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import estar.leftsection.utils.PaintUtil;

/**
 * Created by xueliang on 2018/3/14.
 */

public class SectionItemDecoration extends RecyclerView.ItemDecoration {

    private int bgLineColor;
    private int itemTextColor;
    private float lineStrokeWidth;
    private float itemTextSize;

    private float decTopMargin;
    private float decLeftMargin;
    private float decRightMargin;
    private float textMarginHor;
    private float textMarginVer;

    private Paint paint = new Paint();
    private Paint textPaint = new Paint();


    private float left;


    public SectionItemDecoration(Context context) {

        bgLineColor = context.getResources().getColor(R.color.bgLineColor);
        itemTextColor = context.getResources().getColor(R.color.itemTextColor);
        lineStrokeWidth = context.getResources().getDimension(R.dimen.lineStorkeWidth);
        itemTextSize = context.getResources().getDimension(R.dimen.itemTextSize);

        decTopMargin = context.getResources().getDimension(R.dimen.decTopMargin);
        decLeftMargin = context.getResources().getDimension(R.dimen.decLeftMargin);
        decRightMargin = context.getResources().getDimension(R.dimen.decRightMargin);
        textMarginHor = context.getResources().getDimension(R.dimen.textMarginHor);
        textMarginVer = context.getResources().getDimension(R.dimen.textMarginVer);

        init();

    }


    public void setTextMarginHor(float textMarginHor) {
        this.textMarginHor = textMarginHor;
        init();
    }

    public void setTextMarginVer(float textMarginVer) {
        this.textMarginVer = textMarginVer;
        init();
    }

    public void setBgLineColor(int bgLineColor) {
        this.bgLineColor = bgLineColor;
        init();
    }

    public void setItemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        init();
    }

    public void setLineStrokeWidth(float lineStrokeWidth) {
        this.lineStrokeWidth = lineStrokeWidth;
        init();
    }

    public void setItemTextSize(float itemTextSize) {
        this.itemTextSize = itemTextSize;
        init();
    }

    public void setDecTopMargin(float decTopMargin) {
        this.decTopMargin = decTopMargin;
        init();
    }

    public void setDecLeftMargin(float decLeftMargin) {
        this.decLeftMargin = decLeftMargin;
        init();
    }

    public void setDecRightMargin(float decRightMargin) {
        this.decRightMargin = decRightMargin;
        init();
    }

    private void init() {

        paint.setColor(bgLineColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineStrokeWidth);
        paint.setAntiAlias(true);

        textPaint.setColor(itemTextColor);
        textPaint.setTextSize(itemTextSize);
        textPaint.setAntiAlias(true);
        float textWidth = PaintUtil.getTextWidth(textPaint, "图");
        left = decLeftMargin + textWidth + textMarginHor * 2;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int dex4S = (int) (decTopMargin / 4);
        int left = (int) (this.left - dex4S);
        int paddingLeft = parent.getPaddingLeft();
        int paddingRight = parent.getPaddingRight();
        int paddingBottom = parent.getPaddingBottom();

        if (paddingLeft < this.left) {
            paddingLeft = (int) this.left;
        }
        if (paddingRight < dex4S) {
            paddingRight = dex4S;
        }
        if (paddingBottom < dex4S) {
            paddingBottom = dex4S;
        }
        parent.setPadding(paddingLeft, parent.getPaddingTop(), paddingRight, paddingBottom);

        int right = parent.getWidth() - (paddingRight - dex4S);

        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        BaseSectionAdapter adapter = (BaseSectionAdapter) parent.getAdapter();

        int type = adapter.getItemViewType(0);
        RecyclerView.ViewHolder holder = adapter.createViewHolder(parent, type);
        View itemView = holder.itemView;
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(lp);
        }
        int heightSize = View.MeasureSpec.getSize(lp.height);


        int firstVisibleItem = manager.findFirstVisibleItemPosition();
        int lastVisibleItem = manager.findLastVisibleItemPosition();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);

            SupperSection supperSection = adapter.getItem(position);
            int bottom = 0;
            int top = childView.getTop() - dex4S;


            boolean isC = false;
            if (supperSection.isSection()) {
                top = childView.getTop() - dex4S;
                isC = true;
            } else {
                if (i == 0) {
                    top = childView.getTop() - heightSize;
                    isC = true;
                }
            }
            if (isC) {
                for (int i1 = position + 1; i1 < adapter.getItemCount(); i1++) {
                    if (adapter.getItem(i1).isSection()) {
                        int index = i1 - firstVisibleItem - 1;
                        if (index < 0) {
                            index = 0;
                        }
                        if (index <= lastVisibleItem) {
                            bottom = parent.getChildAt(index).getBottom() + dex4S;
                        }
                        if (bottom < top) {
                            bottom = parent.getChildAt(index + 1).getBottom() + dex4S;
                        }

                        break;
                    }
                }

                if (bottom == 0) {
                    if (lastVisibleItem == adapter.getItemCount() - 1) {
                        bottom = parent.getChildAt(lastVisibleItem - firstVisibleItem).getBottom() + dex4S;
                    } else {
                        bottom = parent.getHeight() - dex4S;
                    }
                }

                Rect rect = new Rect(left, top, right, bottom);


                RectF rectF = new RectF(rect);
                c.drawRect(rectF, paint);

                float textHeight = PaintUtil.getTextHeight(textPaint, "外");
                float textWidth = PaintUtil.getTextWidth(textPaint, "外");

                textPaint.setColor(bgLineColor);

                int textLength = supperSection.getSectionText().length();

                Path path = new Path();
                RectF rectF1 = new RectF(left - textWidth - textMarginHor * 2, top - lineStrokeWidth / 2, left, top + textHeight * textLength + textMarginVer * 2);
                path.addRoundRect(rectF1, new float[]{8, 8, 0, 0, 0, 0, 8, 8}, Path.Direction.CW);

                c.drawPath(path, textPaint);

                textPaint.setColor(itemTextColor);
                for (int i1 = 0; i1 < supperSection.getSectionText().length(); i1++) {
                    char s = supperSection.getSectionText().charAt(i1);
                    c.drawText(s + "", left - textWidth - textMarginHor, top + textHeight * (1 + i1) + textMarginVer, textPaint);
                }
            }


        }

    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int index = manager.getSpanSizeLookup().getSpanIndex(position, manager.getSpanCount());
        BaseSectionAdapter adapter = (BaseSectionAdapter) parent.getAdapter();
        int top = 0;
        int left = 0;
        int right = 0;
        if (position > 0 && adapter.getItem(position - index).isSection()) {
            top = (int) this.decTopMargin;
        }

        outRect.set(left, top, right, 0);

    }
}
