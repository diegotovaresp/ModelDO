package doagency.com.modeldo;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.objetos.Usuario;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private FrameLayout mMainframe;
    private InicioFragment inicioFragment;
    private EventoFragment eventoFragment;
    private BookFragment bookFragment;
    private AgenciaFragment agenciaFragment;
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;
    String newEmail;
    Usuario usuario;
    int idModelo,idUsuario;
    String NombreModelo,elusuariom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
         mMainframe = (FrameLayout) findViewById(R.id.main_frame);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newEmail = null;
            } else {
                newEmail = extras.getString("EXTRA_SESSION_EMAIL");
            }
        } else {
            newEmail = (String) savedInstanceState.getSerializable("EXTRA_SESSION_EMAIL");
        }

        new GetModelo().execute();
         inicioFragment= new InicioFragment();
         eventoFragment= new EventoFragment();
         bookFragment = new BookFragment();
         agenciaFragment= new AgenciaFragment();
         SetFragment(inicioFragment);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
        case R.id.navigation_home:
       // navigation.setItemBackgroundResource(R.color.colorPrimary);
        SetFragment(inicioFragment);
        return true;
        case R.id.navigation_book:
        SetFragment(bookFragment);
        return true;
        case R.id.navigation_eventos:
        SetFragment(eventoFragment);
        return true;
        case R.id.navigation_agencias:
        SetFragment(agenciaFragment);
        return true;
        }
        return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.main_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, intent);
        }
    }

    private void SetFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("Email",newEmail);
        bundle.putString("idModelo",String.valueOf(idModelo));
        bundle.putString("idUsuario",String.valueOf(idUsuario));
        bundle.putString("NombreModelo",NombreModelo);


        FragmentManager fragmentManager = getFragmentManager();
          Fragment fff= fragmentManager.findFragmentById(R.id.main_frame);
        if (fff == null){
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit();
        }else{
          if(!fff.equals(fragment)  ){
              fragment.setArguments(bundle);
              fragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit();
          }
        }
    }


    private class GetModelo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            // Making a request to url and getting response
            HashMap<String, String> params = new HashMap<>();
            params.put("idUsuario", String.valueOf(newEmail));
            String url = "http://model.do-agency.com/BD/BLL/Usuario/elusuario.php";
            String jsonStr = hh.sendPostRequest(url, params);

            Log.e("InicioFragment", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String nombre = c.getString("nombre");
                        NombreModelo=nombre;
                        String id=c.getString("id");
                        String emailc=c.getString("email");
                        String fechanac=c.getString("fechanac");
                        String paisn=c.getString("pais");
                        String paisa=c.getString("paisactual");
                        String musuario=c.getString("musuario");
                        elusuariom=musuario;
                        idModelo=Integer.parseInt(c.getString("idmodelo"));
                        idUsuario=Integer.parseInt(c.getString("id"));;
                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        usuario = new Usuario();
                        // adding each child node to HashMap key => value

                        usuario.setNombre(nombre);
                        usuario.setId(Integer.parseInt(id));
                        // adding contact to contact list
                        //   guestList.add(contact);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("InicioFragmento", "Couldn't get json from server.");
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);



        }
    }

}
