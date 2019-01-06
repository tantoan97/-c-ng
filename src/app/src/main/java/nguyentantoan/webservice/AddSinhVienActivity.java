package nguyentantoan.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class AddSinhVienActivity extends AppCompatActivity {

    EditText edtThietBi, edtTrangThai, edtGhiChu;
    Button btnThem, btnHuy;
    String urlInsert = "https://tantoanbk.000webhostapp.com/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        AnhXa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String thietbi = edtThietBi.getText().toString().trim();
                String trangthai = edtTrangThai.getText().toString().trim();
                String ghichu = edtGhiChu.getText().toString().trim();
                if(thietbi.isEmpty() || trangthai.isEmpty() || ghichu.isEmpty()){
                    Toast.makeText(AddSinhVienActivity.this,"Vui lòng nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
                }else{
                    ThemSinhVien(urlInsert);
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void ThemSinhVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(AddSinhVienActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddSinhVienActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(AddSinhVienActivity.this,"Lỗi thêm!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddSinhVienActivity.this,"Xảy ra lỗi!",Toast.LENGTH_SHORT).show();
                        Log.d("AAA","Lỗi!\n" + error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("thietbiSV", edtThietBi.getText().toString().trim());
                params.put("trangthaiSV", edtTrangThai.getText().toString().trim());
                params.put("ghichuSV", edtGhiChu.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(){
        btnHuy = (Button) findViewById(R.id.buttonHuyThem);
        btnThem = (Button) findViewById(R.id.buttonThem);
        edtThietBi = (EditText) findViewById(R.id.editTextThietBi);
        edtTrangThai = (EditText) findViewById(R.id.editTextTrangThai);
        edtGhiChu = (EditText) findViewById(R.id.editTextGhiChu);
    }
}
