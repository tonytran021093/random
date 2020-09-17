package tht.app.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    TextView txtKQ2;
    EditText edtMin,edtMax;
    ImageButton imbRanDom2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment2_layout, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtKQ2 = view.findViewById(R.id.txtKQ2);
        edtMin = view.findViewById(R.id.edtMin);
        edtMax = view.findViewById(R.id.edtMax);
        imbRanDom2 = view.findViewById(R.id.imbRanDom2);

        imbRanDom2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyRandom();
            }
        });
    }

    private void xulyRandom() {
        int so1 = Integer.parseInt(edtMin.getText().toString());
        int so2 = Integer.parseInt(edtMax.getText().toString());
        
    }

    public int getRandomMinMax(int min,int max) {
        return (int) Math.floor(Math.random() * (max - min)) + min;
    }
}
