package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageSwimming  extends ChooseScene{

	public StageSwimming() {
		super(10, 11, 12,new String[]{"","千寻","真弓","雪奈"}, "swimmingroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("千寻", 10, "swimmingroom", 
							new String[] { 
							"我：千寻和服真美！", 
							"千寻：谢谢拉，不过我是来游泳的！",
							"我：嗯，话说你这样怎么下水、、",
							"千寻：闭上眼睛，我马上换！", 
							"我：千寻的和服，大约8秒的样子。" },
							new String[] { "", 
							"别睁开眼睛嘛！", 
							"你、、你真是太心急了！",
							"阿，快被过去！",
							"嗯哼？泳衣也很不错吧！" }, 
							800,10,true));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("真弓", 11, "swimmingroom", 
							new String[] { 
							"我：真弓，外面很冷吧！", 
							"真弓：嗯，这样呢！",
							"我：你赶快换衣服吧！游泳馆马上就开了！",
							"真弓：可是更衣室还没开啊、你先闭上眼睛！", 
							"我：这套衣服好像很复杂的样子、大约12秒吧。" },
							new String[] { "", 
							"诶、不是说不要睁开眼睛的嘛！", 
							"坏死了！",
							"吐艳！！！",
							"好了~我们出去玩吧！别游泳了！" }, 
							1200,5,true));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("雪奈", 12, "swimmingroom", 
							new String[] { 
							"我：雪奈，你也是来游泳的吧！", 
							"雪奈：对啊，你闭上眼睛，我换下衣服。",
							"我：雪奈的话，大约7秒吧",
							"雪奈：闭上眼睛啦！", 
							"我：嗯~" },
							new String[] { "", 
							"忽然睁眼、真是坏。", 
							"你这时候睁眼，是让我勾引你吗~",
							"喂喂、我脱掉一件都不剩了，你就睁眼吗？",
							"换好啦！让我们来做愉快的啪啪啪运动！" }, 
							700,5,true));
	}

}
