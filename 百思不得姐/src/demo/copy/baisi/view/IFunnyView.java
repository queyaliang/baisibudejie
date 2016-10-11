package demo.copy.baisi.view;

import java.util.List;

import demo.copy.baisi.entity.Funny;

public interface IFunnyView extends IView{
	void updateFunnyList(List<Funny> funnys);
}
