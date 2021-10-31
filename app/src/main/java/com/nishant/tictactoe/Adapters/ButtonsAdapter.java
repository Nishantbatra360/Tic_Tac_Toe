package com.nishant.tictactoe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishant.tictactoe.Model.Btn;
import com.nishant.tictactoe.Enums.ButtonAction;
import com.nishant.tictactoe.R;

import java.util.ArrayList;

public class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.ViewHolder> {
    private static ArrayList<Btn> btnArrayList;
    OnButtonListener onButtonListener;

    public ButtonsAdapter(ArrayList<Btn> btnArrayList, OnButtonListener onButtonListener) {
        this.btnArrayList = btnArrayList;
        this.onButtonListener = onButtonListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.button_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onButtonListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btn.setText(btnArrayList.get(position).getButtonLabel());
    }

    @Override
    public int getItemCount() {
        return btnArrayList.size();
    }

    public interface OnButtonListener {
        void onButtonClick(ButtonAction buttonAction);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button btn;
        OnButtonListener onButtonListener;

        public ViewHolder(@NonNull View itemView, OnButtonListener onButtonListener) {
            super(itemView);
            this.onButtonListener=onButtonListener;
            btn=itemView.findViewById(R.id.button_adapter_btn);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onButtonListener.onButtonClick(btnArrayList.get(getAdapterPosition()).getButtonAction());
        }
    }
}
