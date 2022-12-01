import com.panayotis.gnuplot.JavaPlot;

import java.util.*;

public class OuterSpace
{
    private final LinkedList<Body> space;
    public OuterSpace()
    {
        this.space = new LinkedList<>();
    }

    public void addBody(Body body)
    {
        space.add(body);
    }

    public Body getBody(int index)
    {
        return space.get(index);
    }

    public int getSize()
    {
        return space.size();
    }
    public void update(double timeStep)
    {
        for(Body body : space)
        {
            body.calculateNextVelocity(space, timeStep);
        }
    }

    public void update(double timeStep, int n)
    {
        for(Body body : space)
        {
            body.calculateNextVelocity(space, timeStep, n);
        }
    }

    public void plotPositionData()
    {
        JavaPlot p = new JavaPlot();

        for(Body body : space)
        {
            p.addPlot(body.get2DPositionArray());
        }

        double range = 2e11;
        p.getAxis("x").setBoundaries(-range, range);
        p.getAxis("y").setBoundaries(-range, range);

        p.plot();
    }

    public void plotVelocityData()
    {
        JavaPlot p = new JavaPlot();

        for(Body body : space)
        {
            p.addPlot(body.get2DVelocityArray());
        }

        double range = 2e11;
        p.getAxis("x").setBoundaries(-range, range);
        p.getAxis("y").setBoundaries(-range, range);

        p.plot();
    }
}
