package cn.Arthur.Game.Scene;

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

public class HelpScene extends Scene implements IPageControlCallback{
	
	
	

	final int ZTAG=100;
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	Button GameButton;
	Label GameLabel;
	Sprite[] Help=new Sprite[5];
	WYSize mWindow;
	PageControl pageLayer;
	
	public HelpScene(){
		mWindow=Director.getInstance().getWindowSize();
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onBackButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/2,GameButton.getHeight()/2);
		GameLabel=Label.make("返回标题画面", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/2, GameButton.getHeight()/2);
		GameLabel.setColor(WYColor3B.make(255, 0, 132));
		GameButton.setEnabled(false);
		GameButton.setVisible(false);
		GameLabel.setVisible(false);
		this.addChild(GameButton, 111);
		this.addChild(GameLabel, 112);
		pageLayer = PageControl.make();
		Help[1]=Sprite.make(R.drawable.help1);
		Help[2]=Sprite.make(R.drawable.help2);
		Help[3]=Sprite.make(R.drawable.help3);
		Help[4]=Sprite.make(R.drawable.help4);
		for (int i=1;i<=4;i++) {
			Help[i].setAutoFit(true);
			Help[i].setContentSize(mWindow.width, mWindow.height);
			pageLayer.addPage(Help[i]);
		}
		pageLayer.setCallback(this);
		pageLayer.setPageSpacing(DP(0));
		pageLayer.setVertical(false);
		pageLayer.setInitialPage(0);
		this.addChild(pageLayer,ZTAG+1);
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
	public void onPageChanged(int arg0, int index) {
		if (index+1==4){
			GameButton.setEnabled(true);
			GameButton.setVisible(true);
			GameLabel.setVisible(true);
		}else{
			GameButton.setEnabled(false);
			GameButton.setVisible(false);
			GameLabel.setVisible(false);
		}
		
	}

	@Override
	public void onPageClicked(int arg0, int index) {
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
