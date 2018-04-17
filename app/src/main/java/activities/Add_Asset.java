package activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upkeep.upkeep.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import activitynew.Location_add_part;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by SIDDEEQ DESIGNER on 1/8/2018.
 */

public class Add_Asset extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    TextView title;
    ImageView backarrow, ast_save, ast_scan, ast_pics;
  public static  EditText ast_name, ast_model, ast_barcode, ast_area, ast_desc, ast_typeinfo;
    public static TextView ast_locc;
    static TextView ast_wrk;
    TextView ast_parent;
    LinearLayout ast_scanupc;
    String picturePath, vString;
    private ZXingScannerView mScannerView;
    Bitmap bitmap;
    static EditText barcodenew;
    FirebaseFirestore mFirebasestore;
    ProgressDialog mDialogs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);


        title = (TextView) findViewById(R.id.toolbar_title);
        backarrow = (ImageView) findViewById(R.id.toolbar_image);

        ast_save = (ImageView) findViewById(R.id.asset_save);
        ast_scan = (ImageView) findViewById(R.id.asset_scanbarcode);
        ast_pics = (ImageView) findViewById(R.id.ivImage);

        ast_name = (EditText) findViewById(R.id.asset_namee);
        ast_model = (EditText) findViewById(R.id.asset_model);
        ast_barcode = (EditText) findViewById(R.id.asset_barcode);
        ast_area = (EditText) findViewById(R.id.asset_area);
        ast_desc = (EditText) findViewById(R.id.asset_description);
        ast_typeinfo = (EditText) findViewById(R.id.asset_typeinfo);
        ast_locc = (TextView) findViewById(R.id.asset_loc);
        ast_wrk = (TextView) findViewById(R.id.asset_worker);
        ast_parent = (TextView) findViewById(R.id.asset_parentasset);
        ast_scanupc = (LinearLayout) findViewById(R.id.asset_scanupc);

        //barcodenew=(EditText)findViewById(R.id.asset_barcodenew);

        Toolbar topToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        title.setText("Add Asset");

        mFirebasestore=FirebaseFirestore.getInstance();
        mDialogs=new ProgressDialog(Add_Asset.this);
        mDialogs.setMessage("Savings....");


        String new_data=getIntent().getStringExtra("aaa");
        ast_barcode.setText(new_data);

        ast_locc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(getApplicationContext(), Location_add_part.class);
               /* in.putExtra("aaa","astloc");*/
                startActivity(in);
            }
        });

        ast_locc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        ast_wrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(),Assign_workers.class);
               i.putExtra("aaa","astwrkr");
                startActivity(i);
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        ast_pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });



        ast_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialogs.show();

                String assetname = ast_name.getText().toString();
                String assetmodel = ast_model.getText().toString();
                String assetbarcode = ast_barcode.getText().toString();
                String assetarea = ast_area.getText().toString();
                String assetdescr = ast_desc.getText().toString();
                String assettypeinfo = ast_typeinfo.getText().toString();
                String assetlloc= ast_locc.getText().toString();
                String assetwrkr = ast_wrk.getText().toString();
                String assetparrent = ast_parent.getText().toString();

                Map<String, String> userMap = new HashMap<>();
                userMap.put("astimg",picturePath);
                userMap.put("astname",assetname);
                userMap.put("astmodel",assetmodel);
                userMap.put("astbr",assetbarcode);
                userMap.put("astarea",assetarea);
                userMap.put("astdesc",assetdescr);
                userMap.put("asttype",assettypeinfo);
                userMap.put("astloc",assetlloc);
                userMap.put("astwrkr",assetwrkr);
                userMap.put("astparent",assetparrent);


                mFirebasestore.collection("Assets").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Asset.this);

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

                } else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);


                } else if (options[item].equals("Cancel")) {

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

                    ast_pics.setImageBitmap(bitmap);


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

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                Log.e("EEEEEEEEEEEEEEEEEEEEE", picturePath);
                Log.e("EEEEEEEEEEEEEEEEEEEEE", String.valueOf(thumbnail));

                ast_pics.setImageBitmap(thumbnail);

            }
        }
    }




        public void QrScanner(View view){
            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view<br />
            setContentView(mScannerView);
            mScannerView.setResultHandler(Add_Asset.this); // Register ourselves as a handler for scan results.<br />
            mScannerView.startCamera();
        }

        @Override
        public void handleResult(final com.google.zxing.Result result) {

            Log.e("resultssssssssss",result.toString());
           // p_loc.setText(result.getText());
            mScannerView.removeAllViews();
            mScannerView.stopCamera();

           // Add_Asset.barcodenew.setText(result.getText().toString());
            //setContentView(R.layout.activity_add_part);
            vString = result.getText().toString();
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {

                    Toast.makeText(getApplicationContext(),vString,Toast.LENGTH_LONG).show();

                    finish();
                    Intent intent = new Intent(getApplicationContext(), Add_Asset.class);
                    intent.putExtra("aaa",result.getText().toString());
                    startActivity(intent);
                }
            });

        }


    }

