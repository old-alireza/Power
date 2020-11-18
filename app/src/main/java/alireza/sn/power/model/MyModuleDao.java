package alireza.sn.power.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyModuleDao {

    @Insert
    public void insertTask (MyModule module);

    @Delete
    public void deleteTask (MyModule module);

    @Update
    public void updateTask (MyModule module);

    @Query("select * from mymodule order by id asc")
    public List<MyModule> getAll();
}
