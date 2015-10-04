package cn.Arthur.Game.Main;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.wiyun.engine.actions.Action;
import com.wiyun.engine.actions.IntervalAction;
import com.wiyun.engine.actions.MoveTo;
import com.wiyun.engine.actions.RepeatForever;
import com.wiyun.engine.actions.ScaleBy;
import com.wiyun.engine.actions.Sequence;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.Node;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.Scheduler;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.ScrollableLayer.IScrollableLayerCallback;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.nodes.Timer;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYColor4B;
import com.wiyun.engine.types.WYPoint;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.ResourceDecoder;
import com.wiyun.engine.utils.TargetSelector;

public abstract class GameScene extends Scene implements IScrollableLayerCallback{
	
	final int BGZ=0;
	final int GIRLZ=1;
	final int TALKZ=10;
	final int CHOOSEZ=10;
	final int SCENEZ=10;
	
	Texture2D girlTexture2D;
	Sprite girlSprite;
	
	Texture2D talkTexture2D;
	Sprite talkBg;
	Label talkLabel;
	String talkFinalStr,talkShowStr;
	int indexStr;
	boolean isOutputAll=false;
	boolean isTalk=false;
	Timer talkTimer;
	TargetSelector talkSelector;
	
	Texture2D bgTexture2D;
	Sprite bgSprite;
	
	Texture2D chooseTexture2D;
	Sprite chooseBg;
	Label chooseTitle;
	Texture2D chose1Texture2D,chose2Texture2D;
	NinePatchSprite chose1Sprite,chose2Sprite;
	ScrollableLayer choseLayer;
	Button choseButton[]=new Button[21];
	Label choseLabel[]=new Label[21];
	
	Sprite sceneNameSprite;
	Label sceneNameLabel;
	
	WYSize mWindow=Director.getInstance().getWindowSize();
	
	public GameScene() {
		loadFile();
		
		setTouchEnabled(true);
		autoRelease(false);
    }
	
	/**
	 * 像素转换
	 * @param v
	 * @return
	 */
	private float DP(float v) {
		return ResolutionIndependent.resolveDp(v);
	}
	
	/**
	 * 载入文件
	 */
	public void loadFile(){
		talkTexture2D= Texture2D.makePNG(R.drawable.talk_bg);
		talkBg=Sprite.make(talkTexture2D);
		talkBg.setAutoFit(true);
		chose1Texture2D=Texture2D.makePNG(R.drawable.btn_bg1);
		chose2Texture2D=Texture2D.makePNG(R.drawable.btn_bg2);
		chose1Sprite=NinePatchSprite.make(chose1Texture2D, ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		chose2Sprite=NinePatchSprite.make(chose2Texture2D, ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		chooseTexture2D=Texture2D.makePNG(R.drawable.choose_bg);
		chooseBg=Sprite.make(chooseTexture2D);
		chooseBg.setAutoFit(true);
		sceneNameSprite=Sprite.make(R.drawable.scenename_bg);
		sceneNameSprite.setAutoFit(true);
	}
	
	/**
	 * 设置妹子
	 * @param resId
	 */
	public void setGirlPic(int resId){
		girlTexture2D=(Texture2D)Texture2D.makePNG(resId).autoRelease();
		if (girlSprite!=null){
			girlSprite.resumeAllActions();
			this.removeChild(girlSprite, true);
		}
		girlSprite=(Sprite)Sprite.make(girlTexture2D).autoRelease();
		//girlSprite.setAnchor(girlSprite.getHeight()/2, girlSprite.getWidth()/2);
		girlSprite.setAutoFit(true);
		girlSprite.setContentSize(girlSprite.getWidth()*mWindow.height/3*4/girlSprite.getHeight(), mWindow.height/3*4);
		this.addChild(girlSprite,GIRLZ);
		girlSprite.setPosition(mWindow.width/2, mWindow.height/4);
		IntervalAction ScaleToLarge = (IntervalAction)ScaleBy.make(2f,1.025f, 1.025f).autoRelease();
        IntervalAction ScaleToSmall = (IntervalAction)ScaleToLarge.reverse().autoRelease();
        IntervalAction ScaleQueue = (IntervalAction)Sequence.make(ScaleToLarge, ScaleToSmall).autoRelease();
        Action RepeatQueue = (Action) RepeatForever.make(ScaleQueue).autoRelease();
        girlSprite.runAction(RepeatQueue);
	}
	public void setGirlPic(String fileName){
		girlTexture2D=(Texture2D)Texture2D.makePNG("girl/"+fileName+".png").autoRelease();
		if (girlSprite!=null){
			girlSprite.resumeAllActions();
			this.removeChild(girlSprite, true);
		}
		girlSprite=(Sprite)Sprite.make(girlTexture2D).autoRelease();
		//girlSprite.setAnchor(girlSprite.getHeight()/2, girlSprite.getWidth()/2);
		girlSprite.setAutoFit(true);
		girlSprite.setContentSize(girlSprite.getWidth()*mWindow.height/girlSprite.getHeight(), mWindow.height);
		this.addChild(girlSprite,GIRLZ);
		girlSprite.setPosition(mWindow.width/2, mWindow.height/8*3);
		IntervalAction ScaleToLarge = (IntervalAction)ScaleBy.make(2f,1.025f, 1.025f).autoRelease();
        IntervalAction ScaleToSmall = (IntervalAction)ScaleToLarge.reverse().autoRelease();
        IntervalAction ScaleQueue = (IntervalAction)Sequence.make(ScaleToLarge, ScaleToSmall).autoRelease();
        Action RepeatQueue = (Action) RepeatForever.make(ScaleQueue).autoRelease();
        girlSprite.runAction(RepeatQueue);
	}
	
	/**
	 * 去除女孩
	 */
	public void removeGirl(){
		girlSprite.resumeAllActions();
		this.removeChild(girlSprite, true);
		girlSprite=null;
	}
	
	/**
	 * 获得女孩事例
	 * @return
	 */
	public Sprite getGirl(){
		return girlSprite;
	}
	
	/**
	 * 设置背景
	 * @param resId
	 */
	public void setBgPic(int resId){
		bgTexture2D=Texture2D.makeJPG(resId);
		if (bgSprite!=null){
			this.removeChild(bgSprite, true);
		}
		bgSprite=Sprite.make(bgTexture2D);
		bgSprite.setAutoFit(true);
		bgSprite.setContentSize(mWindow.width,mWindow.height);
		this.addChild(bgSprite,BGZ);
		bgSprite.setPosition(mWindow.width/2, mWindow.height/2);
	}
	public void setBgPic(String fileName){
		bgTexture2D=Texture2D.makeJPG("scene_bg/"+fileName+".jpg");
		bgTexture2D.autoRelease();
		if (bgSprite!=null){
			this.removeChild(bgSprite, true);
		}
		bgSprite=Sprite.make(bgTexture2D);
		bgSprite.setAutoFit(true);
		bgSprite.setContentSize(mWindow.width,mWindow.height);
		this.addChild(bgSprite,BGZ);
		bgSprite.setPosition(mWindow.width/2, mWindow.height/2);
	}
	
	/**
	 * 设置场景名字
	 * @param name
	 */
	public void setSceneName(String name){
		if (sceneNameLabel!=null){
			sceneNameLabel.setText(name);
			return ;
		}
		sceneNameSprite.setContentSize(mWindow.width/2-DP(5), sceneNameSprite.getHeight()*(mWindow.width/2)/sceneNameSprite.getWidth());
		sceneNameLabel=Label.make(name, 25,"font/title_font.ttf", false, mWindow.width-100,Texture2D.CENTER);
		sceneNameSprite.addChild(sceneNameLabel);
		sceneNameLabel.setPosition(sceneNameSprite.getWidth()/2+DP(5), sceneNameSprite.getHeight()/2+DP(3));
		sceneNameLabel.setColor(WYColor3B.make(0, 255, 186));
		sceneNameLabel.setFontStyle(Typeface.BOLD);
		this.addChild(sceneNameSprite, SCENEZ);
		sceneNameSprite.setPosition(-sceneNameSprite.getWidth()/2, mWindow.height-sceneNameSprite.getHeight()/2);
		MoveTo move = (MoveTo)MoveTo.make(2, sceneNameSprite.getPositionX(), sceneNameSprite.getPositionY(),(mWindow.width/2-DP(5))/2,sceneNameSprite.getPositionY()).autoRelease();
		sceneNameSprite.runAction(move);
	}
	
	public Sprite getScenePic(){
		return sceneNameSprite;
	}
	
	/**
	 * 显示对话
	 * @param talk
	 * @param Align
	 */
	public void showTalk(String talk,int Align){
		isTalk=true;
		//背景
		talkBg.setContentSize(mWindow.width, mWindow.height/5);
		this.addChild(talkBg,TALKZ);
		talkBg.setPosition(mWindow.width/2, mWindow.height/10);
		//文本
		talkFinalStr=talk;
		talkShowStr="";
		talkLabel=Label.make(talkShowStr, 20);
		talkLabel.setLineWidth(mWindow.width-DP(20));
		talkLabel.setAlignment(Align);
		
		this.addChild(talkLabel,TALKZ+1);
		talkLabel.setPosition(mWindow.width/2, mWindow.height/10);
		talkLabel.setColor(WYColor3B.make(141, 0, 255));
		//逐字
		indexStr=-1;
		isOutputAll=false;
		talkSelector=new TargetSelector(this,"updateTalk(float)",new Object[]{0f});
		talkTimer=new Timer(talkSelector,10);
		Scheduler.getInstance().schedule(talkTimer);
	}
	
	/**
	 * 逐字效果
	 * @param delta
	 */
	public void updateTalk(float delta){
		if (isOutputAll==true){
			talkLabel.setText(talkFinalStr);
			if (talkTimer!=null) Scheduler.getInstance().unschedule(talkTimer);
		}else{
			talkShowStr+=talkFinalStr.toCharArray()[++indexStr];
			talkLabel.setText(talkShowStr);
			if (indexStr+1>=talkFinalStr.length()) isOutputAll=true;
		}
	}
	
	/**
	 * 结束对话
	 */
	public void endTalk(){
		if (isOutputAll==false){
			isOutputAll=true;
			talkLabel.setText(talkFinalStr);
			if (talkTimer!=null) Scheduler.getInstance().unschedule(talkTimer);
		}else{
			isOutputAll=false;
			isTalk=false;
			if (talkTimer!=null) Scheduler.getInstance().unschedule(talkTimer);
			if (talkBg!=null) this.removeChild(talkBg, true);
			if (talkLabel!=null) this.removeChild(talkLabel, true);
			TalkEnd();
		}
		
	}
	
	/**
	 * 添加选项
	 * @param n
	 * @param str
	 */
	public void showChoose(int n,String str[],String Title){
		//背景
		chooseBg.setContentSize(mWindow.width,DP(80)/2*3);
		this.addChild(chooseBg,CHOOSEZ);
		chooseBg.setPosition(mWindow.width/2, DP(80)/2*3/2);
		//标题
		chooseTitle=Label.make(Title, 20,"font/title_font.ttf", false,mWindow.width, Texture2D.CENTER);
		chooseTitle.setColor(WYColor3B.make(255, 132, 0));
		chooseTitle.setPosition(mWindow.width/2, DP(90));
		this.addChild(chooseTitle,CHOOSEZ);
		//浮动框
		choseLayer=ScrollableLayer.make(WYColor4B.make(255, 0, 0, 0));
		choseLayer.setContentSize(mWindow.width, DP(80));
		choseLayer.setRelativeAnchorPoint(true);
		choseLayer.setPosition(mWindow.width / 2, DP(40));
		choseLayer.setVertical(false);
		choseLayer.setHorizontal(true);
		choseLayer.setLeftMargin(DP(10));
		choseLayer.setRightMargin(DP(10));
		choseLayer.setTopMargin(DP(10));
		choseLayer.setBottomMargin(DP(10));
		choseLayer.setCallback(this);
		this.addChild(choseLayer,CHOOSEZ);
		chose1Sprite.setContentSize(mWindow.width/3,DP(60));
		chose2Sprite.setContentSize(mWindow.width/3,DP(60));
		for (int i=1;i<=n;i++){
			choseButton[i]=Button.make(chose1Sprite, chose2Sprite, null, null, new TargetSelector(this, "onChoseButtonClicked(int)", new Object[] {i}));
			choseButton[i].setPosition(mWindow.width/3*i, DP(40));
			choseLabel[i]=Label.make(str[i], 20,"font/talk_font.otf", false, DP(100), Texture2D.CENTER);
			choseLabel[i].setPosition(mWindow.width/3*i, DP(40));
			choseLabel[i].setColor(WYColor3B.make(255, 0, 132));
			choseLayer.addScrollableChild(choseButton[i]);
			choseLayer.addScrollableChild(choseLabel[i]);
		}
		if (n==2){
			for (int i=1;i<=n;i++){
				choseButton[i].setPosition(mWindow.width/3*i, DP(40));
				choseLabel[i].setPosition(mWindow.width/3*i, DP(40));
			}
		}
	}
	
	//按钮选择处理
	public void onChoseButtonClicked(int index){
		this.removeChild(choseLayer, true);
		this.removeChild(chooseBg, true);
		this.removeChild(chooseTitle, true);
		ChooseEnd(index);
	}
	
	
	/**
	 * 触摸处理
	 */
	public boolean wyTouchesBegan(MotionEvent event) {
		WYPoint convertedLocation = Director.getInstance().convertToGL(event.getX(), event.getY());
		if (isTalk==true) {
			endTalk();
			return true;
		}
		
		TouchBegin(event);
		return true;
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
	
	/**
	 * 对话结束接口
	 */
	public abstract void TalkEnd();
	
	/**
	 * 触摸接口
	 * @param event
	 */
	public abstract void TouchBegin(MotionEvent event);
	
	/**
	 * 选项结束接口
	 * @param index
	 */
	public abstract void ChooseEnd(int index);

}
