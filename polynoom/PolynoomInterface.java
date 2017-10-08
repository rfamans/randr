/*
 * XXX: PAS DIT BESTAND NIET AAN!
 */

interface PolynoomInterface {
    Polynoom telop(Polynoom ander);
    Polynoom trekaf(Polynoom ander);
    Polynoom vermenigvuldig(Polynoom ander);
    Polynoom differentieer();
    Polynoom integreer();
    boolean equals(Polynoom ander);
    String toString();
}
