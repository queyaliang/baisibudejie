package demo.copy.baisi.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import demo.copy.baisi.entity.Voice;

import demo.copy.baisi.model.IVoiceModel;
import demo.copy.baisi.model.VoiceModelCallback;
import demo.copy.baisi.model.ipml.VoiceModel;
import demo.copy.baisi.presenter.IVoicePresenter;
import demo.copy.baisi.view.IVoiceView;

public class Voicepresenter implements IVoicePresenter {
	private IVoiceModel model;
	private IVoiceView view;
	private List<Voice> voiceses = new ArrayList<Voice>();
	
	public Voicepresenter( IVoiceView view) {
		this.model = new VoiceModel();
		this.view = view;
	}

	public void LoadVoiceList(int i) {
		model.LoadVoiceList(i ,new VoiceModelCallback() {
			public void onSuccess(Object success) {
				List<Voice> voices = (List<Voice>) success;
				voiceses.addAll(voices);
				view.updateList(voiceses);
			}
			public void onError(Object error) {
			}
		});
		
	}

}
