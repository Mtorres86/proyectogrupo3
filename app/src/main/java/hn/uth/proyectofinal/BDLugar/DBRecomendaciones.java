package hn.uth.proyectofinal.BDLugar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import hn.uth.proyectofinal.DAO.LugarDAO;
import hn.uth.proyectofinal.Entities.Lugar;


    @Database(version = 1, exportSchema = false, entities = {Lugar.class})
    public abstract class DBRecomendaciones extends RoomDatabase {
        public abstract LugarDAO lugarDao();


        private static volatile DBRecomendaciones INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;

        public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        //Generando una sola instancia con el patron singleton
        public static DBRecomendaciones getDataBase(final Context context){
            if(INSTANCE == null){
                synchronized (DBRecomendaciones.class){
                    if (INSTANCE == null){
                        Callback callback = new Callback(){
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db){
                                super.onCreate(db);
                                databaseWriteExecutor.execute(() -> {
                                   LugarDAO testDao = INSTANCE.lugarDao();
                                    testDao.deleteAll();

                                testDao.insert(new Lugar("San Pedro Sula","Valle",new Date(),23.23,23.23,"Test San pedro",true));
                                testDao.insert(new Lugar("Villanueva","Valle",new Date(),25.23,26.23,"Test Villanueva",true));
                                testDao.insert(new Lugar("Lago de Yojoa","Valle",new Date(),25.23,26.23,"Test Lago",false));










                                });
                            }
                        };
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),DBRecomendaciones.class,"db_recomendaciones").addCallback(callback).build();

                    }
                }
            }
            return INSTANCE;
        }

    }

