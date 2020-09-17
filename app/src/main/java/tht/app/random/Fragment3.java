package tht.app.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment3 extends Fragment {

    EditText edtNhapGiaTri;
    TextView txtKQLV;
    ImageButton btnRanDom3,btnAdd;

    ListView lvDanhSach;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    Timer timer;
    int stop = 0;
    int totalItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment3_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNhapGiaTri = view.findViewById(R.id.edtNhapGiaTri);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnRanDom3 = view.findViewById(R.id.btnRanDom3);
        txtKQLV = view.findViewById(R.id.txtKQLV);

        lvDanhSach = view.findViewById(R.id.lvDanhSach);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, arrayList);
        lvDanhSach.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNhapGiaTri.getText().toString().trim().length() > 0) {
                    String ten = edtNhapGiaTri.getText().toString();
                    arrayList.add(ten);
                    adapter.notifyDataSetChanged();
                    edtNhapGiaTri.setText("");
                    edtNhapGiaTri.requestFocus();
                }else {
                    Toast.makeText(getContext(),"Vui lòng nhập giá trị",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRanDom3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                totalItem = arrayList.size();
                if(totalItem == 0){
                    Toast.makeText(getContext(),"Vui lòng nhập giá trị",Toast.LENGTH_LONG).show();
                }else {
                    xulyRanDom();
                }
            }
        });
    }

    private void xulyRanDom() {
        timer = new Timer();
        RunRanDomPhepToan runRanDomPhepToan = new RunRanDomPhepToan();
        timer.scheduleAtFixedRate(runRanDomPhepToan,70,70);
        btnRanDom3.setEnabled(false);

    }

    class RunRanDomPhepToan extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stop = stop + 1;
                    if(stop == 20){
                        timer.cancel();
                        stop = 0 ;
                        btnRanDom3.setEnabled(true);
                    }else {
                        int itemOnArray = getRandom(totalItem);
                        txtKQLV.setText(arrayList.get(itemOnArray));
                    }
                }
            });
        }
    }

    public int getRandom(int max) {
        return (int) (Math.random() * max);
    }
}