package demo.copy.baisi.model;

import java.util.List;

import demo.copy.baisi.entity.AllFather;
import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.entity.Picture;
import demo.copy.baisi.entity.Radio;
import demo.copy.baisi.entity.Voice;

public interface ISearchModelCallBack {
	
	void loadPicture(List<AllFather> objects);
	
}
