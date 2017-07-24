package com.example.devin.flamingo;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends YouTubeBaseActivity implements AsyncResponse {

    private static final int CAM_REQUEST = 1;
    private static final int SELECTED_PICTURE = 2;
    private Uri postImage;
    Bitmap bitmapImage;
    Uri imageUri;
    String result = "";
    TextView displayResult;
    VideoView videoView;
    MediaController mediaController;

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    YouTubePlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                mPlayer = youTubePlayer;

                mPlayer.loadVideo("F0XhOUM7AHs");
                //mPlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(this, "Failed to initialize video", Toast.LENGTH_LONG).show();
            }
        };

        youTubePlayerView.initialize(PlayerConfig.API_KEY, onInitializedListener);

        ImageButton uploadphoto = (ImageButton) findViewById(R.id.button_upload);
        displayResult = (TextView) findViewById(R.id.text_view_id);
        displayResult.setText("");
        AsyncResponse delegate = null;
        //TextView titleView = (TextView)findViewById(R.id.title_view_id);
       // titleView.setTypeface(Typeface.createFromFile(getAssets(),"font/geforce_bold"));

//        videoView =(VideoView)findViewById(R.id.videoView);
//        mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//
//        //m.youtube.com/watch?v=VIDEO_ID&app=m
//        //load tutorial video
//        Uri uri= Uri.parse("rtsp://r6---sn-a5mlrnes.googlevideo.com/Cj0LENy73wIaNAkPni6fsNB1AhMYDSANFC2cKGNZMOCoAUIASARg7NOHsuPGnsFYigELZzNqenlwY0tKLVUM/6F316BE7E400BA732E8F17C7D380077A908B2052.71B507F41F10E08F611B6B1D184FF0AC5A673552/yt6/1/video.3gp");
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(uri);
//        videoView.requestFocus();
//        videoView.start();

//plane
        //rtsp://r4---sn-a5mlrnes.googlevideo.com/Cj0LENy73wIaNAkAA9lsSZ6CRhMYDSANFC3Rl2JZMOCoAUIASARg7NOHsuPGnsFYigELZzNqenlwY0tKLVUM/86E9299C14A1391AD9349CC02DF8AA485D120EF0.E45CB30A1D6A8CFD2F945985C448C80A0B0F8A22/yt6/1/video.3gp

//heli
        //rtsp://r3---sn-a5mekn7r.googlevideo.com/Cj0LENy73wIaNAloeSYWJeMAjRMYDSANFC0fmGJZMOCoAUIASARg7NOHsuPGnsFYigELZzNqenlwY0tKLVUM/927B552A38EB1FD1D8195B25FCCEF3487E379A4E.7F14F6D190637C003B113603A685B168FD9401C3/yt6/1/video.3gp

//motorcycle

        //rtsp://r4---sn-a5mlrnez.googlevideo.com/Cj0LENy73wIaNAmmTcRqgYhQFxMYDSANFC1tmGJZMOCoAUIASARg7NOHsuPGnsFYigELZzNqenlwY0tKLVUM/3031192F0E6E8C45B3AAA16F38FD7F2DD4588374.1B5F31D14B67A26349B324362207C0ECB26D97F0/yt6/1/video.3gp

//revolver

        //rtsp://r6---sn-a5mekney.googlevideo.com/Cj0LENy73wIaNAk1jbqv49hRYhMYDSANFC2xmGJZMOCoAUIASARg7NOHsuPGnsFYigELZzNqenlwY0tKLVUM/0CCCCC3367F713404CDFD9DBB7B88C8E85BF8AD9.C2B36A6EE9EF758FE2D684FF948FDB9B6A3FD497/yt6/1/video.3gp



//        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)displayResult.getLayoutParams();
//        p.leftMargin = 20; // in PX
//        p.topMargin = 40; // in PX
//        displayResult.setLayoutParams(p);

        uploadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CAM_REQUEST);
                //selectImage();
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //permission has  not been granted, bring up dialog
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            SELECTED_PICTURE);
                } else {
                    //permission has already been granted in the past. bring up photos to choose from.
                    chooseUploadMethod();
                }
            }
        });

        /*StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            //if called permissions by pressing gallery btn
            case SELECTED_PICTURE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted,
                    chooseUploadMethod();

                } else {
                    //user denied permissions, show toast?
                    Toast.makeText(this, "User has denied permissions", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void chooseUploadMethod() {
        final CharSequence[] items = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Upload Screenshot");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    dispatchTakePictureIntent();
                } else if (items[item].equals("Gallery")) {
                    dispatchUploadFromGallery();
                }
            }
        });
        builder.show();
    }

    //launch camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            takePictureIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);

            startActivityForResult(takePictureIntent, CAM_REQUEST);
        }
    }

    //launch gallery
    private void dispatchUploadFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //get a Uri representation
        Uri data = Uri.parse(pictureDirectoryPath);
        //set the data and type.    get all image types
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, SELECTED_PICTURE);
    }


    private static int getRotation(Context context, Uri selectedImage) {
        int rotation = 0;
        ContentResolver content = context.getContentResolver();

        Cursor mediaCursor = content.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{"orientation", "date_added"}, null, null, "date_added desc");

        if (mediaCursor != null && mediaCursor.getCount() != 0) {
            while (mediaCursor.moveToNext()) {
                rotation = mediaCursor.getInt(0);
                break;
            }
        }
        mediaCursor.close();
        return rotation;
    }

    private static Bitmap rotateImageIfRequired(Context context, Bitmap img, Uri selectedImage) {
        // Detect rotation
        int rotation = getRotation(context, selectedImage);
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
            img.recycle();
            return rotatedImg;
        } else {
            return img;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (resultCode == RESULT_OK
                && null != data) {
            imageUri = data.getData();
            // decodeUri(data.getData());
            uploadImage(imageUri);
        }
    }


    //set the Uri image to the imageView
    public void uploadImage(Uri inImage) {

        postImage = inImage;
        //convert uri to bitmap
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(inImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        //ImageView imageView = (ImageView) findViewById(R.id.image_upload);
        bitmapImage = BitmapFactory.decodeFile(picturePath);

        Bitmap rotBit = rotateImageIfRequired(this, bitmapImage, inImage);
        Bitmap resize = getResizedBitmap(rotBit, 200, 200);
        Bitmap display = getResizedBitmap(rotBit, 720, 720);
        //imageView.setImageBitmap(display);

        new UploadImage().execute(resize);
        //displayResponseText(result);
        //send the image to flask server to classify
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException{

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();

        return result;

    }

    private class UploadImage extends AsyncTask<Bitmap, Void, String> {

        Bitmap image;
        //private ProgressDialog pd = new ProgressDialog(MainActivity.this);
        ProgressDialog progDailog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            displayResult.setText("");
            progDailog.setMessage("One Moment Please...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(true);
            progDailog.show();
        }



        /*public UploadImage(Bitmap image) {
            this.image = image;
        }*/

        @Override
        protected String doInBackground(Bitmap... params) {

            image = params[0];
            InputStream stream = null;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodeImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
            //web: gunicorn app.py:app
            try {
                //https://copper-sol-174321.appspot.com
                //"http://10.0.0.175:5000/"
                //"https://dnngamehints.herokuapp.com/"
                //https://copper-sol-174321.appspot.com
                URL url = new URL("https://dnngamehints.herokuapp.com/");
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setReadTimeout(30000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                c.setConnectTimeout(30000);
                // For this use case, set HTTP method to GET.
                c.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                c.setDoInput(true);
                c.setDoOutput(true);
                // Open communications link (network traffic occurs here).
                OutputStream os = c.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(encodeImage);
                writer.flush();
                writer.close();
                os.close();
                c.connect();
                //publishProgress(DownloadCallback.Progress.CONNECT_SUCCESS);
                int responseCode = c.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }
                // Retrieve the response body as an InputStream.
                stream = c.getInputStream();
                //publishProgress(DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS, 0);
                if (stream != null) {
                    // Converts Stream to String with max length of 500.
                    result = readStream(stream, 500);

                    Log.d("res", result);
                }
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            progDailog.cancel();
            return result;
            //return null;
        }
        //web: gunicorn run:app.py
        @Override
        protected void onPostExecute(String response) {
            displayResponseText(response);
            super.onPostExecute(response);
            //delegate.processFinish(result);
        }

    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;

    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    private String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        // Read InputStream using the UTF-8 charset.
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        // Create temporary buffer to hold Stream data with specified max length.
        char[] buffer = new char[maxLength];
        // Populate temporary buffer with Stream data.
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            int pct = (100 * numChars) / maxLength;
            //publishProgress(DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS, pct);
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            // The stream was not empty.
            // Create String that is actual length of response body if actual length was less than
            // max length.
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }

    @Override
    public void processFinish(String result){
        //Here you will receive the result fired from async class
    }

    public void displayResponseText(String result){

        if(result.equals("[0]")){
            displayResult.setText("1Forest CP1.1");
            //mPlayer.seekToMillis(12000);
            mPlayer.loadVideo("h1FU-T2EsVA",12000);

            //mPlayer.play();
        }
        else if (result.equals("[1]")){
            displayResult.setText("1Forest CP1.2");
            //mPlayer.seekToMillis(67000);
            mPlayer.loadVideo("h1FU-T2EsVA", 67000);

            //mPlayer.play();
        }
        else if(result.equals("[2]")){
            displayResult.setText("1Forest CP2.1");
            //mPlayer.seekToMillis(101000);
            mPlayer.loadVideo("h1FU-T2EsVA",101000);

            //mPlayer.play();
        }
        else if(result.equals("[3]")){
            displayResult.setText("1Forest CP2.2");
            //mPlayer.seekToMillis(134000);
            mPlayer.loadVideo("h1FU-T2EsVA",134000);

            //mPlayer.play();
        }
        else if (result.equals("[4]")){
            displayResult.setText("1Forest CP2.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 167000);
            //mPlayer.play();
        }
        else if(result.equals("[5]")){
            displayResult.setText("1Forest CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 202000);
            //mPlayer.play();
        }
        else if(result.equals("[6]")){
            displayResult.setText("1Forest CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 258000);
            //mPlayer.play();
        }
        else if (result.equals("[8]")){
            displayResult.setText("1Forest CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 311000);
            //mPlayer.play();
        }
        else if(result.equals("[9]")){
            displayResult.setText("1Forest CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 376000);
            //mPlayer.play();
        }
        else if(result.equals("[10]")){
            displayResult.setText("1Forest CP6.2");

            mPlayer.loadVideo("btS9rWtm_Ms", 22000);
            //mPlayer.play();
        }
        else if (result.equals("[11]")){
            displayResult.setText("2Farm CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 452000);
            //mPlayer.play();
        }
        else if(result.equals("[12]")){
            displayResult.setText("2Farm CP1.2");

            mPlayer.loadVideo("btS9rWtm_Ms", 1324000);
            //mPlayer.play();
        }
        else if(result.equals("[13]")){
            displayResult.setText("2Farm CP1.3");
            
            mPlayer.loadVideo("btS9rWtm_Ms", 78000);
            //mPlayer.play();
        }
        else if (result.equals("[14]")){
            displayResult.setText("2Farm CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 521000);
            //mPlayer.play();
        }
        else if(result.equals("[15]")){
            displayResult.setText("2Farm CP2.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 537000);
            //mPlayer.play();
        }
        else if(result.equals("[16]")){
            displayResult.setText("2Farm CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 661000);
            //mPlayer.play();
        }
        else if (result.equals("[17]")){
            displayResult.setText("2Farm CP3.2");

            mPlayer.loadVideo("btS9rWtm_Ms", 134000);
            //mPlayer.play();
        }
        else if(result.equals("[18]")){
            displayResult.setText("2Farm CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA",691000);
            //mPlayer.play();
        }
        else if(result.equals("[19]")){
            displayResult.setText("2Farm CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",731000);
            //mPlayer.play();
        }
        else if (result.equals("[20]")){
            displayResult.setText("2Farm CP5.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 753000);
            //mPlayer.play();
        }
        else if(result.equals("[21]")){
            displayResult.setText("2Farm CP5.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 794000);
            //mPlayer.play();
        }
        else if(result.equals("[22]")){
            displayResult.setText("2Farm CP5.4");

            mPlayer.loadVideo("h1FU-T2EsVA", 837000);
            //mPlayer.play();
        }
        else if (result.equals("[23]")){
            displayResult.setText("3City CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 870000);
            //mPlayer.play();
        }
        else if(result.equals("[24]")){
            displayResult.setText("3City CP1.2");

            mPlayer.loadVideo("h1FU-T2EsVA",938000);
            //mPlayer.play();
        }
        else if(result.equals("[25]")){
            displayResult.setText("3City CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 954000);
            //mPlayer.play();
        }
        else if (result.equals("[26]")){
            displayResult.setText("3City CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA",1004000);
            //mPlayer.play();
        }
        else if(result.equals("[27]")){
            displayResult.setText("3City CP4.1");

            mPlayer.loadVideo("btS9rWtm_Ms", 189000);
            //mPlayer.play();
        }
        else if(result.equals("[28]")){
            displayResult.setText("3City CP4.2");

            mPlayer.loadVideo("h1FU-T2EsVA",1039000);
            //mPlayer.play();
        }
        else if(result.equals("[29]")){
            displayResult.setText("3City CP4.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 1095000);
            //mPlayer.play();
        }
        else if(result.equals("[30]")){
            displayResult.setText("3City CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1124000);
            //mPlayer.play();
        }
        else if(result.equals("[31]")){
            displayResult.setText("3City CP5.2 ");

            mPlayer.loadVideo("btS9rWtm_Ms", 216000);
            //mPlayer.play();
        }
        else if(result.equals("[32]")){
            displayResult.setText("3City CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA",1185000);
            //mPlayer.play();
        }
        else if(result.equals("[33]")){
            displayResult.setText("3City CP6.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 1208000);
            //mPlayer.play();
        }
        else if(result.equals("[34]")){
            displayResult.setText("3City CP7.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1235000);
            //mPlayer.play();
        }
        else if(result.equals("[35]")){
            displayResult.setText("4Factory CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1320000);
            //mPlayer.play();
        }
        else if(result.equals("[36]")){
            displayResult.setText("4Factory CP1.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 1327000);
            //mPlayer.play();
        }
        else if(result.equals("[37]")){
            displayResult.setText("4Factory CP1.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 1452000);
            //mPlayer.play();
        }
        else if(result.equals("[38]")){
            displayResult.setText("4Factory CP1.4");

            mPlayer.loadVideo("h1FU-T2EsVA", 1483000);
            //mPlayer.play();
        }
        else if(result.equals("[39]")){
            displayResult.setText("4Factory CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1521000);
            //mPlayer.play();
        }
        else if(result.equals("[40]")){
            displayResult.setText("4Factory CP2.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 1534000);
            //mPlayer.play();
        }
        else if(result.equals("[41]")){
            displayResult.setText("4Factory CP2.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 1563000);
            //mPlayer.play();
        }
        else if(result.equals("[42]")){
            displayResult.setText("4Factory CP2.4");

            mPlayer.loadVideo("h1FU-T2EsVA", 1621000);
            //mPlayer.play();
        }
        else if(result.equals("[43]")){
            displayResult.setText("4Factory CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1636000);
            //mPlayer.play();
        }
        else if(result.equals("[44]")){
            displayResult.setText("5Subway CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1713000);
            //mPlayer.play();
        }
        else if(result.equals("[45]")){
            displayResult.setText("5Subway CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1816000);
            //mPlayer.play();
        }
        else if(result.equals("[46]")){
            displayResult.setText("5Subway CP2.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 1878000);
            //mPlayer.play();
        }
        else if(result.equals("[47]")){
            displayResult.setText("5Subway CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 1914000);
            //mPlayer.play();
        }
        else if(result.equals("[48]")){
            displayResult.setText("5Subway CP3.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 2005000);
            //mPlayer.play();
        }
        else if(result.equals("[49]")){
            displayResult.setText("5Subway CP3.3");

            mPlayer.loadVideo("h1FU-T2EsVA",2042000);
            //mPlayer.play();
        }
        else if(result.equals("[50]")){
            displayResult.setText("6Depths CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2122000);
            //mPlayer.play();
        }
        else if(result.equals("[51]")){
            displayResult.setText("6Depths CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2172000);
            //mPlayer.play();
        }
        else if(result.equals("[52]")){
            displayResult.setText("6Depths CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2216000);
            //mPlayer.play();
        }
        else if(result.equals("[53]")){
            displayResult.setText("6Depths CP3.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 2239000);
            //mPlayer.play();
        }
        else if(result.equals("[54]")){
            displayResult.setText("6Depths CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA",2269000);
            //mPlayer.play();
        }
        else if(result.equals("[55]")){
            displayResult.setText("6Depths CP4.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 2299000);
            //mPlayer.play();
        }
        else if(result.equals("[56]")){
            displayResult.setText("6Depths CP4.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 2341000);
            //mPlayer.play();
        }
        else if(result.equals("[57]")){
            displayResult.setText("6Depths CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",2390000);
            //mPlayer.play();
        }
        else if(result.equals("[58]")){
            displayResult.setText("6Depths CP5.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 2430000);
            //mPlayer.play();
        }
        else if(result.equals("[59]")){
            displayResult.setText("6Depths CP5.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 2443000);
            //mPlayer.play();
        }
        else if(result.equals("[60]")){
            displayResult.setText("6Depths CP5.4");

            mPlayer.loadVideo("h1FU-T2EsVA", 2460000);
            //mPlayer.play();
        }
        else if(result.equals("[61]")){
            displayResult.setText("6Depths CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2496000);
            //mPlayer.play();
        }
        else if(result.equals("[62]")){
            displayResult.setText("6Depths CP6.2");

            mPlayer.loadVideo("btS9rWtm_Ms", 238000);
            //mPlayer.play();
        }
        else if(result.equals("[63]")){
            displayResult.setText("6Depths CP6.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 2519000);
            //mPlayer.play();
        }
        else if(result.equals("[64]")){
            displayResult.setText("6Depths CP7.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2614000);
            //mPlayer.play();
        }
        else if(result.equals("[65]")){
            displayResult.setText("6Depths CP7.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 2659000);
            //mPlayer.play();
        }
        else if(result.equals("[66]")){
            displayResult.setText("6Depths CP7.3");

            mPlayer.loadVideo("btS9rWtm_Ms",403000);
            //mPlayer.play();
        }
        else if(result.equals("[67]")){
            displayResult.setText("7Mines CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2712000);
            //mPlayer.play();
        }
        else if(result.equals("[68]")){
            displayResult.setText("7Mines CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 2852000);
            //mPlayer.play();
        }
        else if(result.equals("[69]")){
            displayResult.setText("7Mines CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA",2981000);
            //mPlayer.play();
        }
        else if(result.equals("[70]")){
            displayResult.setText("7Mines CP3.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 3030000);
            //mPlayer.play();
        }
        else if(result.equals("[71]")){
            displayResult.setText("7Mines CP3.3");

            mPlayer.loadVideo("h1FU-T2EsVA",3101000);
            //mPlayer.play();
        }
        else if(result.equals("[72]")){
            displayResult.setText("7Mines CP3.4");

            mPlayer.loadVideo("btS9rWtm_Ms",624000);
            //mPlayer.play();
        }
        else if(result.equals("[73]")){
            displayResult.setText("7Mines CP3.5");

            mPlayer.loadVideo("h1FU-T2EsVA", 3367000);
            //mPlayer.play();
        }
        else if(result.equals("[74]")){
            displayResult.setText("7Mines CP3.6");

            mPlayer.loadVideo("h1FU-T2EsVA", 3380000);
            //mPlayer.play();
        }
        else if(result.equals("[75]")){
            displayResult.setText("7Mines CP3.7");

            mPlayer.loadVideo("h1FU-T2EsVA", 3300000);
            //mPlayer.play();
        }
        else if(result.equals("[76]")){
            displayResult.setText("7Mines CP3.8");

            mPlayer.loadVideo("h1FU-T2EsVA", 3244000);
            //mPlayer.play();
        }
        else if(result.equals("[77]")){
            displayResult.setText("7Mines CP3.9");

            mPlayer.loadVideo("h1FU-T2EsVA", 3459000);
            //mPlayer.play();
        }
        else if(result.equals("[78]")){
            displayResult.setText("8Bridge CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3482000);
            //mPlayer.play();
        }
        else if(result.equals("[79]")){
            displayResult.setText("8Bridge CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3537000);
            //mPlayer.play();
        }
        else if(result.equals("[80]")){
            displayResult.setText("8Bridge CP2.2");

            mPlayer.loadVideo("h1FU-T2EsVA",3573000);
            //mPlayer.play();
        }
        else if(result.equals("[81]")){
            displayResult.setText("8Bridge CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3601000);
            //mPlayer.play();
        }
        else if(result.equals("[82]")){
            displayResult.setText("8Bridge CP3.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 3612000);
            //mPlayer.play();
        }
        else if(result.equals("[83]")){
            displayResult.setText("8Bridge CP3.3");

            mPlayer.loadVideo("h1FU-T2EsVA", 3645000);
            //mPlayer.play();
        }
        else if(result.equals("[84]")){
            displayResult.setText("8Bridge CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 3669000);
            //mPlayer.play();
        }
        else if(result.equals("[85]")){
            displayResult.setText("8Bridge CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3739000);
            //mPlayer.play();
        }
        else if(result.equals("[86]")){
            displayResult.setText("8Bridge CP5.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 3793000);
            //mPlayer.play();
        }
        else if(result.equals("[87]")){
            displayResult.setText("8Bridge CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3822000);
            //mPlayer.play();
        }
        else if(result.equals("[88]")){
            displayResult.setText("8Bridge CP7.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 3859000);
            //mPlayer.play();
        }
        else if(result.equals("[89]")){
            displayResult.setText("8Bridge CP7.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 3894000);
            //mPlayer.play();
        }
        else if(result.equals("[90]")){
            displayResult.setText("8Bridge CP7.3");

            mPlayer.loadVideo("h1FU-T2EsVA",3915000);
            //mPlayer.play();
        }
        else if(result.equals("[91]")){
            displayResult.setText("8Bridge CP8.1");

            mPlayer.loadVideo("h1FU-T2EsVA",3949000);
            //mPlayer.play();
        }
        else if(result.equals("[92]")){
            displayResult.setText("9FloodedBase CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4022000);
            //mPlayer.play();
        }
        else if(result.equals("[93]")){
            displayResult.setText("9FloodedBase CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 4049000);
            //mPlayer.play();
        }
        else if(result.equals("[94]")){
            displayResult.setText("9FloodedBase CP2.2");

            mPlayer.loadVideo("btS9rWtm_Ms",701000);
            //mPlayer.play();
        }
        else if(result.equals("[95]")){
            displayResult.setText("9FloodedBase CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 4133000);
            //mPlayer.play();
        }
        else if(result.equals("[96]")){
            displayResult.setText("9FloodedBase CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4252000);
            //mPlayer.play();
        }
        else if(result.equals("[97]")){
            displayResult.setText("10FacilitiesEntrance CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 4365000);
            //mPlayer.play();
        }
        else if(result.equals("[98]")){
            displayResult.setText("10FacilitiesEntrance CP1.2");

            mPlayer.loadVideo("btS9rWtm_Ms", 761000);
            //mPlayer.play();
        }
        else if(result.equals("[99]")){
            displayResult.setText("10FacilitiesEntrance CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4411000);
            //mPlayer.play();
        }
        else if(result.equals("[100]")){
            displayResult.setText("10FacilitiesEntrance CP2.2");

            mPlayer.loadVideo("btS9rWtm_Ms",852000);
            //mPlayer.play();
        }
        else if(result.equals("[101]")){
            displayResult.setText("10FacilitiesEntrance CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4500000);
            //mPlayer.play();
        }
        else if(result.equals("[102]")){
            displayResult.setText("10FacilitiesEntrance CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 4524000);
            //mPlayer.play();
        }
        else if(result.equals("[103]")){
            displayResult.setText("10FacilitiesEntrance CP4.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 4685000);
            //mPlayer.play();
        }
        else if(result.equals("[104]")){
            displayResult.setText("10FacilitiesEntrance CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4776000);
            //mPlayer.play();
        }
        else if(result.equals("[105]")){
            displayResult.setText("11ResearchFacility CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA",4857000);
            //mPlayer.play();
        }
        else if(result.equals("[106]")){
            displayResult.setText("11ResearchFacility CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5038000);
            //mPlayer.play();
        }
        else if(result.equals("[107]")){
            displayResult.setText("11ResearchFacility CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA",5231000);
            //mPlayer.play();
        }
        else if(result.equals("[108]")){
            displayResult.setText("11ResearchFacility CP3.2");

            mPlayer.loadVideo("btS9rWtm_Ms",1159000);
            //mPlayer.play();
        }
        else if(result.equals("[109]")){
            displayResult.setText("11ResearchFacility CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5363000);
            //mPlayer.play();
        }
        else if(result.equals("[110]")){
            displayResult.setText("11ResearchFacility CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",5399000);
            //mPlayer.play();
        }
        else if(result.equals("[111]")){
            displayResult.setText("11ResearchFacility CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA",5544000);
            //mPlayer.play();
        }
        else if(result.equals("[112]")){
            displayResult.setText("11ResearchFacility CP6.2");

            mPlayer.loadVideo("btS9rWtm_Ms",1227000);
            //mPlayer.play();
        }
        else if(result.equals("[113]")){
            displayResult.setText("11ResearchFacility CP7.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5631000);
            //mPlayer.play();
        }
        else if(result.equals("[114]")){
            displayResult.setText("12Escape CP1.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5708000);
            //mPlayer.play();
        }
        else if(result.equals("[115]")){
            displayResult.setText("12Escape CP1.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 5824000);
            //mPlayer.play();
        }
        else if(result.equals("[116]")){
            displayResult.setText("12Escape CP1.3");

            mPlayer.loadVideo("h1FU-T2EsVA",5843000);
            //mPlayer.play();
        }
        else if(result.equals("[117]")){
            displayResult.setText("12Escape CP2.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5876000);
            //mPlayer.play();
        }
        else if(result.equals("[118]")){
            displayResult.setText("12Escape CP3.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 5992000);
            //mPlayer.play();
        }
        else if(result.equals("[119]")){
            displayResult.setText("12Escape CP3.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 6033000);
            //mPlayer.play();
        }
        else if(result.equals("[120]")){
            displayResult.setText("12Escape CP4.1");

            mPlayer.loadVideo("h1FU-T2EsVA",6088000);
            //mPlayer.play();
        }
        else if(result.equals("[121]")){
            displayResult.setText("12Escape CP5.1");

            mPlayer.loadVideo("h1FU-T2EsVA",6149000);
            //mPlayer.play();
        }
        else if(result.equals("[122]")){
            displayResult.setText("12Escape CP6.1");

            mPlayer.loadVideo("h1FU-T2EsVA",6276000);
            //mPlayer.play();
        }
        else if(result.equals("[123]")){
            displayResult.setText("12Escape CP7.1");

            mPlayer.loadVideo("h1FU-T2EsVA",6347000);
            //mPlayer.play();
        }
        else if(result.equals("[124]")){
            displayResult.setText("12Escape CP8.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 6378000);
            //mPlayer.play();
        }
        else if(result.equals("[125]")){
            displayResult.setText("12Escape CP8.2");

            mPlayer.loadVideo("h1FU-T2EsVA", 6392000);
            //mPlayer.play();
        }
        else if(result.equals("[126]")) {
            displayResult.setText("12Escape CP9.1");

            mPlayer.loadVideo("h1FU-T2EsVA", 6532000);
            //mPlayer.play();
        }
        else{
            displayResult.setText("There has been an error...");
        }

    }


}


