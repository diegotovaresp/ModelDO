package doagency.com.modeldo;


import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import doagency.com.modeldo.helper.AgenciaViewAdapter;
import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.objetos.Agencia;


public class AgenciaFragment extends Fragment {

    ListView lvAgencia;
    AgenciaViewAdapter adapter;
    AgenciaNuevaFragment agenciaNuevaFragment;

    int idModelo,idUsuario;
    String newEmail, NombreModelo,elusuariom;

    ArrayList<Agencia> agenciaList;
    FloatingActionButton nuevagencia;

    public AgenciaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_agencia, container, false);
        lvAgencia = (ListView) v.findViewById(R.id.lvAgencias);
        agenciaNuevaFragment= new AgenciaNuevaFragment();
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));
            NombreModelo=bundle.getString("NombreModelo");
        }
        agenciaList = new ArrayList<>();

        nuevagencia=(FloatingActionButton)v.findViewById(R.id.fabnueagencia);

        nuevagencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFragment(agenciaNuevaFragment);
            }
        });


        new GetAgencias().execute();
        return  v;
    }


    private void SetFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("Email",newEmail);
        bundle.putString("idModelo",String.valueOf(idModelo));
        bundle.putString("idUsuario",String.valueOf(idUsuario));
        bundle.putString("NombreModelo",NombreModelo);


        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame,fragment).commit();
    }


    private class GetAgencias extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            HashMap<String, String> params = new HashMap<>();
            params.put("idModelo", String.valueOf(idModelo));
            String url = "http://model.do-agency.com/BD/BLL/Agencia/listamob.php";
            String jsonStr =  sh.sendPostRequest(url, params);

            Log.e("AgenciaFragment", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String idagencia = c.getString("idagencia");
                        String idagenciamodelo = c.getString("idagenciamodelo");
                        String nombreagencia = c.getString("nombreagencia");
                        String rucagencia = c.getString("rucagencia");
                        String soles = c.getString("soles");
                        String dolares = c.getString("dolares");
                        String telefono= c.getString("telagencia");
                        String direccion = c.getString("diragencia");

                        String idpais = c.getString("idpais");
                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        Agencia laagencia= new Agencia();
                        // adding each child node to HashMap key => value

                        laagencia.setIdModelo(idModelo);
                        laagencia.setNombreAgencia(nombreagencia);
                        laagencia.setIdPais(Integer.parseInt(idpais));
                        laagencia.setIdAgencia(Integer.parseInt(idagencia));
                        laagencia.setIdAgenciaModelo(Integer.parseInt(idagenciamodelo));
                        laagencia.setRUCAgencia(rucagencia);
                        laagencia.setSoles(soles);
                        laagencia.setDolares(dolares);
                        laagencia.setNombreModelo(NombreModelo);
                        laagencia.setNewEmail(newEmail);
                        laagencia.setTelefono(telefono);
                        laagencia.setDireccion(direccion);
                        // adding contact to contact list
                        //   guestList.add(contact);
                        agenciaList.add(laagencia);
                    }
                } catch (final JSONException e) {
                    Log.e("AgenciaFragment", "Json parsing error: " + e.getMessage());
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
                Log.e("AgenciaFragment", "Couldn't get json from server.");
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
            adapter = new AgenciaViewAdapter(getActivity(), agenciaList);
            lvAgencia = (ListView) getActivity().findViewById(R.id.lvAgencias);
            lvAgencia.setAdapter(adapter);
        }
    }


}
