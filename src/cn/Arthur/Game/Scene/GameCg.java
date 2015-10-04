package cn.Arthur.Game.Scene;

import net.youmi.android.spot.SpotManager;
import cn.Arthur.Game.Main.MainActivity;
import cn.Arthur.Game.Main.R;

import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.PageControl;
import com.wiyun.engine.nodes.PageControl.IPageControlCallback;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class GameCg extends Scene implements IPageControlCallback{
	
	final int CGNUM=32;
	final int ZTAG=100;
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	Button GameButton;
	Label GameLabel;
	Sprite Cg[]=new Sprite[50];
	Boolean isIn[]=new Boolean[50];
	WYSize mWindow;
	PageControl pageLayer;
	
	Label loadLabel;
	Label titleLabel;
	Label authorLabel;
	
	public GameCg(){
		mWindow=Director.getInstance().getWindowSize();
		
		SpotManager.getInstance(Director.getInstance().getContext()).showSpotAds(Director.getInstance().getContext());
		
		loadLabel=Label.make("Res Loading");
		loadLabel.setFontSize(35);
		loadLabel.setLineWidth(Director.getInstance().getWindowSize().width);
		loadLabel.setPosition(Director.getInstance().getWindowSize().width/2, Director.getInstance().getWindowSize().height/4);
		this.addChild(loadLabel);
		titleLabel=Label.make("游戏CG正在准备中……");
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
	
	public void initView(){

		this.removeChild(loadLabel, true);
		this.removeChild(titleLabel, true);
		this.removeChild(authorLabel, true);
		
		setBgPic("cg_bg");
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onBackButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/2,GameButton.getHeight()/2);
		GameButton.setEnabled(true);
		GameLabel=Label.make("返回", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/2, GameButton.getHeight()/2);
		GameLabel.setColor(WYColor3B.make(255, 0, 132));
		this.addChild(GameButton, 111);
		this.addChild(GameLabel, 112);
		
		pageLayer = PageControl.make();
		pageLayer.setCallback(this);
		pageLayer.setPageSpacing(DP(80));
		for (int i=1;i<=CGNUM;i++){
			Cg[i]=Sprite.make(Texture2D.makePNG("girl/"+"final_cg_"+String.valueOf(i)+".png"));
			Cg[i].setAutoFit(true);
			Cg[i].setContentSize(Cg[i].getWidth()*(mWindow.height/2)/Cg[i].getHeight(), mWindow.height/2);
			pageLayer.addPage(Cg[i]);
		}
		pageLayer.setVertical(true);
		pageLayer.setInitialPage(0);
		this.addChild(pageLayer,ZTAG+1);
	}
	
	//设置背景
	public void setBgPic(String fileName){
		Sprite bgSprite=Sprite.make(Texture2D.makeJPG("scene_bg/"+fileName+".jpg"));
		bgSprite.setAutoFit(true);
		bgSprite.setContentSize(mWindow.width,mWindow.height);
		this.addChild(bgSprite,0);
		bgSprite.setPosition(mWindow.width/2, mWindow.height/2);
	}
	
	private void updateLoadLabel(int index) {
		 loadLabel.setText("Res Loading:" + index + "/"+String.valueOf(CGNUM));
    }
	
	public void loadData(){
		 // start a thread to load texture
   	Director.getInstance().runThread(new Runnable() {
			@Override
			public void run() {
       		// load textures
			for (int i=1;i<=CGNUM;i++){
				((Texture2D)Texture2D.makePNG("girl/"+"final_cg_"+String.valueOf(i)+".png").autoRelease()).loadTexture();
       			updateLoadLabel(i);
			}
       		Director.getInstance().runOnGLThread(new Runnable() {
					@Override
					public void run() {
						initView();
					}
				});
			}
		});
	}
	
	
	/**
	 * 像素转换
	 * @param v
	 * @return
	 */
	private float DP(float v) {
		return ResolutionIndependent.resolveDp(v);
	}

	@Override
	public void onPageChanged(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageClicked(int arg0, int index) {
		//if (isIn[index+1]){
			Director.getInstance().pushScene(new ShowGameCg(index+1));
		//}
		
	}

	@Override
	public void onPagePositionChanged(int arg0, int arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 返回
	 * @param delta
	 */
	public void onBackButtonClicked(int delta){
		Director.getInstance().popScene();
	}

}
