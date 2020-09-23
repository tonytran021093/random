package tht.app.random;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
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

    Database database;

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

        database = new Database(getContext(),"danhsach.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS DanhSach(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenRanDom VARCHAR(200))");

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

                    database.QueryData("INSERT INTO DanhSach VALUES(null,'" + ten + "')");
                    GetDataDanhSach();
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

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popuplv, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.lvSua:
                                SuaLV(position);
                                break;
                            case R.id.lvXoa:
                                XoaLV(position);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    private void GetDataDanhSach() {
        Cursor dataRanDom = database.GetData("SELECT * FROM DanhSach");
        arrayList.clear();
        while ((dataRanDom.moveToNext())){
            int id = dataRanDom.getInt(0);
            String ten = dataRanDom.getString(1);
            arrayList.add(ten);
        }
    }

    private void SuaLV(final int position) {
       /* edtNhapGiaTri.setText(arrayList.get(position));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.set(position,edtNhapGiaTri.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });*/

    }

    private void XoaLV(int position) {
        String item = adapter.getItem(position);
        adapter.remove(item);
        adapter.notifyDataSetChanged();
        assert item != null;
        database.QueryData("DELETE FROM DanhSach WHERE ID='" + item + "'");
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