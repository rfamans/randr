import org.apache.commons.cli.*;
import java.io.File;

class Opgave5
{
    static Options opties = initOpties();
    static Polynoom polynoom1, polynoom2;
    static String[] bestanden;

    public static void main(String[] args)
    {
        CommandLine cmd = parseOpties(args);
        maakPolynomen();

        System.out.println("Polynoom 1: " + polynoom1);
        System.out.println("Polynoom 2: " + polynoom2);

        verwerkOpties(cmd);
    }

    private static Options initOpties()
    {
        Options opties = new Options();
        opties.addOption("o", "optellen", false, "Tel beiden polynomen bij elkaar op");
        opties.addOption("a", "aftrekken", false, "Trek polynomen van elkaar af");
        opties.addOption("v", "vermenigvuldigen", false, "Vermenigvuldig polynomen met elkaar");
        opties.addOption("d", "differentieer", false, "Differientiëer de polynoom");
        opties.addOption("i", "integreer", false, "Integreer de polynoom");

        return opties;
    }

    private static CommandLine parseOpties(String[] args)
    {
        CommandLine cmd = null;

        try
        {
            cmd = new DefaultParser().parse(opties, args);
            bestanden = cmd.getArgs();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return cmd;
    }

    private static void maakPolynomen()
    {
        /*
         * Hier lezen we de twee polynomen in uit bestanden. Als de eerste niet
         * bestaat stoppen we maar de tweede is optioneel.
         */
        try {
            switch (bestanden.length)
            {
                case 2:
                    polynoom2 = new Polynoom(new File(bestanden[1]));
                case 1:
                    polynoom1 = new Polynoom(new File(bestanden[0]));
                    break;
                default:
                    throw new IllegalArgumentException("Het aantal bestanden moet een of twee zijn.");
            }
        } catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void verwerkOpties(CommandLine cmd)
    {
        /*
         * Dit handelt alle operaties af zoals optellen, aftrekken, etc.
         */
        if (cmd.hasOption("optellen"))
        {
            if(polynoom2 != null)
            {
                System.out.println("Som: " + polynoom1.telop(polynoom2));
            }
            else
            {
                System.out.println("Polynoom 2 nodig om op te tellen!");
            }
        }
        if (cmd.hasOption("aftrekken"))
        {
            if(polynoom2 != null)
            {
                System.out.println("Verschil: " + polynoom1.trekaf(polynoom2));
            }
            else
            {
                System.out.println("Polynoom 2 nodig om af te trekken!");
            }
        }
        if (cmd.hasOption("vermenigvuldigen"))
        {
            if(polynoom2 != null)
            {
                System.out.println("Product: " + polynoom1.vermenigvuldig(polynoom2));
            }
            else
            {
                System.out.println("Polynoom 2 nodig om te vermenigvuldigen!");
            }
        }
        if (cmd.hasOption("differentieer"))
        {
            if(polynoom2 == null)
            {
                System.out.println("Afgeleide: " + polynoom1.differentieer());
            }
            else
            {
                System.out.println("Alleen polynoom 1 is nodig om te differentieren!");
            }
        }
        if (cmd.hasOption("integreer"))
        {
            if(polynoom2 == null)
            {
                System.out.println("Primitieve: " + polynoom1.integreer());
            }
            else
            {
                System.out.println("Alleen polynoom 1 is nodig om te integreren!");
            }
        }

    }
}
