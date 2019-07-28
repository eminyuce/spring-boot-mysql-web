package guru.springframework.entity;

public class N5gLogLevel {
	private String name;
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public N5gLogLevel() {
		// TODO Auto-generated constructor stub
	}

	public N5gLogLevel(String name,boolean selected) {
		this.name = name;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
