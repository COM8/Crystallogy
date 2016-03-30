package de.comeight.crystallogy.network;

public class NetworkPacketParticleToClient extends NetworkPacketParticleToServer {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketParticleToClient(){
		super();
	}
	
	public NetworkPacketParticleToClient(NetworkParticle nP){
		super(nP);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketParticleToClient[NetworkParticle=" + nP.toString() + "]";
	}
	
}
