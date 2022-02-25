package com.firstapp.arthub;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.firstapp.arthub.models.CouponCodeModel;
import com.firstapp.arthub.models.OrderDetailsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Ref;
import java.util.Calendar;
import java.util.UUID;

public class paymentdashb extends AppCompatActivity implements PaymentResultListener {

    ProgressDialog progressDialog,progressDialog2;
    FirebaseFirestore database;
    TextView framecharges,totalcharges,selectedbudgetpay,deliverycharge,makepayment;
    ConstraintLayout framecharges_layout1,selectedbudget_layout1,makepaymentdb_btn;
    TextView Couponcode1,Couponcode20,Couponcode3,Couponcode4,Couponcode5;
    EditText CouponCodeedittext;
    TextView applybtn,successcoupon,failedcoupon,removebtn;
    ProgressBar progressBarmp;
    TextView contact ;
    Dialog dialog;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private DatabaseReference databaseReference1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdashb);

        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        framecharges_layout1 = findViewById(R.id.framecharges_layout1);
        CouponCodeedittext = findViewById(R.id.coupon_dashb);
        applybtn = findViewById(R.id.couponapplybtn);
        Couponcode1 = findViewById(R.id.cctext1);
        successcoupon = findViewById(R.id.succefullytext_dashb);
        failedcoupon = findViewById(R.id.failedtext_dashb);
        deliverycharge = findViewById(R.id.dcharge);
        selectedbudget_layout1 = findViewById(R.id.selectedbudget_layout1);
        framecharges = findViewById(R.id.framechargepaydb);
        removebtn = findViewById(R.id.removebtn);
        makepaymentdb_btn = findViewById(R.id.makepaymentdb_btn);
        Couponcode20 = findViewById(R.id.cctext2);
        Couponcode3 = findViewById(R.id.cctext3);
        Couponcode4 = findViewById(R.id.cctext4);
        Couponcode5 = findViewById(R.id.cctext5);

        makepayment = findViewById(R.id.textViewdashb);
        totalcharges = findViewById(R.id.totalchargespaydb);
        progressBarmp = findViewById(R.id.progressbar_tb);
        selectedbudgetpay = findViewById(R.id.selectedbudgetpaydb);
        framecharges.setText(getIntent().getStringExtra("FrameChrages"));
        totalcharges.setText(getIntent().getStringExtra("TotalCharge"));
        selectedbudgetpay.setText(getIntent().getStringExtra("SelectedBudget"));

        progressDialog = new ProgressDialog(paymentdashb.this);
        progressDialog2 = new ProgressDialog(paymentdashb.this);

        database = FirebaseFirestore.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Orders");

        dialog = new Dialog(paymentdashb.this);
        dialog.setContentView(R.layout.custom_dialogdashb);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_gradient));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Button ok = dialog.findViewById(R.id.continue_cdbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent in = new Intent(paymentdashb.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });




        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               CouponCodeedittext.setText(null);
               framecharges.setText(getIntent().getStringExtra("FrameChrages"));
               totalcharges.setText(getIntent().getStringExtra("TotalCharge"));
               selectedbudgetpay.setText(getIntent().getStringExtra("SelectedBudget"));
               selectedbudget_layout1.setVisibility(View.VISIBLE);
                String a = getIntent().getStringExtra("IncludeFrame");
                if (a!=null){
                    framecharges_layout1.setVisibility(View.VISIBLE);
                }
                successcoupon.setVisibility(View.GONE);
                failedcoupon.setVisibility(View.GONE);
                removebtn.setVisibility(View.GONE);
                applybtn.setVisibility(View.VISIBLE);

            }
        });

        String a = getIntent().getStringExtra("IncludeFrame");
        if (a==null){
            framecharges_layout1.setVisibility(View.GONE);
        }




        progressDialog = new ProgressDialog(paymentdashb.this);
        database = FirebaseFirestore.getInstance();
        database.collection("CouponCode").document("1")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CouponCodeModel user = documentSnapshot.toObject(CouponCodeModel.class);
                Couponcode1.setText(String.valueOf(user.getName().toString()));


            }
        });
        database.collection("CouponCode").document("2")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CouponCodeModel user = documentSnapshot.toObject(CouponCodeModel.class);
                Couponcode20.setText(String.valueOf(user.getName().toString()));
            }
        });
        database.collection("CouponCode").document("3")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CouponCodeModel user = documentSnapshot.toObject(CouponCodeModel.class);
                Couponcode3.setText(String.valueOf(user.getName().toString()));
            }
        });
        database.collection("CouponCode").document("4")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CouponCodeModel user = documentSnapshot.toObject(CouponCodeModel.class);
                Couponcode4.setText(String.valueOf(user.getName().toString()));
            }
        });
        database.collection("CouponCode").document("5")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                CouponCodeModel user = documentSnapshot.toObject(CouponCodeModel.class);
                Couponcode5.setText(String.valueOf(user.getName().toString()));
            }
        });


        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                String ac = selectedbudgetpay.getText().toString();
                String cccode1 = Couponcode1.getText().toString();
                String cccode3 = Couponcode3.getText().toString();
                String cccode4 = Couponcode4.getText().toString();
                String cccode5 = Couponcode5.getText().toString();
                String cccode20 = Couponcode20.getText().toString();
                String cc = CouponCodeedittext.getText().toString();
                String mnop = totalcharges.getText().toString();

                if(cc.isEmpty()){
                    CouponCodeedittext.setError("Enter coupon code");
                    failedcoupon.setVisibility(View.GONE);
                    successcoupon.setVisibility(View.GONE);
                    applybtn.setText("APPLY");
                    progressDialog.dismiss();
                }else if(cc.contains(cccode1)){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successcoupon.setVisibility(View.VISIBLE);
                            failedcoupon.setVisibility(View.GONE);
                            totalcharges.setText("199");
                            framecharges_layout1.setVisibility(View.GONE);
                            deliverycharge.setText("199");
                            progressDialog.dismiss();
                            removebtn.setVisibility(View.VISIBLE);

                            applybtn.setVisibility(View.GONE);
                            selectedbudget_layout1.setVisibility(View.GONE);
                        }
                    },1000);

                }else if(cc.contains(cccode20)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successcoupon.setVisibility(View.VISIBLE);
                            failedcoupon.setVisibility(View.GONE);
                            Integer cx = Integer.parseInt(mnop);
                            float cd = (float) (0.2*cx);
                            Integer ef = (int) cd;
                            Integer fg = cx-ef;
                            String pop = String.valueOf(fg);
                            totalcharges.setText(pop);
                            progressDialog.dismiss();
                            removebtn.setVisibility(View.VISIBLE);
                            applybtn.setVisibility(View.GONE);
                        }
                    },1000);
                }else if(cc.contains(cccode3)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successcoupon.setVisibility(View.VISIBLE);
                            failedcoupon.setVisibility(View.GONE);
                            Integer cx = Integer.parseInt(mnop);
                            float cd = (float) (0.2*cx);
                            Integer ef = (int) cd;
                            Integer fg = cx-ef;
                            String pop = String.valueOf(fg);
                            totalcharges.setText(pop);
                            progressDialog.dismiss();
                            removebtn.setVisibility(View.VISIBLE);
                            applybtn.setVisibility(View.GONE);
                        }
                    },1000);
                }else if(cc.contains(cccode4)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successcoupon.setVisibility(View.VISIBLE);
                            failedcoupon.setVisibility(View.GONE);
                            Integer cx = Integer.parseInt(mnop);
                            float cd = (float) (0.2*cx);
                            Integer ef = (int) cd;
                            Integer fg = cx-ef;
                            String pop = String.valueOf(fg);
                            totalcharges.setText(pop);
                            progressDialog.dismiss();
                            removebtn.setVisibility(View.VISIBLE);
                            applybtn.setVisibility(View.GONE);
                        }
                    },1000);
                }
                else if(cc.contains(cccode5)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            successcoupon.setVisibility(View.VISIBLE);
                            failedcoupon.setVisibility(View.GONE);
                            Integer cx = Integer.parseInt(mnop);
                            float cd = (float) (0.2*cx);
                            Integer ef = (int) cd;
                            Integer fg = cx-ef;
                            String pop = String.valueOf(fg);
                            totalcharges.setText(pop);
                            progressDialog.dismiss();
                            removebtn.setVisibility(View.VISIBLE);
                            applybtn.setVisibility(View.GONE);
                        }
                    },1000);
                }else{
                    progressDialog.dismiss();
                    CouponCodeedittext.setError("Invalid Code!");
                    successcoupon.setVisibility(View.GONE);
                }
            }
        });


        makepaymentdb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarmp.setVisibility(View.VISIBLE);
                makepayment.setVisibility(View.INVISIBLE);
                makepaymentt();
            }
        });


    }


    private void makepaymentt() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_QjrnrxuGkMeObi");
        checkout.setImage(R.drawable.logo);
        int amount = 19900;
        String email = getIntent().getStringExtra("Email");
        String contactt = getIntent().getStringExtra("Phone_no");


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
            options.put("prefill.email", email);
            options.put("prefill.contact",contactt);
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
        progressBarmp.setVisibility(View.INVISIBLE);
        makepayment.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed!", Toast.LENGTH_LONG).show();
        progressBarmp.setVisibility(View.INVISIBLE);
        makepayment.setVisibility(View.VISIBLE);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveorder() {

        progressDialog2.setMessage("Please Wait While Placing Your Order");
        progressDialog2.setCanceledOnTouchOutside(false);
        progressDialog2.show();

        Calendar cdate =Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String savedate = currentdate.format(cdate.getTime());
        String OrderDate = savedate;
        String FirstName =  getIntent().getStringExtra("FirstName");
        String LastName =   getIntent().getStringExtra("LastName");
        String Email =  getIntent().getStringExtra("Email");
        String Phone_no =  getIntent().getStringExtra("Phone_no");
        String Whatsapp_no =  getIntent().getStringExtra("Whatsapp_no");
        String CateGory =  getIntent().getStringExtra("CateGory");
        String PaperSize =  getIntent().getStringExtra("PaperSize");
        String SelectedBudget =  getIntent().getStringExtra("SelectedBudget");
        String ReferenceImage =  getIntent().getStringExtra("ImageUrl");
        String Description =  getIntent().getStringExtra("Description");
        String Addresslin1 =  getIntent().getStringExtra("Addresslin1");
        String Addresslin2 =  getIntent().getStringExtra("Addresslin2");
        String State =  getIntent().getStringExtra("Addresslin3");
        String pincode =  getIntent().getStringExtra("Addresslinpin");
        String Framecharges = getIntent().getStringExtra("FrameChrages");
        String IncludeFrame;
        String TotalCharges = totalcharges.getText().toString();


        String a = getIntent().getStringExtra("IncludeFrame");
        if (a==null){
            IncludeFrame =  "No";
        }else{
            IncludeFrame = "Yes";
        }

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String Orderid = databaseReference1.child(CateGory).child(uid).push().getKey();
        Uri ImageRef = Uri.parse(ReferenceImage);

        StorageReference fileref = reference.child("All_Orders_images").child(System.currentTimeMillis()+"."+getFileExtension(ImageRef));
        fileref.putFile(ImageRef).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String Comp_ImageUrl = uri.toString();
                        OrderDetailsModel user = new OrderDetailsModel(Orderid,FirstName,LastName,Phone_no,Whatsapp_no,OrderDate,Description
                        ,Email,CateGory,SelectedBudget,PaperSize,Framecharges,IncludeFrame,ReferenceImage,Addresslin1,Addresslin2,State,pincode,TotalCharges);
                        databaseReference1.child(uid).push().setValue(user);
                        dialog.show();
                        progressDialog2.dismiss();
                    }
                });
            }
        });

    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}