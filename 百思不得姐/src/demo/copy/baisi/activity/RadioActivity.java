package demo.copy.baisi.activity;

import demo.copy.baisi.R;
import demo.copy.baisi.util.MediaController;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.widget.LinearLayout.LayoutParams;
import android.widget.VideoView;


public class RadioActivity extends Activity implements OnPreparedListener{
	private VideoView mVideoView;

	private MediaController mMediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("_url");
		palyRadio(url);
	}

	private void palyRadio(String url) {
		mVideoView = (VideoView) findViewById(R.id.video_view);
		mVideoView.setVideoURI(Uri.parse(url));
		mVideoView.setOnPreparedListener(this);
		mMediaController = new MediaController(this);
		mVideoView.setMediaController(mMediaController);
		mVideoView.setFocusableInTouchMode(false);
		mVideoView.setFocusable(false);
		mVideoView.setEnabled(false);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity =Gravity.CENTER;
		params.weight=1;
		mVideoView.setLayoutParams(params);
	}
	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mVideoView.start();
	}
}
