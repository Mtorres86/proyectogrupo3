package hn.uth.proyectofinal.ui.notifications;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import hn.uth.proyectofinal.Contact;

import hn.uth.proyectofinal.OnItemClickListener;
import hn.uth.proyectofinal.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment implements OnItemClickListener<Contact> {
    private static final int PERMISSION_REQUEST_READ_CONTACT = 400;
    private  ContactAdapter adaptador;



    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.adaptador = new ContactAdapter(new ArrayList<>(),this);
        setupRecyclerView();
        adaptador.setItems(solicitarPermisoContactos());
        binding.btnBuscar.setOnClickListener(v -> {
            adaptador.setItems(solicitarPermisoContactos());
        });


        return root;
    }

    ///
    private List<Contact> solicitarPermisoContactos(){
        //PREGUNTANDO SI YA TENGO UN DETERMINADO PERMISO
        if(ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            //ENTRA AQUI SI NO ME HAN DADO EL PERMISO, Y DEBO DE SOLICITARLO
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACT);
            return new ArrayList<>();
        }else{
            //ENTRA AQUI SI EL USUARIO YA ME OTORGÓ EL PERMISO ANTES, PUEDO HACER USO DE LA LECTURA DE CONTACTOS
            return getContacts(this.getContext(),binding);
        }
    }

    private List<Contact> getContacts(Context context,FragmentNotificationsBinding binding) {
        List<Contact> contactos = new ArrayList<>();

        String buscar = binding.tlBuscar.getEditText().getText().toString();


        if (buscar.isEmpty()) buscar = "A";

        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, ContactsContract.Contacts.DISPLAY_NAME + " LIKE '"+buscar+"%'", null, ContactsContract.Contacts.DISPLAY_NAME + " DESC");

        boolean continuar = true;
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                int idColumnIndex = Math.max(cursor.getColumnIndex(ContactsContract.Contacts._ID), 0);
                int nameColumnIndex = Math.max(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME), 0);
                int phoneColumnIndex = Math.max(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER), 0);//ME DICE SI TIENE O NO UN TELEFONO GUARDADO

                String id = cursor.getString(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                if(Integer.parseInt(cursor.getString(phoneColumnIndex)) > 0){

                    //EL CONTACTO SI TIENE TELEFONO ALMACENADO
                    Cursor cursorPhone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{id}, null);

                    String phone = "";
                    while (cursorPhone.moveToNext()){
                        int phoneCommonColumIndex = cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        phone = cursorPhone.getString(phoneCommonColumIndex);

                        /*Contacto nuevo = new Contacto();
                        nuevo.setName(name);
                        nuevo.setPhone(phone);
                        contactos.add(nuevo);*/
                        continuar = false;
                    }

                    Cursor cursorCorreo = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=?", new String[]{id}, null);
                    String correo ="";
                    while (cursorCorreo.moveToNext()){
                        int emailCommonColumIndex = cursorCorreo.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1);
                        correo = cursorCorreo.getString(emailCommonColumIndex);
                    }
                    Contact nuevo = new Contact(name,phone,correo);

                    contactos.add(nuevo);

                    cursorCorreo.close();
                    cursorPhone.close();
                }
            }
            cursor.close();
        }


        return contactos;
    }



    ///

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClickt(Contact data) {

    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        binding.rvContacts.setLayoutManager(linearLayoutManager);
        binding.rvContacts.setAdapter(adaptador);
    }


}
