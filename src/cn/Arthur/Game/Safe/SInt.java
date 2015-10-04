package cn.Arthur.Game.Safe;

public class SInt {
	boolean isLessThanZero=false;
	char num[]=new char[10];
	int Max=-1,Min=1;
	public SInt(int x){
		Max=-1;
		Min=1;
		saveInt(x);
	}
	
	public SInt(int x,int min,int max){
		Max=max;
		Min=min;
		saveInt(x);
	}

	public void saveInt(int x){
		if (Max>=Min){
			if (x>Max) x=Max;
			if (x<Min) x=Min;
		}
		if (x<0){
			isLessThanZero=true;
			x*=-1;
		}
		int nums=0;
		while(x!=0){
			nums++;
			num[nums]=(char)(x % 10+'0');
			x/=10;
		}
		for (int i=nums+1;i<=9;i++){
			num[i]='0';
		}
	}
	
	public String toString(){
		String ans="";
		boolean isStart=false;
		for (int i=9;i>=1;i--){
			if (num[i]!='0') isStart=true;
			if (isStart) ans=ans+num[i];
		}
		if (isLessThanZero) ans='-'+ans;
		return ans;
	}
	
	public int toInt(){
		int ans=0;
		for (int i=9;i>=1;i++){
			ans*=10+(num[i]-'0');
		}
		if (isLessThanZero) ans*=-1;
		return ans;
	}
	
	public int plus(int x){
		int ans=this.toInt();
		ans+=x;
		saveInt(ans);
		return ans;
	}
	
	public int minus(int x){
		int ans=this.toInt();
		ans-=x;
		saveInt(ans);
		return ans;
	}
	
	public int time(int x){
		int ans=this.toInt();
		ans*=x;
		saveInt(ans);
		return ans;
	}
	
	public int div(int x){
		int ans=this.toInt();
		ans/=x;
		saveInt(ans);
		return ans;
	}
}
