package model;
import java.util.ArrayList;

public class BoardControl {

    private Score root;
    private Pipe head;
    private int []size=new int[2];
    private String name;
    private Pipe f;
    private Pipe d;
    private int counterOfPipes;

    public BoardControl(){

        size[0]=8;
        size[1]=8;

        root=null;
        head=null;
    }


    //Pipes****************************************************************************************************************************


    public void createBoard (String name) {
        counterOfPipes = -2;
        name=this.name;
        head=null;

        for(int i=0; i<=(size[0]*size[1]); i++){

            Pipe newNode= new Pipe (i+1);
            addPipe(newNode,head);
        }

        addStartFinish();
	}

    public Pipe addPipe(Pipe newNode,Pipe pointer) {

        if (pointer==null) {
            head = newNode;
            return pointer;
        }
        else if (pointer.getNext()==null) {
            pointer.setNext(newNode);
            newNode.setPrevious(pointer);
            return pointer;
        }
        else{
            addPipe(newNode, pointer.getNext());
            return newNode;
        }
	}

    public Pipe searchPipe(int [] positions, Pipe pointer){
        
        int columnHelp=8;
        for (int row=0; row< positions[1] ; row++){


            if (row== positions[1]-1) columnHelp=positions[0]-1;
           

            for(int column=0; column < columnHelp; column++){
                pointer=pointer.getNext();
            }
        }
       
        return pointer;

    }

    
    public void addStartFinish() {


        int finish, finish2;
        int star= (int)(Math.random() * ((8 - 1) + 1)) + 1;
        int star2= (int)(Math.random() * ((8 - 1) + 1)) + 1;
        finish2=(int)(Math.random() * ((8 - 1) + 1)) + 1;
        

        do{
        finish=(int)(Math.random() * ((8 - 1) + 1)) + 1;
        

        }while (star==finish);

        int [] starPosition = {star,star2};
        int [] finishPosition = {finish, finish2};

        f=searchPipe(starPosition, head);
        d=searchPipe(finishPosition, head);

        selectType( f,5);
        selectType(d, 6);
	}
    
    // The method has in the parameter a pipe and a numeber of the type of pipe
    public void selectType(Pipe current, int typeNum) {
        counterOfPipes++;
        PipeType type=PipeType.EMPTY;

        switch(typeNum){

            case 1:

                type=PipeType.HORIZONTAL;
                break;
            
            case 2:

                type=PipeType.CIRCULAR;
                break;
            case 3:

                type=PipeType.EMPTY;
                break;
            case 4:

                type=PipeType.VERTICAL;
                break;
            case 5:

                type=PipeType.START;
                break;
            case 6:

                type=PipeType.FINAL;
                break;    
        }

        changePipe(current, type);

	}

    public boolean changePipe(Pipe current, PipeType type){

        boolean verification=false;

        if (current.getType()!= PipeType.START && current.getType()!=PipeType.FINAL){

            current.setType(type);
            verification = true;
        }

        if(verification==false) System.out.println("\n***La posicion seleccionada no se puede cambiar***\n");
        
        

        return verification;
	}

    public void BoardtoString (){

        
        Pipe pointer= head;

        for(int i=0; i<8; i++){
            
            System.out.println("\n");

            for(int j=0; j<8; j++){

                pointer.printerBoard();;
                pointer = pointer.getNext();
            }
        }

    }

    public boolean preSimulate(){


        if (simulate(f,0)==true){
            
            return true;
        }
        else {
            
            return false;
        }

    }

    public Pipe upDown(Pipe pointer, int upDown) {
        
        for (int i=0; i<8; i++){

            if (upDown==1){
                pointer=pointer.getPrevious();
            }
            else{
                pointer=pointer.getNext();
            }
        }
        return pointer;
	}

    //direction= 1 arriba, 2 derecha, 3 abajo, 4 izquierda

    public boolean simulate(Pipe pointer, int direction) {


        if (pointer!=null){
            
            if (pointer.getType()==PipeType.FINAL){
                
                return true;
            }

            //For start
            else if (pointer.getType()==PipeType.START){
                

                if (pointer.getNext().getType()==PipeType.HORIZONTAL) {
                    return simulate(pointer.getNext(),2);
                }

                if (pointer.getPrevious().getType()==PipeType.HORIZONTAL) {
                    return simulate(pointer.getPrevious(),4);
                }

                if (upDown(pointer,1).getType()==PipeType.VERTICAL) {
                    return simulate(upDown(pointer, 1),1);
                }

                if (upDown(pointer,2).getType()==PipeType.VERTICAL) {
                    return simulate(upDown(pointer, 2),3);
                }
            }

            //For horizontal
            else if (pointer.getType()==PipeType.HORIZONTAL){
                

                if (direction==2){

                    if (pointer.getNext().getType()==PipeType.HORIZONTAL || pointer.getNext().getType()==PipeType.CIRCULAR || pointer.getNext().getType()==PipeType.FINAL) {
                        return simulate(pointer.getNext(), 2);
                    }
                }
                if (direction==4){

                    if (pointer.getPrevious().getType()==PipeType.HORIZONTAL || pointer.getPrevious().getType()==PipeType.CIRCULAR ||pointer.getPrevious().getType()==PipeType.FINAL) {
                        return simulate(pointer.getPrevious(), 4);
                    }
                }
            }

            //For vertical
            else if (pointer.getType()==PipeType.VERTICAL){

                

                if (direction==1){

                    if (upDown(pointer,1).getType()==PipeType.VERTICAL|| upDown(pointer,1).getType()==PipeType.CIRCULAR || upDown(pointer,1).getType()==PipeType.FINAL) {
                        return simulate(upDown(pointer, 1), 1);
                    }
                }

                if (direction==3){

                    if (upDown(pointer,2).getType()==PipeType.VERTICAL|| upDown(pointer,2).getType()==PipeType.CIRCULAR ||upDown(pointer,2).getType()==PipeType.FINAL) {
                        return simulate(upDown(pointer, 2),3);
                    }
                }
            }

            //For circular
            else if (pointer.getType()==PipeType.CIRCULAR){

                

                if (direction==1||direction==3){

                    if (pointer.getNext().getType()==PipeType.HORIZONTAL) {
                        return simulate(pointer.getNext(), 2);
                    }

                    if (pointer.getPrevious().getType()==PipeType.HORIZONTAL ) {
                        return simulate(pointer.getPrevious(), 4);
                    }
                }

                if (direction==2||direction==4){

                    if (upDown(pointer,1).getType()==PipeType.VERTICAL) {
                        return simulate(upDown(pointer, 1), 1);
                    }

                    if (upDown(pointer,2).getType()==PipeType.VERTICAL) {
                        return simulate(upDown(pointer, 2),3);
                    }
                }
            }
            else{
                return false;
            }
        }
        return false;
	}

    //Score****************************************************************************************************************************

    public void createScore(double val, String name) {

        Score newNode= new Score (val, name);
        addScore(newNode,root);
	}

    private Score addScore(Score newNode,Score pointer) {

        if (pointer==null) {
            root = newNode;
            return pointer;
        }
        else if (newNode.getValue()>pointer.getValue()) {

            if (pointer.getRight()==null){
                pointer.setRight(newNode);

                return pointer;
            }
            else{
                return addScore(newNode, pointer.getRight());
            }
        }
        else if (newNode.getValue()<pointer.getValue()){

            if (pointer.getLeft()==null){
                pointer.setLeft(newNode);

                return pointer;
            }
            else{
                return addScore(newNode, pointer.getLeft());
            }
        }
        else{
            return null;
        }
	}

    public double calculateScore(double totalTime){
        


        double score = counterOfPipes * 100 -(60 - totalTime) * 10;
       

        return score;
    }

    public Score searchByScore(int score,Score pointer) {

        if (pointer==null){
            return null;
        }
        else if (pointer.getValue()==score) {
            return pointer;
        }
        else if (pointer.getValue()<score ){
            return searchByScore(score, pointer.getRight());

        }
        else{
            return searchByScore(score, pointer.getLeft());
        }
	}

    public ArrayList<Score> createArray (){
        ArrayList<Score> array = new ArrayList<>();
        return listScores(root, array);
    }

    public ArrayList<Score> listScores(Score pointer,ArrayList<Score> array) {

        if (pointer.getLeft()!=null){
            listScores(pointer.getLeft(), array);
        }
        
        array.add(pointer);

        if(pointer.getRight()!=null){
            listScores(pointer.getRight(),array); 
      }
      return array;
	}


    public void scoreToString(ArrayList<Score> array){

        System.out.printf("%21s %11s","Nombre", "Score");
        System.out.println("");

        for (int i=array.size();i>0; i--){
        array.get(i-1).tableScore();
        System.out.println("");
        }

        
    }


    public Score getRoot(){
        return root;
    }

    public Pipe getHead(){
        return head;
    }
}
