package demo.copy.baisi.presenter.impl;

import java.util.List;

import demo.copy.baisi.entity.Funny;
import demo.copy.baisi.model.IFunnyModel;
import demo.copy.baisi.model.IFunnyModelCallback;
import demo.copy.baisi.model.ipml.FunnyModel;
import demo.copy.baisi.presenter.IFunnyPresenter;
import demo.copy.baisi.view.IFunnyView;

public class FunnyPresenter implements IFunnyPresenter{
	private IFunnyModel funnyModel;
	private IFunnyView funnyView;
	private int page;
	
	public FunnyPresenter(IFunnyView funnyView,int page){
		this.funnyModel=new FunnyModel();
		this.funnyView=funnyView;
		
	}
	@Override
	public void loadFunny(int page) {
		funnyModel.getFunnyList(page,new IFunnyModelCallback.AsyncCallback() {
			
			@Override
			public void onSuccess(Object obj) {
				// TODO Auto-generated method stub
				List<Funny> funnys=(List<Funny>)obj;
				funnyView.updateFunnyList(funnys);
			}
			
			@Override
			public void onError(Object error) {
				
			}
		});
		
	}

}
