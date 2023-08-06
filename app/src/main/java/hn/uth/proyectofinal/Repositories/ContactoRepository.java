package hn.uth.proyectofinal.Repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.proyectofinal.BDLugar.DBRecomendaciones;
import hn.uth.proyectofinal.DAO.ContactoDAO;
import hn.uth.proyectofinal.Entities.Contacto;

public class ContactoRepository {
    private ContactoDAO contactoDao;

    public ContactoRepository(ContactoDAO contactoDao) {
        this.contactoDao = contactoDao;
    }

    public LiveData<List<Contacto>> getAllContactos() {
        return contactoDao.getContacto();
    }

    public void insertContacto(Contacto contacto) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            contactoDao.insert(contacto);
        });
    }

    public void updateContacto(Contacto contacto) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            contactoDao.update(contacto);
        });
    }

    public void deleteContacto(Contacto contacto) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            contactoDao.delete(contacto);
        });
    }
}

