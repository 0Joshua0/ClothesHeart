package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageDemo extends ChooseScene{

	public StageDemo() {
		super(1, 1, 1,new String[]{"","��������1","��������2","��������3"}, "324");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("����", 1, "home", 
							new String[] { 
							"�ң����ӣ����ǳ�ȥ���~", 
							"���ӣ���ѽ������ôһ�µ������������ˣ����ˣ�������۾����һ��·���",
							"�ң����������·�����Լ6��ɡ�6���ץסʱ��Ŷ��",
							"���ӣ������ǆ���ʲô��������۾���", 
							"-" },
							new String[] { "", 
							"��������˵��Ҫ�����۾����", 
							"�������쵰��",
							"��åT_T",
							"����~���ǳ�ȥ��ɣ�" }, 
							600,10,false));
	}

}
