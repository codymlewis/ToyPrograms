/**
 * StandardModel.java - ChemistryProject
 * Author: Cody Lewis
 * Last Modified: 18-07-2017
 * Description:
 * Creates QuantumParticle objects of each particle in the standard model
 */
public class StandardModel{
	// Constructor structure: family, type, generation, charge, mass
	QuantumParticle upQuark = new QuantumParticle("Quark","Up",1,2/3);
	QuantumParticle downQuark = new QuantumParticle("Quark","Down",1,-1/3);
	QuantumParticle charmQuark = new QuantumParticle("Quark","Charm",2,2/3);
	QuantumParticle strangeQuark = new QuantumParticle("Quark","Strange",2,-1/3);
	QuantumParticle topQuark = new QuantumParticle("Quark","Top",3,2/3);
	QuantumParticle botQuark = new QuantumParticle("Quark","Bottom",3,-1/3);
	QuantumParticle electron = new QuantumParticle("Lepton","Electron",1,-1);
	QuantumParticle eNeutrino = new QuantumParticle("Lepton","Electron Neutrino",1,0);
	QuantumParticle muon = new QuantumParticle("Lepton","Muon",2,-1);
	QuantumParticle mNeutrino = new QuantumParticle("Lepton","Muon Neutrino",2,0);
	QuantumParticle tau = new QuantumParticle("Lepton","Tau",3,-1);
	QuantumParticle tNeutrino = new QuantumParticle("Lepton","Tau Neutrino",3,0);
	QuantumParticle zBoson = new QuantumParticle("Boson","Z",0,0);
	QuantumParticle wBoson = new QuantumParticle("Boson","W",0,1);
	QuantumParticle photon = new QuantumParticle("Boson","Photon",0,0);
	QuantumParticle gluon = new QuantumParticle("Boson","Gluon",0,0);
	public QuantumParticle getQP(String choice){
		return choice;
	}
}
