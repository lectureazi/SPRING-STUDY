package proxy;

public class DeveloperProxy implements Developer{
	
	private Developer developer;

	public DeveloperProxy(Developer developer) {
		super();
		this.developer = developer;
	}

	@Override
	public void develop() {
		
		System.out.println("출근 카드를 찍는다.");
		
		try {
			developer.develop();
		} catch (Exception e) {
			System.out.println("쉬는 날이었다.");
		}finally {
			System.out.println("집에 간다.");
		}
	}

}
