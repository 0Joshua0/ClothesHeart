package cn.Arthur.Game.Scene;

import android.graphics.Typeface;
import cn.Arthur.Game.Main.MainActivity;
import cn.Arthur.Game.Main.R;
import com.wiyun.engine.actions.FadeTo;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.ScrollableLayer.IScrollableLayerCallback;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYColor4B;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.ResourceDecoder;
import com.wiyun.engine.utils.TargetSelector;

public class StartScene extends Scene implements IScrollableLayerCallback{
	Sprite BGSprite;
	Sprite TitleSprite;
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	ScrollableLayer ButtonLayer;
	WYSize mWindow;
	String ButtonName[]={"","退出","帮助","福利CG","游泳池","运动馆","教室","房间"};
	Button StartButton[]=new Button[101];
	Label StartLabel[]=new Label[101];
	
	public StartScene(){
		initView();
	}
	
	public void initView(){
		mWindow=Director.getInstance().getWindowSize();
		
		BGSprite=Sprite.make(R.drawable.start_bg);
		TitleSprite=Sprite.make(R.drawable.start_title);
		BGSprite.setAutoFit(true);
		TitleSprite.setAutoFit(true);
		BGSprite.setContentSize(mWindow.width,mWindow.height);
		TitleSprite.setContentSize(mWindow.width, TitleSprite.getHeight()*mWindow.width/TitleSprite.getWidth());
		BGSprite.setPosition(mWindow.width/2, mWindow.height/2);
		TitleSprite.setPosition(mWindow.width/2, mWindow.height+TitleSprite.getHeight()/2);
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2-DP(20), DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2-DP(20), DP(60));
		
		ButtonLayer=ScrollableLayer.make(WYColor4B.make(255, 0, 0, 0));
		ButtonLayer.setContentSize(mWindow.width/2, mWindow.height/2);
		ButtonLayer.setRelativeAnchorPoint(true);
		ButtonLayer.setPosition(mWindow.width / 4*3, mWindow.height/4);
		ButtonLayer.setVertical(true);
		ButtonLayer.setHorizontal(false);
		ButtonLayer.setLeftMargin(DP(10));
		ButtonLayer.setRightMargin(DP(10));
		ButtonLayer.setTopMargin(DP(10));
		ButtonLayer.setBottomMargin(DP(10));
		ButtonLayer.setCallback(this);
		for (int i=1;i<ButtonName.length;i++){
			StartButton[i]=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onChoseButtonClicked(int)", new Object[] {i}));
			StartButton[i].setPosition(mWindow.width/2, mWindow.height/2-DP(20)+DP(90)*i+DP(30));
			StartLabel[i]=Label.make(ButtonName[i], 20,"font/title_font.ttf", false, mWindow.width/2-DP(20), Texture2D.CENTER);
			StartLabel[i].setPosition(mWindow.width/2, mWindow.height/2-DP(20)+DP(90)*i+DP(30));
			StartLabel[i].setColor(WYColor3B.make(255, 52, 109));
			StartLabel[i].setFontStyle(Typeface.BOLD);
			ButtonLayer.addScrollableChild(StartButton[i],3);
			ButtonLayer.addScrollableChild(StartLabel[i],4);
		}
		
		this.addChild(BGSprite, 0);
		this.addChild(TitleSprite,1);
		this.addChild(ButtonLayer,2);
		
		MoveTo moveTitle = (MoveTo)MoveTo.make(2, TitleSprite.getPositionX(), TitleSprite.getPositionY(),mWindow.width/2,mWindow.height-TitleSprite.getHeight()/2).autoRelease();
		TitleSprite.runAction(moveTitle);
		
		//FadeTo fadeButton = (FadeTo)FadeTo.make(2,0,255,true).autoRelease();
		//ButtonLayer.runAction(fadeButton);
		 RefreshButton();
	}
	
	
	
	/**
	 * 刷新BUTTON
	 */
	public void RefreshButton(){
		for (int i=1;i<=ButtonName.length-2;i++){
			((MainActivity)Director.getInstance().getContext()).unLockStage(i);
		}
		for (int i=3;i<ButtonName.length;i++){
			if (((MainActivity)Director.getInstance().getContext()).getStage(ButtonName.length-i)==true){
				StartButton[i].setEnabled(true);
				StartLabel[i].setText(ButtonName[i]);
			}else{
				StartButton[i].setEnabled(false);
				StartLabel[i].setText("Locked");
			}
		}
	}
	
	//ReplaceScene
	public void onChoseButtonClicked(int index){
		
		if (index==1) ((MainActivity)Director.getInstance().getContext()).finish();
		if (index==2) Director.getInstance().pushScene(new HelpScene());
		if (index==3) Director.getInstance().pushScene(new GameCg());
		if (index==4) Director.getInstance().pushScene(new StageSwimming());
		if (index==5) Director.getInstance().pushScene(new StageFitRoom());
		if (index==6) Director.getInstance().pushScene(new StageClassRoom());
		if (index==7) Director.getInstance().pushScene(new StageHome());
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
	public void onEndFling(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollOffsetChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollableChildNotVisible(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartFling(int arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
