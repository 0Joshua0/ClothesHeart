package cn.Arthur.Game.Main;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import cn.Arthur.Game.Safe.*;
import cn.Arthur.Game.Scene.*;

import com.wiyun.engine.nodes.Director;
import com.wiyun.engine.nodes.Director.IDirectorLifecycleListener;
import com.wiyun.engine.nodes.Scene;
import com.wiyun.engine.opengl.WYGLSurfaceView;
import com.wiyun.engine.sound.AudioManager;
import com.wiyun.engine.utils.ResourceDecoder;

/**
 * 主程�? * 
 * @author Arthur
 * 
 */
public class MainActivity extends Activity implements
		IDirectorLifecycleListener{
	static {
		/*
		 * 杩欓噷鎮ㄥ彲浠ュ幓鎺変綘涓嶈鐨勫簱, 浣嗘槸wiskia, xml2鍜寃iengine鏄竴�?氳淇濈�?���?		 */
		System.loadLibrary("wiskia");// *
		System.loadLibrary("xml2");// *
		System.loadLibrary("wiengine");// *
		System.loadLibrary("wiengine_binding");
		//System.loadLibrary("lua");
		//System.loadLibrary("chipmunk");
		//System.loadLibrary("box2d");
		System.loadLibrary("wisound");
	}

	protected WYGLSurfaceView mGLView;

	private boolean mStarted;

	public boolean isFirstStart;
	public boolean GirlCg[][]=new boolean[101][11];
	public boolean Stage[]=new boolean[101];
	public Scene StartScene;
	public final String APPID="8f7c5d6b17e28156";
	public final String APPKEY="56fd70650df2ecd3";
	public final Boolean isTest=false;
	private int GameTimes=0;
	
	/* 游戏存档 */
	public SharedPreferences GameSave;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		initAD();
		
		if (android.os.Build.VERSION.SDK_INT>=18 || android.os.Build.VERSION.SDK_INT<=8){
			Intent it = new Intent(MainActivity.this, ACDsee.class);
			startActivity(it);
			this.finish();
		}else{
			mGLView = new WYGLSurfaceView(this);
			setContentView(mGLView);

			GameSave = getSharedPreferences("cn.Arthur.Game.ClothesHeart", 0);
		
			LoadGame();
			
			
		
			setVolumeControlStream(android.media.AudioManager.STREAM_MUSIC);

			Director.getInstance().addLifecycleListener(this);

			Director.getInstance().setDisplayFPS(false);
		}
		
	}
	
	
	/**
	 * 初始化广告
	 */
	public void initAD(){
		
		AdManager.getInstance(this).init(APPID, APPKEY, isTest);
		//预载入广告
		SpotManager.getInstance(this).loadSpotAds();
		//超时时间
		SpotManager.getInstance(this).setSpotTimeout(5000);
		//自动关闭
		SpotManager.getInstance(this).setAutoCloseSpot(true);
		SpotManager.getInstance(this).setCloseTime(5000);
	}
	
	/**
	 * 激活插屏
	 */
	public void actAD(){
		GameTimes++;
		if (GameTimes%3==0){
			if (SpotManager.getInstance(this).checkLoadComplete()){
				SpotManager.getInstance(this).showSpotAds(this);
			}
		}
	}
	
    //返回键
	public void onBackPressed() { 
		ExitDialog();
	} 

	@Override
	protected void onStop() {
	    //如果不调用此方法，则按home键的时候会出现图标无法显示的情况。
	    SpotManager.getInstance(this).disMiss(false);

	    super.onStop();
	}
	
	
	//退出框
    public void ExitDialog(){
		new AlertDialog.Builder(MainActivity.this)
		.setIcon(R.drawable.icon)
		.setTitle("确认退出吗？T_T")
    	.setMessage("点击确认退出。")
    	.setPositiveButton("确定", new DialogInterface.OnClickListener() {              
    	    public void onClick(DialogInterface dialog, int which) {
    	    	finish();
    	    	dialog.dismiss();
    	    }
    	})
    	.setNegativeButton("取消", new DialogInterface.OnClickListener() {              
    	    public void onClick(DialogInterface dialog, int which) {
    	    	dialog.dismiss();
    	    }
    	})
    	.show();
	}

	@Override
	public void onPause() {
		super.onPause();

		Director.getInstance().pause();
	}

	@Override
	public void onResume() {
		super.onResume();

		Director.getInstance().resume();
	}

	@Override
	public void onDestroy() {
		Director.getInstance().end();
		SpotManager.getInstance(this).unregisterSceenReceiver();
		super.onDestroy();
	}

	@Override
	public void onDirectorEnded() {
	}

	@Override
	public void onDirectorPaused() {
	}

	@Override
	public void onDirectorResumed() {
	}

	@Override
	public void onDirectorScreenCaptured(String path) {
	}

	@Override
	public void onSurfaceChanged(int w, int h) {
		boolean landscape = getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
		if (!mStarted && (landscape && w >= h || !landscape && h >= w)) {
			mStarted = true;
			AudioManager.playBackgroundMusic(R.raw.bg_mp3, AudioManager.FORMAT_MP3, -1);
			Director.getInstance().runWithScene(new FirstSceneLoading());
		}
	}
	
	/**
	 * 开始游戏
	 */
	public void RunFirstScene(){
		StartScene=new StartScene();
		Director.getInstance().replaceScene(StartScene);
	}

	@Override
	public void onSurfaceCreated() {
	}

	@Override
	public void onSurfaceDestroyed() {

	}
	
	/**
	 * 解锁CG
	 * @param i
	 * @param j
	 */
	public void UnlockCg(int i,int j){
		if (GirlCg[i][j]) return;
		GirlCg[i][j]=true;
		GameSave.edit().putBoolean(String.format("Cg_%03d_%02d",i,j), GirlCg[i][j]).commit();
		GirlCg[i][5]=true;
		for (int k=1;k<=4;k++)	GirlCg[i][5]=GirlCg[i][k]&&GirlCg[i][5];
		GameSave.edit().putBoolean(String.format("Cg_%03d_%02d",i,5), GirlCg[i][5]).commit();
	}
	
	/**
	 * 获得解锁成就
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean getCg(int i,int j){
		return GirlCg[i][j];
	}
	
	public void refreshStart(){
		if (StartScene!=null)((cn.Arthur.Game.Scene.StartScene) StartScene).RefreshButton();
	}
	
	/**
	 * 解锁场景
	 * @param index
	 * @return
	 */
	public boolean unLockStage(int index){
		if (index==1){
			Stage[1]=true;
			GameSave.edit().putBoolean(String.format("Stage_%03d",index), Stage[index]).commit();
		}else{
			Stage[index]=true;
			for (int i=(index-1)*3-2;i<=(index-1)*3;i++){
				Stage[index]=Stage[index]&&GirlCg[i][5];
			}
			GameSave.edit().putBoolean(String.format("Stage_%03d",index), Stage[index]).commit();
		}
		return Stage[index];
	}
	
	
	/**
	 * 获得解锁舞台
	 * @param index
	 * @return
	 */
	public boolean getStage(int index){
		return Stage[index];
	}
	
	public void SaveGame() {
		GameSave.edit().putBoolean("isFirstStart", isFirstStart).commit();
		for (int i=1;i<=100;i++){
			for (int j=1;j<=10;j++){
				GameSave.edit().putBoolean(String.format("Cg_%03d_%02d",i,j), GirlCg[i][j]).commit();
			}
		}
	}

	public void LoadGame() {
		isFirstStart = GameSave.getBoolean("isFirstStart", true);
		for (int i=1;i<=100;i++){
			Stage[i]=GameSave.getBoolean(String.format("Stage_%03d",i), false);
			for (int j=1;j<=10;j++){
				 GirlCg[i][j]=GameSave.getBoolean(String.format("Cg_%03d_%02d",i,j),false);
			}
		}
	}

}
