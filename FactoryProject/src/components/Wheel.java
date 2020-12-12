/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author Cerpo
 */
public class Wheel extends Component {
    final static String category = "Wheel";
    
    private int rimSize;
    private int tireSize;
    private boolean isSummerTire;
    private int weight;

	public Wheel() {}

	public Wheel(Wheel other) {
		this.rimSize = other.rimSize;
		this.tireSize = other.tireSize;
		this.isSummerTire = other.isSummerTire;
		this.weight = other.weight;
	}

	public int getRimSize() {
		return rimSize;
	}

	public int getTireSize() {
		return tireSize;
	}

	public boolean isSummerTire() {
		return isSummerTire;
	}

	public int getWeight() {
		return weight;
	}

	@Override
    public String getCategory() {
        return category;
    }

}
