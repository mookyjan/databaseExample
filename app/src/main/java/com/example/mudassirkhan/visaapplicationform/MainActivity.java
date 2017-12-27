package com.example.mudassirkhan.visaapplicationform;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mudassirkhan.visaapplicationform.dao.CustomerTable;
import com.example.mudassirkhan.visaapplicationform.model.Customer;
import com.example.mudassirkhan.visaapplicationform.sqlite.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public final static String TAG="MainActivity";
    EditText edit_customer_name,edit_place_birth, edit_dob,edit_nationality,edit_occupation,edit_address,
    edit_type_travel,edit_number,edit_place_isssue,edit_valid_until,edit_sponsor_name,edit_nric,edit_telephone,
    edit_sponsor_address,edit_state,edit_duration_stay;
    String type_application, customer_name,gender,place_birth, dob,nationality,occupation,address,marital_status,
            type_travel,number,place_isssue,valid_until,sponsor_name,nric,telephone,
            sponsor_address,state,duration_stay,purpose_journey;
    RadioButton radioButton_type_application,radioButton_gender,radioButton_marital_status,radioButton_purpose_journey;
    RadioGroup radioGroup_marital_status,radioGroup_gender,radioGroup_type_application,radioGroup_purpose_journey;
    MyEditTextDatePicker myEditTextDatePicker;
    Button btnSend;
    DatabaseHelper db;
    int selected_type_application,selected_gender,selected_martial_status,selected_journey_purpose;
    List<Customer> customerModelClassList=new ArrayList<>();
    CustomerTable customerTable=new CustomerTable();
    ImageView imgTake;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    byte[] imgeByteArray;
    MyParcelable myParcelableObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        imgTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            selectImage();
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                type_application=radioButton_type_application.getText().toString();

                gender=radioButton_gender.getText().toString();
                marital_status=radioButton_marital_status.getText().toString();

                customer_name=edit_customer_name.getText().toString();
                place_birth=edit_place_birth.getText().toString();
                dob=edit_dob.getText().toString();
                nationality=edit_nationality.getText().toString();
                occupation=edit_occupation.getText().toString();
                address=edit_address.getText().toString();
                type_travel=edit_type_travel.getText().toString();
                number=edit_number.getText().toString();
                place_isssue=edit_place_isssue.getText().toString();
                valid_until=edit_valid_until.getText().toString();
                sponsor_name=edit_sponsor_name.getText().toString();
                nric=edit_nric.getText().toString();
                telephone=edit_telephone.getText().toString();
                sponsor_address=edit_sponsor_address.getText().toString();
                state=edit_state.getText().toString();
                duration_stay=edit_duration_stay.getText().toString();
                purpose_journey=radioButton_purpose_journey.getText().toString();

                //Check that the imageView is null or the picture have taken
                if (imgTake.getDrawable()==null){
                    Toast.makeText(MainActivity.this,"Please Take an Image First",Toast.LENGTH_SHORT).show();
                }else {
                    Customer newCustomer = new Customer();
                    newCustomer.setImage(imgeByteArray);
                    newCustomer.setType_application(type_application);
                    newCustomer.setCustomerFullName(customer_name);
                    newCustomer.setGender(gender);
                    newCustomer.setDob(dob);
                    newCustomer.setCountryBirth(place_birth);
                    newCustomer.setNationality(nationality);
                    newCustomer.setOccupation(occupation);
                    newCustomer.setAddress(address);
                    newCustomer.setMarital_status(marital_status);
                    newCustomer.setType_travel(type_travel);
                    newCustomer.setNumber(number);
                    newCustomer.setCountryOfIssue(place_isssue);
                    newCustomer.setDateValidUntil(valid_until);
                    newCustomer.setSponsorFullName(sponsor_name);
                    newCustomer.setNRIC(nric);
                    newCustomer.setTelephone(telephone);
                    newCustomer.setSponsorAddress(sponsor_address);
                    newCustomer.setState(state);
                    newCustomer.setDurationOfStay(duration_stay);
                    newCustomer.setPurposeOfJourney(purpose_journey);

//                db=new DatabaseHelper();
                    //              SQLiteDatabase sqLiteDatabase=db.getWritableDatabase();
                    // db.insertCustomer(sqLiteDatabase);
                    //          db.addCustomer(newCustomer);
                    customerTable.insertCustomer(newCustomer);
                    customerModelClassList.add(newCustomer);
                    Log.d(TAG, customerModelClassList + " name " + newCustomer.getCustomerFullName() + newCustomer.getGender());
                    Intent myIntent = new Intent(MainActivity.this, PreviewActivity.class);
                    myIntent.putExtra("MyClass", newCustomer);
                    // myIntent.putExtra("MyClass",newCustomer);
                    startActivity(myIntent);
//                SQLiteDatabase database = getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("Name", queryValues.get("name"));
//                database.insert("student", null, values); database.close();
                }

            }
        });

        List<Customer> customers= customerTable.getAllCustomer();
        for (int i=0;i<customers.size();i++){
//            Log.d(TAG,"image "+ BitmapFactory.decodeByteArray(customers.get(i).getImage(), 0, customers.get(i).getImage().length)+
//                    "Type Applicaiton "+customers.get(i).getType_application()+" Name "+customers.get(i).getCustomerFullName()+
//                    " Gender "+customers.get(i).getGender()+" Country Birth "+customers.get(i).getCountryBirth()+
//                    " Dob "+customers.get(i).getDob()+
//                    " Nationality  " +customers.get(i).getNationality()+" Occupation "+
//                    customers.get(i).getOccupation()+" Address "+customers.get(i).getAddress()+" Marital Status "+
//                    customers.get(i).getMarital_status()
//                    +" Travel Type "+customers.get(i).getType_travel()+" Number  "+
//                    customers.get(i).getNumber()+" Country Issue "+customers.get(i).getCountryOfIssue()+" Valid Until "
//                    +customers.get(i).getDateValidUntil()+" Sponsor name "+customers.get(i).getSponsorFullName()+" Nric "
//                    +customers.get(i).getNRIC()+" telelphone  "+
//                    customers.get(i).getTelephone()+" Sponsor Address "+customers.get(i).getSponsorAddress()+" state "
//                    +customers.get(i).getState()+" Duration Stay "+customers.get(i).getDurationOfStay()+" purpose Journey "
//                    +customers.get(i).getPurposeOfJourney());

        }
        Log.d(TAG,"All Data"+customers);

    }

    public boolean isEmpty(EditText editText) {
        boolean isEmptyResult = false;
        if (editText.getText().length() == 0) {
            isEmptyResult = true;
        }
        return isEmptyResult;
    }
    // simple class that just has one member property as an example
    public class MyParcelable implements Parcelable {
        private int mData;

    /* everything below here is for implementing Parcelable */

        // 99.9% of the time you can just ignore this
        @Override
        public int describeContents() {
            return 0;
        }

        // write your object's data to the passed-in Parcel
        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(mData);
        }

        // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
        public  final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {
            public MyParcelable createFromParcel(Parcel in) {
                return new MyParcelable(in);
            }

            public MyParcelable[] newArray(int size) {
                return new MyParcelable[size];
            }
        };

        // example constructor that takes a Parcel and gives you an object populated with it's values
        private MyParcelable(Parcel in) {
            mData = in.readInt();
        }
    }
    public void initViews(){

        imgTake=(ImageView)findViewById(R.id.setImage);
        radioGroup_type_application=(RadioGroup)findViewById(R.id.radio_type_application);
        //selected_type_application=radioGroup_type_application.getCheckedRadioButtonId();
        radioGroup_type_application.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                selected_type_application = radioGroup.indexOfChild(radioButton);
            }
        });
        edit_customer_name=(EditText)findViewById(R.id.txt_full_name);
        edit_dob=(EditText)findViewById(R.id.edit_date_birth);
        edit_valid_until=(EditText)findViewById(R.id.edit_valid_until);
        myEditTextDatePicker=new MyEditTextDatePicker(this,edit_dob);
        myEditTextDatePicker=new MyEditTextDatePicker(this,edit_valid_until);
        edit_place_birth=(EditText)findViewById(R.id.edit_place_birth);
        edit_nationality=(EditText)findViewById(R.id.edit_nationality);
        edit_occupation=(EditText)findViewById(R.id.edit_occupation);
        edit_address=(EditText)findViewById(R.id.edit_address);
        edit_type_travel=(EditText)findViewById(R.id.edit_type_travel);
        edit_number=(EditText)findViewById(R.id.edit_number);
        edit_place_isssue=(EditText)findViewById(R.id.spinner_country_issue);
        edit_sponsor_name=(EditText)findViewById(R.id.edit_full_name_sponsor);
        edit_sponsor_address=(EditText)findViewById(R.id.edit_address_sponsor);
        edit_nric=(EditText)findViewById(R.id.edit_nric);
        edit_telephone=(EditText)findViewById(R.id.edit_telephone);
        edit_state=(EditText)findViewById(R.id.edit_state);
        edit_duration_stay=(EditText)findViewById(R.id.edit_duration_stay);

        radioGroup_gender=(RadioGroup)findViewById(R.id.radioGroup_gender);
        radioGroup_marital_status=(RadioGroup)findViewById(R.id.radio_martial_status);
        radioGroup_purpose_journey=(RadioGroup)findViewById(R.id.radioGroup_purpose_journey);
        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                selected_gender = radioGroup.indexOfChild(radioButton);
            }
        });
        radioGroup_marital_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                selected_martial_status = radioGroup.indexOfChild(radioButton);
            }
        });
        radioGroup_purpose_journey.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                selected_journey_purpose = radioGroup.indexOfChild(radioButton);
            }
        });

        //radioButton_type_application = (RadioButton)  radioGroup_type_application.getChildAt(idx);
        radioButton_type_application=(RadioButton)radioGroup_type_application.getChildAt(selected_type_application);
       // selected_gender=radioGroup_gender.getCheckedRadioButtonId();
        radioButton_gender=(RadioButton)radioGroup_type_application.getChildAt(selected_gender);
      //  selected_martial_status=radioGroup_marital_status.getCheckedRadioButtonId();
        radioButton_marital_status=(RadioButton)radioGroup_marital_status.getChildAt(selected_martial_status);
       // selected_journey_purpose=radioGroup_purpose_journey.getCheckedRadioButtonId();
        radioButton_purpose_journey=(RadioButton)radioGroup_purpose_journey.getChildAt(selected_journey_purpose);

        btnSend=(Button)findViewById(R.id.btnSend);
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=Utils.checkPermission(MainActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
               imgeByteArray= onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
             imgeByteArray=  onCaptureImageResult(data);
        }
    }
    @SuppressWarnings("deprecation")
    private byte[] onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imgTake.setImageBitmap(bm);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        return stream.toByteArray();
    }

    private byte[] onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgTake.setImageBitmap(thumbnail);
        return bytes.toByteArray();
    }
    public class MyEditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
        EditText _editText;
        private int _day;
        private int _month;
        private int _birthYear;
        private Context _context;

        public MyEditTextDatePicker(Context context, EditText editTextViewID)
        {
            Activity act = (Activity)context;
            this._editText=editTextViewID;
            //this._editText = (EditText)act.findViewById(editTextViewID);
            this._editText.setOnClickListener(this);
            this._context = context;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            _birthYear = year;
            _month = monthOfYear;
            _day = dayOfMonth;
            updateDisplay();
        }
        @Override
        public void onClick(View v) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

            DatePickerDialog dialog = new DatePickerDialog(_context, this,
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();

        }

        // updates the date in the birth date EditText
        private void updateDisplay() {

            _editText.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
        }
    }
}
