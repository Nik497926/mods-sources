package appeng.parts.p2p;

public interface IPartGT5Power {
    long injectEnergyUnits(long voltage, long amperage);

    boolean inputEnergy();

    boolean outputsEnergy();
}
