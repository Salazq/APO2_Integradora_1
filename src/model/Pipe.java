package model;

public class Pipe {

    private PipeType type;
    private int id;
    private Pipe next;
    private Pipe previous;

    public Pipe (int value){
        type=null;
        next=null;
    }

    public String toString(){
        
        return ""+ type;
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

}
