package cn.Arthur.Game.Scene;

import android.view.MotionEvent;
import cn.Arthur.Game.Main.GameScene;
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

public abstract class ChooseScene extends GameScene implements IPageControlCallback{
	
	final int ZTAG=100;
	Sprite passSprite,noPassSprite;
	PageControl pageLayer;
	Sprite[] Girls=new Sprite[4];
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	NinePatchSprite BackButtonSpriteNormal,BackButtonSpritePressed;
	Button GameButton;
	Button CgButton;
	Label GameLabel,CgLabel;
	int[] GirlPic=new int[4];
	int selected=1;
	String GameBg;
	String[] GirlName;
	NinePatchSprite NameSprite;
	Label NameLabel;
	Button BackButton;
	Label BackLabel;
	Sprite Tip;
	
	
	
	WYSize mWindow;
	
	public ChooseScene(int Girl1,int Girl2,int Girl3,String[] name,String Bg){
		mWindow=Director.getInstance().getWindowSize();
		setBgPic(Bg);
		GameBg=Bg;
		GirlPic[1]=Girl1;
		GirlPic[2]=Girl2;
		GirlPic[3]=Girl3;
		GirlName=name;
		initView();
	}
	
	public void initView(){
		passSprite=Sprite.make(R.drawable.gold);
		noPassSprite=Sprite.make(R.drawable.silver);
		passSprite.setAutoFit(true);
		passSprite.setContentSize((int)(mWindow.width/4*1.5), mWindow.width/4);
		passSprite.setPosition(mWindow.width-passSprite.getWidth()/2-DP(5), mWindow.height-passSprite.getHeight()/2-DP(5));
		noPassSprite.setAutoFit(true);
		noPassSprite.setContentSize((int)(mWindow.width/4*1.5), mWindow.width/4);
		noPassSprite.setPosition(mWindow.width-noPassSprite.getWidth()/2-DP(5), mWindow.height-noPassSprite.getHeight()/2-DP(5));
		passSprite.setVisible(false);
		noPassSprite.setVisible(false);
		this.addChild(noPassSprite, ZTAG+1);
		this.addChild(passSprite, ZTAG+1);
		
		
		NameSprite=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		NameSprite.setContentSize(mWindow.width/2, DP(60));
		NameSprite.setPosition(mWindow.width/4, mWindow.height-DP(30));
		NameLabel=Label.make("无名字", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		NameLabel.setPosition(mWindow.width/4, mWindow.height-DP(30));
		NameLabel.setColor(WYColor3B.make(18, 255, 0));
		this.addChild(NameSprite,ZTAG+1);
		this.addChild(NameLabel, ZTAG+2);
		
		
		
		BackButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		BackButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		BackButtonSpriteNormal.setContentSize(mWindow.width, DP(60));
		BackButtonSpritePressed.setContentSize(mWindow.width, DP(60));
		BackButton=Button.make(BackButtonSpriteNormal, BackButtonSpritePressed, null, null, new TargetSelector(this, "onBackButtonClicked(int)", new Object[] {1}));
		BackButton.setPosition(mWindow.width/2,BackButton.getHeight()/2);
		BackLabel=Label.make("返回标题界面", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		BackLabel.setPosition(mWindow.width/2, BackButton.getHeight()/2);
		BackLabel.setColor(WYColor3B.make(94, 94, 94));
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onPlayButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/4,GameButton.getHeight()/2+BackButton.getHeight());
		CgButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onCgButtonClicked(int)", new Object[] {1}));
		CgButton.setPosition(mWindow.width/4*3,CgButton.getHeight()/2+BackButton.getHeight());
		GameLabel=Label.make("进入游戏", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/4, GameButton.getHeight()/2+BackButton.getHeight());
		GameLabel.setColor(WYColor3B.make(255, 70, 70));
		CgLabel=Label.make("人物图鉴", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		CgLabel.setPosition(mWindow.width/4*3, CgButton.getHeight()/2+BackButton.getHeight());
		CgLabel.setColor(WYColor3B.make(17, 0, 177));
		
		Tip=Sprite.make(R.drawable.tip);
		Tip.setContentSize(mWindow.width, Tip.getHeight()*mWindow.width/Tip.getHeight());
		Tip.setPosition(mWindow.width/2, GameButton.getHeight()+BackButton.getHeight()+Tip.getHeight()/2);
		this.addChild(Tip, ZTAG+3);
		
		this.addChild(BackButton, ZTAG+3);
		this.addChild(BackLabel,ZTAG+4);
		this.addChild(GameButton,ZTAG+3);
		this.addChild(GameLabel,ZTAG+4);
		this.addChild(CgButton,ZTAG+3);
		this.addChild(CgLabel,ZTAG+4);
		
		for (int i=1;i<=3;i++) {
			Girls[i]=Sprite.make(Texture2D.makePNG("girl/"+"girl"+String.valueOf(GirlPic[i])+"_1.png"));
			Girls[i].setAutoFit(true);
			Girls[i].setContentSize(Girls[i].getWidth()*(mWindow.height/4*3)/Girls[i].getHeight(), mWindow.height/4*3);
		}
		
		pageLayer = PageControl.make();
		pageLayer.setCallback(this);
		pageLayer.setPageSpacing(DP(80));
		pageLayer.addPage(Girls[1]);
		pageLayer.addPage(Girls[2]);
		pageLayer.addPage(Girls[3]);
		pageLayer.setInitialPage(0);
		this.addChild(pageLayer,ZTAG+1);
		
		if (((MainActivity)Director.getInstance().getContext()).getCg(GirlPic[1], 5)==true){
			passSprite.setVisible(true);
			noPassSprite.setVisible(false);
		}else{
			passSprite.setVisible(false);
			noPassSprite.setVisible(true);
		}
		NameLabel.setText(GirlName[1]);
		
	}
	
	/**
	 * 返回
	 * @param delta
	 */
	public void onBackButtonClicked(int delta){
		((MainActivity)Director.getInstance().getContext()).refreshStart();
		Director.getInstance().popScene();
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
	public void TalkEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TouchBegin(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ChooseEnd(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageChanged(int arg0, int arg1) {
		selected=arg1+1;
		if (((MainActivity)Director.getInstance().getContext()).getCg(GirlPic[selected], 5)==true){
			passSprite.setVisible(true);
			noPassSprite.setVisible(false);
		}else{
			passSprite.setVisible(false);
			noPassSprite.setVisible(true);
		}
		NameLabel.setText(GirlName[selected]);
		
	}

	@Override
	public void onPageClicked(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPagePositionChanged(int arg0, int arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * 获得选择
	 * @return
	 */
	public int getSelect(){
		return selected;
	}
	
	/**
	 * 进入游戏
	 * @param delta
	 */
	public abstract void onPlayButtonClicked(int delta);
	
	/**
	 * 进入Cg
	 * @param delta
	 */
	public void onCgButtonClicked(int delta){
		Director.getInstance().pushScene(new CgChooseScene(GirlPic[selected],GameBg));
	}

}
