package nguyentantoan.webservice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtThietBi, txtTrangThai, txtGhiChu;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtThietBi = (TextView) view.findViewById(R.id.textviewThietBiCustom);
            holder.txtTrangThai = (TextView) view.findViewById(R.id.textviewTrangThaiCustom);
            holder.txtGhiChu = (TextView) view.findViewById(R.id.textviewGhiChuCustom);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageviewDelete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageviewEdit);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        final SinhVien sinhvien=sinhVienList.get(i);
        holder.txtThietBi.setText(sinhvien.getThietBi());
        holder.txtTrangThai.setText("Trạng thái: " + sinhvien.getTrangThai());
        holder.txtGhiChu.setText(sinhvien.getGhiChu());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateSinhVienActivity.class);
                intent.putExtra("dataSinhVien", sinhvien);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(sinhvien.getThietBi(), sinhvien.getId());
            }
        });

        return view;
    }

    private void XacNhanXoa(String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa thiết bị " + ten + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteStudent(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
}
