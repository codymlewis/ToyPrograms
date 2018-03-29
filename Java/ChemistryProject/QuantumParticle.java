/**
 * QuantumParticle.java - ChemistryProject
 * Author: Cody Lewis
 * Last Modified: 23-07-2017
 * Description:
 * Class that hold data format of a quantum particle
 */
public class QuantumParticle{
	private String type;		//Boson, Lepton, Quark
	private String partType;	//Up, Electron, ...
	private int gen;			//The generation of the particle
	private double charge,mass;
	public QuantumParticle(){
		type = "No type set";
		partType = "No particle type set";
		gen = 0;
		charge = 0;
		mass = 0;
	}
	public QuantumParticle(String newType, String newPartType, int newGen,double newCharge,double newMass){
		type = newType;
		partType = newPartType;
		gen = newGen;
		charge = newCharge;
		mass = newMass;
	}
	public String getType(){
		return type;
	}
	public String getPartType(){
		return partType;
	}
	public int getGen(){
		return gen;
	}
	public double getCharge(){
		return charge;
	}
	public double getMass(){
		return mass;
	}
}
