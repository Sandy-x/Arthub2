package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_user_competetionlists;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.widget.Toast.LENGTH_SHORT;

public class DetailActivity_PencilColor extends AppCompatActivity implements PaymentResultListener {

    ImageView image,upimage;
    TextView lastdate,fee,topic,pntext,compPCA_id;
    Button uploadimagebtn;
    EditText email,name;
    private Uri imageuri;
    Dialog dialog;
    ProgressDialog progressDialog;

    ConstraintLayout partipate;

    ProgressBar progressBar;

    FirebaseFirestore database;
    private DatabaseReference databaseReference1,databaseReference2,databaseReference3;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__pencil_color);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        Checkout.preload(getApplicationContext());
        Checkout.clearUserData(getApplicationContext());

        progressDialog = new ProgressDialog(DetailActivity_PencilColor.this);

        image = findViewById(R.id.imagepassfrom_recycledacp);
        fee = findViewById(R.id.entryfeedrawing_recycledacp);
        lastdate = findViewById(R.id.lastdatedrawing_recycledacp);
        topic = findViewById(R.id.topicdrawing_recycledacp);
        partipate = findViewById(R.id.participatedacp_btn);
        upimage = findViewById(R.id.uploadedimg_dacp);
        email = findViewById(R.id.email_dacp);
        name = findViewById(R.id.fullname_dacp);
        compPCA_id = findViewById(R.id.compPCA_id);
        uploadimagebtn = findViewById(R.id.upload_imagebtndacp);
        dialog = new Dialog(DetailActivity_PencilColor.this);
        dialog.setContentView(R.layout.custom_dialogbox);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_gradient));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Button ok = dialog.findViewById(R.id.continue_cdbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent in = new Intent(DetailActivity_PencilColor.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });

        database = FirebaseFirestore.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Particular_parti_lists");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("All_participants_lists");
        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Results");

        uploadimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(DetailActivity_PencilColor.this)
                        .crop()
                        .maxResultSize(1080, 1080)
                        .start();

            }
        });

        //drawing
        Intent i = getIntent();
        String imagee = i.getStringExtra("imagecp");
        Picasso.get().load(imagee).into(image);
        fee.setText(getIntent().getStringExtra("feecp"));
        compPCA_id.setText(getIntent().getStringExtra("compID"));
        topic.setText(getIntent().getStringExtra("topiccp"));
        lastdate.setText(getIntent().getStringExtra("last_datecp"));



        progressBar = findViewById(R.id.progressbar_tb);
        pntext = findViewById(R.id.textViewpn);

        partipate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String Name = name.getText().toString();
                String Email = email.getText().toString();
                if (Name.isEmpty()){
                    name.setError("Please Enter Your Name");
                }else if (Email.isEmpty()){
                    email.setError("Please Enter Your Email");
                }else{

                    progressBar.setVisibility(View.VISIBLE);
                    pntext.setVisibility(View.INVISIBLE);
                    makepayment();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageuri = data.getData();
        upimage.setImageURI(imageuri);
        upimage.setVisibility(View.VISIBLE);
    }

    private void makepayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_QjrnrxuGkMeObi");
        checkout.setImage(R.drawable.logo);

        String i = fee.getText().toString();
        int a = Integer.parseInt(String.valueOf(i));
        int ui = 100;
        int amount = a*ui;


        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Art Hub");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount",amount);//pass amount in currency subunits amountx100
            options.put("prefill.email", "");
            options.put("prefill.contact","");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);
            checkout.open(activity, options);

        } catch(Exception e) {

            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onPaymentSuccess(String s) {

        saveorder();
        progressBar.setVisibility(View.INVISIBLE);
        pntext.setVisibility(View.VISIBLE);
    }



    @Override
    public void onPaymentError(int i, String s) {
        progressBar.setVisibility(View.INVISIBLE);
        pntext.setVisibility(View.VISIBLE);

        Toast.makeText(this, "Payment Failed!", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveorder() {

        progressDialog.setMessage("Please Wait While Registration...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Calendar cdate =Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String savedate = currentdate.format(cdate.getTime());
        String RegisteredDate = savedate;


        String Email = email.getText().toString();
        String Name = name.getText().toString();
        String Topic = topic.getText().toString();
        String Fees = fee.getText().toString();
        String LastDate = lastdate.getText().toString();
        String PaymentStatus = "Paid";
        String Poster_url = getIntent().getStringExtra("imagecp");
        String Category = "Color Pencil";
        String Compid = compPCA_id.getText().toString();

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(imageuri != null){
            StorageReference fileref = reference.child("All_comp_images").child(System.currentTimeMillis()+"."+getFileExtension(imageuri));
            fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String Comp_ImageUrl = uri.toString();
                            All_user_competetionlists user = new All_user_competetionlists( Category,Compid,Email, Name,Fees,LastDate,Category,PaymentStatus,Topic,Poster_url,Comp_ImageUrl,RegisteredDate);
                            databaseReference1.child("colorPencil").child(uid).push().setValue(user);
                            databaseReference2.child(uid).push().setValue(user);
                            databaseReference3.child("colorPencil").child(uid).push().setValue(user);
                            dialog.show();
                            progressDialog.dismiss();
                        }
                    });

                }
            });
        }else{
            String Comp_ImageUrl = "Image not Uploaded";
            All_user_competetionlists user = new All_user_competetionlists(Category,Compid, Email, Name,Fees,LastDate,Category,PaymentStatus,Topic,Poster_url,Comp_ImageUrl,RegisteredDate);
            databaseReference1.child("colorPencil").child(uid).push().setValue(user);
            databaseReference2.child(uid).push().setValue(user);
            databaseReference3.child("colorPencil").child(uid).push().setValue(user);
            dialog.show();
            progressDialog.dismiss();

        }
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}