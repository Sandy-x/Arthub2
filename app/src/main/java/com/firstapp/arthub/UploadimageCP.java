package com.firstapp.arthub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadimageCP extends AppCompatActivity {
    private Uri imageuri;
    Button selectimg;
    ImageView imageView;
    DatabaseReference databaseReference,databaseReference1;
    FirebaseFirestore database;
    TextView Compid,textViewpn;
    ConstraintLayout UploadBtn;
    ProgressBar progressbar_tb;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage_c_p);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.blue));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        selectimg = findViewById(R.id.upload_imagebtnUICP);
        imageView = findViewById(R.id.uploadedimg_UICP);
        textViewpn = findViewById(R.id.textViewpnCP);
        progressbar_tb = findViewById(R.id.progressbar_tbCP);

        UploadBtn = findViewById(R.id.participatedada_btnUICP);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Particular_parti_lists").child("colorPencil").child(uid);

        selectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(UploadimageCP.this)
                        .crop()
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        UploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewpn.setVisibility(View.INVISIBLE);
                progressbar_tb.setVisibility(View.VISIBLE);
                if(imageuri != null){
                    StorageReference fileref = reference.child("All_comp_images").child(System.currentTimeMillis()+"."+getFileExtension(imageuri));
                    fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String Comp_ImageUrl = uri.toString();
                                    databaseReference.child(getIntent().getStringExtra("CP_OrdId")).child("comp_ImageUrl").setValue(Comp_ImageUrl);
                                    Toast.makeText(UploadimageCP.this, "uploaded successfully", Toast.LENGTH_LONG).show();
                                    textViewpn.setVisibility(View.VISIBLE);
                                    progressbar_tb.setVisibility(View.INVISIBLE);


                                }
                            });

                        }
                    });
                }else{
                    Toast.makeText(UploadimageCP.this, "Image not selected!!", Toast.LENGTH_SHORT).show();
                    textViewpn.setVisibility(View.VISIBLE);
                    progressbar_tb.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr  = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageuri = data.getData();
        imageView.setImageURI(imageuri);
        imageView.setVisibility(View.VISIBLE);
    }
}