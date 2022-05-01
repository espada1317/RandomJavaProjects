public class Main
{
    public static void main(String[] args)
    {
        Director director = new Director();
        PCBuilder pcBuilder = new PCBuilder();
        director.constructGamingPC(pcBuilder);
        PC pc = pcBuilder.build();
        System.out.println("PC build:\n" + pc.print());

        PCManualBuilder manualBuilder = new PCManualBuilder();
        director.constructUniversityPC(manualBuilder);
        Manual pcManual = manualBuilder.build();
        System.out.println("\nPC build:\n" + pcManual.print());
    }
}