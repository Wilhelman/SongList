package info.pauek.songlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {

    public static final int EDIT_SONG = 0;

    private List<Song> songs;
    private RecyclerView song_list_view;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        songs = new ArrayList<>();
        songs.add(new Song("...And Justice For All","BandTest","1997"));

        adapter = new Adapter();

        song_list_view = findViewById(R.id.song_list_view);
        song_list_view.setLayoutManager(new LinearLayoutManager(this));
        song_list_view.setAdapter(adapter);
    }

    public void onSongClick(int position) {

        Intent intent = new Intent(SongListActivity.this, SongEditActivity.class);

        String str_data = songs.get(position).getTitle().toString();

        intent.putExtra("title",str_data);
        str_data = songs.get(position).getBand().toString();
        intent.putExtra("band", str_data);
        str_data = songs.get(position).getYear().toString();
        intent.putExtra("year", str_data);

        intent.putExtra("index", position);
        setResult(RESULT_OK, intent);

        startActivityForResult(intent, EDIT_SONG);

    }

    /*Menu stuff*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.song_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_new_song:
                Intent intent = new Intent(this, SongEditActivity.class);
                startActivityForResult(intent, EDIT_SONG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title_view;
        private TextView band_view;
        private TextView year_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_view = itemView.findViewById(R.id.title_view);
            band_view = itemView.findViewById(R.id.band_view);
            year_view = itemView.findViewById(R.id.year_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSongClick(getAdapterPosition());
                }
            });
        }

    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.song_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            holder.title_view.setText(songs.get(i).getTitle());
            holder.band_view.setText(songs.get(i).getBand());
            holder.year_view.setText(songs.get(i).getYear());
        }

        @Override
        public int getItemCount() {
            return songs.size();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case EDIT_SONG:
                if(resultCode == RESULT_OK){
                    String title = data.getStringExtra("title");
                    String band = data.getStringExtra("band");
                    String year = data.getStringExtra("year");
                    int index = data.getIntExtra("index", -1);

                    if(index == -1){
                        songs.add(new Song(title,band,year));
                        adapter.notifyItemInserted(songs.size()-1);
                    }else{
                        Song current_song = songs.get(index);
                        current_song.setBand(band);
                        current_song.setTitle(title);
                        current_song.setYear(year);
                        adapter.notifyItemChanged(index);
                    }

                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
