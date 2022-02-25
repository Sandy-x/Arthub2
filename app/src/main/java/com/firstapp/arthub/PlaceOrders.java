package com.firstapp.arthub;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.arthub.models.All_Users;
import com.firstapp.arthub.models.CouponCodeModel;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceOrders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceOrders extends Fragment {

    Integer abcvg;

    EditText firstname,lastname,phoneno,whatsappno,email,addressline1,addressline2,addressline3,addresspincode,projectdesc;


    ConstraintLayout framelayoutcharges,deliverychargeslayout,SelectedBudgetlayout,totalpaymentlayout;
    Integer deliverycharge = 59;

    Uri imageeuri;

    TextView selectedbudgetText;
    ImageView imageView;
    Button uploagimgbtn;
    CheckBox framecheckbox;
    Button placeorder,placeorder2;

    String papersize,Category,budgget,includeframe;

    LinearLayout checkbox,address;
    TextView noofp,framecharges,totalcharges,errormsg,immageeurl;


    Spinner spinner,spinner1,spinner2,spinner4;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter1;
    ArrayAdapter<String> arrayAdapter2;
    String[] category = {"Select Category","Sketch","Painting","Digital Art","Graphic Designing","Logo Making","Poster Making"};
    String[] budgetsketch = {"Select Budget(Rs)","600","800","1000","1200","1400","3500"};
    String[] budgetpainting = {"Select Budget(Rs)","800","1200","1500","2000","4000","6000"};
    String[] papersize2000 ={"Select PaperSize","A0 84.1 x 118.9cm","A1 59.4 x 84.1cm"};
    String[] papersize600={"Select PaperSize","A4 (21 x 29.7cm)","A3 (29.7 x 42cm)"};
    String[] papersize800={"Select PaperSize","A4 (21 x 29.7cm)","A3 (29.7 x 42cm)","A2 (42 x 59.4cm)"};
    String[] papersize1000={"Select PaperSize","A4 (21 x 29.7cm)","A3 (29.7 x 42cm)","A2 (42 x 59.4cm)"};
    String[] papersize1200={"Select PaperSize","A3 (29.7 x 42cm)","A2 (42 x 59.4cm)"};
    String[] papersize1400 = {"Select PaperSize","A3 (29.7 x 42cm)","A2 (42 x 59.4cm)","A1 (59.4 x 84.1cm)"};
    String[] budget = {"Select Budget(Rs)","400","600","800","1200","1500","2000"};



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlaceOrders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlaceOrders.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaceOrders newInstance(String param1, String param2) {
        PlaceOrders fragment = new PlaceOrders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_place_orders, container, false);

        uploagimgbtn = root.findViewById(R.id.upload_imagebtndashb);
        imageView = root.findViewById(R.id.uploadedimg_dashb);
        firstname = root.findViewById(R.id.firstname_dashb);
        lastname = root.findViewById(R.id.lastname_dashb);
        framecharges = root.findViewById(R.id.framecharges);
        phoneno = root.findViewById(R.id.phoneno_dashb);
        noofp = root.findViewById(R.id.noofperson);
        totalpaymentlayout = root.findViewById(R.id.totalpaymentlayout);
        totalcharges = root.findViewById(R.id.totalcharges);
        framelayoutcharges= root.findViewById(R.id.constraint_framecharges);
        placeorder = root.findViewById(R.id.textViewdashb);
        placeorder2 = root.findViewById(R.id.textViewdashb2);
        deliverychargeslayout = root.findViewById(R.id.deliverycharges_layout);
        whatsappno = root.findViewById(R.id.whatsappphoneno_dashb);
        email = root.findViewById(R.id.email_dashb);
        checkbox = root.findViewById(R.id.checkframelinear);
        SelectedBudgetlayout = root.findViewById(R.id.selectedbudget_layout);
        immageeurl = root.findViewById(R.id.imageurl);
        framecheckbox = root.findViewById(R.id.checkbox_frame);
        address  = root.findViewById(R.id.linearaddress);
        addressline1 = root.findViewById(R.id.addressline1_dashb);
        addressline2 = root.findViewById(R.id.addressline2_dashb);
        addressline3 = root.findViewById(R.id.addressline3_dashb);
        addresspincode = root.findViewById(R.id.addresslinepin_dashb);
        projectdesc = root.findViewById(R.id.desc_dashb);
        errormsg =root.findViewById(R.id.errormsg);
        selectedbudgetText = root.findViewById(R.id.selectedbudgetpaydb);

        immageeurl.setText("0");
        includeframe = null;



        //coupon2 = root.findViewById(R.id.coupontext2);


        uploagimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(PlaceOrders.this)
                        .crop()
                        .maxResultSize(1080, 1080)
                        .start();

            }
        });

        framecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    framelayoutcharges.setVisibility(View.VISIBLE);
                    String nnb = abcvg.toString();
                    String bvc = totalcharges.getText().toString();
                    int a = Integer.parseInt(nnb);
                    int ba = Integer.parseInt(bvc);
                    int newtotalcharges = ba+a;
                    String vnji = String.valueOf(newtotalcharges);
                    totalcharges.setText(vnji);
                    includeframe="0";


                }else{
                    includeframe = null;

                    try {
                        String nnb = abcvg.toString();
                        String bvc = totalcharges.getText().toString();
                        int a = Integer.parseInt(nnb);
                        int ba = Integer.parseInt(bvc);
                        int newtotalcharges = ba-a;
                        String vnji = String.valueOf(newtotalcharges);
                        totalcharges.setText(vnji);
                        framelayoutcharges.setVisibility(View.GONE);
                    }catch (Exception e){
                        framelayoutcharges.setVisibility(View.GONE);
                    }



                }
            }
        });



        spinner = root.findViewById(R.id.spinner_type);
        arrayAdapter  = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,category);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(R.color.black);
                if(position==0){
                    Category = "0";
                }
                else if(position==1){
                    spinner1.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,budgetsketch);
                    spinner1.setAdapter(arrayAdapter1);
                    spinner4.setVisibility(View.GONE);
                    totalcharges.setText(null);
                    noofp.setVisibility(View.GONE);
                    totalpaymentlayout.setVisibility(View.INVISIBLE);
                    placeorder2.setVisibility(View.GONE);
                    placeorder.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);
                    Category = parent.getItemAtPosition(position).toString();

                }else if ( position==2){
                    spinner1.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,budgetpainting);
                    spinner1.setAdapter(arrayAdapter1);
                    spinner4.setVisibility(View.GONE);
                    placeorder2.setVisibility(View.GONE);
                    noofp.setVisibility(View.GONE);
                    placeorder.setVisibility(View.VISIBLE);
                    totalcharges.setText(null);
                    totalpaymentlayout.setVisibility(View.INVISIBLE);
                    Category = parent.getItemAtPosition(position).toString();
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    address.setVisibility(View.VISIBLE);

                } else if(position==3 || position==4 || position==5 || position==6){
                    spinner4.setVisibility(View.VISIBLE);
                    totalpaymentlayout.setVisibility(View.INVISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,budget);
                    spinner4.setAdapter(arrayAdapter1);
                    spinner1.setVisibility(View.GONE);
                    checkbox.setVisibility(View.GONE);
                    Category = parent.getItemAtPosition(position).toString();
                    placeorder2.setVisibility(View.VISIBLE);
                    placeorder.setVisibility(View.GONE);
                    totalcharges.setText(null);
                    noofp.setVisibility(View.GONE);
                    address.setVisibility(View.GONE);
                    spinner2.setVisibility(View.GONE);
                    framecheckbox.setChecked(false);
                    deliverychargeslayout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        spinner1 = root.findViewById(R.id.ammount_type);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(R.color.black);
                if(position==0){
                    totalcharges.setText("0");
                    budgget = "0";
                    SelectedBudgetlayout.setVisibility(View.GONE);

                }else if(position==1){
                    spinner2.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize600);
                    spinner2.setAdapter(arrayAdapter1);
                    checkbox.setVisibility(View.VISIBLE);
                    framecharges.setText("280");
                    noofp.setText("*for 1 person");
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    framecheckbox.setChecked(false);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);
                    noofp.setVisibility(View.VISIBLE);
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    String pop = parent.getItemAtPosition(position).toString();
                    String cvg = framecharges.getText().toString();
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    totalcharges.setText(tcc);
                    totalpaymentlayout.setVisibility(View.VISIBLE);

                }else if (position==2){
                    spinner2.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize800);
                    spinner2.setAdapter(arrayAdapter1);
                    noofp.setText("*for 1 person");
                    framecharges.setText("290");
                    budgget = parent.getItemAtPosition(position).toString();
                    noofp.setVisibility(View.VISIBLE);
                    checkbox.setVisibility(View.VISIBLE);
                    framecheckbox.setChecked(false);
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    String pop = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    String cvg = framecharges.getText().toString();
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    totalcharges.setText(tcc);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);

                }else if(position==3){
                    spinner2.setVisibility(View.VISIBLE);
                    noofp.setText("*upto 2 person");
                    checkbox.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize1000);
                    spinner2.setAdapter(arrayAdapter1);
                    framecheckbox.setChecked(false);
                    framecharges.setText("300");
                    budgget = parent.getItemAtPosition(position).toString();
                    noofp.setVisibility(View.VISIBLE);
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    String pop = parent.getItemAtPosition(position).toString();
                    String cvg = framecharges.getText().toString();
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    totalcharges.setText(tcc);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);

                }else if(position==4){
                    spinner2.setVisibility(View.VISIBLE);
                    noofp.setText("*upto 2 person");
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    checkbox.setVisibility(View.VISIBLE);
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize1200);
                    spinner2.setAdapter(arrayAdapter1);
                    framecharges.setText("320");
                    noofp.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    String pop = parent.getItemAtPosition(position).toString();
                    String cvg = framecharges.getText().toString();
                    framecheckbox.setChecked(false);
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    totalcharges.setText(tcc);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                }else if(position==5){
                    spinner2.setVisibility(View.VISIBLE);
                    checkbox.setVisibility(View.VISIBLE);
                    noofp.setText("*upto 3 person");
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize1400);
                    spinner2.setAdapter(arrayAdapter1);
                    framecharges.setText("400");
                    noofp.setVisibility(View.VISIBLE);
                    String pop = parent.getItemAtPosition(position).toString();
                    String cvg = framecharges.getText().toString();
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    framecheckbox.setChecked(false);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);
                    budgget = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    totalcharges.setText(tcc);
                }else if(position==6){
                    spinner2.setVisibility(View.VISIBLE);
                    framecheckbox.setChecked(false);
                    checkbox.setVisibility(View.VISIBLE);
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    noofp.setText("*upto 4 person");
                    arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,papersize2000);
                    spinner2.setAdapter(arrayAdapter1);
                    framecharges.setText("400");
                    noofp.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    deliverychargeslayout.setVisibility(View.VISIBLE);
                    String pop = parent.getItemAtPosition(position).toString();
                    String cvg = framecharges.getText().toString();
                    abcvg = Integer.parseInt(String.valueOf(cvg));
                    int tc=Integer.parseInt(pop)+deliverycharge;
                    String tcc = String.valueOf(tc);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);
                    totalcharges.setText(tcc);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner2 = root.findViewById(R.id.paper_size);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(R.color.black);
                if (position==0){
                    papersize = "0";
                }else{
                    papersize = parent.getItemAtPosition(position).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinner4 = root.findViewById(R.id.ammount_type2);

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(R.color.black);
                if(position==0){

                    SelectedBudgetlayout.setVisibility(View.GONE);
                }
                else if (position==1){
                    totalcharges.setText( parent.getItemAtPosition(position).toString());
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    String adfb = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    selectedbudgetText.setText(adfb);

                }else if (position==2){
                    totalcharges.setText(parent.getItemAtPosition(position).toString());
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);

                }else if (position==3){
                    totalcharges.setText(parent.getItemAtPosition(position).toString());
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);

                }else if (position==4){
                    totalcharges.setText(parent.getItemAtPosition(position).toString());
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    String adfb = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    selectedbudgetText.setText(adfb);

                }else if (position==5){
                    totalcharges.setText(parent.getItemAtPosition(position).toString());
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    String adfb = parent.getItemAtPosition(position).toString();
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    selectedbudgetText.setText(adfb);

                }else if (position==6){
                    totalcharges.setText(parent.getItemAtPosition(position).toString());
                    SelectedBudgetlayout.setVisibility(View.VISIBLE);
                    totalpaymentlayout.setVisibility(View.VISIBLE);
                    budgget = parent.getItemAtPosition(position).toString();
                    String adfb = parent.getItemAtPosition(position).toString();
                    selectedbudgetText.setText(adfb);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        placeorder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String Firstname = firstname.getText().toString();
                String Lastname = lastname.getText().toString();
                String Email = email.getText().toString();
                String Phone_no = phoneno.getText().toString();
                String Whatsapp_no = whatsappno.getText().toString();
                String PaperSize = papersize;
                String CateGory = Category;
                String TotalCharge = totalcharges.getText().toString();
                String FrameChrages = framecharges.getText().toString();
                String Addresslin1 = addressline1.getText().toString();
                String Addresslin2 = addressline2.getText().toString();
                String Addresslin3 = addressline3.getText().toString();
                String Addresslinpin = addresspincode.getText().toString();
                String Description = projectdesc.getText().toString();
                String Imageurl = immageeurl.getText().toString();
                String SelectedBudget = budgget;
                String Includeframe = includeframe;

                if(Firstname.isEmpty()){
                    firstname.setError("Please fill this section");
                }else if(Lastname.isEmpty()){
                    lastname.setError("Please fill this section");
                }else if(Email.isEmpty()){
                    email.setError("Enter Email");
                }else if(Phone_no.isEmpty()){
                    phoneno.setError("Please fill this section");
                }else if(Whatsapp_no.isEmpty()){
                    whatsappno.setError("Please fill this section");
                }else if(PaperSize == "0"){
                    errormsg.setText("Select Paper size");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else if(CateGory=="0"){
                    errormsg.setError("Select Category");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else if(TotalCharge == "0"){
                    errormsg.setText("Select Budget");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else if(Addresslin1.isEmpty()){
                    addressline1.setError("Please fill this section");
                    errormsg.setVisibility(View.GONE);
                }else if(Addresslin2.isEmpty()){
                    addressline2.setError("Please fill this section");
                }else if(Addresslin3.isEmpty()){
                    addressline3.setError("Please fill this section");
                }else if(Addresslinpin.isEmpty()){
                    addresspincode.setError("Please fill this section");
                }else if(Imageurl=="0"){
                    errormsg.setText("Upload Image");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else{
                    Intent intent = new Intent(getActivity(),paymentdashb.class);
                    intent.putExtra("FirstName",Firstname);
                    intent.putExtra("LastName",Lastname);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Phone_no",Phone_no);
                    intent.putExtra("Whatsapp_no",Whatsapp_no);
                    intent.putExtra("PaperSize",PaperSize);
                    intent.putExtra("CateGory",CateGory);
                    intent.putExtra("TotalCharge",TotalCharge);
                    intent.putExtra("Addresslin1",Addresslin1);
                    intent.putExtra("Addresslin2",Addresslin2);
                    intent.putExtra("Addresslin3",Addresslin3);
                    intent.putExtra("Addresslinpin",Addresslinpin);
                    intent.putExtra("FrameChrages",FrameChrages);
                    intent.putExtra("ImageUrl",Imageurl);
                    intent.putExtra("Description",Description);
                    intent.putExtra("SelectedBudget",SelectedBudget);
                    intent.putExtra("IncludeFrame",Includeframe);
                    getActivity().startActivity(intent);
                }

            }
        });

        placeorder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Firstname = firstname.getText().toString();
                String Lastname = lastname.getText().toString();
                String Email = email.getText().toString();
                String Phone_no = phoneno.getText().toString();
                String Whatsapp_no = whatsappno.getText().toString();
                String CateGory = Category;
                String TotalCharge = totalcharges.getText().toString();
                String Description = projectdesc.getText().toString();
                String Imageurl = immageeurl.getText().toString();
                String SelectedBudget = budgget;
                if(Firstname.isEmpty()){
                    firstname.setError("Please fill this section");
                }else if(Lastname.isEmpty()){
                    lastname.setError("Please fill this section");
                }else if(Email.isEmpty()){
                    email.setError("Enter Email");
                }else if(Phone_no.isEmpty()){
                    phoneno.setError("Please fill this section");
                }else if(Whatsapp_no.isEmpty()) {
                    whatsappno.setError("Please fill this section");
                }else if(CateGory=="0") {
                    errormsg.setError("Select Category");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    }, 1500);
                }else if(TotalCharge == "0"){
                    errormsg.setText("Select Budget");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else if(Imageurl=="0"){
                    errormsg.setText("Upload Image");
                    errormsg.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            errormsg.setVisibility(View.GONE);
                        }
                    },1500);
                }else{
                    Intent intent = new Intent(getActivity(),paymentdashbsecond.class);
                    intent.putExtra("FirstName",Firstname);
                    intent.putExtra("LastName",Lastname);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Phone_no",Phone_no);
                    intent.putExtra("Whatsapp_no",Whatsapp_no);
                    intent.putExtra("CateGory",CateGory);
                    intent.putExtra("TotalCharge",TotalCharge);
                    intent.putExtra("ImageUrl",Imageurl);
                    intent.putExtra("Description",Description);
                    intent.putExtra("SelectedBudget",SelectedBudget);
                    getActivity().startActivity(intent);
                }



            }
        });





        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageeuri = data.getData();
        imageView.setImageURI(imageeuri);
        imageView.setVisibility(View.VISIBLE);
        immageeurl.setText(imageeuri.toString());

    }

}