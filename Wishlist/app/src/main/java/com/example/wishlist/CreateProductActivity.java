import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wishlist.Classesapp.Product;
import com.example.wishlist.Classesapp.ProductRepository;
import com.example.wishlist.Classesapp.Wishlist;
import com.example.wishlist.Classesapp.WishlistRepository;
import com.example.wishlist.HomeActivity;
import com.example.wishlist.R;

public class CreateProductActivity extends AppCompatActivity {

    EditText edt_product_price, edt_product_name, edt_product_link,edt_product_category;
    Button Create_Product_btn;
    RatingBar ratingBar;

    String name;
    int price;


    private View.OnClickListener CreateProduct_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ProductRepository productRepository = new ProductRepository(getApplicationContext());
            if(edt_product_name.getText().toString().isEmpty() || edt_product_price.getText().toString().isEmpty() ) {

                Toast.makeText(getApplicationContext(),"Please fill details",Toast.LENGTH_LONG).show();

            }
            else if(productRepository.isNameUsed(edt_product_name.getText().toString())){
                Toast.makeText(getApplicationContext(),"Name Already Used",Toast.LENGTH_LONG).show();
            } ///Create Wishlist
            else {
                name = edt_product_name.getText().toString().trim();
                price = Integer.parseInt(edt_product_price.getText().toString().trim());


                Product product = new Product(name,price);
                productRepository.InsertTask(product);

                openHomeActivity();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        edt_product_name = findViewById(R.id.edt_product_name);
        edt_product_price = findViewById(R.id.edt_product_price);
        edt_product_link=findViewById(R.id.edt_product_link);
        Create_Product_btn = findViewById(R.id.create_product_btn);
        edt_product_category=findViewById(R.id.edt_product_category);

        ratingBar =findViewById(R.id.ratingBar);

        Create_Product_btn.setOnClickListener(CreateProduct_listener);

    }

    @Override  //required for back animation
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void openHomeActivity()
    {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // Animation entre écran
    }
}
