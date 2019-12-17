package eslam.com.newmediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView = findViewById(R.id.myVideoView);
        Uri mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.google_assistant);
        mVideoView.setVideoURI(mUri);
//        Uri online = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
//        mVideoView.setVideoURI(online);
        mMediaController = new MediaController(this);
        mMediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mMediaController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.music_menu:
                Intent intent = new Intent(MainActivity.this, MusicPlayer.class);
                startActivity(intent);
                return true;
            case R.id.m_menu:
                Toast.makeText(this, "M_Menu Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
