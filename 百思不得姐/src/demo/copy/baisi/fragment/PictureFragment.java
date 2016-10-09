package demo.copy.baisi.fragment;

import org.xutils.x;

import demo.copy.baisi.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PictureFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.picture_fragment, null);
		x.view().inject(this, view);

		return view;
	}
}
