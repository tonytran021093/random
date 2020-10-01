package tht.app.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment2 extends Fragment {

    Timer timer;
    int stop;
    TextView txtKQ2;
    EditText edtMin, edtMax;
    ImageButton imbRanDom2;
    int so1;
    int so2;
    int soRanDom;

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

                if (edtMin.getText().toString().trim().length() == 0 || edtMax.getText().toString().trim().length() == 0) {
                    Toast.makeText(getContext(), "Please enter the full value", Toast.LENGTH_LONG).show();
                } else {
                    so1 = Integer.parseInt(edtMin.getText().toString());
                    so2 = Integer.parseInt(edtMax.getText().toString());

                    if (so1 >= so2) {
                        Toast.makeText(getContext(), "Please enter the Min number less than the Max number", Toast.LENGTH_LONG).show();
                    } else {
                        xulyRanDom();
                    }
                }
            }
        });
    }

    private void xulyRanDom() {
        timer = new Timer();
        RunRanDomPhepToan runRanDomPhepToan = new RunRanDomPhepToan();
        timer.scheduleAtFixedRate(runRanDomPhepToan, 70, 70);
        imbRanDom2.setEnabled(false);

    }

    class RunRanDomPhepToan extends TimerTask {

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stop = stop + 1;
                    if (stop == 20) {
                        timer.cancel();
                        stop = 0;
                        imbRanDom2.setEnabled(true);
                    } else {
                        soRanDom = getRandomMinMax(so1, so2 + 1);
                        txtKQ2.setText(String.valueOf(soRanDom));
                    }
                }
            });
        }
    }

    public int getRandomMinMax(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min)) + min;
    }
}
