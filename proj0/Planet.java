public class Planet {
	/** Its current x position. */
	public double xxPos;
	/** Its current y position. */
	public double yyPos;
	/** Its current velocity in the x direction. */
	public double xxVel;
	/**  Its current velocity in the y direction. */
	public double yyVel;
    /** Its mass. */
	public double mass;
    /** The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif). */
	public String imgFileName;
    /** Gravitational constant.*/
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
	}

	/** Calculates the distance between two Planets. */
	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;

		return Math.sqrt(dx * dx + dy * dy);
	}

    /**  Returns a double describing the force exerted on this planet by the given planet. */
	public double calcForceExertedBy(Planet p) {
		return G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}
 
    /** Returns the force exerted in the X directions. */
	public double calcForceExertedByX(Planet p) {
		double F = this.calcForceExertedBy(p);
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);

		return F * dx / r;
	}

    /** Returns the force exerted in the Y directions. */
	public double calcForceExertedByY(Planet p) {
		double F = this.calcForceExertedBy(p);
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);

		return F * dy / r;

	}

    /** Take in an array of Planets and calculate the net X force exerted by all planets in that array upon the current Planet. */
	public double calcNetForceExertedByX(Planet[] planets) {
		double Fnetx = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				Fnetx += this.calcForceExertedByX(p);
			}
		}
		return Fnetx;
	}

    /** Take in an array of Planets and calculate the net Y force exerted by all planets in that array upon the current Planet. */
	public double calcNetForceExertedByY(Planet[] planets) {
		double Fnety = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				Fnety += this.calcForceExertedByY(p);
			}
		}
		return Fnety;
	}

    /** Update the planet’s position and velocity instance variables. */
	public void update(double dt, double xxForce, double yyForce) {
        double xxAcc = xxForce / mass;
        double yyAcc = yyForce / mass;

        xxVel = xxVel + dt * xxAcc;
        yyVel = yyVel + dt * yyAcc;

        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
	}

    /** Draw the Planet’s image at the Planet’s position. */
	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
		StdDraw.show();
	}
}