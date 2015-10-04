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

public class CgChooseScene extends Scene implements IPageControlCallback{
	
	final int ZTAG=100;
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	Button GameButton;
	Label GameLabel;
	Sprite Cg[]=new Sprite[6];
	Boolean isIn[]=new Boolean[6];
	WYSize mWindow;
	PageControl pageLayer;
	int Girl;
	String Bg;
	
	public CgChooseScene(int girl,String bg){
		mWindow=Director.getInstance().getWindowSize();
		
		Girl=girl;
		
		setBgPic(bg);
		Bg=bg;
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onBackButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/2,GameButton.getHeight()/2);
		GameLabel=Label.make("·µ»Ø", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/2, GameButton.getHeight()/2);
		GameLabel.setColor(WYColor3B.make(255, 0, 132));
		this.addChild(GameButton, 111);
		this.addChild(GameLabel, 112);
		
		pageLayer = PageControl.make();
		pageLayer.setCallback(this);
		pageLayer.setPageSpacing(DP(80));
		for (int i=1;i<=5;i++){
			
			if (((MainActivity)Director.getInstance().getContext()).getCg(girl, i)==true){
				isIn[i]=true;
				Cg[i]=Sprite.make(Texture2D.makePNG("girl/"+"girl"+String.valueOf(girl)+"_"+String.valueOf(i)+".png"));
				Cg[i].setAutoFit(true);
				Cg[i].setContentSize(Cg[i].getWidth()*(mWindow.height/2)/Cg[i].getHeight(), mWindow.height/2);
				pageLayer.addPage(Cg[i]);
			}else{
				isIn[i]=false;
				Cg[i]=Sprite.make(R.drawable.cg_blank);
				pageLayer.addPage(Cg[i]);
			}
		}
		pageLayer.setVertical(true);
		pageLayer.setInitialPage(0);
		this.addChild(pageLayer,ZTAG+1);
	}
	
	//ÉèÖÃ±³¾°
	public void setBgPic(String fileName){
		Sprite bgSprite=Sprite.make(Texture2D.makeJPG("scene_bg/"+fileName+".jpg"));
		bgSprite.setAutoFit(true);
		bgSprite.setContentSize(mWindow.width,mWindow.height);
		this.addChild(bgSprite,0);
		bgSprite.setPosition(mWindow.width/2, mWindow.height/2);
	}
	
	/**
	 * ÏñËØ×ª»»
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
		if (isIn[index+1]){
			Director.getInstance().pushScene(new ShowCg(Girl,index+1,Bg));
		}
		
	}

	@Override
	public void onPagePositionChanged(int arg0, int arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * ·µ»Ø
	 * @param delta
	 */
	public void onBackButtonClicked(int delta){
		Director.getInstance().popScene();
	}

}
