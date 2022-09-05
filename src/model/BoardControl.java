package model;
import java.util.ArrayList;

public class BoardControl {

    private Score root;
    private Pipe head;
    private int []size=new int[2];
    private String name;

    public BoardControl(){

        size[0]=8;
        size[1]=8;

        root=null;
        head=null;
    }


    //Pipes****************************************************************************************************************************


    public void createBoard (String name) {

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


        selectType(searchPipe(starPosition, head), 5);
        selectType(searchPipe(finishPosition, head), 6);
	}

    public void selectType(Pipe current, int typeNum) {

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
        
  

        return verification;
	}

    public String BoardtoString (){

        String out="";
        Pipe pointer= head;

        for(int i=0; i<8; i++){
            
            out+="\n";

            for(int j=0; j<8; j++){

                out+= "["+pointer.toString()+"]";
                pointer = pointer.getNext();
            }
        }
        return out;
    }

    //Score****************************************************************************************************************************

    public void createScore(int val, String name) {

        Score newNode= new Score (val, name);
        addScore(newNode,root);
	}

    public Score addScore(Score newNode,Score pointer) {

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


    public String scoreToString(ArrayList<Score> array){

        String out="Score      Nombre";

        for (int i=array.size();i>0; i--){
            out+="\n"+array.get(i-1).toString();
        }

        return out;
    }


    public Score getRoot(){
        return root;
    }

    public Pipe getHead(){
        return head;
    }
}
