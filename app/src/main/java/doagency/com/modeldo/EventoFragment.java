package doagency.com.modeldo;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import doagency.com.modeldo.helper.EventoViewAdapter;
import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.objetos.Evento;


public class EventoFragment extends Fragment {

    private String TAG = "EventoFragment";
    EventoViewAdapter adapter;
    String newEmail, NombreModelo, elusuariom;
    ListView lv;
    ArrayList<Evento> eventoList;
    int idModelo,idUsuario;


    public EventoFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_evento, container, false);
        lv = (ListView) v.findViewById(R.id.lvieweve);
        eventoList = new ArrayList<>();

        new GetEventos().execute();
        return  v;
    }

    private class GetEventos extends AsyncTask<Void, Void, Void> {

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
            String url = "http://model.do-agency.com/BD/BLL/Evento/listamob.php";
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
                        String idevento = c.getString("idevento");
                        String pagado = c.getString("pagado");
                        String enviado = c.getString("enviado");
                        String evento = c.getString("evento");
                        String idagencia= c.getString("idagencia");
                        String agencia = c.getString("agencia");
                        String fechainicial = c.getString("fechainicial");
                        String horainicial = c.getString("horainicial");
                        String fechafinal= c.getString("fechafinal");
                        String horafinal = c.getString("horafinal");
                        String canthoras = c.getString("canthoras");
                        String mes = c.getString("mes");
                        String precio= c.getString("precio");
                        String idtipomoneda = c.getString("idtipomoneda");
                        String tipomoneda = c.getString("tipomoneda");

                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        Evento elevento= new Evento();
                        // adding each child node to HashMap key => value

                        elevento.setIdModelo(idModelo);
                        elevento.setNAgencia(agencia);
                        elevento.setIdEvento(Integer.parseInt(idevento));
                        elevento.setIdAgencia(Integer.parseInt(idagencia));
                        elevento.setPagado(Integer.parseInt(pagado));
                        elevento.setEnviado(Integer.parseInt(enviado));
                        elevento.setNEvento(evento);
                        elevento.setNombreModelo(NombreModelo);
                        elevento.setNewEmail(newEmail);
                        elevento.setTipoMoneda(tipomoneda);
                        elevento.setCantHoras(Integer.parseInt(canthoras));
                        elevento.setFechaInicio(Date.valueOf(fechainicial));
                        elevento.setFechaFin(Date.valueOf(fechafinal));
                        elevento.setHoraInicio(Time.valueOf(horainicial));
                        elevento.setHoraFin(Time.valueOf(horafinal));
                        elevento.setPrecio(Float.valueOf(precio));
                        elevento.setIdTipoMoneda(Integer.valueOf(idtipomoneda));
                        elevento.setElusuariom(elusuariom);
                        elevento.setIdUsuario(idUsuario);
                        // adding contact to contact list
                        //   guestList.add(contact);
                        eventoList.add(elevento);
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
            adapter = new EventoViewAdapter(getActivity(), eventoList);
            lv.setAdapter(adapter);
        }
    }


}
