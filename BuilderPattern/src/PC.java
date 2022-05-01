public class PC
{
    private final PCType pcType;
    private final CPU cpu;
    private final GPU gpu;
    private final MotherBoard motherBoard;
    private final RAM ram;
    private final PowerBlock powerBlock;
    private final SSD ssd;

    public PC(PCType pcType, CPU cpu, GPU gpu, MotherBoard motherBoard, RAM ram, PowerBlock powerBlock, SSD ssd)
    {
        this.pcType = pcType;
        this.cpu = cpu;
        this.gpu = gpu;
        this.motherBoard = motherBoard;
        this.ram = ram;
        this.powerBlock = powerBlock;
        this.ssd = ssd;
    }

    public String print()
    {
        String info = "";
        info += "Type of PC: " + pcType + "\n";
        info += "Type of CPU: " + cpu.toString() + "\n";
        if(gpu != null)
            info += "Type of GPU: " + gpu.toString() + "\n";
        else
            info += "Type of GPU: NaN\n";
        info += "Type of MotherBoard: " + motherBoard.toString() + "\n";
        info += "Type of RAM: " + ram.toString() + "\n";
        info += "Type of PowerBlock: " + powerBlock.toString() + "\n";
        info += "Type of SSD: " + ssd.toString() + "\n";

        return info;
    }
}