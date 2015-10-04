package cn.Arthur.Game.Scene;

import com.wiyun.engine.nodes.Director;

public class StageFitRoom extends ChooseScene{

	public StageFitRoom() {
		super(7, 8, 9,new String[]{"","����","�}��ɳ","����"}, "fitroom");
	}

	@Override
	public void onPlayButtonClicked(int delta) {
		int selected=super.getSelect();
		if (selected == 1)
			Director.getInstance().pushScene(
					new PlayScene("����", 7, "fitroom", 
							new String[] { 
							"�ң���������ô�磬�������ݶ���ѽ~", 
							"�������ǰ��ǰ�����Ҫ�Ȼ����·������±����۾���",
							"�ң�5��ɣ������Ƚϼ򵥡�",
							"�����������۾�����", 
							"�ң��õģ�" },
							new String[] { "", 
							"�źߣ������ʲô����", 
							"�ۣ��Ȳ��������������۰���",
							"��ι����������ĺ��",
							"�ţ����������˶����ˣ�" }, 
							500,5,false));
		if (selected == 2)
			Director.getInstance().pushScene(
					new PlayScene("�}��ɳ ", 8, "fitroom", 
							new String[] { 
							"�ң��}��ɳ ����Ҳ�Ǹո�COS���������·����Ȱ�~", 
							"�}��ɳ ��лл��������Ҫ�������������·����������۾�",
							"�ң�һ��һ�׵��£���ԼҲ��5��ɡ�",
							"�}��ɳ ���������۾�����", 
							"�ң���֪������" },
							new String[] { "", 
							"��ʲô�أ�", 
							"������ʣ�����·��ˡ���",
							"�����ۣ��һ�û���ã�",
							"��������ô����˧�ɣ�" }, 
							500,10,true));
		if (selected == 3)
			Director.getInstance().pushScene(
					new PlayScene("����", 9, "fitroom", 
							new String[] { 
							"�ң�������һ���紩Ů��װ��ѧУ������", 
							"��������ѽ���㲻Ҫ˵���������ϻ��·���",
							"�ң��컻�ɣ��ҿ����㻻HHH",
							"��������Ҫ���������۾���", 
							"�ң�Ů��װ�Ƚϸ��ӣ���Լ10�롣" },
							new String[] { "", 
							"��û�����أ��ٵȵȣ�", 
							"��������ʲô����",
							"�ۣ��Ͽ�����۾�����",
							"�����������ۣ�������û�������������" }, 
							1000,5,true));
	}
	

}
