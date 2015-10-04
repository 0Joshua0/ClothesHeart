package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageFitRoom extends ChooseScene{

	public StageFitRoom() {
		super(7, 8, 9,new String[]{"","亜栗","絵梨沙","琴美"}, "fitroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("亜栗", 7, "fitroom", 
							new String[] { 
							"我：亜栗，这么早，来体育馆锻炼呀~", 
							"亜栗：是啊是啊！我要先换个衣服，你下闭上眼睛。",
							"我：5秒吧，亜栗比较简单。",
							"亜栗：闭上眼睛啦！", 
							"我：好的！" },
							new String[] { "", 
							"嗯哼？你想干什么？！", 
							"噗，等不及啦？干嘛睁眼啊！",
							"啊喂，你这样真的好嘛？",
							"嗯，这样方便运动多了！" }, 
							500,5,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("絵梨沙 ", 8, "fitroom", 
							new String[] { 
							"我：絵梨沙 ，你也是刚刚COS完吗，这是衣服好萌啊~", 
							"絵梨沙 ：谢谢，我马上要练武术，换个衣服，你快闭上眼睛",
							"我：一脱一套的事，大约也是5秒吧。",
							"絵梨沙 ：快点闭上眼睛啦！", 
							"我：我知道啦！" },
							new String[] { "", 
							"想什么呢！", 
							"阿、就剩两件衣服了、、",
							"快点闭眼！我还没穿好！",
							"哈哈，怎么样！帅吧！" }, 
							500,10,true));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("琴美", 9, "fitroom", 
							new String[] { 
							"我：琴美，一大早穿女仆装来学校啊、、", 
							"琴美：啊呀，你不要说啦！我马上换衣服。",
							"我：快换吧！我看着你换HHH",
							"琴美：不要啦！闭上眼睛。", 
							"我：女仆装比较复杂，大约10秒。" },
							new String[] { "", 
							"还没换好呢！再等等！", 
							"看、、看什么看！",
							"噗，赶快闭上眼睛啦！",
							"换好啦！【噗，这样和没换的区别在哪里。" }, 
							1000,5,true));
	}
	

}
