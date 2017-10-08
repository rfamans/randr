import java.util.*;
import java.lang.*;
import java.io.IOException;
import java.io.File;

class Polynoom implements PolynoomInterface
{
    public ArrayList<Paar> termen;  // private maken en getter maken

    Polynoom()
    {
        termen = new ArrayList<Paar>();
    }

    Polynoom(File bestand)
    {
        this();
        this.termen = leesPolynoom(bestand);
        Collections.sort(this.termen);
        this.termen = vereenvoudig(this.termen);
    }

    Polynoom(double... lijst)
    {
        this();
        arrayNaarList(lijst);
        Collections.sort(termen);
        termen = vereenvoudig(termen);
    }

    Polynoom(ArrayList<Paar> termen)
    {
        Collections.sort(termen);
        this.termen = vereenvoudig(termen);
    }

    public ArrayList<Paar> arrayNaarList(double[] lijst)
    {
        if(!(lijst.length % 2 == 1) )
        {
            for(int i = 0 ; i < lijst.length; i += 2)
            {
                termen.add(new Paar(lijst[i], (int)lijst[i + 1]));
            }
        }
        else
        {
            System.out.println("Error, oneven aantal ingevoerd.");
            // nog iets toevoegen dat de aanmaak van het polynoom stopt bij oneven invoer
        }

        return termen;
    }

    public Polynoom telop(Polynoom ander)
    {
        ArrayList<Paar> termenOpgeteld;
        termenOpgeteld = new ArrayList<Paar>();

        this.termen.addAll(ander.termen);
        termenOpgeteld.addAll(this.termen);

        return new Polynoom(termenOpgeteld);
    }

    public Polynoom trekaf(Polynoom ander)
    {
        ArrayList<Paar> termenAfgetrokken;
        termenAfgetrokken = new ArrayList<Paar>();

        termenAfgetrokken.addAll(this.termen);

        for(Paar paar : ander.termen)
        {
            termenAfgetrokken.add(paar.vermenigvuldig(new Paar(-1.0, 0)));
        }

        return new Polynoom(termenAfgetrokken);
    }

    public Polynoom vermenigvuldig(Polynoom ander)
    {
        ArrayList<Paar> termenVermenigvuldigd;
        termenVermenigvuldigd = new ArrayList<Paar>();

        for(Paar paar : this.termen)
        {
            for(Paar anderPaar : ander.termen)
            {
                termenVermenigvuldigd.add(paar.vermenigvuldig(anderPaar));
            }
        }

        return new Polynoom(termenVermenigvuldigd);
    }

    public Polynoom differentieer()
    {
        ArrayList<Paar> termenAfgeleid;
        termenAfgeleid = new ArrayList<Paar>();

        for(Paar paar : termen)
        {
            termenAfgeleid.add(new Paar(paar.getCoefficient() * paar.getMacht(), paar.getMacht() - 1));
        }

        return new Polynoom(termenAfgeleid);
    }

    public Polynoom integreer()
    {
        ArrayList<Paar> termenIntegraal;
        termenIntegraal = new ArrayList<Paar>();

        for(Paar paar : termen)
        {
            termenIntegraal.add(new Paar(paar.getCoefficient()/(paar.getMacht() +1), paar.getMacht() + 1));
        }

        return new Polynoom(termenIntegraal);
    }

    public boolean equals(Polynoom ander)
    {
        if(this.termen.toString().equals(ander.termen.toString()))
        {
            return true;
        }

        return false;
    }

    public boolean equals(Object o)
    {
        if(o instanceof Polynoom)
        {
            return this.equals((Polynoom) o);
        }

        return false;
    }

    public String toString()
    {
        String polynoomString = "";
        polynoomString = termen.get(0).toString();

        for(int i = 1; i < termen.size(); i++)
        {
            polynoomString = polynoomString +" + " +termen.get(i).toString();
        }

        return polynoomString;
    }

    public ArrayList<Paar> vereenvoudig(ArrayList<Paar> termen)
    {
        ArrayList<Paar> termenVereenvoudigd;
        termenVereenvoudigd = new ArrayList<Paar>();

        int index = 0;
        int macht = termen.get(index).getMacht();

        termenVereenvoudigd.add(termen.get(index));

        for(int i = 1; i < termen.size(); i++)
            {
                if(macht == termen.get(i).getMacht())
                {
                    termenVereenvoudigd.set(index, termenVereenvoudigd.get(index).telop(termen.get(i)));
                }
                else
                {
                    termenVereenvoudigd.add(termen.get(i));
                    index++;
                    macht = termen.get(i).getMacht();
                }
            }

        return termenVereenvoudigd;
    }

    private ArrayList<Paar> leesPolynoom(File bestand)
    {
        try
        {
            ArrayList<Paar> termenFile;
            termenFile = new ArrayList<Paar>();
            Scanner input = new Scanner(bestand);

            while (input.hasNextLine())
            {
                int i = 0;
                String[] regel = input.nextLine().split(",");

                regel[i] = regel[i].trim();
                regel[i+1] = regel[i+1].trim();
                termenFile.add(new Paar(Double.parseDouble(regel[i]), Integer.parseInt(regel[i + 1])));
            }
            input.close();
            return termenFile;
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("Er is iets fout gegaan met inlezen.");
        }
    }
}
