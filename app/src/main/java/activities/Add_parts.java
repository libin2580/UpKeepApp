package activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;
import com.upkeep.upkeep.MainActivity;
import com.upkeep.upkeep.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import activitynew.Location_add_part;
import calendars.App;
import fragmentactivity.AllpartsFragment;
import fragmentactivity.LocationFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.media.MediaRecorder.VideoSource.CAMERA;

/**
 * Created by SIDDEEQ DESIGNER on 1/8/2018.
 */

public class Add_parts extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    TextView title;
    ImageView backarrow,setimg,saveimg,qrscanner;
    LinearLayout imagepicker,select_location;
    FirebaseFirestore mFirebasestore;
    ProgressDialog mDialogs;
    private ZXingScannerView mScannerView;
    String picturePath,vString;
    Bitmap bitmap;
    EditText p_name;
    EditText p_des;
    EditText p_ucost;
    EditText p_qntity;
    static EditText p_barcode;
    EditText p_minqntity;
    EditText p_area;
    EditText p_typeinfo;
    public static TextView p_loc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);


        String resultscan=getIntent().getStringExtra("aaa");

        title=(TextView)findViewById(R.id.toolbar_title);
       // save=(TextView)findViewById(R.id.toolbar_save);
        backarrow=(ImageView)findViewById(R.id.toolbar_image);
        imagepicker=(LinearLayout) findViewById(R.id.imageclick);
        setimg=(ImageView)findViewById(R.id.ivImage);
        saveimg=(ImageView)findViewById(R.id.toolbar_save);


        qrscanner=(ImageView)findViewById(R.id.qrscaner);
        p_name=(EditText)findViewById(R.id.pname);
        p_des=(EditText)findViewById(R.id.pdesc);
        p_ucost=(EditText)findViewById(R.id.pucost);
        p_qntity=(EditText)findViewById(R.id.pqntity);
        p_barcode=(EditText)findViewById(R.id.pbarcode);
        p_minqntity=(EditText)findViewById(R.id.pminqntity);
        p_area=(EditText)findViewById(R.id.parea);
        p_typeinfo=(EditText)findViewById(R.id.pinfo);
        select_location=(LinearLayout) findViewById(R.id.select_loc);
        p_loc=(TextView) findViewById(R.id.ploc);

        Toolbar topToolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        title.setText("Add Part");
        p_barcode.setText(resultscan);

        mFirebasestore = FirebaseFirestore.getInstance();
        mDialogs = new ProgressDialog(Add_parts.this);
        mDialogs.setMessage("Saving...");


        imagepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                selectImage();

            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        p_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent in=new Intent(getApplicationContext(), Location_add_part.class);
               /* in.putExtra("aaa","partloc");*/
                startActivity(in);


            }
        });


        saveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDialogs.show();

                String partname = p_name.getText().toString();
                String partdesc = p_des.getText().toString();
                String partucost = p_ucost.getText().toString();
                String partqntity = p_qntity.getText().toString();
                String partbarcode = p_barcode.getText().toString();
                String partminimumqntity = p_minqntity.getText().toString();
                String partarea = p_area.getText().toString();
                String partinfo = p_typeinfo.getText().toString();
                String partloc = p_loc.getText().toString();

                Map<String, String> userMap = new HashMap<>();
                userMap.put("img",picturePath);
                userMap.put("pname",partname);
                userMap.put("pdes",partdesc);
                userMap.put("pcost",partucost);
                userMap.put("pqnt",partqntity);
                userMap.put("pbar",partbarcode);
                userMap.put("pminqnt",partminimumqntity);
                userMap.put("parear",partarea);
                userMap.put("pdetails",partinfo);
                userMap.put("ploc",partloc);



                mFirebasestore.collection("PartsInventory").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        mDialogs.dismiss();
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                        finish();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        mDialogs.dismiss();
                        String error = e.getMessage();
                        Toast.makeText(getApplicationContext(), "Error " + error, Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


    }
    private void selectImage() {



        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(Add_parts.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);

                }

                else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);



                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                File f = new File(Environment.getExternalStorageDirectory().toString());

                for (File temp : f.listFiles()) {

                    if (temp.getName().equals("temp.jpg")) {

                        f = temp;

                        break;

                    }

                }

                try {



                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();


                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),

                            bitmapOptions);

                    setimg.setImageBitmap(bitmap);



                     picturePath = android.os.Environment

                            .getExternalStorageDirectory()

                            + File.separator

                            + "Phoenix" + File.separator + "default";

                    f.delete();

                    OutputStream outFile = null;

                    File file = new File(picturePath, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {

                        outFile = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);

                        outFile.flush();

                        outFile.close();

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();

                    } catch (IOException e) {

                        e.printStackTrace();

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            } else if (requestCode == 2) {



                Uri selectedImage = data.getData();

                String[] filePath = { MediaStore.Images.Media.DATA };

                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                 picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                Log.e("EEEEEEEEEEEEEEEEEEEEE",picturePath);
                Log.e("EEEEEEEEEEEEEEEEEEEEE", String.valueOf(thumbnail));

                setimg.setImageBitmap(thumbnail);

            }

        }


    }
    public void QrScanner(View view){
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view<br />
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.<br />
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(final com.google.zxing.Result result) {

       Log.e("resultssssssssss",result.toString());

        mScannerView.removeAllViews();
        mScannerView.stopCamera();
        //setContentView(R.layout.activity_add_part);


        vString = result.getText();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //Add_parts.p_barcode.setText(result.getText());

                Toast.makeText(getApplicationContext(),vString,Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), Add_parts.class);
                intent.putExtra("aaa",result.getText().toString());
                startActivity(intent);

            }
        });

    }



}


