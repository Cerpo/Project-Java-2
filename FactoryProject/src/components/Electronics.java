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
public class Electronics extends Component {
    final static String category = "Electronics";
    
    private boolean hasGps;
    private boolean hasClimate;
    private String lampType;

    public Electronics() {}

    public Electronics(Electronics other) {
        this.hasGps = other.hasGps;
        this.hasClimate = other.hasClimate;
        this.lampType = other.lampType;
    }

    public boolean hasGps() {
		return hasGps;
	}

	public boolean hasClimate() {
		return hasClimate;
	}

	public String getLampType() {
		return lampType;
	}

    @Override
    public String getCategory() {
        return category;
    }

}
