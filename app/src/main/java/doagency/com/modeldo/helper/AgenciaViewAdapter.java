package doagency.com.modeldo.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import doagency.com.modeldo.AgenciaEditActivity;
import doagency.com.modeldo.R;
import doagency.com.modeldo.objetos.Agencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Diego on 24/01/2017.
 */

public class AgenciaViewAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Agencia> Agencialist = null;
    private ArrayList<Agencia> arraylist;
    int idModelo;
    int idModeloAlbum;
    String NombreModelo, NewEmail,elusuariom;
    int idUsuario;

    public AgenciaViewAdapter(Context context, List<Agencia> Agencialist) {
        mContext = context;
        this.Agencialist = Agencialist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Agencia>();
        this.arraylist.addAll(Agencialist);
    }

    public class ViewHolder {
        TextView nombreagencia;
        ImageButton btagencia;
        TextView tvrucagencia;
        TextView tvdeusol;
        TextView tvdeudol;
        ImageButton fallamaragencia;
    }

    @Override
    public int getCount() {
        return Agencialist.size();
    }

    @Override
    public Agencia getItem(int position) {
        return Agencialist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.agencialist_item, null);
            // Locate the TextViews in listview_item.xml
            holder.nombreagencia = (TextView) view.findViewById(R.id.nombreagencia);
            holder.tvrucagencia = (TextView) view.findViewById(R.id.tvrucagencia);
            holder.tvdeusol = (TextView) view.findViewById(R.id.tvdeusol);
            holder.tvdeudol = (TextView) view.findViewById(R.id.tvdeudol);
            holder.fallamaragencia = (ImageButton) view.findViewById(R.id.fallamaragencia);
            holder.btagencia = (ImageButton) view.findViewById(R.id.btagencia);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.nombreagencia.setText(Agencialist.get(position).getNombreAgencia());
        String TXTRUC = "RUC" + Agencialist.get(position).getRUCAgencia();
        holder.tvrucagencia.setText(TXTRUC);
        String TXTSOLES = "Deuda Soles: S/. " + Agencialist.get(position).getSoles();
        String Telefono2 = Agencialist.get(position).getTelefono();

        holder.tvdeusol.setText(TXTSOLES);
        String TXTDOL = "Deuda Dolares: $ " + Agencialist.get(position).getDolares();

        holder.tvdeudol.setText(TXTDOL);

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, AgenciaEditActivity.class);
                // Pass all data rank
                intent.putExtra("nombre", (Agencialist.get(position).getNombreAgencia()));
                intent.putExtra("Email", (Agencialist.get(position).getNewEmail()));
                intent.putExtra("idModelo", (Agencialist.get(position).getIdModelo()));
                intent.putExtra("idUsuario", (Agencialist.get(position).getIdUsuario()));
                intent.putExtra("NombreModelo", (Agencialist.get(position).getNombreModelo()));



                // Pass all data country
                // Pass all data population

                // Pass all data flag
                // Start SingleItemView Class
                //  mContext.startActivity(intent);
            }
        });
        holder.fallamaragencia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String Telefono = Agencialist.get(position).getTelefono();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String llamar = "tel:" + Telefono;
                callIntent.setData(Uri.parse(llamar));
               mContext.startActivity(callIntent);


    }
});
        holder.btagencia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                idModelo= Agencialist.get(position).getIdModelo();
                NombreModelo=Agencialist.get(position).getNombreModelo();
                NewEmail=Agencialist.get(position).getNewEmail();
                elusuariom=Agencialist.get(position).getElusuariom();
                Intent intent = new Intent(mContext, AgenciaEditActivity.class);
                idUsuario= Agencialist.get(position).getIdUsuario();
                intent.putExtra("EXTRA_SESSION_IDMODELO",idModelo);
                intent.putExtra("EXTRA_SESSION_NOMBRE",NombreModelo);
                intent.putExtra("EXTRA_SESSION_EMAIL",NewEmail);
                intent.putExtra("EXTRA_SESSION_IDUSUARIO",idUsuario);

                intent.putExtra("EXTRA_SESSION_MUSUARIO",elusuariom);
                intent.putExtra("idAgencia",(Agencialist.get(position).getIdAgencia()));
                intent.putExtra("idAgenciaModelo",(Agencialist.get(position).getIdAgenciaModelo()));
                intent.putExtra("Agencia",(Agencialist.get(position).getNombreAgencia()));
                intent.putExtra("Direccion",(Agencialist.get(position).getDireccion()));
                intent.putExtra("RUC",(Agencialist.get(position).getRUCAgencia()));
                intent.putExtra("Telefono",(Agencialist.get(position).getTelefono()));

                mContext.startActivity(intent);





            }
        });
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Agencialist.clear();
        if (charText.length() == 0) {
            Agencialist.addAll(arraylist);
        }
        else
        {
            for (Agencia wp : arraylist)
            {

                if (wp.getNombreAgencia().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    Agencialist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }





}
