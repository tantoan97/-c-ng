package nguyentantoan.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateSinhVienActivity extends AppCompatActivity {

    EditText edtThietBi, edtTrangThai, edtGhiChu;
    Button btnCapNhat, btnHuy;
    int id = 0;
    String urlUpdate = "https://tantoanbk.000webhostapp.com/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinh_vien);

        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");

        AnhXa();

        id = sinhVien.getId();
        edtThietBi.setText(sinhVien.getThietBi());
        edtTrangThai.setText(sinhVien.getTrangThai() + "");
        edtGhiChu.setText(sinhVien.getGhiChu());

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thietbi = edtThietBi.getText().toString().trim();
                String trangthai = edtTrangThai.getText().toString().trim();
                String ghichu = edtGhiChu.getText().toString().trim();
                if(thietbi.matches("") || trangthai.equals("") || ghichu.length() ==0){
                    Toast.makeText(UpdateSinhVienActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    CapNhatSinhVien(urlUpdate);
                }
            }
        });
    }

    private void CapNhatSinhVien(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(UpdateSinhVienActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateSinhVienActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(UpdateSinhVienActivity.this, "Lỗi cập nhật!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateSinhVienActivity.this, "Xảy ra lỗi, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("idSV", String.valueOf(id));
                params.put("thietbiSV", edtThietBi.getText().toString().trim());
                params.put("trangthaiSV", edtTrangThai.getText().toString().trim());
                params.put("ghichuSV", edtGhiChu.getText().toString().trim());
                
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhat);
        btnHuy = (Button) findViewById(R.id.buttonHuyEdit);
        edtTrangThai = (EditText) findViewById(R.id.editTextTrangThaiEdit);
        edtThietBi = (EditText) findViewById(R.id.editTextThietBiEdit);
        edtGhiChu = (EditText) findViewById(R.id.editTextGhiChuEdit);

    }
}
