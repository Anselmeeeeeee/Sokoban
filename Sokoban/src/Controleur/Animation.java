package Controleur;

class Animation {
    public int caseDepartX, caseDepartY;
    public int caseArriveeX, caseArriveeY;
    double progression;
    double vitesse = 0.1;

    public Animation(int xd, int yd, int xa, int ya){
        caseDepartX = xd;
        caseDepartY = yd;
        caseArriveeX = xa;
        caseArriveeY = ya;
    }

    public void mettreAJour() {
        progression += vitesse;
        if (progression > 1.0) progression = 1.0;
    }

    public boolean estTerminee() {
        return progression >= 1.0;
    }


}
