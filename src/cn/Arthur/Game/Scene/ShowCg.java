package cn.Arthur.Game.Scene;

import cn.Arthur.Game.Main.MainActivity;
import cn.Arthur.Game.Main.R;

import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.nodes.ScrollableLayer;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYColor4B;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;

public class ShowCg extends Scene{
	
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	Button GameButton;
	Label GameLabel;
	Sprite Cg;
	ScrollableLayer CgLayer;
	WYSize mWindow;
	
	public ShowCg(int i,int j,String bg){
		mWindow=Director.getInstance().getWindowSize();
		((MainActivity)Director.getInstance().getContext()).actAD();
		
		setBgPic(bg);
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onBackButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/2,GameButton.getHeight()/2);
		GameLabel=Label.make("·µ»Ø", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/2, GameButton.getHeight()/2);
		GameLabel.setColor(WYColor3B.make(255, 0, 132));
		this.addChild(GameButton, 11);
		this.addChild(GameLabel, 12);
		
		CgLayer = ScrollableLayer.make(WYColor4B.make(255, 0, 0, 0));
		CgLayer.setContentSize(mWindow.width, mWindow.height);
		CgLayer.setRelativeAnchorPoint(true);
		CgLayer.setPosition(mWindow.width / 2, mWindow.height / 2);
		CgLayer.setVertical(true);
		CgLayer.setHorizontal(true);
		CgLayer.setLeftMargin(DP(0));
		CgLayer.setRightMargin(DP(0));
		CgLayer.setTopMargin(DP(0));
		CgLayer.setBottomMargin(DP(0));
		this.addChild(CgLayer,1);
		
		Cg=Sprite.make(Texture2D.makePNG("girl/"+"girl"+String.valueOf(i)+"_"+String.valueOf(j)+".png"));
		Cg.setAutoFit(true);
		Cg.setContentSize(Cg.getWidth()*mWindow.height/Cg.getHeight(), mWindow.height);
		Cg.setPosition(mWindow.width/2-DP(0), mWindow.height/2-DP(0));
		
		CgLayer.addScrollableChild(Cg);
		
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
	
	/**
	 * ·µ»Ø
	 * @param delta
	 */
	public void onBackButtonClicked(int delta){
		Director.getInstance().popScene();
	}

}
