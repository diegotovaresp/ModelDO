package doagency.com.modeldo;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.objetos.Usuario;


public class InicioFragment extends Fragment {

    LinearLayout lltop;
    String newEmail;
    String elusuariom;
    int idModelo,idUsuario;
    Usuario usuario;
    String newNombre="",ImageUrl="";
    TextView NomAnfitriona,DeudaSol,DeudaDol;
    String deudad,deudas="";
    CircleImageView ivPerfil;
    FotoPerfilFragment fotoPerfilFragment;
    View v;

    String NombreModelo;

    public InicioFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){



         v= inflater.inflate(R.layout.fragment_inicio,container, false);
        lltop=(LinearLayout) v.findViewById(R.id.lltoply);
        Context context=getActivity().getApplicationContext();
        WindowManager windowManager =(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = null;
        if (windowManager != null) {
            display = windowManager.getDefaultDisplay();
        }
        Point size = new Point();
        display.getSize(size);
        ivPerfil=(CircleImageView) v.findViewById(R.id.ivPerfil);
        fotoPerfilFragment= new FotoPerfilFragment();
        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            SetFragment(fotoPerfilFragment);
            }
        });
        int width= size.x;
        int heigth= size.y;
        ViewGroup.LayoutParams layoutParams= lltop.getLayoutParams();
        layoutParams.height= (int) Math.round(heigth*0.5);
        lltop.setLayoutParams(layoutParams);
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
        }

       new GetModelo().execute();

        return v;

    }



    private void SetFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("Email",newEmail);
        bundle.putString("idModelo",String.valueOf(idModelo));
        bundle.putString("idUsuario",String.valueOf(idUsuario));
        bundle.putString("NombreModelo",NombreModelo);
        bundle.putString("elusuariom",elusuariom);

        bundle.putString("perfilImagen",ImageUrl);



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
                        String fotoPerfil=c.getString("fotoPerfil");
                        ImageUrl=fotoPerfil;
                        elusuariom=musuario;
                        idModelo=Integer.parseInt(c.getString("idmodelo"));
                        idUsuario=Integer.parseInt(c.getString("id"));
                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        usuario = new Usuario();
                        // adding each child node to HashMap key => value

                        usuario.setNombre(nombre);
                        usuario.setFotoPerfil(fotoPerfil);
                        usuario.setId(Integer.parseInt(id));
                        newNombre = nombre;
                        deudad=c.getString("deudad");
                        deudas=c.getString("deudas");
                        // adding contact to contact list
                        //   guestList.add(contact);
                    }
                } catch (final JSONException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e("InicioFragmento", "Couldn't get json from server.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
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

            NomAnfitriona = (TextView) v.findViewById(R.id.tvAnfitriona);

            NomAnfitriona.setText(newNombre);
            DeudaSol = (TextView) v.findViewById(R.id.tvdeudasol);
            DeudaSol.setText(deudas);
            DeudaDol = (TextView) v.findViewById(R.id.tvdeudadol);
            DeudaDol.setText(deudad);

            ivPerfil= (CircleImageView) v.findViewById(R.id.ivPerfil);
            if (!ImageUrl.isEmpty()){
                Picasso.with(getActivity().getApplicationContext()).load(ImageUrl).into(ivPerfil);

            }

        }
    }



}
