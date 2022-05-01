public class Director
{
    public void constructGamingPC(Builder builder)
    {
        builder.setPCType(PCType.GAMING_PC);
        builder.setCPU(new CPU("Ryzen 9 5900X", 5.7, 24));
        builder.setGPU(new GPU("RTX 3090 TI",500));
        builder.setMotherBoard(new MotherBoard("Gigabyte Z690 Aorus Pro",4));
        builder.setPowerBlock(new PowerBlock("Corsair RM750x", 1000));
        builder.setRAM(new RAM("DDR5 Trident Z5 RGB", 6000, 4));
        builder.setMemory(new SSD("WD Black", 10000));
    }

    public void constructUniversityPC(Builder builder)
    {
        builder.setPCType(PCType.UNIVERSITY_PC);
        builder.setCPU(new CPU("Intel Pentium", 1.5, 2));
        builder.setMotherBoard(new MotherBoard("Asus Intel G41",1));
        builder.setPowerBlock(new PowerBlock("Corsair", 200));
        builder.setRAM(new RAM("DDR2 Something", 300, 1));
        builder.setMemory(new SSD("WD Blue", 100));
    }
}
