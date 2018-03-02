package doagency.com.modeldo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import doagency.com.modeldo.objetos.Usuario;

public class AgenciaEditActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView navigation;
    private FrameLayout mMainframe;
    private InicioFragment inicioFragment;
    private EventoFragment eventoFragment;
    private BookFragment bookFragment;
    private AgenciaFragment agenciaFragment;
    private AgenciaEditFragment agenciaEditFragment;
    String newEmail;
    Usuario usuario;
    int idModelo,idUsuario;
    int idAgencia,idAgenciaModelo;
    String NAgencia,Direccion,Telefono,RUC;
    String NombreModelo,elusuariom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mMainframe = (FrameLayout) findViewById(R.id.main_frame);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                idModelo = 0;
                idUsuario = 0;
                idAgencia=0;
                newEmail = null;
                NombreModelo=null;
                NAgencia=null;
                Direccion=null;
                Telefono=null;
                RUC=null;
                idAgenciaModelo=0;
                elusuariom=null;
            } else {
                idModelo = extras.getInt("EXTRA_SESSION_IDMODELO");
                idUsuario = extras.getInt("EXTRA_SESSION_IDUSUARIO");
                newEmail = extras.getString("EXTRA_SESSION_EMAIL");
                NombreModelo = extras.getString("EXTRA_SESSION_NOMBRE");
                idAgencia = extras.getInt("idAgencia");
                idAgenciaModelo = extras.getInt("idAgenciaModelo");
                NAgencia = extras.getString("Agencia");
                Direccion = extras.getString("Direccion");
                Telefono = extras.getString("Telefono");
                RUC = extras.getString("RUC");
                elusuariom=extras.getString("EXTRA_SESSION_MUSUARIO");

            }
        } else {
            idModelo = (int) savedInstanceState.getSerializable("EXTRA_SESSION_IDMODELO");
            idUsuario = (int) savedInstanceState.getSerializable("EXTRA_SESSION_IDUSUARIO");
            newEmail = (String) savedInstanceState.getSerializable("EXTRA_SESSION_EMAIL");
            NombreModelo = (String) savedInstanceState.getSerializable("EXTRA_SESSION_NOMBRE");
            idAgencia = (int) savedInstanceState.getSerializable("idAgencia");
            idAgenciaModelo = (int) savedInstanceState.getSerializable("idAgenciaModelo");
            NAgencia = (String) savedInstanceState.getSerializable("Agencia");
            Direccion = (String) savedInstanceState.getSerializable("Direccion");
            Telefono = (String) savedInstanceState.getSerializable("Telefono");
            RUC = (String) savedInstanceState.getSerializable("RUC");
            elusuariom = (String) savedInstanceState.getSerializable("EXTRA_SESSION_MUSUARIO");

        }

        inicioFragment= new InicioFragment();
        eventoFragment= new EventoFragment();
        bookFragment = new BookFragment();
        agenciaFragment= new AgenciaFragment();
        agenciaEditFragment= new AgenciaEditFragment();
        SetFragment(agenciaEditFragment);
        navigation.setSelectedItemId(R.id.navigation_agencias);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // navigation.setItemBackgroundResource(R.color.colorPrimary);
                        SetFragment(inicioFragment);
                        return true;
                    case R.id.navigation_book:
                        SetFragment(bookFragment);
                        return true;
                    case R.id.navigation_eventos:
                        SetFragment(eventoFragment);
                        return true;
                    case R.id.navigation_agencias:
                        SetFragment(agenciaFragment);
                        return true;
                }
                return false;
            }
        });
    }


    private void SetFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("Email",newEmail);
        bundle.putString("idModelo",String.valueOf(idModelo));
        bundle.putString("idUsuario",String.valueOf(idUsuario));
        bundle.putString("NombreModelo",NombreModelo);
        bundle.putString("NAgencia",NAgencia);
        bundle.putString("Direccion",Direccion);
        bundle.putString("Telefono",Telefono);
        bundle.putString("RUC",RUC);
        bundle.putString("idAgencia",String.valueOf(idAgencia));
        bundle.putString("idAgenciaModelo",String.valueOf(idAgenciaModelo));


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


}
