package tht.app.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    ImageView imgHinhQuay;
    ImageButton imbQuay;
    TextView txtQuay;

    int from = 0;

    int[] so = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] vitri = {0, 36, 72, 108, 144, 180, 216, 252, 288, 324};
    int[] vong = {1080, 1440, 1800};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgHinhQuay = view.findViewById(R.id.imgHinhQuay);
        imbQuay = view.findViewById(R.id.imbQuay);
        txtQuay = view.findViewById(R.id.txtQuay);

        imbQuay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyQuay();
            }
        });
    }

    private void xulyQuay() {
        final int position = getRandom(vitri.length);
        int positionVong = getRandom(vong.length);
        int to = (int) vitri[position] + vong[positionVong];

        imbQuay.setEnabled(false);
        txtQuay.setAnimation(null);
        txtQuay.setVisibility(View.INVISIBLE);

        final RotateAnimation animation1 = new RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_SELF, 0.5F);
        animation1.setDuration(5000);
        animation1.setInterpolator(new DecelerateInterpolator());
        animation1.setFillAfter(true);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (so[position]) {
                    case 1:
                        txtQuay.setText("1");
                        break;
                    case 2:
                        txtQuay.setText("2");
                        break;
                    case 3:
                        txtQuay.setText("3");
                        break;
                    case 4:
                        txtQuay.setText("4");
                        break;
                    case 5:
                        txtQuay.setText("5");
                        break;
                    case 6:
                        txtQuay.setText("6");
                        break;
                    case 7:
                        txtQuay.setText("7");
                        break;
                    case 8:
                        txtQuay.setText("8");
                        break;
                    case 9:
                        txtQuay.setText("9");
                        break;
                    default:
                        txtQuay.setText("10");
                        break;
                }
                Animation animascale = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale);
                txtQuay.startAnimation(animascale);

                imbQuay.setEnabled(true);
                txtQuay.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imgHinhQuay.startAnimation(animation1);
        from = (int) vitri[position];

    }

    public int getRandom(int max) {
        return (int) (Math.random() * max);
    }
}

