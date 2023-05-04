package databaseFramework;

/**
 * Poem object class.
 * @author 
 *
 */
public class Poem {
	private String author;
	private String title;
	private String text;

	public Poem(String author, String title, String textString) {
		super();
		this.author = author;
		this.title = title;
		this.text = textString;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getTextString() {
		return text;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTextString(String textString) {
		this.text = textString;
	}

	@Override
	public String toString() {
		return author + "," + title + "," + text;
	}
}