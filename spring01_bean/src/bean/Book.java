package bean;

public class Book {
	
	private String title;
	private String author;
	private int page;
	
	public Book() {
		// TODO Auto-generated constructor stub
		System.out.println("Book클래스의 인스턴스를 기본생성자로 생성");
	}
	
	public Book(String title, String author, int page) {
		super();
		this.title = title;
		this.author = author;
		this.page = page;
		System.out.println("Book클래스의 인스턴스를 매개변수가 있는 생성자로 생성");
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", page=" + page + "]";
	}
	

}
