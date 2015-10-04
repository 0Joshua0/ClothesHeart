package cn.Arthur.Game.Scene;

import com.wiyun.engine.actions.Blink;
import com.wiyun.engine.actions.FadeTo;
import com.wiyun.engine.actions.Shake;
import com.wiyun.engine.nodes.Button;
import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Label;
import com.wiyun.engine.nodes.NinePatchSprite;
import com.wiyun.engine.nodes.Scheduler;
import com.wiyun.engine.nodes.Sprite;
import com.wiyun.engine.nodes.Timer;
import com.wiyun.engine.opengl.Texture2D;
import com.wiyun.engine.types.WYColor3B;
import com.wiyun.engine.types.WYRect;
import com.wiyun.engine.types.WYSize;
import com.wiyun.engine.utils.ResolutionIndependent;
import com.wiyun.engine.utils.TargetSelector;
import android.view.MotionEvent;
import cn.Arthur.Game.Main.*;

public class PlayScene extends GameScene{

	private final int ZTAG=100;
	
	private String GirlName,GameBg;
	private int GirlPic;
	private String[] TalkStr;
	private int GameTime;
	private int deltaTime;
	private int GameIndex=-1;
	private Sprite BlackBg;
	private Label TimeLabel;
	TargetSelector Timing;
	Timer TimingTimer;
	private int playTime;
	private boolean isPlay;
	private int GameResult=-1;
	private String[] GameResultStr;
	private Boolean isGameEnd=false;
	private Boolean isHide=false;
	
	Sprite Clear,PClear;
	
	NinePatchSprite ButtonSpriteNormal,ButtonSpritePressed;
	Button GameButton;
	Label GameLabel;
	
	NinePatchSprite GoalSprite;
	Label GoalLabel;
	
	
	WYSize mWindow;
	
	
	public PlayScene(String GirlName,int GirlPic,String GameBg,String[] TalkStr,String[] GameResultStr,int GameTime,int deltaTime,boolean isHide){
		this.GirlName=GirlName;
		this.GirlPic=GirlPic;
		this.GameBg=GameBg;
		this.TalkStr=TalkStr;
		this.GameResultStr=GameResultStr;
		this.GameTime=GameTime;
		this.deltaTime=deltaTime;
		this.isHide=isHide;
		mWindow=Director.getInstance().getWindowSize();
		initGame();
		addView();
		initView();
		((MainActivity)Director.getInstance().getContext()).UnlockCg(GirlPic,1);
		GameEvent();
		
	}
	
	/**
	 * 初始化游戏
	 */
	private void initGame(){
		GameIndex=0;
		GameResult=-1;
		isGameEnd=false;
		isPlay=false;
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
	 * 添加场景
	 */
	private void initView(){
		BlackBg.setVisible(false);
		TimeLabel.setVisible(false);
		GameLabel.setVisible(false);
		GameButton.setVisible(false);
		GameButton.setEnabled(false);
		GoalSprite.setVisible(false);
		GoalLabel.setVisible(false);
		Clear.setVisible(false);
		PClear.setVisible(false);
		GoalLabel.setText(String.format("过关时间:\n%02d:%02d-%02d:%02d",GameTime/100,GameTime%100,(GameTime+100)/100,(GameTime+100)%100));
		//setBgPic(GameBg);
		setGirlPic("girl"+String.valueOf(GirlPic)+"_1");
		//setSceneName(GirlName);
	}
	
	/**
	 * 初始化场景
	 */
	private void addView(){
		setBgPic(GameBg);
		//setGirlPic(GirlPic+"_1");
		setSceneName(GirlName);
		
		BlackBg=Sprite.make(R.drawable.black_bg);
		BlackBg.setAutoFit(true);
		BlackBg.setContentSize(mWindow.width, mWindow.height);
		BlackBg.setPosition(mWindow.width/2, mWindow.height/2);
		//BlackBg.setVisible(false);
		this.addChild(BlackBg,ZTAG+1);
		
		
		TimeLabel=Label.make("00:00", 30);
		TimeLabel.setPosition(mWindow.width/2, mWindow.height/2);
		TimeLabel.setColor(WYColor3B.make(255, 255, 255));
		//TimeLabel.setVisible(false);
		this.addChild(TimeLabel,ZTAG+2);
		
		Clear=Sprite.make(R.drawable.clear);
		PClear=Sprite.make(R.drawable.perfect_clear);
		if (Clear.getWidth()>mWindow.width) Clear.setContentSize(mWindow.width, Clear.getHeight()*mWindow.width/Clear.getWidth());
		if (PClear.getWidth()>mWindow.width) PClear.setContentSize(mWindow.width, PClear.getHeight()*mWindow.width/PClear.getWidth());
		Clear.setPosition(mWindow.width/2, mWindow.height/4*3);
		PClear.setPosition(mWindow.width/2, mWindow.height/4*3);
		//Clear.setVisible(false);
		//PClear.setVisible(false);
		this.addChild(Clear, ZTAG+2);
		this.addChild(PClear, ZTAG+2);
		
		ButtonSpriteNormal=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg1), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpritePressed=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		ButtonSpriteNormal.setContentSize(mWindow.width/2, DP(60));
		ButtonSpritePressed.setContentSize(mWindow.width/2, DP(60));
		GameButton=Button.make(ButtonSpriteNormal, ButtonSpritePressed, null, null, new TargetSelector(this, "onChoseButtonClicked(int)", new Object[] {1}));
		GameButton.setPosition(mWindow.width/2, mWindow.height/4);
		GameLabel=Label.make("", 20,"font/title_font.otf", false, mWindow.width/2, Texture2D.CENTER);
		GameLabel.setPosition(mWindow.width/2, mWindow.height/4);
		GameLabel.setColor(WYColor3B.make(255, 0, 132));
		//GameLabel.setVisible(false);
		//GameButton.setVisible(false);
		//GameButton.setEnabled(false);
		this.addChild(GameButton, ZTAG+3);
		this.addChild(GameLabel, ZTAG+4);
		
		
		GoalLabel=Label.make(String.format("过关时间:\n%02d:%02d-%02d:%02d",GameTime/100,GameTime%100,(GameTime+100)/100,(GameTime+100)%100), 20,"font/title_font.ttf", false, mWindow.width/2, Texture2D.CENTER);
		GoalSprite=NinePatchSprite.make(Texture2D.makePNG(R.drawable.btn_bg2), ResolutionIndependent.resolve(WYRect.make(20, 20, 5, 5)));
		GoalSprite.setContentSize(super.getScenePic().getWidth(), super.getScenePic().getHeight()*2);
		GoalSprite.setPosition(mWindow.width/4*3, mWindow.height-GoalSprite.getHeight()/2);
		GoalLabel.setPosition(mWindow.width/4*3, mWindow.height-GoalSprite.getHeight()/2);
		GoalLabel.setColor(WYColor3B.make(44, 44, 255));
		//GoalSprite.setVisible(false);
		//GoalLabel.setVisible(false);
		this.addChild(GoalSprite,ZTAG+3);
		this.addChild(GoalLabel, ZTAG+4);
		
	}
	
	public void GameEvent(){
		switch(GameIndex){
		case -1:
			//GameStart!
			break;
		case 0:
			showTalk(TalkStr[GameIndex],0);
			GameIndex++;
			break;
		case 1:
			if (TalkStr[GameIndex].endsWith("-")){
				GameIndex++;
				GameEvent();
			}else{
				showTalk(TalkStr[GameIndex],0);
				GameIndex++;
			}
			
			break;
		case 2:
			if (TalkStr[GameIndex].endsWith("-")){
				GameIndex++;
				GameEvent();
			}else{
				showTalk(TalkStr[GameIndex],0);
				GameIndex++;
			}
			break;
		case 3:
			if (TalkStr[GameIndex].endsWith("-")){
				GameIndex++;
				GameEvent();	
			}else{
				showTalk(TalkStr[GameIndex],0);
				GameIndex++;
			}
			break;
		case 4:
			if (TalkStr[GameIndex].endsWith("-")){
				GameIndex++;
				GameEvent();
			}else{
				showTalk(TalkStr[GameIndex],0);
				GameIndex++;
			}
			break;
		case 5:
			GameButton.setVisible(true);
			GameButton.setEnabled(true);
			TimeLabel.setVisible(true);
			GameLabel.setVisible(true);
			GoalSprite.setVisible(true);
			GoalLabel.setVisible(true);
			GameLabel.setText("闭上眼睛");
			TimeLabel.setText("00:00");
			GameIndex++;
			break;
		case 6:
			BlackBg.setVisible(true);
			TimeLabel.setVisible(true);
			GameLabel.setText("睁开眼睛");
			isPlay=true;
			playTime=0;
			TimeLabel.setText(String.format("%2d:%2d",playTime/100,playTime%100));
			Timing = new TargetSelector(this, "updateTime(float)", new Object[] { 0f,});
			TimingTimer = new Timer(Timing, 0.01f);
        	Scheduler.getInstance().schedule(TimingTimer);
			FadeTo fadeBg = (FadeTo)FadeTo.make(2f,0,255,true).autoRelease();
			BlackBg.runAction(fadeBg);
			FadeTo fadeLabel = (FadeTo)FadeTo.make(4f,255,0,true).autoRelease();
			if (isHide) TimeLabel.runAction(fadeLabel);
			GameIndex++;
			break;
		case 7:
			showTalk("……………………………………",0);
			GameIndex++;
			break;
		case 8:
			if (GameResult==3 || GameResult==2) {
				Shake girlShake=Shake.make(1f, DP(10));
				super.getGirl().runAction(girlShake);
			}
			showTalk(GirlName + "："+ GameResultStr[GameResult],0);
			GameIndex++;
			break;
		case 9:
			((MainActivity)Director.getInstance().getContext()).actAD();
			isGameEnd=true;
			BlackBg.setVisible(true);
			FadeTo fadeBg2 = (FadeTo)FadeTo.make(.5f,0,200,true).autoRelease();
			if (GameResult==2) Clear.setVisible(true);
			if (GameResult==3) PClear.setVisible(true);
			GoalLabel.setText(String.format("完美过关时间:\n%02d:%02d-%02d:%02d",GameTime/100,GameTime%100,(GameTime+deltaTime)/100,(GameTime+deltaTime)%100));
			BlackBg.runAction(fadeBg2);
			TimeLabel.setVisible(true);
			if (((MainActivity)Director.getInstance().getContext()).getCg(GirlPic, 5)==true) {
				TimeLabel.setText("点击屏幕重来~");
			}else{
				TimeLabel.setText("全CG未解锁\n点击屏幕重来~");
			}
			GameButton.setEnabled(true);
			GameButton.setVisible(true);
			GameLabel.setVisible(true);
			GameLabel.setText("返回选择画面");
			GameIndex++;
			break;
		}
		
	}
	
	/**
	 * 更新事件
	 * @param delta
	 */
	public void updateTime(float delta){
		if (isPlay) playTime++;
		if (playTime>=100)
			TimeLabel.setText(String.format("%02d:%02d",playTime/100,playTime%100));
		else
			TimeLabel.setText(String.format("%02d:%02d",playTime/100,playTime%100));
	}
	
	/**
	 * 按钮点击
	 */
	public void onChoseButtonClicked(int delta){
		if (isGameEnd==false){
				if (isPlay){
					isPlay=false;
					GameButton.setVisible(false);
					GameButton.setEnabled(false);
					GameLabel.setVisible(false);
					if (TimingTimer!=null) Scheduler.getInstance().unschedule(TimingTimer);
						FadeTo fadeLabel = (FadeTo)FadeTo.make(0.01f,0,255,true).autoRelease();
						if (isHide) TimeLabel.runAction(fadeLabel);
						Blink blink=Blink.make(1f,5);
						TimeLabel.runAction(blink);
						FadeTo fadeButton = (FadeTo)FadeTo.make(5f,255,0,true).autoRelease();
						BlackBg.runAction(fadeButton);
			
						if (GameTime<=playTime && playTime<=GameTime+deltaTime){
							GameResult=3;
							setGirlPic("girl"+String.valueOf(GirlPic)+"_3");
						}else if(GameTime<=playTime && playTime<=GameTime+100){
							GameResult=2;
							setGirlPic("girl"+String.valueOf(GirlPic)+"_2");
						}else if (playTime<GameTime){
							GameResult=1;
							setGirlPic("girl"+String.valueOf(GirlPic)+"_1");
						}else if(GameTime+100<playTime){
							GameResult=4;
							setGirlPic("girl"+String.valueOf(GirlPic)+"_4");
						}
						((MainActivity)Director.getInstance().getContext()).UnlockCg(GirlPic,GameResult);
						scheduleOnce(new TargetSelector(this, "updateGameEvent(float)", new Object[] { 0f }), 2f);
				}else{
					GameEvent();
				}
		}else{
			Director.getInstance().popScene();
		}
	}
	
	public void updateGameEvent(float delta){
		TimeLabel.setVisible(false);
		GameEvent();
	}
	
	@Override
	public void TalkEnd() {
		GameEvent();
	}

	@Override
	public void TouchBegin(MotionEvent event) {
		if (isGameEnd==true){
			initGame();
			initView();
			GameEvent();
		}
		
	}

	@Override
	public void ChooseEnd(int index) {
		// TODO Auto-generated method stub
		
	}

}
