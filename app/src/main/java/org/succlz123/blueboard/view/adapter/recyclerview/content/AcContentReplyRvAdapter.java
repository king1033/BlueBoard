package org.succlz123.blueboard.view.adapter.recyclerview.content;

import com.facebook.drawee.view.SimpleDraweeView;

import org.succlz123.blueboard.MyApplication;
import org.succlz123.blueboard.R;
import org.succlz123.blueboard.base.BaseRvViewHolder;
import org.succlz123.blueboard.model.bean.acfun.AcContentReply;
import org.succlz123.blueboard.model.utils.common.OkUtils;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * Created by succlz123 on 15/8/4.
 */
public class AcContentReplyRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext = MyApplication.getInstance().getApplicationContext();
    private ArrayList<AcContentReply.DataEntity.Entity> mAcContentReply;
    private OnClickListener mOnClickListener;

    public interface OnClickListener {
        void onClick(View view, int position, String userId);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public ArrayList<AcContentReply.DataEntity.Entity> getmAcContentReply() {
        return mAcContentReply;
    }

    public void setContentReply(ArrayList<AcContentReply.DataEntity.Entity> acContentReply) {
        this.mAcContentReply = acContentReply;
        notifyDataSetChanged();
    }

    public void addContentReply(ArrayList<AcContentReply.DataEntity.Entity> acContentReply) {
        this.mAcContentReply.addAll(acContentReply);
        notifyDataSetChanged();
    }

    public class TitleInfoViewHolder extends BaseRvViewHolder {
        private TextView tvName;
        private TextView tvTime;
        private TextView tvReply;
        private SimpleDraweeView userImg;
        private LinearLayout linearLayout;
        private CardView cardView;

        public TitleInfoViewHolder(View itemView) {
            super(itemView);
            tvName = f(itemView, R.id.ac_rv_content_reply_name_tv);
            tvTime = f(itemView, R.id.ac_rv_content_reply_time_tv);
            tvReply = f(itemView, R.id.ac_rv_content_reply_reply_tv);
            userImg = f(itemView, R.id.ac_rv_content_reply_user_img);
            linearLayout = f(itemView, R.id.quote_reply_linear_layout);
            cardView = f(itemView, R.id.cv_content_reply);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View titleInfoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ac_rv_cardview_content_reply, parent, false);

        return new TitleInfoViewHolder(titleInfoView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mAcContentReply != null) {
            AcContentReply.DataEntity.Entity entity = mAcContentReply.get(position);

            if (entity == null) {
                return;
            }

            if (entity.getAvatar() != null) {
                ((TitleInfoViewHolder) holder).userImg.setImageURI(Uri.parse(entity.getAvatar()));
            }

            if (entity.getUsername() != null) {
                ((TitleInfoViewHolder) holder).tvName.setText("#" + (entity.getFloor() + " " + entity.getUsername()));
            }

            ((TitleInfoViewHolder) holder).tvTime.setText(OkUtils.getDateToString(entity.getTime()));

            if (entity.getContent() != null) {
                ((TitleInfoViewHolder) holder).tvReply.setText(Html.fromHtml(entity.getContent()));
            }

            ((TitleInfoViewHolder) holder).linearLayout.removeAllViews();

            int deep = entity.getDeep();
            int height = Math.min(3, deep);

            List<View> viewList = new ArrayList<>();

            while (height > 0) {
                entity = entity.getQuoteReply();

                if (entity == null) {
                    return;
                }

                TextView floorAndNameTv = new TextView(mContext);
                LinearLayout.LayoutParams floorAndNameTvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                floorAndNameTvLp.setMargins(0, OkUtils.dp2px(mContext, 8f), 0, 0);
                floorAndNameTv.setLayoutParams(floorAndNameTvLp);
                floorAndNameTv.setTextSize(12);
                floorAndNameTv.setTextColor(mContext.getResources().getColor(R.color.font_blue));
                floorAndNameTv.setText("   #" + entity.getFloor() + " " + entity.getUsername());

                TextView quoteContentTv = new TextView(MyApplication.getInstance().getApplicationContext());
                LinearLayout.LayoutParams quoteContentTvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                quoteContentTvLp.setMargins(0, OkUtils.dp2px(mContext, 4f), 0, OkUtils.dp2px(mContext, 8f));
                quoteContentTv.setLayoutParams(quoteContentTvLp);
                quoteContentTv.setTextColor(mContext.getResources().getColor(R.color.font_light));
                quoteContentTv.setText("   " + Html.fromHtml(entity.getContent()));

                View driverView = new View(mContext);
                LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        OkUtils.dp2px(mContext, 1f));

                driverView.setLayoutParams(viewLayoutParams);
                driverView.setBackgroundResource(R.color.android_base);

                viewList.add(driverView);
                viewList.add(quoteContentTv);
                viewList.add(floorAndNameTv);

                height--;
            }

            ListIterator<View> iterator = viewList.listIterator(viewList.size());
            while (iterator.hasPrevious()) {
                ((TitleInfoViewHolder) holder).linearLayout.addView(iterator.previous());
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mAcContentReply != null) {
            return mAcContentReply.size();
        }
        return 0;
    }
}
