package model;

public class Pipe {

    private PipeType type;
    private int id;
    private Pipe next;
    private Pipe previous;

    public Pipe (int id){
        type=PipeType.EMPTY;
        next=null;
        this.id=id;
    }

    public String toString(){

        String out ="";

        switch (type){

            case EMPTY:

                out="X";
                break;

            case CIRCULAR:

                out="o";
                break;

            case FINAL:

                out="D";
                break;
            
            case START:

                out="F";

                break;
            
            
            case HORIZONTAL:

                out="=";

                break;

            case VERTICAL:

                out="||";

                break;
        }
        
        return out;
    }

    public Pipe getNext(){
        
        return next;
    }

    public void setNext(Pipe next){
        
        this.next=next;
    }

    public Pipe getPrevious(){
        
        return previous;
    }

    public void setPrevious(Pipe previous){
        
        this.previous=previous;
    }

    public int getId(){
        return id;
    }

}
