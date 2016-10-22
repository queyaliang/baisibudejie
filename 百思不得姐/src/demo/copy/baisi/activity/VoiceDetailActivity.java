package demo.copy.baisi.activity;

import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import demo.copy.baisi.R;
import demo.copy.baisi.app.BaisiApplication;
import demo.copy.baisi.entity.Voice;
import demo.copy.baisi.service.VoicePlayerService;
import demo.copy.baisi.service.VoicePlayerService.VoiceBinder;
import demo.copy.baisi.util.BitmapUtils;
import demo.copy.baisi.util.ShareThings;
public class VoiceDetailActivity extends Activity {
	//
	private int totalZan;
	private int totalHate;
	private int pauseposition;
	private Voice voice;
	private VoiceBinder binder;
	@ViewInject(R.id.tv_name)
	private TextView tvName;
	@ViewInject(R.id.civ_profil_image)
	private ImageView civProfileImage;
	@ViewInject(R.id.tv_publish_time)
	private TextView tvPublishTime;
	@ViewInject(R.id.iv_image3)
	private ImageView ivImage3;
	@ViewInject(R.id.iv_background)
	private ImageView ivBackground;
	@ViewInject(R.id.tv_zan)
	private TextView tvZan;
	@ViewInject(R.id.tv_tucao)
	private TextView tvTuCao;
	@ViewInject(R.id.ib_play_or_pause)
	private ImageButton ibPlayOrPause;
	@ViewInject(R.id.seekBar1)
	private SeekBar seekBar;
	@ViewInject(R.id.ib_zan)
	private ImageButton ibZan;
	@ViewInject(R.id.ib_tucao)
	private ImageButton ibTucao;
	@ViewInject(R.id.ib_fenxiang)
	private ImageButton ibFenxiang;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_detail);
		BaisiApplication.getApplication().addActivity(this);
		int a = 0;
		Intent intent = getIntent();
		voice = (Voice) intent.getSerializableExtra("voice");
		//��ʼ���ؼ�
		x.view().inject(this);
		setVoiceInfo();
		bindVoiceService();
		setListeners();
		//ע�����
		registComponents();
	}
	private void registComponents() {
		BroadcastReceiver receiver = new VoiceReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("ACTION_UPDATE_MUSIC_PROGRESS");
		this.registerReceiver(receiver, filter );
	}
	class VoiceReceiver extends BroadcastReceiver{
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals("ACTION_UPDATE_MUSIC_PROGRESS")){
				//���յ��˸������ֽ�ȵĹ㲥
				int total = intent.getIntExtra("total", -1);
				int progress = intent.getIntExtra("progress", -1);
				//���½����
				seekBar.setMax(total);
				seekBar.setProgress(progress);
			}
		}

	}
	private void setListeners() {
		ibPlayOrPause.setOnClickListener(new OnClickListener() {


			public void onClick(View v) {
				String url = voice.getVoice_uri();
				MediaPlayer player = binder.getplayer();
				pauseposition = binder.getpauseposition();
				if(player.isPlaying()){
					binder.pauseplayer();
					ibPlayOrPause.setImageResource(R.drawable.ic_play_normal);
				}else if(pauseposition!=0){
					binder.seekTo(pauseposition);
					ibPlayOrPause.setImageResource(R.drawable.ic_pause_normal);
				}else {
					binder.playVoice(url);
					ibPlayOrPause.setImageResource(R.drawable.ic_pause_normal);
				}
			}
		});
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if(fromUser){ //������ĸ��������û������
					pauseposition = progress;
					binder.seekTo(pauseposition);
				}
			}
		});
		ibZan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				totalZan = voice.getLove()+1;
				tvZan.setText(totalZan+"");
			}
		});
		ibTucao.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				totalHate = voice.getHate()+1;
				tvTuCao.setText(totalHate+"");
			}
		});
		ibFenxiang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			ShareThings.showShare(VoiceDetailActivity.this, voice.getVoice_uri());
			}
		});
		
	}
	/**
	 * 
	 * ��service
	 */
	private void bindVoiceService() {
		Intent intent = new Intent(this,VoicePlayerService.class);
		ServiceConnection conn = new ServiceConnection() {
			public void onServiceDisconnected(ComponentName name) {
			}
			public void onServiceConnected(ComponentName name, IBinder service) {
				binder = (VoiceBinder) service;
			}
		};
		int flags = Service.BIND_AUTO_CREATE;
		this.bindService(intent, conn, flags);
	}
	private void setVoiceInfo() {
		tvName.setText(voice.getName());
		tvPublishTime.setText(voice.getCreate_time());
		tvZan.setText(voice.getLove()+"");
		tvTuCao.setText(voice.getHate()+"");
		x.image().bind(ivImage3,  voice.getImage3(),new Callback.CommonCallback<Drawable>() {
			public void onSuccess(Drawable drawable) {
				Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
				bitmap = BitmapUtils.createBlurBitmap(bitmap,100);
				ivBackground.setImageBitmap(bitmap);
			}
			public void onCancelled(CancelledException arg0) {
			}
			public void onError(Throwable arg0, boolean arg1) {
			}
			public void onFinished() {
			}
		});
		x.image().bind(civProfileImage, voice.getProfile_image());
	}
	@Override
	protected void onDestroy() {
		BaisiApplication.getApplication().removeActivity(this);
		super.onDestroy();
	}
	

}
