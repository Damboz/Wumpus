package Wumpus;

import java.util.*;

class WumpusGame {

    int size;
    char[][] grid;

    int agentRow = 0;
    int agentCol = 0;

    int goldRow, goldCol;

    List<int[]> pits = new ArrayList<>();
    List<int[]> wumpus = new ArrayList<>();

    boolean gameOver = false;
    int score = 0;

    boolean arrowAvailable = true;
    boolean wumpusAlive = true;

    Random rand = new Random();

    public WumpusGame(int size, int numPits, int numWumpus) {

        this.size = size;
        grid = new char[size][size];

        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                grid[i][j]='.';

        placeElements(numPits,numWumpus);
    }

    void placeElements(int numPits,int numWumpus){

        List<int[]> positions = new ArrayList<>();

        for(int i=0;i<size;i++)
            for(int j=0;j<size;j++)
                if(!(i==0 && j==0))
                    positions.add(new int[]{i,j});

        Collections.shuffle(positions);

        int[] g = positions.remove(0);
        goldRow = g[0];
        goldCol = g[1];
        grid[goldRow][goldCol]='G';

        for(int i=0;i<numWumpus;i++){
            int[] w = positions.remove(0);
            wumpus.add(w);
            grid[w[0]][w[1]]='W';
        }

        for(int i=0;i<numPits;i++){
            int[] p = positions.remove(0);
            pits.add(p);
            grid[p[0]][p[1]]='P';
        }
    }

    boolean isValid(int r,int c){
        return r>=0 && r<size && c>=0 && c<size;
    }

    List<int[]> neighbors(int r,int c){

        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};

        List<int[]> list = new ArrayList<>();

        for(int[] d:dir){

            int nr=r+d[0];
            int nc=c+d[1];

            if(isValid(nr,nc))
                list.add(new int[]{nr,nc});
        }

        return list;
    }

    Map<String,Boolean> getPercepts(){

        Map<String,Boolean> p = new HashMap<>();

        p.put("stench",false);
        p.put("breeze",false);
        p.put("glitter",false);

        if(agentRow==goldRow && agentCol==goldCol)
            p.put("glitter",true);

        for(int[] n:neighbors(agentRow,agentCol)){

            for(int[] w:wumpus)
                if(Arrays.equals(n,w) && wumpusAlive)
                    p.put("stench",true);

            for(int[] pit:pits)
                if(Arrays.equals(n,pit))
                    p.put("breeze",true);
        }

        return p;
    }

    void shootArrow(String dir){

        if(!arrowAvailable){
            System.out.println("No arrows left!");
            return;
        }

        arrowAvailable=false;

        int r=agentRow;
        int c=agentCol;

        if(dir.equals("up")) r--;
        if(dir.equals("down")) r++;
        if(dir.equals("left")) c--;
        if(dir.equals("right")) c++;

        if(!isValid(r,c)){
            System.out.println("Arrow hit a wall!");
            return;
        }

        for(int[] w:wumpus){

            if(r==w[0] && c==w[1] && wumpusAlive){

                System.out.println("YOU KILLED THE WUMPUS!");
                wumpusAlive=false;
                grid[w[0]][w[1]]='.';
                score+=500;
                return;
            }
        }

        System.out.println("Arrow missed!");
    }

    void move(String dir){

        System.out.println("\n--- BEFORE MOVE ---");
        printState();

        int r=agentRow;
        int c=agentCol;

        System.out.println("Agent move: "+dir);

        if(dir.equals("up")) r--;
        if(dir.equals("down")) r++;
        if(dir.equals("left")) c--;
        if(dir.equals("right")) c++;

        if(!isValid(r,c)){
            System.out.println("BUMP! You hit a wall.");
            return;
        }

        agentRow=r;
        agentCol=c;

        score--;

        for(int[] w:wumpus)
            if(agentRow==w[0] && agentCol==w[1] && wumpusAlive){
                System.out.println("EATEN BY WUMPUS!");
                gameOver=true;
                score-=1000;
            }

        for(int[] p:pits)
            if(agentRow==p[0] && agentCol==p[1]){
                System.out.println("YOU FELL INTO A PIT!");
                gameOver=true;
                score-=1000;
            }

        if(agentRow==goldRow && agentCol==goldCol){
            System.out.println("YOU FOUND THE GOLD!");
            score+=1000;
            gameOver=true;
        }

        System.out.println("\n--- AFTER MOVE ---");
        printState();
    }

    void printState(){

        System.out.println("Agent Position: ("+agentRow+","+agentCol+")");
        System.out.println("Score: "+score);
        System.out.println("Game Over: "+gameOver);
        System.out.println("Arrow Available: "+arrowAvailable);
    }

    void displayGrid(boolean showHazards){

        System.out.println("\nWORLD GRID");

        for(int i=0;i<size;i++){

            for(int j=0;j<size;j++){

                if(i==agentRow && j==agentCol)
                    System.out.print("A ");

                else if(showHazards)
                    System.out.print(grid[i][j]+" ");

                else
                    System.out.print(". ");
            }

            System.out.println();
        }

        System.out.println();
    }
}


class WumpusAgent{

    WumpusGame world;
    Scanner scanner = new Scanner(System.in);

    public WumpusAgent(WumpusGame world){
        this.world=world;
    }

    String getMove(){

        System.out.println("Choose Move:");
        System.out.println("W = Up | S = Down | A = Left | D = Right");
        System.out.println("F = Shoot Arrow | Q = Quit");

        String key = scanner.nextLine().toLowerCase();

        switch(key){

            case "w": return "up";
            case "s": return "down";
            case "a": return "left";
            case "d": return "right";

            case "f":
                shootDirection();
                return null;

            case "q":
                world.gameOver=true;
                return null;

            default:
                System.out.println("Invalid key!");
                return getMove();
        }
    }

    void shootDirection(){

        System.out.println("Shoot Direction:");
        System.out.println("W = Up | S = Down | A = Left | D = Right");

        String key = scanner.nextLine().toLowerCase();

        switch(key){

            case "w":
                world.shootArrow("up");
                break;

            case "s":
                world.shootArrow("down");
                break;

            case "a":
                world.shootArrow("left");
                break;

            case "d":
                world.shootArrow("right");
                break;

            default:
                System.out.println("Invalid direction!");
        }
    }

    void run(){

        while(!world.gameOver){

            world.displayGrid(false);

            Map<String,Boolean> percepts = world.getPercepts();

            System.out.println("Percepts: "+percepts);

            System.out.println("Agent Position: ("+world.agentRow+","+world.agentCol+")");

            String move = getMove();

            if(move!=null)
                world.move(move);
        }

        System.out.println("\nGAME OVER");

        world.displayGrid(true);

        System.out.println("Final Score: "+world.score);
    }
}


class Main {

    public static void main(String[] args){

        WumpusGame world = new WumpusGame(4,2,1);

        WumpusAgent agent = new WumpusAgent(world);

        agent.run();
    }
}