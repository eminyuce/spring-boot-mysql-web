package i2i.n5g.logs.entity;

public class NfType {
	private String name;
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public NfType() {
		// TODO Auto-generated constructor stub
	}

	public NfType(String name,boolean selected) {
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
