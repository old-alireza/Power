package alireza.sn.power.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class MyModule {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String coreName;

    @ColumnInfo
    String serialNumber;

    @ColumnInfo
    String date;

    @ColumnInfo
    String mailNumber;

    @ColumnInfo
    String mailDate;

    @Ignore
    public MyModule(int id, String coreName, String serialNumber, String date, String mailNumber , String mailDate) {
        this.id = id;
        this.coreName = coreName;
        this.serialNumber = serialNumber;
        this.date = date;
        this.mailNumber = mailNumber;
        this.mailDate = mailDate;
    }

    public MyModule(String coreName, String serialNumber, String date, String mailNumber , String mailDate) {
        this.coreName = coreName;
        this.serialNumber = serialNumber;
        this.date = date;
        this.mailNumber = mailNumber;
        this.mailDate = mailDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMailNumber() {
        return mailNumber;
    }

    public void setMailNumber(String mailNumber) {
        this.mailNumber = mailNumber;
    }

    public String getMailDate() {
        return mailDate;
    }

    public void setMailDate(String mailDate) {
        this.mailDate = mailDate;
    }
}
