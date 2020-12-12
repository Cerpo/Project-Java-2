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
public class Engine extends Component {
    final static String category = "Engine";
    
    private int cylinderCapacity;
    private boolean isGasoline;
    private int horsePower;
    private int numberOfCylinders;

	public Engine() {}

	public Engine(Engine other) {
		this.cylinderCapacity = other.cylinderCapacity;
		this.isGasoline = other.isGasoline;
		this.horsePower = other.horsePower;
		this.numberOfCylinders = other.numberOfCylinders;
	}

	public int getCylinderCapacity() {
		return cylinderCapacity;
	}

	public boolean isGasoline() {
		return isGasoline;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}

	@Override
    public String getCategory() {
        return category;
    }

}
