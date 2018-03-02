package doagency.com.modeldo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
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

import doagency.com.modeldo.helper.AlbumViewAdapter;
import doagency.com.modeldo.helper.HttpHandler;
import doagency.com.modeldo.objetos.Album;


public class BookFragment extends Fragment {

    ListView lvAlbum;
    AlbumViewAdapter adapter;

    int idModelo,idUsuario;
    String newEmail, NombreModelo,elusuariom;

    ArrayList<Album> albumList;

    public BookFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_book, container, false);

        lvAlbum = (ListView) v.findViewById(R.id.lviewlba);
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));
            NombreModelo=bundle.getString("NombreModelo");
        }
        albumList= new ArrayList<>();
        new GetAlbums().execute();
        return v;
    }



    private class GetAlbums extends AsyncTask<Void, Void, Void> {

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
            String url = "http://model.do-agency.com/BD/BLL/Album/listamob.php";
            String jsonStr =  sh.sendPostRequest(url, params);

            Log.e("BookFragment", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String idModelo = c.getString("idModelo");
                        String nombre = c.getString("nombre");
                        String idModeloAlbums = c.getString("idModeloAlbum");
                        String idTipoModelo = c.getString("idTipoModelo");

                        // tmp hash map for single contact
                        //  HashMap<String, String> contact = new HashMap<>();
                        Album elalbum= new Album();
                        // adding each child node to HashMap key => value

                        elalbum.setIdModelo(Integer.parseInt(idModelo));
                        elalbum.setNombre(nombre);
                        elalbum.setIdModeloAlbum(Integer.parseInt(idModeloAlbums));
                        elalbum.setIdTipoModelo(Integer.parseInt(idTipoModelo));
                        elalbum.setNombreModelo(NombreModelo);
                        elalbum.setNewEmail(newEmail);
                        elalbum.setElusuariom(elusuariom);
                        // adding contact to contact list
                        //   guestList.add(contact);
                        albumList.add(elalbum);
                    }
                } catch (final JSONException e) {
                    Log.e("Book Fragment", "Json parsing error: " + e.getMessage());
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
                Log.e("Book Fragment", "Couldn't get json from server.");
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
            adapter = new AlbumViewAdapter(getActivity(), albumList);
            lvAlbum.setAdapter(adapter);
        }
    }



}
