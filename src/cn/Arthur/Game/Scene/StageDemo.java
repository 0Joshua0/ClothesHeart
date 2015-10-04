package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageDemo extends ChooseScene{

	public StageDemo() {
		super(1, 1, 1,new String[]{"","测试妹子1","测试妹子2","测试妹子3"}, "324");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("由子", 1, "home", 
							new String[] { 
							"我：由子，我们出去玩吧~", 
							"由子：诶呀，你怎么一下到我卧室里来了！算了，你闭上眼睛，我换衣服。",
							"我：由子这身衣服，大约6秒吧。6秒后，抓住时机哦！",
							"由子：你在那啰嗦什么，快比上眼睛！", 
							"-" },
							new String[] { "", 
							"诶、不是说不要睁开眼睛的嘛！", 
							"啊啊！混蛋！",
							"流氓T_T",
							"好了~我们出去玩吧！" }, 
							600,10,false));
	}

}
