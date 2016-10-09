package demo.copy.baisi.model;

public interface IRadioModel {
	
	public void loadRadio(int page,IRadioModelCallBack callBack);
	public void loadRadioImage(String url,String path ,int position, IRadioImageModelCallBack callback);

}
