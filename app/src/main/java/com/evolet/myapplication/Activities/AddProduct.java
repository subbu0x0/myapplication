package com.evolet.myapplication.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.evolet.myapplication.Items.Materials;
import com.evolet.myapplication.R;
import com.evolet.myapplication.db.SQLiteHandler;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final int GalleryPick = 1;
    private int GALLERY = 1, CAMERA = 2;
    private static final String IMAGE_DIRECTORY = "/evolet";
    Button addImage;
    ImageView selectedImage;
    EditText mProductName,mProductPrice, mProductUnit;
    String mName,mPrice,mUnit;
    Materials mtemMaterials;String saveCurrentDate,saveCurrentTime;
    String productRandomKey,downloadUrl;
    StorageReference mImageReference;
    Button mBtnSubmit;   Uri contentURI;String mProductCategory;
    Spinner productCategorySpinner;
    private DatabaseReference mDatabaseReference;
    ProgressDialog mProgressDialog;
    SQLiteHandler mSqLiteHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mImageReference= FirebaseStorage.getInstance().getReference().child("Product Images");
        mDatabaseReference=FirebaseDatabase.getInstance().getReference().child("Products");


        mSqLiteHandler=new SQLiteHandler(getApplicationContext());

          productCategorySpinner = (Spinner)findViewById(R.id.prodCategory);

        productCategorySpinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Medicine");
        categories.add("MediNeeds");
        categories.add("Nursing");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddProduct.this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner

        productCategorySpinner.setAdapter(adapter);
        selectedImage = (ImageView)findViewById(R.id.selectedImage) ;
            mProgressDialog=new ProgressDialog(this);

        addImage = (Button)findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  openGallery();
                  showPictureDialog();
            }
        });
        mProductName=findViewById(R.id.prodName)  ;
        mProductPrice=findViewById(R.id.prodPrice);
        mProductUnit =findViewById(R.id.prodUnit);
        mBtnSubmit=findViewById(R.id.submit);
       // mProductCategory = productCategorySpinner.getSelectedItem().toString();
        mName=mProductName.getText().toString();
        mPrice=mProductPrice.getText().toString();
        mUnit=mProductUnit.getText().toString();



        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProductData();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        mProductCategory = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + mProductCategory, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //openGallery();
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void choosePhotoFromGallary() {
        //openGallery();
        if (ContextCompat.checkSelfPermission(AddProduct.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddProduct.this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(AddProduct.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},GALLERY
                        );
            }
            }else {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, GALLERY);
        }
    }

    private void takePhotoFromCamera() {
        if (ContextCompat.checkSelfPermission(AddProduct.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddProduct.this,
                    Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(AddProduct.this,
                        new String[]{Manifest.permission.CAMERA},
                        CAMERA);
            }
        }else{
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        }
    }
    public void openGallery(){
        Intent galleryIntent=new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("Image/*");
        startActivityForResult(galleryIntent,GALLERY);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            contentURI = data.getData();
            selectedImage.setImageURI(contentURI);
            selectedImage.setVisibility(View.VISIBLE);
        }

    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    public void validateProductData(){
        mName=mProductName.getText().toString();
        mPrice=mProductPrice.getText().toString();
        mUnit=mProductUnit.getText().toString();
        
        if (contentURI==null){
            Toast.makeText(this, "Product Image Mandatory", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(mName)){
            Toast.makeText(this, "Product Name is empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(mPrice)){
            Toast.makeText(this, "Product Price is empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(mUnit)){
            Toast.makeText(this, "Product/Service Id is empty", Toast.LENGTH_SHORT).show();
        }else {
            storeProductItem();
        }

    }
public void storeProductItem(){

    mProgressDialog.setTitle("Adding product");
    mProgressDialog.setMessage("Please wait, While we are adding new product");
    mProgressDialog.setCanceledOnTouchOutside(false);
    mProgressDialog.show();
        Calendar mCalendar=Calendar.getInstance();
    SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("MMM dd,yyyy");
    saveCurrentDate=mSimpleDateFormat.format(mCalendar.getTime());

    SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
    saveCurrentTime=currentTime.format(mCalendar.getTime());
   // productRandomKey=saveCurrentDate+saveCurrentTime;
    productRandomKey=mName;
      //final StorageReference filePath=mImageReference.child(contentURI.getLastPathSegment()+productRandomKey+".jpg");
    final StorageReference filePath=mImageReference.child(mName);

        final UploadTask mUploadTask=filePath.putFile(contentURI);
  /*  filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {
            downloadUrl = uri.toString();
        }
    });*/
        mUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message=e.toString();
                Toast.makeText(AddProduct.this, "Error"+message, Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AddProduct.this, "Product upload Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask=mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            mProgressDialog.dismiss();
                                throw task.getException();

                        }
                        //downloadUrl=filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();


                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            Uri d = task.getResult();
                            downloadUrl = d.toString();
                            Toast.makeText(AddProduct.this, "Product Image success", Toast.LENGTH_SHORT).show();
                            saveProductInfoIntoDatabase();
                            mSqLiteHandler.addProducts(mName,mUnit,mPrice,mProductCategory);
                        }

                    }
                });
            }
        });


}

        private void saveProductInfoIntoDatabase(){

            HashMap<String,Object> mProductMap=new HashMap<>();
            mProductMap.put("pid",productRandomKey);
            mProductMap.put("mDate",saveCurrentDate);
            mProductMap.put("mTime",saveCurrentTime);
            mProductMap.put("material_name",mName);
            mProductMap.put("material_price",mPrice);
            mProductMap.put("mImage",downloadUrl);
            mProductMap.put("mProductUnit",mUnit);
            mProductMap.put("material_category",mProductCategory);
            mDatabaseReference.child(productRandomKey).updateChildren(mProductMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        mProgressDialog.dismiss();
                        Toast.makeText(AddProduct.this, "Product added successfully..", Toast.LENGTH_SHORT).show();
                    }else {
                        mProgressDialog.dismiss();
                        String message=task.getException().toString();
                        Toast.makeText(AddProduct.this, ""+message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddProduct.this, AdminActivity.class);

        startActivity(intent);
    }
}
