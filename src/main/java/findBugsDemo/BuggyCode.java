package findBugsDemo;

public class BuggyCode {

	private String str = null;

	public BuggyCode(){
		this.str = "test";
	}
	
	private String getValue(){
		return this.str;
	}
	
	public boolean equals(Object obj) {	
		BuggyCode notHowToEquals = (BuggyCode) obj;
		return this.getValue().equals(notHowToEquals.getValue());	
	}

	private void testEquals(){
		if("test".equalsIgnoreCase(this.str)){
			//Some stuff
		} 
		else if("test".equals(this.str)){
			//Never will run
		}
	}

	private boolean assignmentDuringIf(){
		boolean value = false;
		if(value = true){ //Don't do this!
			return true;
		}
		return false;
	}
	
	private void emptySynchronized(){
		synchronized(this){
			//No implementation
		}
	}

	private void sleepInSynchronized() throws InterruptedException{
		synchronized(this){
			Thread.sleep(1000); //Threads shouldn't sleep!
			//They will hog this resource
		}
	}
	
	public static void main(){
		BuggyCode bugsEverywhere = new BuggyCode();
		bugsEverywhere.testEquals();
		bugsEverywhere.assignmentDuringIf();
		bugsEverywhere.emptySynchronized();
		
		try{
			bugsEverywhere.sleepInSynchronized();
		} catch (Exception e){}
		
	}
}
