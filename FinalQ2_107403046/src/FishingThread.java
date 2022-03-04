import java.security.SecureRandom;

public class FishingThread implements Runnable{
	private static final SecureRandom generator = new SecureRandom();
	private Fishing fishing;
	private int flag=0;		//�P�_���k
	private int counttime=0;
	private boolean exe=true;     //�P�_�O�_�w�Q�M��
	private int[] fishX=new int[50];
	private int[] fishY=new int[50];
	private int fishcount=0;

	
	public FishingThread(Fishing f){
		fishing=f;
	}
	
	public void close() {exe = false;}
	public boolean getExe() {return exe;}
	
	public void run() {
		int y=fishing.getY();
		while(exe) {	
			if(counttime==5) {
				if(flag==0)
					flag=1;
				else if(flag==1)
					flag=0;
				
				counttime=0;
			}
			
			try 
			{		
				Thread.sleep(generator.nextInt(500));

				if(flag==0) {
					y += generator.nextInt(5);
					fishing.setY(y);
					counttime+=1;
				}
				else if(flag==1) {
					y -= generator.nextInt(5);
					fishing.setY(y);
					counttime++;
				}
			}
			catch (InterruptedException exception) 
	         {
	            Thread.currentThread().interrupt(); 
	         } 
		}
	}

}