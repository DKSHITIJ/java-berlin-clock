package com.ubs.opsit.interviews;

/**
 * Lamp can have two states on or off represented by isOn. In the on state It
 * can have lights in multiple colors. current light for the lamp is represented by
 * lampColor.
 * 
 */
public class Lamp {

	private LampColor lampColor;
	private int rowNo;
	private int colNo;

	public Lamp(int rowNo, int colNo) {
		this.rowNo = rowNo;
		this.colNo = colNo;
		this.lampColor = LampColor.OFF;
	}

	public Lamp(int rowNo, int colNo, LampColor lampColor) {
		this.rowNo = rowNo;
		this.colNo = colNo;
		this.lampColor = lampColor;
	}

	public boolean isOn() {
		if (this.lampColor != LampColor.OFF)
			return true;
		return false;
	}

	public LampColor getLampColor() {
		return lampColor;
	}

	public void setLampColor(LampColor lampColor) {
		this.lampColor = lampColor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colNo;
		result = prime * result + ((lampColor == null) ? 0 : lampColor.hashCode());
		result = prime * result + rowNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lamp other = (Lamp) obj;
		if (colNo != other.colNo)
			return false;
		if (lampColor != other.lampColor)
			return false;
		if (rowNo != other.rowNo)
			return false;
		return true;
	}

}
