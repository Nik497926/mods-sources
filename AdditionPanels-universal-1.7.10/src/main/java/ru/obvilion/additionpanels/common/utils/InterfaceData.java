package ru.obvilion.additionpanels.common.utils;

public class InterfaceData{
    private static final InterfaceData x9 = new InterfaceData(211, 97,35,53,90);
    private static final InterfaceData x18 = new InterfaceData(222, 90,32,50,104);
    private static final InterfaceData x27 = new InterfaceData(222, 72,16,34,104);
    private static final InterfaceData x36 = new InterfaceData(239, 71,16,34,120);
    public final int height;
    public final int yPattern;
    public final int yConfig;
    public final int yStorage;
    public final int n;


    public InterfaceData(int height, int yPattern, int yConfig, int yStorage, int n) {
        this.height = height;
        this.yPattern = yPattern;
        this.yConfig = yConfig;
        this.yStorage = yStorage;
        this.n = n;
    }

    public static InterfaceData getFromPatterSize(int size){
        switch (size){
            case 18:
                return x18;
            case 27:
                return x27;
            case 36:
                return x36;
        }
        return x9;
    }
}