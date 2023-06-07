package com.mathi.wmkart.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mathi.wmkart.R;
import com.mathi.wmkart.adapter.ProductAdapter;
import com.mathi.wmkart.domain.Product;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    FirebaseFirestore db;
    private List<Product> mProductList;
    private ProductAdapter mProductAdapter;
    private RecyclerView mProductRecyclerView;
    private EditText searchBarEditText;
    private TextView totalAmountTextView;
    private String searchQuery;
    private CheckBox selectAllCheckBox;
    private TextView selectedItemCountTextView;
    private int selectedProductCount;

    private double totalAmount;

    public HomeFragment() {
        // Required empty public constructor
    }

    public HomeFragment(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchBarEditText = view.findViewById(R.id.edit_search);
        searchBarEditText.setText(searchQuery);
        totalAmountTextView = view.findViewById(R.id.text_total_amount);
        selectAllCheckBox = view.findViewById(R.id.select_all_check);
        selectedItemCountTextView = view.findViewById(R.id.select_all_text);
        db = FirebaseFirestore.getInstance();
        mProductRecyclerView=view.findViewById(R.id.product_recycler_view);

        //For Product
        mProductList =new ArrayList<>();
        mProductAdapter=new ProductAdapter(getContext(),mProductList);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mProductRecyclerView.setAdapter(mProductAdapter);
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.i("TAG",document.toString());
                                Product product = productDocumentMapper(document);
                                mProductList.add(product);
                                mProductAdapter.notifyDataSetChanged();
                                System.out.println(product.toString());
                            }
                            updateTotalAmount();
                            updateSelectedItemCount();
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        updateTotalAmount();
        updateSelectedItemCount();
        updateSelectAllCheckBox();
        selectAllCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = selectAllCheckBox.isChecked();
                for (Product product : mProductList) {
                    product.setSelected(isChecked);
                }
                mProductAdapter.notifyDataSetChanged();
                updateTotalAmount();
                updateSelectedItemCount();
                updateSelectAllCheckBox();
            }
        });
        mProductAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Product product = mProductList.get(position);
                product.setSelected(!product.isSelected());
                mProductAdapter.notifyItemChanged(position);
                updateTotalAmount();
                updateSelectedItemCount();
                updateSelectAllCheckBox();
            }
        });
        return view;
    }

    private Product productDocumentMapper(QueryDocumentSnapshot document){
        String productName = document.getString("product_name");
        String description = document.getString("description");
        String imageUrl = document.getString("image");
        double retailPrice = document.getDouble("retail_price");
        boolean selected = true ;
        return new Product(productName,description,retailPrice,imageUrl,selected);
    }

    private void updateTotalAmount() {
        totalAmount = 0;
        for (Product product : mProductList) {
            if (product.isSelected()) {
                totalAmount += product.getRetailPrice();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        totalAmountTextView.setText(MessageFormat.format("Total Amount: ${0}", decimalFormat.format(totalAmount)));
    }

    private void updateSelectedItemCount() {
        selectedProductCount = 0;
        for (Product product : mProductList) {
            if (product.isSelected()) {
                selectedProductCount++;
            }
        }
        selectedItemCountTextView.setText(MessageFormat.format("Selected: {0} / {1}", selectedProductCount,mProductList.size() ));
    }

    private void updateSelectAllCheckBox() {
        boolean allSelected = true;
        for (Product product : mProductList) {
            if (!product.isSelected()) {
                allSelected = false;
                break;
            }
        }
        selectAllCheckBox.setOnCheckedChangeListener(null);
        selectAllCheckBox.setChecked(allSelected);
        selectAllCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (Product product : mProductList) {
                    product.setSelected(isChecked);
                }
                mProductAdapter.notifyDataSetChanged();
                updateTotalAmount();
                updateSelectedItemCount();
            }
        });
    }

}