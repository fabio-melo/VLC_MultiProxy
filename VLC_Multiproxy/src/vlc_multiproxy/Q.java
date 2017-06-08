package vlc_multiproxy;

class Q
{
	int num;
	boolean valueSet = false;
	
	public synchronized void put(int num)
	{
		while(valueSet)
		{
			try { wait(); } catch(Exception e){}
		}
		System.out.println("Put: " + num);
		this.num = num;
		valueSet = true;
		notify();
	}
	public synchronized void get()
	{
		while(!valueSet)
		{
			try { wait(); } catch(Exception e){}
		}
		System.out.println("Get: " + num);
		valueSet = false;
		notify();
	}
}


class Produtor implements Runnable
{
	Q q;

	public Produtor(Q q)
	{
		this.q = q;
		Thread t = new Thread(this, "Produtor");
		t.start();
	}
	
	public void run()
	{
		int i = 0;
		while(true)
		{
			q.put(i++);
			try { Thread.sleep(1000); } catch(Exception e){}
	}
}
	

	
class Consumidor implements Runnable
{
	Q q;

	public Consumidor(Q q)
	{
		this.q = q;
		Thread t = new Thread(this, "Consumidor");
		t.start();
	}
	
	public void run()
	{
		while(true)
		{
			q.get();
			try { Thread.sleep(1000); } catch(Exception e){}
	}
}

public class Threads
{
	public void main(String[] args)
	{
		Q q = new Q();
		new Produtor(q);
		new Consumidor(q);
	}

}}}
