public class PCBuilder implements Builder
{
    private PCType pcType;
    private CPU cpu;
    private GPU gpu;
    private MotherBoard motherBoard;
    private RAM ram;
    private PowerBlock powerBlock;
    private SSD ssd;

    public void setPCType(PCType pcType) {
        this.pcType = pcType;
    }

    public void setCPU(CPU cpu) {
        this.cpu = cpu;
    }

    public void setGPU(GPU gpu) {
        this.gpu = gpu;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public void setRAM(RAM ram) {
        this.ram = ram;
    }

    public void setPowerBlock(PowerBlock powerBlock) {
        this.powerBlock = powerBlock;
    }

    public void setMemory(SSD ssd) {
        this.ssd = ssd;
    }

    public PC build()
    {
        return new PC(pcType, cpu, gpu, motherBoard, ram, powerBlock, ssd);
    }
}