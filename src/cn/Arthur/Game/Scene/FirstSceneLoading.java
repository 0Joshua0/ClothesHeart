package cn.Arthur.Game.Scene;
import cn.Arthur.Game.Main.R;
import cn.Arthur.Game.Main.MainActivity;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.opengl.Texture2D;


public class FirstSceneLoading extends Scene{
	
	Label loadLabel;
	Label titleLabel;
	Label authorLabel;
	
	public FirstSceneLoading(){
		loadLabel=Label.make("Res Loading");
		loadLabel.setFontSize(35);
		loadLabel.setLineWidth(Director.getInstance().getWindowSize().width);
		loadLabel.setPosition(Director.getInstance().getWindowSize().width/2, Director.getInstance().getWindowSize().height/4);
		this.addChild(loadLabel);
		titleLabel=Label.make("《"+Director.getInstance().getContext().getString(R.string.app_name)+"》正在准备中……");
		titleLabel.setFontSize(35);
		titleLabel.setLineWidth(Director.getInstance().getWindowSize().width);
		titleLabel.setPosition(Director.getInstance().getWindowSize().width/2, Director.getInstance().getWindowSize().height/4*3);
		this.addChild(titleLabel);
		authorLabel=Label.make("Powered by "+"霜Z");
		authorLabel.setFontSize(35);
		authorLabel.setLineWidth(Director.getInstance().getWindowSize().width);
		authorLabel.setPosition(Director.getInstance().getWindowSize().width/2, Director.getInstance().getWindowSize().height/2);
		this.addChild(authorLabel);
		loadData();
	}
	
	 private void updateLoadLabel(int index) {
		 loadLabel.setText("Res Loading:" + index + "/8");
     }

	
	public void loadData(){
		 // start a thread to load texture
    	Director.getInstance().runThread(new Runnable() {
			@Override
			public void run() {
        		// load textures
        		((Texture2D)Texture2D.makePNG(R.drawable.btn_bg1).autoRelease()).loadTexture();
        		updateLoadLabel(1);
        		((Texture2D)Texture2D.makePNG(R.drawable.btn_bg2).autoRelease()).loadTexture();
        		updateLoadLabel(2);
        		((Texture2D)Texture2D.makePNG(R.drawable.start_bg).autoRelease()).loadTexture();
        		updateLoadLabel(3);
        		((Texture2D)Texture2D.makePNG(R.drawable.start_title).autoRelease()).loadTexture();
        		updateLoadLabel(4);
        		((Texture2D)Texture2D.makePNG(R.drawable.help1).autoRelease()).loadTexture();
        		updateLoadLabel(5);
        		((Texture2D)Texture2D.makePNG(R.drawable.help2).autoRelease()).loadTexture();
        		updateLoadLabel(6);
        		((Texture2D)Texture2D.makePNG(R.drawable.help3).autoRelease()).loadTexture();
        		updateLoadLabel(7);
        		((Texture2D)Texture2D.makePNG(R.drawable.help4).autoRelease()).loadTexture();
        		updateLoadLabel(8);
        		//((Texture2D)Texture2D.makePNG("scene_bg/home.jpg").autoRelease()).loadTexture();
        		//updateLoadLabel(9);
        		//((Texture2D)Texture2D.makePNG("scene_bg/classroom.jpg").autoRelease()).loadTexture();
        		//updateLoadLabel(10);
        		//((Texture2D)Texture2D.makePNG("scene_bg/fitroom.jpg").autoRelease()).loadTexture();
        		//updateLoadLabel(11);
        		//((Texture2D)Texture2D.makePNG("scene_bg/swimmingroom.jpg").autoRelease()).loadTexture();
        		//updateLoadLabel(12);
        		
        		Director.getInstance().runOnGLThread(new Runnable() {
					@Override
					public void run() {
						((MainActivity)Director.getInstance().getContext()).RunFirstScene();
					}
				});
			}
		});
	}

}
