package doagency.com.modeldo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotoPerfilFragment extends Fragment {

    View v;
    String newEmail,NombreModelo,ImageUrl;
    int idModelo,idUsuario;
    ImageView ivfotoPerfil;

    public FotoPerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_foto_perfil, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            newEmail=bundle.getString("Email");
            idModelo=Integer.parseInt(bundle.getString("idModelo"));
            idUsuario=Integer.parseInt(bundle.getString("idUsuario"));
            NombreModelo=bundle.getString("NombreModelo");
            ImageUrl=bundle.getString("perfilImagen");

        }

        ivfotoPerfil=(ImageView) v.findViewById(R.id.ivFotoPerfil);
        Picasso.with(getActivity().getApplicationContext()).load(ImageUrl).into(ivfotoPerfil);


        return v;
    }

}
