package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageHome extends ChooseScene {

	public StageHome() {
		super(1, 2, 3, new String[] { "", "若叶", "智", "美蕙子" }, "home");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected = super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("若叶", 1, "home", 
							new String[] { 
							"我：若叶，我们出去玩吧~", 
							"若叶：诶呀，你怎么一下到我卧室里来了！算了，你闭上眼睛，我换衣服。",
							"我：若叶这身衣服，大约6秒吧。6秒后，抓住时机哦！",
							"若叶：你在那啰嗦什么，快比上眼睛！", 
							"我：诶、好吧！" },
							new String[] { "", 
							"诶、不是说不要睁开眼睛的嘛！", 
							"啊啊！混蛋！",
							"流氓T_T",
							"好了~我们出去玩吧！" }, 
							600,20,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("智", 2, "home", 
							new String[] { 
							"我：智，我们出去玩吧~", 
							"智：我、我弄错时间啦！我马上换衣服、你、闭上眼睛！",
							"我：智这身衣服好像蛮复杂的，大约8秒吧。",
							"智：快、快闭上眼睛啊！", 
							"我：嗯、、、" },
							new String[] { "", 
							"干嘛睁开眼睛！", 
							"不要看，不要看！",
							"断子绝孙脚！",
							"OK，快点出去吧！" }, 
							800,20,false));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("美蕙子", 3, "home", 
							new String[] { 
							"我：美蕙子，我们出去玩吧~", 
							"美蕙子：等我换个衣服！你闭上眼睛！",
							"我：美蕙子这身衣服，大约9秒吧。9秒后，一定能看到她的内内的！",
							"美蕙子：不要这样看在人家，快闭上眼睛~", 
							"我：……" },
							new String[] { "", 
							"诶、闭上眼睛啦~", 
							"诶、怎么样姐姐的身材~",
							"你！你看到了不该看的东西！",
							"Let's Go!" }, 
							900,10,false));
	}

}
