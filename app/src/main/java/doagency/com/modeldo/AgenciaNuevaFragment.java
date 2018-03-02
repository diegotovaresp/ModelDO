package doagency.com.modeldo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.helper.PaisSpinAdapter;
import doagency.com.modeldo.objetos.Pais;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgenciaNuevaFragment extends Fragment {

    private String TAG = "Agencia Nueva Fragment";
    int idModelo,idUsuario;
    String newEmail, NombreModelo;
    String RUC,Telefono,Direccion,Agencia,elusuariom;
    TextView tvemails;
    EditText etnombreagencia,etrucagencia,ettelefonoagencia,etdireccionagencia;
    ArrayList<Pais> paisList;
    ArrayList<String> paisNames = new ArrayList<String>();
    private PaisSpinAdapter adapterp;
    View v;
    int idPais;
    Button btnAgregar;

    public AgenciaNuevaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_agencia_nueva, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));
            NombreModelo=bundle.getString("NombreModelo");
        }
        paisList= new ArrayList<>();
        new GetPaises().execute();

        btnAgregar=(Button) v.findViewById(R.id.btnagragencia);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etnombreagencia=(EditText) v.findViewById(R.id.etnombreagencia);
                etrucagencia=(EditText) v.findViewById(R.id.etrucagencia);
                ettelefonoagencia=(EditText) v.findViewById(R.id.ettelefonoagencia);
                etdireccionagencia=(EditText) v.findViewById(R.id.etdireccionagencia);
                Agencia= etnombreagencia.getText().toString();
                RUC=etrucagencia.getText().toString();
                Telefono=ettelefonoagencia.getText().toString();
                Direccion=etdireccionagencia.getText().toString();
                new SetAgencia().execute();
            }
        });
        return  v;
    }

    private class GetPaises extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            HashMap<String, String> params = new HashMap<>();
            String url = "http://model.do-agency.com/BD/BLL/General/paises.php";
            String jsonStr =  sh.sendPostRequest(url, params);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String IdPais = c.getString("idPais");
                        String Pais = c.getString("Pais");
                        String importancia = c.getString("importancia");
                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        Pais elpais= new Pais();
                        // adding each child node to HashMap key => value

                        elpais.setIdPais(Integer.parseInt(IdPais));
                        elpais.setPais(Pais);

                        elpais.setImportancia(Integer.parseInt(importancia));

                        // adding contact to contact list
                        //   guestList.add(contact);
                        paisList.add(elpais);
                        paisNames.add(Pais);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
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
                Log.e(TAG, "Couldn't get json from server.");
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
            Spinner spinnerpais= (Spinner) v.findViewById(R.id.spinnerpais);
            adapterp=     new PaisSpinAdapter(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, paisList);

            spinnerpais.setAdapter(adapterp);
            spinnerpais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Pais pais= adapterp.getItem(position);

                    idPais=pais.getIdPais();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }


    private class SetAgencia extends AsyncTask<Void, Void, Void> {

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
            params.put("usuario", String.valueOf(idUsuario));
            params.put("RUC", String.valueOf(RUC));
            params.put("idPais", String.valueOf(idPais));
            params.put("Agencia", String.valueOf(Agencia));
            params.put("Telefono", String.valueOf(Telefono));
            params.put("Direccion", String.valueOf(Direccion));

            String url = "http://model.do-agency.com/BD/BLL/Agencia/nuevomob.php";
            String jsonStr =  sh.sendPostRequest(url, params);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {

            } else {
                Log.e(TAG, "Couldn't get json from server.");
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
            Toast.makeText(getActivity().getApplicationContext(),"Agregado con Exito",Toast.LENGTH_LONG).show();

        }
    }


}
