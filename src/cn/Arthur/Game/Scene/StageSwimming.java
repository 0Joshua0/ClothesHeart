package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageSwimming  extends ChooseScene{

	public StageSwimming() {
		super(10, 11, 12,new String[]{"","ǧѰ","�湭","ѩ��"}, "swimmingroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("ǧѰ", 10, "swimmingroom", 
							new String[] { 
							"�ң�ǧѰ�ͷ�������", 
							"ǧѰ��лл����������������Ӿ�ģ�",
							"�ң��ţ���˵��������ô��ˮ����",
							"ǧѰ�������۾��������ϻ���", 
							"�ң�ǧѰ�ĺͷ�����Լ8������ӡ�" },
							new String[] { "", 
							"�������۾��", 
							"�㡢��������̫�ļ��ˣ�",
							"�����챻��ȥ��",
							"�źߣ�Ӿ��Ҳ�ܲ���ɣ�" }, 
							800,10,true));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("�湭", 11, "swimmingroom", 
							new String[] { 
							"�ң��湭���������ɣ�", 
							"�湭���ţ������أ�",
							"�ң���Ͽ컻�·��ɣ���Ӿ�����ϾͿ��ˣ�",
							"�湭�����Ǹ����һ�û���������ȱ����۾���", 
							"�ң������·�����ܸ��ӵ����ӡ���Լ12��ɡ�" },
							new String[] { "", 
							"��������˵��Ҫ�����۾����", 
							"�����ˣ�",
							"���ޣ�����",
							"����~���ǳ�ȥ��ɣ�����Ӿ�ˣ�" }, 
							1200,5,true));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("ѩ��", 12, "swimmingroom", 
							new String[] { 
							"�ң�ѩ�Σ���Ҳ������Ӿ�İɣ�", 
							"ѩ�Σ��԰���������۾����һ����·���",
							"�ң�ѩ�εĻ�����Լ7���",
							"ѩ�Σ������۾�����", 
							"�ң���~" },
							new String[] { "", 
							"��Ȼ���ۡ����ǻ���", 
							"����ʱ�����ۣ������ҹ�������~",
							"ιι�����ѵ�һ������ʣ�ˣ����������",
							"����������������������žžž�˶���" }, 
							700,5,true));
	}

}
