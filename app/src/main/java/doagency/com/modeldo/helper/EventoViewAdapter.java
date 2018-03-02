package doagency.com.modeldo.helper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import doagency.com.modeldo.EventoActivity;
import doagency.com.modeldo.EventoEditActivity;
import doagency.com.modeldo.R;
import doagency.com.modeldo.objetos.Evento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/**
 * Created by Diego on 24/01/2017.
 */

public class EventoViewAdapter extends BaseAdapter {
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Evento> Eventolist = null;
    private ArrayList<Evento> arraylist;
    int idModelo;
    int idEvento;
    int idModeloAlbum;
    String NombreModelo, NewEmail, elusuariom;
    int idUsuario;

    public EventoViewAdapter(Context context, List<Evento> Eventolist) {
        mContext = context;
        this.Eventolist = Eventolist;

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Evento>();
        this.arraylist.addAll(Eventolist);
    }

    public class ViewHolder {
        TextView nombreEvento;
        Button btEvento;
        TextView tvfecha;
        TextView tvagencia;
        TextView tvhorain;
        TextView tvcanthoras;
        ToggleButton tbpagado;
        ToggleButton tbenviado;
    }

    @Override
    public int getCount() {
        return Eventolist.size();
    }

    @Override
    public Evento getItem(int position) {
        return Eventolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.eventolist_item, null);
            // Locate the TextViews in listview_item.xml
            holder.nombreEvento = (TextView) view.findViewById(R.id.nombreevento);
            holder.tvhorain=(TextView) view.findViewById(R.id.tvHoraev);
            holder.tvfecha = (TextView) view.findViewById(R.id.tvfecha);
            holder.tvcanthoras = (TextView) view.findViewById(R.id.tvHoraev);
            holder.tvagencia = (TextView) view.findViewById(R.id.tvagenciaeve);
            holder.btEvento = (Button) view.findViewById(R.id.btevento);
            holder.tbpagado=(ToggleButton) view.findViewById(R.id.tbpagado);
            holder.tbenviado=(ToggleButton) view.findViewById(R.id.tbenviado);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
       holder.nombreEvento.setText(Eventolist.get(position).getNEvento());
        idEvento=Eventolist.get(position).getIdEvento();
        holder.tvagencia.setText(Eventolist.get(position).getNAgencia());
          String TXTFECHA = "Fecha: " + Eventolist.get(position).getFechaInicio().toString();
        String TXTHORA="Hora: "+Eventolist.get(position).getHoraInicio().toString();
           holder.tvfecha.setText(TXTFECHA);
           holder.tvhorain.setText(TXTHORA);
        String TXTCANT="Duracion: "+ String.valueOf(Eventolist.get(position).getCantHoras())+" horas";
        holder.tvcanthoras.setText(TXTCANT);
        if (Eventolist.get(position).getPagado()==1){
            holder.tbpagado.setChecked(true);
        }else
        {
            holder.tbpagado.setChecked(false);
        }
        if (Eventolist.get(position).getEnviado()==1){
            holder.tbenviado.setChecked(true);
        }else
        {
            holder.tbenviado.setChecked(false);
        }

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, EventoActivity.class);
                // Pass all data rank
                intent.putExtra("nombre", (Eventolist.get(position).getNEvento()));

                // Pass all data country
                // Pass all data population

                // Pass all data flag
                // Start SingleItemView Class
                //  mContext.startActivity(intent);
            }
        });

        holder.tbpagado.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Eventolist.get(position).getPagado()==1){
                    Eventolist.get(position).setPagado(0);

                }else{
                    Eventolist.get(position).setPagado(1);

                }
                new SetPagado().execute();
            }
        });

        holder.tbenviado.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Eventolist.get(position).getEnviado()==1){
                    Eventolist.get(position).setEnviado(0);

                }else{
                    Eventolist.get(position).setEnviado(1);

                }
                new SetEnviado().execute();
            }
        });


        holder.btEvento.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                idModelo= Eventolist.get(position).getIdModelo();
                NombreModelo=Eventolist.get(position).getNombreModelo();
                NewEmail=Eventolist.get(position).getNewEmail();

                Intent intent = new Intent(mContext, EventoEditActivity.class);
                idUsuario= Eventolist.get(position).getIdUsuario();
                intent.putExtra("EXTRA_SESSION_IDMODELO",idModelo);
                intent.putExtra("EXTRA_SESSION_NOMBRE",NombreModelo);
                intent.putExtra("EXTRA_SESSION_EMAIL",NewEmail);
                intent.putExtra("EXTRA_SESSION_IDUSUARIO",idUsuario);
                intent.putExtra("EXTRA_SESSION_MUSUARIO",elusuariom);
                intent.putExtra("EXTRA_SESSION_IDEVENTO",(Eventolist.get(position).getIdEvento()));
                intent.putExtra("EXTRA_SESSION_FECHAINICIO",(Eventolist.get(position).getFechaInicio().toString()));
                intent.putExtra("EXTRA_SESSION_NEVENTO",(Eventolist.get(position).getNEvento()));
                intent.putExtra("EXTRA_SESSION_IDAGENCIA",(Eventolist.get(position).getIdEvento()));
                intent.putExtra("EXTRA_SESSION_CANTHORAS",(Eventolist.get(position).getCantHoras()));
                intent.putExtra("EXTRA_SESSION_HORAINICIO",(Eventolist.get(position).getHoraInicio().toString()));
                intent.putExtra("EXTRA_SESSION_PRECIO",(Eventolist.get(position).getPrecio()));
                intent.putExtra("EXTRA_SESSION_IDTIPOMONEDA",(Eventolist.get(position).getIdTipoMoneda()));

                mContext.startActivity(intent);





            }
        });
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        Eventolist.clear();
        if (charText.length() == 0) {
            Eventolist.addAll(arraylist);
        }
        else
        {
            for (Evento wp : arraylist)
            {

                if (wp.getNEvento().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    Eventolist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }



    private class SetPagado extends AsyncTask<Void,Void,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... arg0) {

            HashMap<String,String> params = new HashMap<>();
            params.put("idEvento",String.valueOf(idEvento));
            HttpHandler hh = new HttpHandler();
            String res = hh.sendPostRequest("http://model.do-agency.com/BD/BLL/Evento/pagadomob.php", params);
            return  res;
        }


    }

    private class SetEnviado extends AsyncTask<Void,Void,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... arg0) {

            HashMap<String,String> params = new HashMap<>();
            params.put("idEvento",String.valueOf(idEvento));
            HttpHandler hh = new HttpHandler();
            String res = hh.sendPostRequest("http://model.do-agency.com/BD/BLL/Evento/enviadomob.php", params);
            return  res;
        }


    }

}
