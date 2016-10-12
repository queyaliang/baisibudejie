package demo.copy.baisi.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;

public class VoicePlayerService extends Service{
	private int pauseposition;
	private boolean isLoop = true;
	private MediaPlayer player = new MediaPlayer();
	public void onCreate() {
		super.onCreate();
		//��mediaplayer��Ӽ���
		player.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				player.start();
				//TODO
			}
		});
		new Thread(){
		 public void run() {
				while(isLoop){
					try {
						Thread.sleep(1000);
						if(player.isPlaying()){
							int total = player.getDuration();
							int progress = player.getCurrentPosition();
							Intent intent = new Intent("ACTION_UPDATE_MUSIC_PROGRESS");
							intent.putExtra("total", total);
							intent.putExtra("progress", progress);
							sendBroadcast(intent);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}.start();
	}
	@Override
	public IBinder onBind(Intent intent) {
		return new VoiceBinder();
	}
	@Override
	public void onDestroy() {
		isLoop = false;
		player.release();
		super.onDestroy();
	}
	public class VoiceBinder extends Binder{
		/**
		 * 
		 * �������ֵĽӿڷ���
		 * @param url
		 */
		public void playVoice(String url){
			try {
				player.reset();
				player.setDataSource(url);
				player.prepareAsync();//�첽��׼��
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		/**
//		 * 
//		 * ��ͣ�򲥷�
//		 */
//		public void startOrPause(){
//			if(player.isPlaying()){
//				player.pause();
//			}else{
//				player.start();
//			}
//		}
		/**
		 * ��ת��position��λ�� �������Ż���ͣ
		 * 
		 */
		public void seekTo(int position){
			player.seekTo(position);
			player.start();
		}
		public  MediaPlayer getplayer(){
			return player;
		}
		public void pauseplayer(){
			player.pause();
			 pauseposition = player.getCurrentPosition();
		}
		public int getpauseposition(){
			return pauseposition;
		}
	}
}
