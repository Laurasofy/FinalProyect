package com.edu.proyectofinal.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edu.proyectofinal.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<Products> products;
    private Context context;

    public ProductsAdapter(List<Products> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_titulo.setText(products.get(position).getProduct_name());
        Glide.with(context).load(products.get(position).getProduct_image()).into(holder.iv_portada);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_portada;
        private TextView tv_titulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada=itemView.findViewById(R.id.iv_portada);
            tv_titulo=itemView.findViewById(R.id.tv_titulo);
        }
    }
}
