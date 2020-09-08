package com.example.bottomnavigation.productdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.model.Comment;
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.data.model.ProductDetails;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.login.LoginSharedViewModel;
import com.example.bottomnavigation.login.LoginSharedViewModelFactory;
import com.example.bottomnavigation.login.LoginStepOneDialogFragment;
import com.example.bottomnavigation.login.LoginStepTwoListener;
import com.example.bottomnavigation.login.di.LoginModule;
import com.example.bottomnavigation.products.di.ProductModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import java.util.List;

import retrofit2.Retrofit;

public class ProductDetailFragment extends Fragment {
    private ImageView avatar;
    private TextView productName;
    private RecyclerView recyclerView;
    private View progressBar;
    private ProductDetailViewModel productDetailViewModel;
    private LoginSharedViewModel sharedViewModel;
    private UserDatabase database = LoginModule.provideUserDatabase();
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private ProductDetailViewModelFactory productDetailViewModelFactory = ProductModule.provideProductDetailViewModelFactory(apiService);
    private LoginRepository loginRepository = LoginModule.provideLoginRepository(apiService, database.userDao());
    private LoginSharedViewModelFactory loginSharedViewModelFactory = LoginModule.provideShareViewModelFactory(loginRepository);
    private String title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productDetailViewModel = new ViewModelProvider(this, productDetailViewModelFactory).get(ProductDetailViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity(), loginSharedViewModelFactory).get(LoginSharedViewModel.class);
        final int id = getArguments().getInt("productId");
        avatar = view.findViewById(R.id.productAvatar);
        productName = view.findViewById(R.id.productName);
        Button commentButton = view.findViewById(R.id.commentButton);
        recyclerView = view.findViewById(R.id.commentRecyclerView);
        progressBar = view.findViewById(R.id.productProgressBar);


        productDetailViewModel.setProductId(id);
        productDetailViewModel.getProductDetail();
        observeProductDetailViewModel();

        sharedViewModel.isLogin.observeSingleEvent(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLogin) {
                if (isLogin) {
                    CommentDialogFragment commentDialogFragment = new CommentDialogFragment(id, title);
                    commentDialogFragment.show(getParentFragmentManager(), "CommentDialogFragment");
                } else {
                    LoginStepOneDialogFragment loginStepOneDialogFragment = new LoginStepOneDialogFragment(new LoginStepTwoListener() {
                        @Override
                        public void onResponse(User user) {
                            CommentDialogFragment commentDialogFragment = new CommentDialogFragment(id, title);
                            commentDialogFragment.show(getParentFragmentManager(), "CommentDialogFragment");
                        }
                    });
                    loginStepOneDialogFragment.show(getParentFragmentManager(), "LoginStepOneDialogFragment");
                }
            }
        });

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.isLogin();
            }
        });

    }

    public void observeProductDetailViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        productDetailViewModel.loadingLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        productDetailViewModel.productDetailLiveData.observe(getViewLifecycleOwner(), new Observer<ProductDetails>() {
            @Override
            public void onChanged(ProductDetails productsList) {
                progressBar.setVisibility(View.GONE);
                productName.setText(productsList.getName());
                title = productsList.getName();
                Glide.with(getContext()).load(productsList.getAvatar()).into(avatar);
            }
        });

        productDetailViewModel.productComment.observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                progressBar.setVisibility(View.GONE);
                showComment(comments);
            }
        });
    }

    public void showComment(List<Comment> comments) {
        ProductCommentAdapter adapter = new ProductCommentAdapter(getContext(), comments);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
