package alireza.sn.power.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import alireza.sn.power.FormActivity;
import alireza.sn.power.R;
import alireza.sn.power.model.MyModule;
import alireza.sn.power.model.MyModuleRepository;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    List<MyModule> list;
    Context context;
    String databaseName;

    public CustomAdapter(List<MyModule> list , Context context, String databaseName) {
        this.list = list;
        this.context = context;
       this.databaseName = databaseName;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        holder.coreName.setText(list.get(position).getCoreName());

        //======start set onClick =======
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo(position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInfo(position);
            }
        });
        //======end set onClick =======

    }

    private void deleteInfo(int position) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(context);
        dialogDelete.setTitle(context.getString(R.string.warning));
        dialogDelete.setMessage(context.getString(R.string.message_delete));
        dialogDelete.setPositiveButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new MyModuleRepository(context,databaseName).delete(list.get(position));
                list.remove(position);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        dialogDelete.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogDelete.show();
    }

    private void updateInfo(int position) {
        AlertDialog.Builder dialogEdit = new AlertDialog.Builder(context);
        dialogEdit.setTitle(context.getString(R.string.edit));
        View view = LayoutInflater.from(context).inflate(R.layout.edit_info,null);

        // =====find views fields of form =============
        EditText editTextCoreName = view.findViewById(R.id.edittext_center_name);
        EditText editTextSerialNumber = view.findViewById(R.id.edittext_serial_number);
        EditText editTextDate = view.findViewById(R.id.edittext_date);
        EditText editTextMailNumber = view.findViewById(R.id.mail_number);
        EditText editTextMailDate = view.findViewById(R.id.mail_date);
        // ========================================================

        //==== set text for fields of form in updating ==========
        editTextCoreName.setText(list.get(position).getCoreName());
        editTextSerialNumber.setText(list.get(position).getSerialNumber());
        editTextDate.setText(list.get(position).getDate());
        editTextMailNumber.setText(list.get(position).getMailNumber()+"");
        editTextMailDate.setText(list.get(position).getMailDate());
        //========================================================

        dialogEdit.setView(view);

        dialogEdit.setPositiveButton(context.getString(R.string.edit), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                    String name = editTextCoreName.getText().toString().trim();
                    String serial = editTextSerialNumber.getText().toString().trim();
                    String date = editTextDate.getText().toString().trim();
                    String mailNumber = editTextMailNumber.getText().toString().trim();
                    String mailDate = editTextMailDate.getText().toString().trim();
                    MyModule myModule = new MyModule(list.get(position).getId(),name,serial,date,mailNumber,mailDate);
                    new MyModuleRepository(context,databaseName).update(myModule);

                    list.get(position).setCoreName(name);
                    list.get(position).setDate(date);
                    list.get(position).setMailNumber(mailNumber);
                    list.get(position).setMailDate(mailDate);
                    list.get(position).setSerialNumber(serial);
                    notifyDataSetChanged();
                    dialog.dismiss();

            }
        });

        dialogEdit.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogEdit.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView coreName;

        ImageView delete;
        ImageView edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfo(getAdapterPosition());
                }
            });
        }

        private void findViews(View itemView) {
            coreName = itemView.findViewById(R.id.row_core_name);
            delete = itemView.findViewById(R.id.icon_delete);
            edit = itemView.findViewById(R.id.icon_edit);
        }
    }

    private void showInfo(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.info);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_info,null);

        // =================find textViews ============================================
        TextView coreName = view.findViewById(R.id.info_name_core);
        TextView serialNumber = view.findViewById(R.id.info_serial_number);
        TextView date = view.findViewById(R.id.info_date);
        TextView mailNumberAndDate = view.findViewById(R.id.info_mail_number_date);
        //=============================================================================

        //==================set textViews =============================================
        coreName.setText(list.get(position).getCoreName());
        serialNumber.setText(list.get(position).getSerialNumber());
        date.setText(list.get(position).getDate());
        mailNumberAndDate.setText(list.get(position).getMailNumber());
        mailNumberAndDate.append("\nشماره تاریخ : "+list.get(position).getMailDate());
        //=============================================================================

        builder.setView(view);
        builder.setNeutralButton(R.string.back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
