package com.example.ecommermattevan.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommermattevan.R;
import com.example.ecommermattevan.ui.Adapter.ProductMainAdapter;
import com.example.ecommermattevan.ui.Model.ProductsMainModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ProductMainAdapter.ItemListener {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    ArrayList<ProductsMainModel> arrayList = new ArrayList<>();

        public HomeFragment() {

        }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        arrayList.add(new ProductsMainModel("Item 1", R.drawable.ic_baseline_home_24, "#09A9FF"));
        arrayList.add(new ProductsMainModel("Item 2", R.drawable.ic_baseline_home_24, "#3E51B1"));
        arrayList.add(new ProductsMainModel("Item 3", R.drawable.ic_baseline_home_24, "#673BB7"));
        arrayList.add(new ProductsMainModel("Item 4", R.drawable.ic_baseline_home_24, "#4BAA50"));
        arrayList.add(new ProductsMainModel("Item 5", R.drawable.ic_baseline_home_24, "#F94336"));
        arrayList.add(new ProductsMainModel("Item 6", R.drawable.ic_baseline_home_24, "#0A9B88"));

        ProductMainAdapter adapter = new ProductMainAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        return root;
    }

    @Override
    public void onItemClick(ProductsMainModel item) {

        Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }
}