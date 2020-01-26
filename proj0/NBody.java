/**
 * The goal of this class is to simulate a universe specified in one of the data files. 
 */
public class NBody {
	/** Return a double corresponding to the radius of the universe in that file. */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int n = in.readInt(); // the number of planets
		double r = in.readDouble(); // the radius of the universe, used to determine the scaling of the drawing window.
		return r;
	}

    /** Return an array of Planets corresponding to the planets in the file. */
    public static Planet[] readPlanets(String fileName) {
    	In in = new In(fileName);
    	int n = in.readInt(); // the number of planets
		double r = in.readDouble(); // the radius of the universe, used to determine the scaling of the drawing window.
		
		Planet[] planets = new Planet[n];

		for (int i = 0; i < n; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args) {
		// Collecting All Needed Input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = NBody.readPlanets(filename);
		double radius = NBody.readRadius(filename);
       
        // Drawing the Background
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();
        StdDraw.pause(2000);

        // Drawing All of the Planets
        for (Planet p : planets) {
        	p.draw();
        }

        // Creating an Animation
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
        	double[] xForces;
        	double[] yForces;

        	for (int i = 0; i < planets.length; i++) {
        		double netXForce = planets[i].calcNetForceExertedByX(planets);
        		xForces[i] = netXForce;
        	}

        	for (int i = 0; i < planets.length; i++) {
        		double netYForce = planets[i].calcNetForceExertedByY(planets);
        		yForces[i] = netYForce;
        	}

        	for (Planet p : planets) {
        		p.update(time, );
        	}
        }
	}
}