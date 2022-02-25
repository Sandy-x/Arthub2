package com.firstapp.arthub.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firstapp.arthub.DeatailCompetetion;
import com.firstapp.arthub.R;
import com.firstapp.arthub.detail_helpandsupport;
import com.firstapp.arthub.models.HelpandSupportModel;
import com.firstapp.arthub.models.drawinglistModel;

import java.util.List;

public class HelpandSupportAdapter extends RecyclerView.Adapter<HelpandSupportAdapter.ViewHolder> {
    private Context context;
    private List<HelpandSupportModel> helpandSupportModels;

    public HelpandSupportAdapter(Context context,List<HelpandSupportModel> helpandSupportModels) {
        this.context = context;
        this.helpandSupportModels =  helpandSupportModels;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.helpandsupportlists,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final HelpandSupportModel temp = helpandSupportModels.get(position);

        holder.question.setText(helpandSupportModels.get(position).getQuestion());
        holder.line1.setText(helpandSupportModels.get(position).getLin1());
        holder.line2.setText(helpandSupportModels.get(position).getLin2());
        holder.line3.setText(helpandSupportModels.get(position).getLin3());
        holder.line4.setText(helpandSupportModels.get(position).getLin4());
        holder.line5.setText(helpandSupportModels.get(position).getLin5());
        holder.line6.setText(helpandSupportModels.get(position).getLin6());
        holder.line7.setText(helpandSupportModels.get(position).getLin7());
        holder.line8.setText(helpandSupportModels.get(position).getLin8());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail_helpandsupport.class);
                intent.putExtra("question",temp.getQuestion());
                intent.putExtra("line1",temp.getLin1());
                intent.putExtra("line2",temp.getLin2());
                intent.putExtra("line3",temp.getLin3());
                intent.putExtra("line4",temp.getLin4());
                intent.putExtra("line5",temp.getLin5());
                intent.putExtra("line6",temp.getLin6());
                intent.putExtra("line7",temp.getLin7());
                intent.putExtra("line8",temp.getLin8());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return helpandSupportModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question, line1,line2,line3,line4,line5,line6,line7,line8;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.questionHS);
            line1 = itemView.findViewById(R.id.line1HS);
            line2 = itemView.findViewById(R.id.line2HS);
            line3 = itemView.findViewById(R.id.line3HS);
            line4 = itemView.findViewById(R.id.line4HS);
            line5 = itemView.findViewById(R.id.line5HS);
            line6 = itemView.findViewById(R.id.line6HS);
            line7 = itemView.findViewById(R.id.line7HS);
            line8 = itemView.findViewById(R.id.line8HS);
            linearLayout = itemView.findViewById(R.id.HSlayout);
        }
    }
}
