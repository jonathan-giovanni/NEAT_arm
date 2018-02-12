package kinematic;
public class Ik {


    double F;
    double T;

    public Ik(double f, double t) {
        F = f;
        T = t;
    }

    public double[] evaluate(double posX,double posY,double posZ){
        //posicion en coordenadas cartesianas
        double X = posX;
        double Y = posY;
        double Z = posZ;

        //calculando distancia euclidiana
        double L    = Math.sqrt(Y*Y+X*X);
        double dia  = Math.sqrt(Z*Z+L*L);

        //calculando grados
        double alpha = Math.PI/2-(Math.atan2(L, Z)+Math.acos((T*T-F*F-dia*dia)/(-2*F*dia)));
        double beta = -Math.PI+Math.acos((dia*dia-T*T-F*F)/(-2*F*T));
        double gamma = Math.atan2(Y, X);
        return new double[]{alpha,beta,gamma};
    }
}
