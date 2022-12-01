import java.io.IOException;

public class Main {

    public static void main(String[] args)
    {
        double AU = 1.5*Math.pow(10, 11);

        double EARTH_MASS = 5.97 * Math.pow(10, 24);
        Position earthInitialPosition = new Position(AU,0, 0);
        Velocity earthInitialVelocity = new Velocity(0, 30000, 0);
        Body Earth = new Body(earthInitialPosition, earthInitialVelocity, EARTH_MASS, "Earth");

        double SUN_MASS = 2 * Math.pow(10, 30);
        Position sunInitialPosition = new Position(0, 0, 0);
        Velocity sunInitialVelocity = new Velocity(0, 0, 0);
        Body Sun = new Body(sunInitialPosition, sunInitialVelocity, SUN_MASS, "Sun");

        double VENUS_MASS = 4.87 * Math.pow(10, 24);
        Position venusInitialPosition = new Position(1.1*Math.pow(10, 11), 0, 0);
        Velocity venusInitialVelocity = new Velocity(0, -35000, 0);
        Body Venus = new Body(venusInitialPosition, venusInitialVelocity, VENUS_MASS, "Venus");

        double MOON_MASS = 7.35 * Math.pow(10, 22);
        Position moonInitialPosition = new Position(AU, 3.8*Math.pow(10, 5)*1000, 2*Math.pow(10, 4)*1000);
        Velocity moonInitialVelocity = new Velocity(-1000, 30000, 0);
        Body Moon = new Body(moonInitialPosition, moonInitialVelocity, MOON_MASS, "Moon");

        double MARS_MASS = 6.39*Math.pow(10, 23);
        Position marsInitialPosition = new Position(1.5*AU, 0, 0);
        Velocity marsInitialVelocity = new Velocity(0, 24077, 0);
        Body Mars = new Body(marsInitialPosition, marsInitialVelocity, MARS_MASS, "Mars");

        double PHOBOS_MASS = 10.6*Math.pow(10, 15);
        Position phobosInitialPosition = new Position(1.5*AU, 9.4*Math.pow(10, 2), 0*Math.pow(10, 6));
        Velocity phobosInitialVelocity = new Velocity(-2138, 24077, 0);
        Body Phobos = new Body(phobosInitialPosition, phobosInitialVelocity, PHOBOS_MASS, "Phobos");

        double DEIMOS_MASS = 1.476*Math.pow(10, 15);
        Position deimosInitialPosition = new Position(1.5*AU, 2.346*Math.pow(10, 7), 1.2*Math.pow(10, 6));
        Velocity deimosInitialVelocity = new Velocity(-1351, 24077, 0);
        Body Deimos = new Body(deimosInitialPosition, deimosInitialVelocity, DEIMOS_MASS, "Deimos");


        OuterSpace os = new OuterSpace();
        os.addBody(Earth);
        os.addBody(Sun);
        os.addBody(Moon);
        os.addBody(Venus);
        os.addBody(Mars);
        os.addBody(Phobos);
        os.addBody(Deimos);

        double timeStep = 10*20000;
        int n = 0;
        for(int i = 1; i < 2*1650; i++)
        {
            os.update(timeStep, n);
        }

//        os.plotPositionData();
//        os.plotVelocityData();

        try
        {
            for(int i = 0; i < os.getSize(); i++)
            {
                os.getBody(i).savaPositionData();
                os.getBody(i).savaVelocityData();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



//        double[][] values = new double[10][10];
//        int x = 0;
//        int y= 0;
//        for(int i = 0; i < 10; i++)
//        {
//            for(int j = 0; j < 10; j+=2)
//            {
//                values[i][j] = x;
//                values[i][j+1] = y;
//                x++;
//                y++;
//            }
//        }


    }
}



