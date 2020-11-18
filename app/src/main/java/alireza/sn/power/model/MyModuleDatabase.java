package alireza.sn.power.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = MyModule.class,version = 1,exportSchema = false)
public abstract class MyModuleDatabase extends RoomDatabase {
    public abstract MyModuleDao myModuleDao();
}
