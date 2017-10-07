class Paar implements Comparable<Paar>
{

    final private double coefficient;
    final private int macht;

    Paar(double coefficient, int macht) {
        this.coefficient = coefficient;
        this.macht = macht;
    }

    public double getCoefficient()
    {
        double publicCoefficient = this.coefficient;

        return publicCoefficient;
    }

    public int getMacht()
    {
        int publicMacht = this.macht;

        return publicMacht;
    }

    public Paar telop(Paar ander)
    {
        double nieuweCoefficient = this.coefficient + ander.coefficient;

        return new Paar(nieuweCoefficient, this.macht);
    }

    public Paar vermenigvuldig(Paar ander)
    {
        double nieuweCoefficient = this.coefficient * ander.coefficient;
        int    nieweMacht        = this.macht + ander.macht;
        return new Paar(nieuweCoefficient, nieweMacht);
    }

    public int compareTo(Paar ander)
    {
        return ander.macht - this.macht;
    }

    public boolean equals(Paar ander)
    {
        if (ander.coefficient == this.coefficient && ander.macht == this.macht )
        {
            return true;
        }

        return false;
    }

    public String toString()
    {
        if(this.macht == 0)
        {
            if(this.coefficient == 0)
            {
                return "0.0";
            }

            return String.valueOf(this.coefficient);
        }
        else if(this.macht == 1)
        {
            if(this.coefficient == 1)
            {
                return "x";
            }
            return coefficient +" x";
        }
        else if(this.coefficient == 0 )
        {
            return "0.0";
        }
        else if(this.coefficient == 1)
        {
            return "x^" +macht;
        }
        return coefficient +" x^" +macht;
    }
}
