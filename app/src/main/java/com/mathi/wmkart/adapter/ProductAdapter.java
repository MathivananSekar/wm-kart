package com.mathi.wmkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mathi.wmkart.R;
import com.mathi.wmkart.domain.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> mProductList;

    private OnItemClickListener listener;
    public ProductAdapter(Context context, List<Product> mProductList) {
        this.context=context;
        this.mProductList=mProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentItem = mProductList.get(position);
        holder.nameView.setText(currentItem.getProductName());
        holder.priceView.setText(String.format("%s", currentItem.getRetailPrice()));
        holder.descriptionView.setText(currentItem.getDescription());
        holder.productCheckbox.setChecked(currentItem.isSelected());
        Glide.with(context).load(currentItem.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView nameView;
        private TextView descriptionView;
        private TextView priceView;

        private CheckBox productCheckbox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            nameView = itemView.findViewById(R.id.product_name);
            descriptionView = itemView.findViewById(R.id.product_description);
            priceView = itemView.findViewById(R.id.product_price);
            productCheckbox = itemView.findViewById(R.id.checkbox_product);
            productCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
