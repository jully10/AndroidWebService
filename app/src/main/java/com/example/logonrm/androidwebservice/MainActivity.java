package com.example.logonrm.androidwebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logonrm.androidwebservice.api.CarroApi;
import com.example.logonrm.androidwebservice.model.Carro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvCarros)
    TextView tvCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        carregaCarros();
    }

    private void carregaCarros(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ec2-35-162-174-152.us-west-2.compute.amazonaws.com:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarroApi api = retrofit.create(CarroApi.class);
        Call<List<Carro>> call = api.listarTodos();

        call.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {

                StringBuilder sb = new StringBuilder();
                for(Carro carro : response.body()){
                    sb.append(carro.getModelo());
                    sb.append("\n");
                }
                tvCarros.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ops!deu ruim", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
