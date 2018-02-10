package org.usfirst.frc.team4485.robot.Subsystems.PIDController;


public class SPID {
	public double dState;
	public double iState;
	public double iMax, iMin;
	
	public double iGain,
		pGain,
		dGain;
	
	/*public SPID(double ds, double is, double iMx, double iMn, double iG, double pG, double dG) {
		dState = ds;
		iState = is;
		iMax = iMx;
		iMin = iMn;
		iGain = iG;
		pGain = pG;
		dGain = dG;
	}*/
}