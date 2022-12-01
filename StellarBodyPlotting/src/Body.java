import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Body
{
    private final LinkedList<Position> position;
    private final LinkedList<Velocity> velocity;
    private final double mass;
    private final String name;

    public Body(Position initialPosition, Velocity initialVelocity, double mass, String name)
    {
        this.name = name;
        this.mass = mass;
        this.position = new LinkedList<>();
        this.velocity = new LinkedList<>();
        position.add(initialPosition);
        velocity.add(initialVelocity);

    }

    public String getName()
    {
        return this.name;
    }

    public double getMass()
    {
        return mass;
    }

    public Position getLastPosition()
    {
        return position.getLast();
    }

    public Velocity getLastVelocity()
    {
        return velocity.getLast();
    }

    /**
     *
     * @param space LinkedList of Body
     * @param timeStep
     * adds next velocity component to the linked list "this is n=0"
     */
    public void calculateNextVelocity(LinkedList<Body> space, double timeStep)
    {
        double nextVX = 0;
        double nextVY = 0;
        double nextVZ = 0;
        double gravitationConstant = -6.67 * Math.pow(10, -11);

        for(Body op : space){
            if(this != op)
            {
                double denominator = Math.pow(Math.pow(op.getLastPosition().getX() -
                        this.getLastPosition().getX(), 2)+Math.pow(op.getLastPosition().getY() -
                        this.getLastPosition().getY(), 2)+Math.pow(op.getLastPosition().getZ() -
                        this.getLastPosition().getZ(), 2), 3/2.0);

                nextVX += (gravitationConstant * op.getMass() * (this.getLastPosition().getX() -
                        op.getLastPosition().getX()) * timeStep) / denominator;
                nextVY += (gravitationConstant * op.getMass() * (this.getLastPosition().getY() -
                        op.getLastPosition().getY()) * timeStep) / denominator;
                nextVZ += (gravitationConstant * op.getMass() * (this.getLastPosition().getZ() -
                        op.getLastPosition().getZ()) * timeStep) / denominator;
            }
        }
        nextVX += this.getLastVelocity().getVx();
        nextVY += this.getLastVelocity().getVy();
        nextVZ += this.getLastVelocity().getVz();

        velocity.add(new Velocity(nextVX, nextVY, nextVZ));
        calculateNextPosition(nextVX, nextVY, nextVZ, timeStep);
    }

    /**
     * this is the normal method to get next position
     * @param vx
     * @param vy
     * @param vz
     * @param timeStep
     */
    private void calculateNextPosition(double vx, double vy, double vz, double timeStep)
    {
        double nextX = vx * timeStep + getLastPosition().getX();
        double nextY = vy * timeStep + getLastPosition().getY();
        double nextZ = vz * timeStep + getLastPosition().getZ();
        position.add(new Position(nextX, nextY, nextZ));
//        System.out.println("X: "+nextX+", Y: "+nextY);

    }

    /**
     *
     * @param space LinkedList of Body
     * @param timeStep
     * adds next velocity component to the linked list with varying grav constants
     */
    public void calculateNextVelocity(LinkedList<Body> space, double timeStep, int n)
    {
        double nextVX = 0;
        double nextVY = 0;
        double nextVZ = 0;
        double gravitationConstant = -6.67 * Math.pow(10, -11);
        double AU = 1.5*Math.pow(10, 11);

        for(Body op : space){
            if(this != op)
            {
                double distance = Math.pow(Math.pow(op.getLastPosition().getX() -
                        this.getLastPosition().getX(), 2)+Math.pow(op.getLastPosition().getY() -
                        this.getLastPosition().getY(), 2)+Math.pow(op.getLastPosition().getZ() -
                        this.getLastPosition().getZ(), 2), 1/2.0);

                gravitationConstant *= Math.pow(AU/distance, n);

                double denominator = Math.pow(Math.pow(op.getLastPosition().getX() -
                        this.getLastPosition().getX(), 2)+Math.pow(op.getLastPosition().getY() -
                        this.getLastPosition().getY(), 2)+Math.pow(op.getLastPosition().getZ() -
                        this.getLastPosition().getZ(), 2), 3/2.0);

                nextVX += (gravitationConstant * op.getMass() * (this.getLastPosition().getX() -
                        op.getLastPosition().getX()) * timeStep) / denominator;
                nextVY += (gravitationConstant * op.getMass() * (this.getLastPosition().getY() -
                        op.getLastPosition().getY()) * timeStep) / denominator;
                nextVZ += (gravitationConstant * op.getMass() * (this.getLastPosition().getZ() -
                        op.getLastPosition().getZ()) * timeStep) / denominator;
            }
        }
        nextVX += this.getLastVelocity().getVx();
        nextVY += this.getLastVelocity().getVy();
        nextVZ += this.getLastVelocity().getVz();

        velocity.add(new Velocity(nextVX, nextVY, nextVZ));
        calculateNextPosition(nextVX, nextVY, nextVZ, timeStep);
    }

    public double[][] get2DPositionArray()
    {
        ArrayList<Double> tempList = new ArrayList<>();
        double[][] values = new double[position.size()][position.size()];

        for (Position temp : position) {
            tempList.add(temp.getX());
            tempList.add(temp.getY());
        }

        int index = 0;
        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values.length; j+=2){

                if(i == 5000 || j == 5000 || index == 5000)
                    break;
                values[i][j] = tempList.get(index);
                values[i][j+1] = tempList.get(index+1);
                index+=2;
//                System.out.println("X: "+temp.getX()+", Y: "+temp.getY());
            }
        }
        return values;

    }

    public double[][] get2DVelocityArray()
    {
        ArrayList<Double> tempList = new ArrayList<>();
        double[][] values = new double[velocity.size()][velocity.size()];

        for (Velocity temp : velocity) {
            tempList.add(temp.getVx());
            tempList.add(temp.getVy());
        }

        int index = 0;
        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values.length; j+=2){

                if(i == 5000 || j == 5000 || index == 5000)
                    break;
                values[i][j] = tempList.get(index);
                values[i][j+1] = tempList.get(index+1);
                index+=2;
//                System.out.println("X: "+temp.getX()+", Y: "+temp.getY());
            }
        }
        return values;

    }
    public void savaPositionData() throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.name+"Position.txt"));
        for(Position position : this.position)
        {
            bw.write(position.getX()+":"+position.getY()+"\n");
        }
        bw.close();
    }
    public void savaVelocityData() throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.name+"Velocity.txt"));
        for(Velocity velocity : this.velocity)
        {
            bw.write(velocity.getVx()+":"+velocity.getVy()+"\n");
        }
        bw.close();
    }
}
