package sg.edu.rp.webservices.problemstatement_knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


public class Frag2 extends Fragment {



    public Frag2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_frag2, container, false);
        Button btnColor = (Button) v.findViewById(R.id.btn_color);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                v.setBackgroundColor(Color.YELLOW);
                Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            }
        });
        return v;
    }
}