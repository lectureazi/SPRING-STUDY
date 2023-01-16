package bean.cheatsheet;

public class Score {
	
	private String name;
	private int kor;
	private int math;
	private int eng;
	
	public Score() {
		// TODO Auto-generated constructor stub
	}

	public Score(String name, int kor, int math, int eng) {
		super();
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	@Override
	public String toString() {
		return "Score [name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng + "]";
	}

	
	
}
