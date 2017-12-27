package com.example.mudassirkhan.visaapplicationform;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mudassirkhan.visaapplicationform.model.Customer;

import static java.security.AccessController.getContext;

public class PreviewActivity extends AppCompatActivity {

    TextView txt_customer_name, txt_place_birth, txt_dob, txt_nationality, txt_occupation, txt_address,
            txt_type_travel, txt_number, txt_place_isssue, txt_valid_until, txt_sponsor_name, txt_nric, txt_telephone,
            txt_sponsor_address, txt_state, txt_duration_stay, radioButton_type_application,radioButton_gender,radioButton_marital_status,radioButton_purpose_journey;
    String type_application, customer_name,gender,place_birth, dob,nationality,occupation,address,marital_status,
            type_travel,number,place_isssue,valid_until,sponsor_name,nric,telephone,
            sponsor_address,state,duration_stay,purpose_journey;
    ImageView imgTake,imgLogo;
    byte[] imgeByteArray;
    Button btnPrint;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initViews();
        Intent intent=getIntent();
        customer=(Customer) intent.getSerializableExtra("MyClass");
        imgTake.setImageBitmap(BitmapFactory.decodeByteArray(customer.getImage(), 0, customer.getImage().length));
        txt_customer_name.setText(customer.getCustomerFullName());
        txt_dob.setText(customer.getDob());
        txt_valid_until.setText(customer.getDateValidUntil());
        txt_place_birth.setText(customer.getCountryBirth());
        txt_nationality.setText(customer.getNationality());
        txt_occupation.setText(customer.getOccupation());
        txt_address.setText(customer.getAddress());
        radioButton_marital_status.setText(customer.getMarital_status());
        txt_type_travel.setText(customer.getType_travel());
        txt_number.setText(""+customer.getNumber());
        txt_place_isssue.setText(customer.getCountryOfIssue());
        txt_valid_until.setText(customer.getDateValidUntil());
        txt_sponsor_name.setText(customer.getSponsorFullName());
        txt_nric.setText(customer.getNRIC());
        txt_telephone.setText(customer.getTelephone());
        txt_sponsor_address.setText(customer.getSponsorAddress());
        txt_state.setText(customer.getState());
        txt_duration_stay.setText(String.valueOf(customer.getDurationOfStay()));
        radioButton_purpose_journey.setText(customer.getPurposeOfJourney());

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View root = (View) inflater.inflate(R.layout.activity_preview, null); //RelativeLayout is root view of my UI(xml) file.
                root.setDrawingCacheEnabled(true);
                Bitmap screenbitMap= getBitmapFromView(getWindow().findViewById(R.id.activity_preview_layout));
                //set the screen into logo image for testing
                imgLogo.setImageBitmap(screenbitMap);
                //print the image
                doPhotoPrint(screenbitMap);
            }
        });

    }

    public void initViews(){
        imgLogo=(ImageView)findViewById(R.id.logo_preview);
        imgTake=(ImageView)findViewById(R.id.setImage_preview);
        txt_customer_name =(TextView) findViewById(R.id.txt_full_name_preview);
        txt_dob =(TextView)findViewById(R.id.txt_date_birth);
        txt_valid_until =(TextView)findViewById(R.id.txt_valid_until);
        txt_place_birth =(TextView)findViewById(R.id.txt_place_birth);
        txt_nationality =(TextView)findViewById(R.id.txt_nationality);
        txt_occupation =(TextView)findViewById(R.id.txt_occupation);
        txt_address =(TextView)findViewById(R.id.txt_address);
        txt_type_travel =(TextView)findViewById(R.id.txt_type_travel);
        txt_number =(TextView)findViewById(R.id.txt_number);
        txt_place_isssue =(TextView)findViewById(R.id.txt_country_issue);
        txt_sponsor_name =(TextView)findViewById(R.id.txt_full_name_sponsor);
        txt_sponsor_address =(TextView)findViewById(R.id.txt_address_sponsor);
        txt_nric =(TextView)findViewById(R.id.txt_nric);
        txt_telephone =(TextView)findViewById(R.id.txt_telelphone_number);
        txt_state =(TextView)findViewById(R.id.txt_state);
        txt_duration_stay =(TextView)findViewById(R.id.txt_duration_stay);
        //radioButton_type_application = (RadioButton)  radioGroup_type_application.getChildAt(idx);
        radioButton_type_application=(TextView)findViewById(R.id.rbtn_type_application);
        // selected_gender=radioGroup_gender.getCheckedRadioButtonId();
        radioButton_gender=(TextView)findViewById(R.id.rbtn_male);
        //  selected_martial_status=radioGroup_marital_status.getCheckedRadioButtonId();
        radioButton_marital_status=(TextView)findViewById(R.id.radio_martial_status);
        // selected_journey_purpose=radioGroup_purpose_journey.getCheckedRadioButtonId();
        radioButton_purpose_journey=(TextView)findViewById(R.id.btn_purpose_journey);

        btnPrint=(Button)findViewById(R.id.btnPrint);
    }
    private void doPhotoPrint(Bitmap bitmap) {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.drawable.droids);
        photoPrinter.printBitmap("droids.jpg - test print", bitmap);
    }
    //convert the view into bitmap
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
}
