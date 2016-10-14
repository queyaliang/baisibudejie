package demo.copy.baisi.activity;
import java.io.File;
import java.io.IOException;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import demo.copy.baisi.R;
import demo.copy.baisi.adapter.ImageAdapter;
import demo.copy.baisi.app.BaisiApplication;


public class ImageActivity extends Activity implements OnItemClickListener ,OnClickListener{
	@ViewInject(R.id.gridView1)
	private GridView gridView;
	@ViewInject(R.id.rb_xiangce)
	private RadioButton rbtnXiangce;
	@ViewInject(R.id.rb_xiangji)
	private RadioButton rbtnXiangji;
	
	private File mPhotoFile;
	private ImageAdapter adapter;
	private int RESULT_LOAD_IMAGE = 200;
	private int CAMERA_RESULT = 100;

	private String saveDir = Environment.getExternalStorageDirectory()
			.getPath() + "/temp_image";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gridview_panel);
		x.view().inject(this);
		
		File savePath = new File(saveDir);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		setGridView();
		setAdapter();
		setListener();
	}

	private void setListener() {
		gridView.setOnItemClickListener(this);
		rbtnXiangce.setOnClickListener(this);
		rbtnXiangji.setOnClickListener(this);
		
	}

	private void setAdapter() {
		adapter =new ImageAdapter(this,BaisiApplication.getApplication().getImages());
		gridView.setAdapter(adapter);
	}

	private void setGridView() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
        float density = dm.density;
        int itemWidth = (int) (90 * density);
        Log.i("demo", "itemWidth"+itemWidth);
        
//        int gridviewWidth =getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        int width =LinearLayout.LayoutParams.MATCH_PARENT;
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
       
//        gridView.setHorizontalSpacing(horizontalSpace); // 设置列表项水平间距
        gridView.setStretchMode(GridView.STRETCH_SPACING);
        gridView.setGravity(Gravity.CENTER_HORIZONTAL);
       
        gridView.setNumColumns(3); // 设置列数量=列表集合数
        int height =(int) (13*density);
        gridView.setVerticalSpacing(height);
        
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent();
		int id =  BaisiApplication.getApplication().getImages().get(position);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
		intent.putExtra("xitong", bitmap);
		setResult(999, intent);
		finish();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_xiangce:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, RESULT_LOAD_IMAGE);
			break;
		case R.id.rb_xiangji:
			String state = Environment.getExternalStorageState();
			if (state.equals(Environment.MEDIA_MOUNTED)) {
				mPhotoFile = new File(saveDir, "temp.jpg");
				mPhotoFile.delete();
				if (!mPhotoFile.exists()) {
					try {
						mPhotoFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(getApplication(), "照片创建失败!",
								Toast.LENGTH_LONG).show();
						return;
					}
				}
				Intent intent = new Intent(
						"android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(mPhotoFile));
				startActivityForResult(intent, CAMERA_RESULT);
			} else {
				Toast.makeText(getApplication(), "sdcard无效或没有插入!",
						Toast.LENGTH_SHORT).show();
			}
			
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
			if (mPhotoFile != null && mPhotoFile.exists()) {
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize = 8;
				int degree = readPictureDegree(mPhotoFile.getAbsolutePath());
				Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(),
						bitmapOptions);
				bitmap = rotaingImageView(degree, bitmap);
				
				Intent intent = new Intent();
				intent.putExtra("xiangji", bitmap);
				setResult(777, intent);
//				mimageViewPhotoShow.setImageBitmap(bitmap);
			}
		}
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			Bitmap bitmap = BitmapFactory
					.decodeFile(picturePath);
			Intent intent = new Intent();
			intent.putExtra("xiangce", bitmap);
			setResult(888, intent);
//			mimageViewPhotoShow.setImageBitmap(bitmap);
		}
		finish();
	}
	private static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}
	private static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
		// 旋转图片 动作
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		System.out.println("angle2=" + angle);
		// 创建新的图片
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}

}














