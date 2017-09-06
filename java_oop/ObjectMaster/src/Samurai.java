
public class Samurai extends Human {
	
	private static int count = 0;

	public Samurai(String name) {
		super(name);
		this.health = 200;
		count += 1;
	}
	
	public void deathBlow(Human target) {
		System.out.println(this.name + " dealt a death blow to " + target.name);
	}

	public void meditate() {
		this.health += (this.health / 2);
	}
	
	public void howMany() {
		System.out.println("There are " + count + " samurai");
	}
}
