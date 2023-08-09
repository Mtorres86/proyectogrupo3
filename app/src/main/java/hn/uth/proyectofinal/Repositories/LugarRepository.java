package hn.uth.proyectofinal.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.proyectofinal.BDLugar.DBRecomendaciones;
import hn.uth.proyectofinal.DAO.LugarDAO;
import hn.uth.proyectofinal.Entities.Lugar;

public class LugarRepository {
    private LugarDAO lugarDao;
    private LiveData<List<Lugar>> dataset;


    public LugarRepository(Application app ) {
        this.lugarDao = lugarDao;
        this.dataset = lugarDao.getLugar();

    }

    public LiveData<List<Lugar>> getAllLugares()
    {
        return dataset;
    }

    public void insertLugar(Lugar lugar) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            lugarDao.insert(lugar);
        });
    }

    public void updateLugar(Lugar lugar) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            lugarDao.update(lugar);
        });
    }

    public void deleteLugar(Lugar lugar) {
        DBRecomendaciones.databaseWriteExecutor.execute(() -> {
            lugarDao.delete(lugar);
        });
    }
}

