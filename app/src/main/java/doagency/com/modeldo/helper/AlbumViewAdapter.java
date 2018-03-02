package doagency.com.modeldo.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import doagency.com.modeldo.FotosActivity;
import doagency.com.modeldo.R;
import doagency.com.modeldo.objetos.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Diego on 24/01/2017.
 */

public class AlbumViewAdapter extends BaseAdapter  {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Album> Albumlist = null;
    private ArrayList<Album> arraylist;
    int idModelo;
    int idModeloAlbum;
    String NombreModelo,NewEmail,elusuariom;
    int idTipoModelo;
    int idUsuario;

    public AlbumViewAdapter(Context context, List<Album> Albumlist) {
        mContext = context;
        this.Albumlist = Albumlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Album>();
        this.arraylist.addAll(Albumlist);
    }

    public class ViewHolder {
        TextView nombre;
        Button tbalbum;
        ImageButton comparte;
    }

    @Override
    public int getCount() {
        return Albumlist.size();
    }

    @Override
    public Album getItem(int position) {
        return Albumlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.albumlist_item, null);
            // Locate the TextViews in listview_item.xml
            holder.nombre = (TextView) view.findViewById(R.id.nombre);
            holder.tbalbum = (Button)  view.findViewById(R.id.tbalbum);
            holder.comparte=(ImageButton) view.findViewById(R.id.tbalbumcomparte);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.nombre.setText(Albumlist.get(position).getNombre());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, FotosActivity.class);
                // Pass all data rank
                intent.putExtra("nombre",(Albumlist.get(position).getNombre()));
                // Pass all data country
                // Pass all data population

                // Pass all data flag
                // Start SingleItemView Class
                //  mContext.startActivity(intent);
            }
        });
        holder.comparte.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                idModelo= Albumlist.get(position).getIdModelo();
                idModeloAlbum=Albumlist.get(position).getIdModeloAlbum();
                elusuariom=Albumlist.get(position).getElusuariom();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String link="http://www.do-agency.com/modelo.php?m="+String.valueOf(elusuariom)+"&a="+ String.valueOf(idModeloAlbum);
                sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Comparto mi Book");

                sendIntent.putExtra(Intent.EXTRA_TEXT, link);
                sendIntent.setType("text/plain");
                Intent escoger= Intent.createChooser(sendIntent,mContext.getResources().getText(R.string.send_to));
                mContext.startActivity(escoger);
            }
        });
        holder.tbalbum.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                idModelo= Albumlist.get(position).getIdModelo();
                idModeloAlbum=Albumlist.get(position).getIdModeloAlbum();
                NombreModelo=Albumlist.get(position).getNombreModelo();
                NewEmail=Albumlist.get(position).getNewEmail();
                Intent intent = new Intent(mContext, FotosActivity.class);
                idUsuario= Albumlist.get(position).getIdUsuario();
                intent.putExtra("idModelo",idModelo);
                intent.putExtra("idModeloAlbum",idModeloAlbum);
                intent.putExtra("NombreModelo",NombreModelo);
                intent.putExtra("NewEmail",NewEmail);
                intent.putExtra("idUsuario",idUsuario);
                mContext.startActivity(intent);





            }
        });
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Albumlist.clear();
        if (charText.length() == 0) {
            Albumlist.addAll(arraylist);
        }
        else
        {
            for (Album wp : arraylist)
            {

                if (wp.getNombre().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    Albumlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }





}
