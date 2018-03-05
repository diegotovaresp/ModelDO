package doagency.com.modeldo;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Base64;


import com.squareup.picasso.Picasso;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import doagency.com.modeldo.helper.FilePath;
import doagency.com.modeldo.helper.HttpHandler;


public class FotoPerfilFragment extends Fragment {

    private View v;
    private String newEmail,NombreModelo,ImageUrl;
    private int idModelo,idUsuario;
    private ImageView ivfotoPerfil;

    String ImageTag = "image_tag" ;

    String ImageName = "image_data" ;
    private String selectedFilePath;
    Bitmap FixBitmap;
    ByteArrayOutputStream byteArrayOutputStream ;
    byte[] byteArray ;
    String ConvertImage ;
    HttpURLConnection httpURLConnection ;
    OutputStream outputStream;

    BufferedWriter bufferedWriter ;
    StringBuilder stringBuilder;
    File file;

    boolean check = true;

    private int GALLERY = 1, CAMERA = 2;
    int RC ;

    BufferedReader bufferedReader ;
    URL url;


    private String SERVER_URL = "http://model.do-agency.com/BD/BLL/Modelo/fotoperfil.php";

    private static final int PICK_FILE_REQUEST = 1;
    TextView tvFileName;
    Button btnUpload;

    ProgressDialog dialog;

    public FotoPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_foto_perfil, container, false);
        Bundle bundle = getArguments();
        byteArrayOutputStream = new ByteArrayOutputStream();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));
            NombreModelo=bundle.getString("NombreModelo");
            ImageUrl=bundle.getString("perfilImagen");

        }

        ivfotoPerfil=(ImageView) v.findViewById(R.id.ivFotoPerfil);
        if (!ImageUrl.isEmpty()){
            Picasso.with(getActivity().getApplicationContext()).load(ImageUrl).into(ivfotoPerfil);

        }
        btnUpload=(Button) v.findViewById(R.id.btnUploadPerfil);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//on upload button Click
                showPictureDialog();

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
   //     super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GALLERY){
                if(data == null){
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(getActivity().getApplicationContext(),selectedFileUri);

                try {
                    FixBitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selectedFileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ivfotoPerfil.setImageBitmap(FixBitmap);
                Log.i("FotoPerfil Fragment","Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    new uploadFile().execute();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == CAMERA) {
                FixBitmap = (Bitmap) data.getExtras().get("data");
                ivfotoPerfil.setImageBitmap(FixBitmap);
                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(getActivity().getApplicationContext(),selectedFileUri);
                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    new uploadFile().execute();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }

                //  saveImage(thumbnail);
                //Toast.makeText(ShadiRegistrationPart5.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }




        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        getActivity().startActivityForResult(Intent.createChooser(intent,"Escoge un Archivo.."),PICK_FILE_REQUEST);
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
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
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    private class uploadFile extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... arg0) {
            FixBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);

            byteArray = byteArrayOutputStream.toByteArray();
            ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            ImageProcessClass imageProcessClass = new ImageProcessClass();


            HashMap<String,String> HashMapParams = new HashMap<>();

            String idmodelos= String.valueOf(idUsuario);
            String tag="idmodelo";

            HashMapParams.put(tag,idmodelos);

            HashMapParams.put(ImageName, ConvertImage);
            HttpHandler hh = new HttpHandler();
            String FinalData = hh.sendPostRequest(SERVER_URL, HashMapParams);
            Log.e("FotoPerfilFragment", "Response from url: " + FinalData);
            if (FinalData != null){

            } else {
            Log.e("FotoPerfilFragment", "Couldn't get json from server.");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
            return FinalData;
    }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }

    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(requestURL);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(20000);

                httpURLConnection.setConnectTimeout(20000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(

                        new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(bufferedWriterDataFN(PData));

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();

                RC = httpURLConnection.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReader.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            stringBuilder = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {
                if (check)
                    check = false;
                else
                    stringBuilder.append("&");

                stringBuilder.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilder.append("=");

                stringBuilder.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilder.toString();
        }

    }
}
