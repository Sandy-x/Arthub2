package com.firstapp.arthub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
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
import com.firstapp.arthub.models.freeCompModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

public class freecompPayment extends AppCompatActivity {
    ImageView referenceimg,uploadedimg;
    TextView lastdate,topic,downloadimage,prize,category,loadingImg,note,pntext;
    CardView cardviewnote;
    EditText fullname,email,instagramid;
    Button uploadimg_btn;
    private Uri imageuri;
    ConstraintLayout particapte_cl;
    FirebaseFirestore database;
    ProgressBar progressBar,progressdi;
    private DatabaseReference databaseReference1;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    Dialog dialog;
    OutputStream outputStream;
    Bitmap bitmap;
    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freecomp_payment);


        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));

        referenceimg = findViewById(R.id.refernceimgfcp);
        lastdate = findViewById(R.id.lastdatefcp);
        topic = findViewById(R.id.topicfcp);
        downloadimage = findViewById(R.id.downloadimage);
        prize = findViewById(R.id.prizefcp);
        category = findViewById(R.id.category_fcp);
        cardviewnote = findViewById(R.id.cardviewnote);
        note = findViewById(R.id.descriptionfcp);
        progressdi = findViewById(R.id.progressdi);
        


        downloadimage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                progressdi.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,WRITE_EXTERNAL_STORAGE_CODE);
                        progressdi.setVisibility(View.GONE);
                    }
                    else {
                        saveimage();
                    }
                }

            }
        });

        fullname = findViewById(R.id.fullname_fcp);
        email = findViewById(R.id.email_fcp);
        instagramid = findViewById(R.id.instaid_fcp);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Free_Competetions_lists");
        database = FirebaseFirestore.getInstance();

        uploadimg_btn = findViewById(R.id.upload_imagebtnfcp);
        uploadedimg = findViewById(R.id.uploadedimg_fcp);
        particapte_cl = findViewById(R.id.participatefcp_btn);
        pntext = findViewById(R.id.textViewpn);
        progressBar = findViewById(R.id.progressbar_fcp);

        dialog = new Dialog(freecompPayment.this);
        dialog.setContentView(R.layout.custom_dialogbox);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_gradient));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Button ok = dialog.findViewById(R.id.continue_cdbtn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent in = new Intent(freecompPayment.this,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                finish();
            }
        });

        category.setText(getIntent().getStringExtra("categoryfc"));
        prize.setText(getIntent().getStringExtra("prizefc"));
        topic.setText(getIntent().getStringExtra("topicfc"));
        lastdate.setText(getIntent().getStringExtra("last_datefc"));
        note.setText(getIntent().getStringExtra("descriptionfc"));
        String ReferenceImage =  getIntent().getStringExtra("referenceimgfc");
        Uri ImageRef = Uri.parse(ReferenceImage);

        Picasso.get().load(ImageRef).into(referenceimg);
        if (referenceimg != null){
            downloadimage.setVisibility(View.VISIBLE);
        }
        if (note.equals(null)){
            cardviewnote.setVisibility(View.VISIBLE);
        }

        uploadimg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(freecompPayment.this)
                        .crop()
                        .maxResultSize(1080, 1080)
                        .start();

            }
        });

        particapte_cl.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String Name = fullname.getText().toString();
                String Email = email.getText().toString();
                if (Name.isEmpty()){
                    fullname.setError("Please Enter Your Name");
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveimage() {
        bitmap = ((BitmapDrawable)referenceimg.getDrawable()).getBitmap();
        String time = new SimpleDateFormat("yyyyMMdd_HHmmmss", Locale.getDefault())
                .format(System.currentTimeMillis());
        File path = Environment.getExternalStorageDirectory();
        File dir = new File(path+"/DCIM");
        dir.mkdirs();
        String imagename = time+".JPEG";
        File file = new File(dir,imagename);
        OutputStream out;
        try {
            progressdi.setVisibility(View.GONE);
            MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"Refrenceimage","");
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.flush();
            out.close();
            Toast.makeText(freecompPayment.this, "Image Saved to Gallery", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            progressdi.setVisibility(View.GONE);
            Toast.makeText(freecompPayment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void makepayment() {
        Calendar cdate =Calendar.getInstance();
        SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
        final String savedate = currentdate.format(cdate.getTime());

        String Email = email.getText().toString();
        String Name = fullname.getText().toString();
        String Topic = topic.getText().toString();
        String LastDate = lastdate.getText().toString();
        String InstagramId = instagramid.getText().toString();
        String Category = category.getText().toString();
        String RegisteredDate = savedate;


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
                            freeCompModel user = new freeCompModel(Name,Email,InstagramId,Category,Topic,LastDate,Comp_ImageUrl);
                            databaseReference1.child(RegisteredDate).child(uid).push().setValue(user);
                            dialog.show();
                        }
                    });

                }
            });
        }else{
            Toast.makeText(this, "Please upload your art", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
            pntext.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageuri = data.getData();
        uploadedimg.setImageURI(imageuri);
        uploadedimg.setVisibility(View.VISIBLE);
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0]==
                PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this, "Permission enable", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}