package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageClassRoom extends ChooseScene{

	public StageClassRoom() {
		super(4, 5, 6,new String[]{"","十六夜","千月","星野"}, "classroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("十六夜", 4, "classroom", 
							new String[] { 
							"我：十六夜，CosPlay结束啦？", 
							"十六夜：嗯，正好教室没有人，等我换好衣服一起出去玩！",
							"我：嗯，我们去看电影吧！",
							"十六夜：你要闭上眼睛我才能换衣服啊，笨！", 
							"我：十六夜这身衣服大约5秒吧！" },
							new String[] { "", 
							"别，别睁开眼睛啊。", 
							"讨厌，都被你看到了！",
							"啊啊、、、、、、好讨厌！",
							"走~" }, 
							500,10,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("千月", 5, "classroom", 
							new String[] { 
							"我：千月，你穿唐装好靓啊！", 
							"千月：谢谢~",
							"我：你赶快换上正常衣服吧。马上还有课呢！",
							"千月：阿，好、、你、闭上眼睛。", 
							"我：千月。嗯，大约11秒吧." },
							new String[] { "", 
							"唔、、、、", 
							"呃、、、、",
							"啊、、、、",
							"嗯哼~" }, 
							1100,5,false));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("星野", 6, "classroom", 
							new String[] { 
							"我：星野，兔女郎好性感！", 
							"星野：哪有这么夸人的嘛！你闭上眼睛，我要换回来了。",
							"我：嗯，好吧、【星野这身衣服大约7秒",
							"星野：千万别睁开哦~", 
							"我：好了，不会的啦！" },
							new String[] { "", 
							"你、讨厌！", 
							"阿，只剩一件内衣，被别人看到了、、",
							"啊啊、别看，一件衣服都没有啦！",
							"怎么样，这件衣服是不是也很性感~" }, 
							700,15,true));
	}

}
