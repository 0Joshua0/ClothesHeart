package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageClassRoom extends ChooseScene{

	public StageClassRoom() {
		super(4, 5, 6,new String[]{"","ʮ��ҹ","ǧ��","��Ұ"}, "classroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("ʮ��ҹ", 4, "classroom", 
							new String[] { 
							"�ң�ʮ��ҹ��CosPlay��������", 
							"ʮ��ҹ���ţ����ý���û���ˣ����һ����·�һ���ȥ�棡",
							"�ң��ţ�����ȥ����Ӱ�ɣ�",
							"ʮ��ҹ����Ҫ�����۾��Ҳ��ܻ��·���������", 
							"�ң�ʮ��ҹ�����·���Լ5��ɣ�" },
							new String[] { "", 
							"�𣬱������۾�����", 
							"���ᣬ�����㿴���ˣ�",
							"���������������������ᣡ",
							"��~" }, 
							500,10,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("ǧ��", 5, "classroom", 
							new String[] { 
							"�ң�ǧ�£��㴩��װ��������", 
							"ǧ�£�лл~",
							"�ң���Ͽ컻�������·��ɡ����ϻ��п��أ�",
							"ǧ�£������á����㡢�����۾���", 
							"�ң�ǧ�¡��ţ���Լ11���." },
							new String[] { "", 
							"��������", 
							"����������",
							"����������",
							"�ź�~" }, 
							1100,5,false));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("��Ұ", 6, "classroom", 
							new String[] { 
							"�ң���Ұ����Ů�ɺ��ԸУ�", 
							"��Ұ��������ô���˵��������۾�����Ҫ�������ˡ�",
							"�ң��ţ��ðɡ�����Ұ�����·���Լ7��",
							"��Ұ��ǧ�������Ŷ~", 
							"�ң����ˣ����������" },
							new String[] { "", 
							"�㡢���ᣡ", 
							"����ֻʣһ�����£������˿����ˡ���",
							"�������𿴣�һ���·���û������",
							"��ô��������·��ǲ���Ҳ���Ը�~" }, 
							700,15,true));
	}

}
