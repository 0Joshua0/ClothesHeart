package cn.Arthur.Game.Main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.youmi.android.AdManager;
import net.youmi.android.spot.SpotManager;

import cn.Arthur.Game.Main.gallery_view.ImageAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery.LayoutParams;

/**
 * ****************************************************************
 * 文件名称	: ImageActivity.java
 * 创建时间	: 2010-11-2 下午05:30:04
 * 文件描述	: 图片浏览器
 *****************************************************************
 */
public class ACDsee extends Activity implements
		AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	/** Called when the activity is first created. */

	/**
	 * 手机设备中图像列表
	 */
	private List<String> ImageList;
	private boolean isUnlock=false;
	private ImageSwitcher mSwitcher;
	
	public final String APPID="8f7c5d6b17e28156";
	public final String APPKEY="56fd70650df2ecd3";
	public int ReadTimes=0;
	public TextView title;
	/* 游戏存档 */
	public SharedPreferences GameSave;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);
		title=(TextView) findViewById(R.id.logo);
		
		
		GameSave = getSharedPreferences("cn.Arthur.Game.ClothesHeartCG", 0);
		ReadTimes = GameSave.getInt("ReadTimes", 0);
		if (ReadTimes>1000) isUnlock=true;
		ImageList = getImagesFromSD();
		
		/**
		 * xml获取Switcher 
		 */
		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		mSwitcher.setFactory(this);

		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.slide_in_left));

		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.slide_out_right));

		/**
		 * xml获取Gallery
		 */
		Gallery g = (Gallery) findViewById(R.id.mygallery);

		
		if(ImageList == null || ImageList.size() == 0)
			return;
		
		/**
		 * 创建适配器，注册适配器
		 */
		g.setAdapter(new ImageAdapter(this, ImageList));

		g.setOnItemSelectedListener(this);

		/**
		 * 点击事件监听器
		 */
		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
			}
		});
		SpotManager.getInstance(this).showSpotAds(this);
	}

	/**
	 * @return
	 */
	private List<String> getImagesFromSD() {
		List<String> imageList = new ArrayList<String>();
		
		try {
			String fileName[]=getAssets().list("girl");
			 for (String name : fileName) {
				 if (isUnlock==false && (name.contains("final_cg")||name.contains("2.png")||name.contains("3.png")||name.contains("5.png"))){
					 
				 }else{
					 imageList.add("girl/"+name);
				 }
				   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageList;
	}

	/**
	 * @param fName
	 * @return
	 */
	private boolean isImageFile(String fName) {
		boolean re;
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/**
		 * 依据文件扩展名判断是否为图像文件
		 */
		if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			re = true;
		} else {
			re = false;
		}
		return re;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		/**
		 * 获取当前要显示的Image的路径
		 */
		String photoURL = ImageList.get(position);
		Log.i("A", String.valueOf(position));
		/**
		 * 设置当前要显示的Image的路径	 
		 */
		try {
			InputStream inputStream =getAssets().open(photoURL);
			Bitmap originalImage=BitmapFactory.decodeStream(inputStream);
			mSwitcher.setImageDrawable(new BitmapDrawable(originalImage));
			inputStream.close();
			ReadTimes++;
			GameSave.edit().putInt("ReadTimes", ReadTimes).commit();
			title.setText(String.format("系统版本不支持，自动进入CG一览。(%d次浏览后解锁全部CG)", 1000-ReadTimes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isUnlock==false&&ReadTimes>1000){
			Toast.makeText(ACDsee.this,"全部Cg已解锁，重启即可查看！", Toast.LENGTH_SHORT).show();
			isUnlock=true;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public View makeView() {

		ImageView i = new ImageView(this);

		//i.setBackgroundColor(0xFF000000);

		i.setScaleType(ImageView.ScaleType.FIT_CENTER );

		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return i;
	}
}