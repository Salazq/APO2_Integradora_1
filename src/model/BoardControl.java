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
}
