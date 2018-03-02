package doagency.com.modeldo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import doagency.com.modeldo.helper.HttpHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgenciaEditFragment extends Fragment {

String newEmail,NombreModelo,NAgencia,Direccion,Telefono,RUC;
int idModelo,idUsuario,idAgenciaModelo,idAgencia;
EditText etenombreagencia,eterucagencia,etetelefonoagencia,etedireccionagencia;
    View v;
    public AgenciaEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_agencia_edit, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));

            idAgencia=Integer.parseInt(bundle.getString("idAgencia"));
            idAgenciaModelo=Integer.parseInt(bundle.getString("idAgenciaModelo"));
            NombreModelo=bundle.getString("NombreModelo");
            NAgencia=bundle.getString("NAgencia");
            Direccion=bundle.getString("Direccion");
            Telefono=bundle.getString("Telefono");
            RUC=bundle.getString("RUC");
        }

        etenombreagencia=(EditText) v.findViewById(R.id.etenombreagencia);
        etenombreagencia.setText(NAgencia);
        eterucagencia=(EditText) v.findViewById(R.id.eterucagencia);
        eterucagencia.setText(RUC);
        etedireccionagencia=(EditText) v.findViewById(R.id.etedireccionagencia);
        etedireccionagencia.setText(Direccion);
        etetelefonoagencia=(EditText) v.findViewById(R.id.etetelefonoagencia);
        etetelefonoagencia.setText(Telefono);

        Button edit = (Button) v.findViewById(R.id.btneagencia);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etenombreagencia=(EditText) v.findViewById(R.id.etenombreagencia);
                eterucagencia=(EditText) v.findViewById(R.id.eterucagencia);
                etetelefonoagencia=(EditText) v.findViewById(R.id.etetelefonoagencia);
                etedireccionagencia=(EditText) v.findViewById(R.id.etedireccionagencia);
                NAgencia= etenombreagencia.getText().toString();
                RUC=eterucagencia.getText().toString();
                Telefono=etetelefonoagencia.getText().toString();
                Direccion=etedireccionagencia.getText().toString();
                new SetAgenciaModelo().execute();
            }
        });

        return v;
    }


    private class SetAgenciaModelo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            HashMap<String, String> params = new HashMap<>();

            params.put("idAgenciaModelo", String.valueOf(idAgenciaModelo));
            params.put("idModelo", String.valueOf(idModelo));
            params.put("idAgencia", String.valueOf(idAgencia));
            params.put("Agencia", String.valueOf(NAgencia));
            params.put("Telefono", String.valueOf(Telefono));
            params.put("Direccion", String.valueOf(Direccion));

            String url = "http://model.do-agency.com/BD/BLL/Agencia/editmob.php";
            String jsonStr =  sh.sendPostRequest(url, params);

            Log.e("AgenciaEditFragment", "Response from url: " + jsonStr);
            if (jsonStr != null) {

            } else {
                Log.e("AgenciaEditFragment", "Couldn't get json from server.");
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
            Toast.makeText(getActivity().getApplicationContext(),"Grabado con Exito",Toast.LENGTH_LONG).show();
        }
    }


}
