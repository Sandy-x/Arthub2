package com.firstapp.arthub;

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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONObject;

import java.util.Calendar;
import java.util.UUID;

public class paymentdashbsecond extends AppCompatActivity  implements PaymentResultListener {


    FirebaseFirestore database;
    TextView selectedbudget,totalcharges;
    ConstraintLayout makepaymentdb_btn1;
    TextView makeptext;
    Dialog dialog;
    ProgressDialog progressDialog,progressDialog2;

    TextView framecharges,deliverycharge,makepayment;

    EditText CouponCodeedittext;
    TextView couponapplybtn,successcoupon,failedcoupon,removebtn;
    TextView Couponcode20,Couponcode3,Couponcode4,Couponcode5;
    ProgressBar progressBar;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    private DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentdashbsecond);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        database = FirebaseFirestore.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Orders");
        selectedbudget = findViewById(R.id.selectedbudgetpaydb11);
        totalcharges = findViewById(R.id.totalchargespaydb1);
        selectedbudget.setText(getIntent().getStringExtra("SelectedBudget"));
        totalcharges.setText(getIntent().getStringExtra("TotalCharge"));
        makepaymentdb_btn1 = findViewById(R.id.makepaymentdb_btn1);
        CouponCodeedittext = findViewById(R.id.coupon_dashb1);
        removebtn = findViewById(R.id.removebtn1);
        successcoupon = findViewById(R.id.succefullytext_dashb1);
        failedcoupon = findViewById(R.id.failedtext_dashb1);

        progressDialog = new ProgressDialog(paymentdashbsecond.this);
        progressDialog2 = new ProgressDialog(paymentdashbsecond.this);

        Couponcode20 = findViewById(R.id.cctext33);
        Couponcode3 = findViewById(R.id.cctext44);
        Couponcode4 = findViewById(R.id.cctext55);
        Couponcode5 = findViewById(R.id.cctext11);
        couponapplybtn = findViewById(R.id.couponapplybtn1);


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




        couponapplybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                String ac = selectedbudget.getText().toString();
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
                    couponapplybtn.setText("APPLY");
                    progressDialog.dismiss();
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
                            couponapplybtn.setVisibility(View.GONE);
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
                            couponapplybtn.setVisibility(View.GONE);
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
                            couponapplybtn.setVisibility(View.GONE);
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
                            couponapplybtn.setVisibility(View.GONE);
                        }
                    },1000);
                }else{
                    progressDialog.dismiss();
                    CouponCodeedittext.setError("Invalid Code!");
                    successcoupon.setVisibility(View.GONE);
                }
            }
        });

        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponCodeedittext.setText(null);
                totalcharges.setText(getIntent().getStringExtra("TotalCharge"));
                selectedbudget.setText(getIntent().getStringExtra("SelectedBudget"));

                successcoupon.setVisibility(View.GONE);
                failedcoupon.setVisibility(View.GONE);
                removebtn.setVisibility(View.GONE);
                couponapplybtn.setVisibility(View.VISIBLE);

            }
        });



        dialog = new Dialog(paymentdashbsecond.this);
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
                Intent in = new Intent(paymentdashbsecond.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });
        makeptext = findViewById(R.id.textViewdashb1);
        progressBar = findViewById(R.id.progressbar_tb1);
        makepaymentdb_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                makeptext.setVisibility(View.INVISIBLE);
                makepayment();
            }
        });

    }

    private void makepayment() {
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
        progressBar.setVisibility(View.INVISIBLE);
        makeptext.setVisibility(View.VISIBLE);
        saveorder();
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
        String SelectedBudget =  getIntent().getStringExtra("SelectedBudget");
        String ReferenceImage =  getIntent().getStringExtra("ImageUrl");
        String Description =  getIntent().getStringExtra("Description");
        String TotalCharges = totalcharges.getText().toString();
        String PaperSize="null";
        String IncludeFrame = "null";
        String Addresslin2 = "null";
        String Addresslin1 = "null";
        String pincode = "null";
        String State = "null";
        String Framecharges = "null";
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
                        databaseReference1.child(uid).child(Orderid).setValue(user);
                        dialog.show();
                        progressDialog2.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Failed!", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
        makeptext.setVisibility(View.VISIBLE);
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}