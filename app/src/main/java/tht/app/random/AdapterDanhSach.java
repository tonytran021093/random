package tht.app.random;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import tht.app.random.object.TenRandom;

public class AdapterDanhSach extends ArrayAdapter<TenRandom> {
    Context context;
    int resource;
    List<TenRandom> objects;

    public AdapterDanhSach(@NonNull Context context, int resource, @NonNull List<TenRandom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();

        View row = inflater.inflate(R.layout.item, null);

        TextView txtLV = row.findViewById(R.id.txtLV);

        TenRandom danhSach = this.objects.get(position);
        txtLV.setText(danhSach.getName());

        return row;
    }
}
