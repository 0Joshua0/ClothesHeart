package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageHome extends ChooseScene {

	public StageHome() {
		super(1, 2, 3, new String[] { "", "��Ҷ", "��", "��ޥ��" }, "home");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected = super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("��Ҷ", 1, "home", 
							new String[] { 
							"�ң���Ҷ�����ǳ�ȥ���~", 
							"��Ҷ����ѽ������ôһ�µ������������ˣ����ˣ�������۾����һ��·���",
							"�ң���Ҷ�����·�����Լ6��ɡ�6���ץסʱ��Ŷ��",
							"��Ҷ�������ǆ���ʲô��������۾���", 
							"�ң������ðɣ�" },
							new String[] { "", 
							"��������˵��Ҫ�����۾����", 
							"�������쵰��",
							"��åT_T",
							"����~���ǳ�ȥ��ɣ�" }, 
							600,20,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("��", 2, "home", 
							new String[] { 
							"�ң��ǣ����ǳ�ȥ���~", 
							"�ǣ��ҡ���Ū��ʱ�����������ϻ��·����㡢�����۾���",
							"�ң��������·����������ӵģ���Լ8��ɡ�",
							"�ǣ��졢������۾�����", 
							"�ң��š�����" },
							new String[] { "", 
							"���������۾���", 
							"��Ҫ������Ҫ����",
							"���Ӿ���ţ�",
							"OK������ȥ�ɣ�" }, 
							800,20,false));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("��ޥ��", 3, "home", 
							new String[] { 
							"�ң���ޥ�ӣ����ǳ�ȥ���~", 
							"��ޥ�ӣ����һ����·���������۾���",
							"�ң���ޥ�������·�����Լ9��ɡ�9���һ���ܿ����������ڵģ�",
							"��ޥ�ӣ���Ҫ���������˼ң�������۾�~", 
							"�ң�����" },
							new String[] { "", 
							"���������۾���~", 
							"������ô���������~",
							"�㣡�㿴���˲��ÿ��Ķ�����",
							"Let's Go!" }, 
							900,10,false));
	}

}
