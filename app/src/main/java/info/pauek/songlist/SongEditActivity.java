package info.pauek.songlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SongEditActivity extends AppCompatActivity {

    private TextView edit_title;
    private TextView edit_band;
    private TextView edit_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_edit);

        edit_title = findViewById(R.id.edit_title);
        edit_band = findViewById(R.id.edit_band);
        edit_year = findViewById(R.id.edit_year);
    }

    public void onClickMinus(View view) {
        int tmp_year = Integer.parseInt(edit_year.getText().toString());
        tmp_year--;
        edit_year.setText(String.valueOf(tmp_year));
    }

    public void onClickPlus(View view) {
        int tmp_year = Integer.parseInt(edit_year.getText().toString());
        tmp_year++;
        edit_year.setText(String.valueOf(tmp_year));
    }
}
