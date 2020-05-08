package com.example.wishlist;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wishlist.Classesapp.ListAndProduct;
import com.example.wishlist.Classesapp.ListAndProductRepository;
import com.example.wishlist.Classesapp.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private ArrayList<Product> dataset;
    Context ActivityContext;
    Context ApplicationContext;
    private int Num_List;



    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_card_view_product_name;
        TextView tv_card_view_product_price;
        Button btn_card_view_product_title;
        LinearLayout ll_card_product;
        RatingBar note_product;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_card_view_product_name = (TextView) itemView.findViewById(R.id.tv_card_view_product_name);
            this.tv_card_view_product_price = (TextView) itemView.findViewById(R.id.tv_card_view_product_price);
            note_product=(RatingBar)itemView.findViewById(R.id.view_product_rank);
            this.btn_card_view_product_title = (Button) itemView.findViewById(R.id.btn_card_view_product_title);
            this.ll_card_product =(LinearLayout) itemView.findViewById(R.id.ll_card_product);
        }

    }



    public ProductAdapter(ArrayList<Product> dataset, int num_List,  Context ActivityContext, Context ApplicationContext) {
        this.dataset = dataset;
        this.ActivityContext = ActivityContext;
        this.ApplicationContext = ApplicationContext;
        Num_List = num_List;
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        ProductAdapter.MyViewHolder myViewHolder = new ProductAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        TextView tv_card_view_product_name = holder.tv_card_view_product_name;
        TextView tv_card_view_product_price = holder.tv_card_view_product_price;
        RatingBar ratingBar_note=holder.note_product;
        Button btn_card_view_title = holder.btn_card_view_product_title;
        LinearLayout ll_card_product = holder.ll_card_product;

        tv_card_view_product_name.setText(dataset.get(position).getName() + "");
        tv_card_view_product_price.setText(Integer.toString(dataset.get(position).getPrix())+"€");
        ratingBar_note.setRating( dataset.get(position).getNote());


        final int pos = position;
        final int num = Num_List;

        if((Activity) ActivityContext instanceof WishlistAddProductActivity)
        {
            setOnClickAdd(ll_card_product,pos,num);
        }
        else if((Activity) ActivityContext instanceof ViewWishlistProductActivity)
        {
            setOnClickViewWishlistProduct(ll_card_product,pos,num);
        }
        else if((Activity) ActivityContext instanceof  ViewProductActivity)
        {
            setOnClickViewProductActivity(ll_card_product,pos);
        }

    }

    ////Les différents onClick Listener selon l'activité
    ////
    ////Pour WishlistAddProduct
    private void setOnClickAdd(final LinearLayout linearLayout, final int pos,final int num)
    {

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = dataset.get(pos).getName();


                ListAndProductRepository listAndProductRepository = new ListAndProductRepository(ApplicationContext);
                if (listAndProductRepository.isObjectInList(name, num)) {
                    Toast.makeText(ActivityContext,"Product Already in list",Toast.LENGTH_LONG).show();
                } else
                {
                    ListAndProduct listAndProduct = new ListAndProduct(Num_List, name);
                    listAndProductRepository.InsertTask(listAndProduct);
                }
            }
        });
    }
    ////Pour ViewWishlistProduct
    private void setOnClickViewWishlistProduct(final LinearLayout ll, final int pos,final  int num)
    {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = dataset.get(pos).getName();
                ListAndProduct listAndProduct = new ListAndProduct(num,name);
                generate_delete_dialog(listAndProduct);
            }
        });
    }
    ///

    ////Pour ViewProductActivity
    private void setOnClickViewProductActivity(final LinearLayout ll, final int pos)
    {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdateProductActivity(pos);
            }
        });
    }
    ///

    public void openUpdateProductActivity(int pos)
    {
        Intent intent = new Intent(ActivityContext, UpdateProductActivity.class);
        intent.putExtra("Product_name",dataset.get(pos).getName());
        intent.putExtra("Product_lien",dataset.get(pos).getLien());
        intent.putExtra("Product_prix",dataset.get(pos).getPrix());
        intent.putExtra("Product_categorie",dataset.get(pos).getCategorie());
        intent.putExtra("Product_note",dataset.get(pos).getNote());
        ActivityContext.startActivity(intent);
    }

    ///Nécessaire, je crois
    @Override
    public int getItemCount(){
        return dataset.size();
    }

    ///Crée l'écran de "Voulez vous supprimer"
    public void generate_delete_dialog(ListAndProduct lAP)
    {
        final ListAndProduct listAndProduct = lAP;
        if(Num_List == -1)
            Toast.makeText(ActivityContext,"FUCK YOU "+ Integer.toString(Num_List),Toast.LENGTH_LONG).show();


        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContext); //do not write getapplicationContextg
        builder.setTitle("WARNING");
        builder.setMessage("Are you sure to delete ?" + "\n Product : " + listAndProduct.getProduct_name());
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListAndProductRepository listAndProductRepository = new ListAndProductRepository(ApplicationContext);
                listAndProductRepository.DeleteTask(listAndProduct);
                ((ViewWishlistProductActivity)ActivityContext).Reload();
               /// finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setIcon(android.R.drawable.ic_delete);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
